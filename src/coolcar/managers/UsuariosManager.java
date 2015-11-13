package coolcar.managers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.ClientePF;
import coolcar.modelos.Usuario;

public class UsuariosManager {


  public ArrayList<Usuario> consulta(Usuario usuario) {
	  return new ArrayList<Usuario>();
  }

  public boolean insere(Usuario usuario) {
    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();
      String sql = "INSERT INTO Usuario (nome, email, senha, endereco_logradouro, "
          + "endereco_numero, endereco_complemento, endereco_cep, endereco_cidade, "
          + "endereco_estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, usuario.getNome());
      stmt.setString(2, usuario.getEmail());
      stmt.setString(3, usuario.getSenha()); // TODO: hash
      stmt.setString(4, usuario.getEndereco().getLogradouro());
      stmt.setInt(5, usuario.getEndereco().getNumero());
      stmt.setString(6, usuario.getEndereco().getComplemento());
      stmt.setString(7, usuario.getEndereco().getCep());
      stmt.setString(8, usuario.getEndereco().getCidade());
      stmt.setString(9, usuario.getEndereco().getEstado());
      int affectedRows = stmt.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Erro ao criar usuario (affectedRows = 0)");
      }

      ResultSet generatedKeys = stmt.getGeneratedKeys();
      if (!generatedKeys.next()) {
        throw new SQLException("generatedKeys nao retornou o ID do usuario recem-criado");
      }

      usuario.setId(generatedKeys.getInt(1));

      if (usuario.getTipo().equals("cliente_pf") || usuario.getTipo().equals("cliente_pj")) {
        sql = "INSERT INTO Cliente (id_usuario) VALUES (?)";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, usuario.getId());
        stmt.executeUpdate();
      }

      if (usuario.getTipo().equals("cliente_pf")) {
        ClientePF clientePF = (ClientePF) usuario;
        sql = "INSERT INTO Cliente_PF (id_usuario, cpf, dt_nascimento, sexo) VALUES (?, ?, ?, ?)";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, usuario.getId());
        stmt.setString(2, clientePF.getCpf());
        stmt.setDate(3, new Date(clientePF.getDtNascimento().getTime()));
        stmt.setString(4, "" + clientePF.getSexo());
        stmt.executeUpdate();
      }

      if (usuario.getTipo().equals("cliente_pj")) {
        // TODO
      }

      if (usuario.getTipo().equals("funcionario")) {
        // TODO
      }

      stmt.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
