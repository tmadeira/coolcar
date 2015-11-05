package coolcar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
  private String host;
  private String user;
  private String pass;

  private Connection connection;
  private Statement statement;

  public BD() throws ClassNotFoundException {
    Config config = Config.getInstance();

    this.host = config.get("db_host", "jdbc:postgresql://127.0.0.1:5432/coolcar");
    this.user = config.get("db_user", System.getProperty("user.name"));
    this.pass = config.get("db_pass", "");

    Class.forName("org.postgresql.Driver");
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  private void conecta() throws SQLException {
    this.connection = DriverManager.getConnection(this.host, this.user, this.pass);
  }

  private void resetStatement() throws SQLException {
    if (this.connection == null) {
      this.conecta();
    }

    if (this.statement != null) {
      try {
        this.statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        this.statement = null;
      }
    }

    this.statement = this.connection.createStatement();
  }

  public void executaAtualizacao(String query) throws SQLException {
    this.resetStatement();
    this.statement.executeUpdate(query);
  }

  public ResultSet executaConsulta(String query) throws SQLException {
    this.resetStatement();
    return this.statement.executeQuery(query);
  }
}
