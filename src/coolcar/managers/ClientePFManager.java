package coolcar.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Endereco;
import coolcar.modelos.ClientePF;

public class ClientePFManager {

  public ArrayList<ClientePF> consulta(ClientePF clientePF) {
    ArrayList<ClientePF> clientesPF = new ArrayList<ClientePF>();

    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();
      String sql = "SELECT * FROM Cliente_PF NATURAL JOIN Usuario WHERE nome LIKE ? AND email LIKE ? AND senha LIKE ? AND cpf LIKE ?";

      PreparedStatement stmt = connection.prepareStatement(sql);

      if (clientePF.getNome() != null)
        stmt.setString(1, clientePF.getNome());
      else
        stmt.setString(1, "%");

      if (clientePF.getEmail() != null)
        stmt.setString(2, clientePF.getEmail());
      else
        stmt.setString(2, "%");

      if (clientePF.getSenha() != null)
        stmt.setString(3, clientePF.getSenha()); // TODO: hash
      else
        stmt.setString(3, "%");

      if (clientePF.getCpf() != null)
        stmt.setString(4, clientePF.getCpf());
      else
        stmt.setString(4, "%");

      ResultSet resultados = stmt.executeQuery();
      while (resultados.next()) {
        Endereco endereco_select = new Endereco(resultados.getString("endereco_logradouro"),
            resultados.getString("endereco_complemento"), resultados.getString("endereco_cep"),
            resultados.getString("endereco_cidade"), resultados.getString("endereco_estado"),
            resultados.getInt("endereco_numero"));

        ClientePF clientePF_select = new ClientePF(resultados.getInt("id_usuario"), resultados.getString("nome"),
            resultados.getString("email"), resultados.getString("senha"), endereco_select, resultados.getString("cpf"),
            resultados.getString("sexo").charAt(0), resultados.getDate("dt_nascimento"));
        clientesPF.add(clientePF_select);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return clientesPF;
  }

}
