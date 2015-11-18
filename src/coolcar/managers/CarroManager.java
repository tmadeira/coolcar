package coolcar.managers;

import coolcar.modelos.CaracteristicasCarro;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Carro;

public class CarroManager {
  public ArrayList<Carro> consulta(Carro carro) {
    ArrayList<Carro> carros = new ArrayList<Carro>();
    String idModeloStr, numPortasStr, numAssentosStr, tamPortaMalasStr, idCaracStr;
    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();

      if (carro.getIdModelo() != -1)
        idModeloStr = Integer.toString(carro.getIdModelo());
      else
        idModeloStr = "id_modelo";

      if (carro.getNumPortas() != -1)
        numPortasStr = Integer.toString(carro.getNumPortas());
      else
        numPortasStr = "num_portas";

      if (carro.getNumAssentos() != -1)
        numAssentosStr = Integer.toString(carro.getNumAssentos());
      else
        numAssentosStr = "num_assentos";

      if (carro.getTamanhoPortaMalas() != -1)
        tamPortaMalasStr = Integer.toString(carro.getTamanhoPortaMalas());
      else
        tamPortaMalasStr = "tamanho_portas_malas";

      if (carro.getId_caracteristicas() != -1)
        idCaracStr = Integer.toString(carro.getId_caracteristicas());
      else
        idCaracStr = "id_caracteristicas";

      String sql = "SELECT * FROM (Carro NATURAL JOIN caracteristicascarro) NATURAL JOIN Modelo WHERE id_modelo = "
          + idModeloStr + " AND num_portas = " + numPortasStr + " AND num_assentos = " + numAssentosStr + " "
          + "AND tamanho_portas_malas = " + tamPortaMalasStr + " AND id_caracteristicas = " + idCaracStr;

      PreparedStatement stmt = connection.prepareStatement(sql);

      ResultSet resultados = stmt.executeQuery();
      while (resultados.next()) {
        CaracteristicasCarro carac_select = new CaracteristicasCarro(resultados.getBoolean("ar_condicionado"),
            resultados.getBoolean("direcao_hidraulica"), resultados.getBoolean("cambio_automatico"));

        Carro carro_select = new Carro(resultados.getString("nome"), resultados.getString("fabricante"),
            resultados.getString("combustivel"), resultados.getString("tipo"),
            new BigDecimal(resultados.getFloat("diaria")), resultados.getInt("id_modelo"),
            resultados.getInt("id_funcionario"), resultados.getInt("num_portas"), resultados.getInt("num_assentos"),
            resultados.getInt("tamanho_portas_malas"), resultados.getInt("id_caracteristicas"), carac_select);
        carros.add(carro_select);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return carros;
  }
}