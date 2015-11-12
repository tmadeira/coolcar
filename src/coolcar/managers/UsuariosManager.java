package coolcar.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;

import coolcar.bd.BD;
import coolcar.modelos.Usuario;

public class UsuariosManager {

  public UsuariosManager() {
  }

  public boolean insere(Usuario usuario) {
    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();
      String sql = "INSERT INTO usuarios (nome) VALUES (?)";
      /*
       * TODO: deve inserir outros dados. Devemos ter subclasses de Usuario para
       * diferentes tipos de usuario (modelar como BD - ver setup.sql)
       */
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, usuario.getNome());
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
