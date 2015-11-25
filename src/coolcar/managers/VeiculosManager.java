package coolcar.managers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.CaracteristicasCarro;
import coolcar.modelos.Carro;
import coolcar.modelos.Moto;
import coolcar.modelos.Veiculo;

public class VeiculosManager {
  public ArrayList<Veiculo> consulta(Veiculo veiculo) {
    ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

    String placaStr, anoStr, filialStr, statusStr, arCondStr, dirHidStr, cambAutoStr, cilindradasStr, tamTanqueStr;
    String sql;

    try {
      BD bd = new BD();
      Connection connection = bd.getConnection();

      if (veiculo.getPlaca() != null)
        placaStr = "'" + veiculo.getPlaca() + "'";
      else
        placaStr = "placa";

      if (veiculo.getAno() != -1)
        anoStr = Integer.toString(veiculo.getAno());
      else
        anoStr = "ano";

      if (veiculo.getFilialAlojada() != -1)
        filialStr = Integer.toString(veiculo.getFilialAlojada());
      else
        filialStr = "filial_alojada";

      if (veiculo.getStatus() != null)
        statusStr = "'" + veiculo.getStatus() + "'";
      else
        statusStr = "status";

      /*---------------------------------------------------------------------------------------CARRO-*/
      if (veiculo.getTipoDoVeiculo().equals("Carro")) {
        if (veiculo.getCarro().getCarac().getArCond() == true)
          arCondStr = "true";
        else
          arCondStr = "ar_condicionado";

        if (veiculo.getCarro().getCarac().getDirHid() == true)
          dirHidStr = "true";
        else
          dirHidStr = "direcao_hidraulica";

        if (veiculo.getCarro().getCarac().getCambAuto() == true)
          cambAutoStr = "true";
        else
          cambAutoStr = "false";

        sql = "SELECT DISTINCT modelo, * FROM veiculo NATURAL JOIN (modelo NATURAL JOIN (carro NATURAL JOIN caracteristicascarro))"
            + "WHERE placa = " + placaStr + " AND ano = " + anoStr + " AND filial_alojada = " + filialStr + " AND"
            + " status = " + statusStr + " AND ar_condicionado = " + arCondStr + " AND direcao_hidraulica = "
            + dirHidStr + " AND cambio_automatico = " + cambAutoStr;

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

          carro_select.setLink(resultados.getString("link"));

          Veiculo veiculo_select = new Veiculo("Carro", resultados.getString("chassi"), resultados.getString("placa"),
              resultados.getInt("kilometragem"), resultados.getInt("ano"), resultados.getString("status"),
              resultados.getInt("id_funcionario_veic"), resultados.getInt("id_modelo"),
              resultados.getInt("filial_alojada"), carro_select, new Moto());

          veiculos.add(veiculo_select);
        }
      }
      /*----------------------------------------------------------------------------------------MOTO-*/
      else {
        if (veiculo.getMoto().getCilindradas() != -1)
          cilindradasStr = Float.toString(veiculo.getMoto().getCilindradas());
        else
          cilindradasStr = "cilindradas";

        if (veiculo.getMoto().getTamanhoTanque() != -1)
          tamTanqueStr = Float.toString(veiculo.getMoto().getTamanhoTanque());
        else
          tamTanqueStr = "tamanho_tanque";

        sql = "SELECT * FROM veiculo NATURAL JOIN (modelo NATURAL JOIN moto)" + "WHERE placa = " + placaStr
            + " AND ano = " + anoStr + " AND filial_alojada = " + filialStr + " AND" + " status = " + statusStr
            + " AND cilindradas = " + cilindradasStr + " AND tamanho_tanque = " + tamTanqueStr;
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet resultados = stmt.executeQuery();
        while (resultados.next()) {
          Moto moto_select = new Moto(resultados.getString("nome"), resultados.getString("fabricante"),
              resultados.getString("combustivel"), resultados.getString("tipo"),
              new BigDecimal(resultados.getFloat("diaria")), resultados.getInt("id_modelo"),
              resultados.getInt("id_funcionario"), resultados.getFloat("cilindradas"),
              resultados.getFloat("tamanho_tanque"));
          moto_select.setLink(resultados.getString("link"));

          Veiculo veiculo_select = new Veiculo("Moto", resultados.getString("chassi"), resultados.getString("placa"),
              resultados.getInt("kilometragem"), resultados.getInt("ano"), resultados.getString("status"),
              resultados.getInt("id_funcionario_veic"), resultados.getInt("id_modelo"),
              resultados.getInt("filial_alojada"), new Carro(), moto_select);
          veiculos.add(veiculo_select);
        }
      }
      /*---------------------------------------------------------------------------------------------*/
    } catch (Exception e) {
      e.printStackTrace();
    }
    return veiculos;
  }
}