package coolcar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.DigestInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coolcar.bd.ScriptRunner;

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

  private String getVersao() {
    try {
      ResultSet rs = this.executaConsulta("SELECT versao FROM versoes");
      if (rs.next()) {
        return rs.getString("versao");
      }
    } catch (SQLException e) {
      System.out.println("Nao foi possivel encontrar a versao do BD.");
      e.printStackTrace();
    }
    return "";
  }

  private void atualizaVersao(String versao) {
    try {
      this.executaAtualizacao("CREATE TABLE IF NOT EXISTS versoes (versao CHAR(32))");
      this.executaAtualizacao("DELETE FROM versoes");
      this.executaAtualizacao("INSERT INTO versoes VALUES ('" + versao + "')");
      System.out.println("Versao do BD atualizada para " + versao);
    } catch (SQLException e) {
      System.out.println("Nao foi possivel atualizar a versao do BD.");
      e.printStackTrace();
    }
  }

  public void setup(String setupFile) {
    try {
      String versaoAtual = this.getVersao();
      String novaVersao = Utils.md5File(setupFile);
      if (!versaoAtual.equals(novaVersao)) {
        ScriptRunner runner = new ScriptRunner(connection, false, false);
        BufferedReader reader = new BufferedReader(new FileReader(setupFile));
        runner.runScript(reader);
        this.atualizaVersao(novaVersao);
      }
    } catch (Exception e) {
      System.out.println("Erro ao executar SQL.");
      e.printStackTrace();
    }
  }
}
