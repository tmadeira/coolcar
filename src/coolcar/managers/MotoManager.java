package coolcar.managers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Moto;

public class MotoManager {
  public ArrayList<Moto> consulta(Moto moto) {
    ArrayList<Moto> motos = new ArrayList<Moto>();
    String idModeloStr, cilindradasStr, tamTanqueStr;
    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();

      if (moto.getIdModelo() != -1)
        idModeloStr = Integer.toString(moto.getIdModelo());
      else
        idModeloStr = "id_modelo";

      if (moto.getCilindradas() != -1)
        cilindradasStr = Float.toString(moto.getCilindradas());
      else
        cilindradasStr = "cilindradas";

      if (moto.getTamanhoTanque() != -1)
        tamTanqueStr = Float.toString(moto.getTamanhoTanque());
      else
        tamTanqueStr = "tamanho_tanque";

      String sql = "SELECT * FROM Moto NATURAL JOIN Modelo WHERE id_modelo = " + idModeloStr + " AND cilindradas = "
          + cilindradasStr + " AND tamanho_tanque = " + tamTanqueStr;

      PreparedStatement stmt = connection.prepareStatement(sql);

      ResultSet resultados = stmt.executeQuery();
      while (resultados.next()) {
        Moto moto_select = new Moto(resultados.getString("nome"), resultados.getString("fabricante"),
            resultados.getString("combustivel"), resultados.getString("tipo"),
            new BigDecimal(resultados.getFloat("diaria")), resultados.getInt("id_modelo"),
            resultados.getInt("id_funcionario"), resultados.getFloat("cilindradas"),
            resultados.getFloat("tamanho_tanque"));
        motos.add(moto_select);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return motos;
  }
}