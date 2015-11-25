package coolcar.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Endereco;
import coolcar.modelos.ClientePJ;

public class ClientesPJManager {

  public ArrayList<ClientePJ> consulta(ClientePJ ClientePJ) {
    ArrayList<ClientePJ> clientesPJ = new ArrayList<ClientePJ>();

    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();
      String sql = "SELECT * FROM Cliente_PJ NATURAL JOIN Usuario WHERE nome LIKE ? AND email LIKE ? AND senha LIKE ? AND cnpj LIKE ?";

      PreparedStatement stmt = connection.prepareStatement(sql);

      if (ClientePJ.getNome() != null)
        stmt.setString(1, ClientePJ.getNome());
      else
        stmt.setString(1, "%");

      if (ClientePJ.getEmail() != null)
        stmt.setString(2, ClientePJ.getEmail());
      else
        stmt.setString(2, "%");

      if (ClientePJ.getSenha() != null)
        stmt.setString(3, ClientePJ.getSenha()); // TODO: hash
      else
        stmt.setString(3, "%");

      if (ClientePJ.getCnpj() != null)
        stmt.setString(4, ClientePJ.getCnpj());
      else
        stmt.setString(4, "%");

      ResultSet resultados = stmt.executeQuery();
      while (resultados.next()) {
        Endereco endereco_select = new Endereco(resultados.getString("endereco_logradouro"),
            resultados.getString("endereco_complemento"), resultados.getString("endereco_cep"),
            resultados.getString("endereco_cidade"), resultados.getString("endereco_estado"),
            resultados.getInt("endereco_numero"));

        ClientePJ ClientePJ_select = new ClientePJ(resultados.getInt("id_usuario"), resultados.getString("nome"),
            resultados.getString("email"), resultados.getString("senha"), endereco_select,
            resultados.getString("cnpj"));
        clientesPJ.add(ClientePJ_select);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return clientesPJ;
  }

}
