package coolcar.managers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Reserva;

public class ReservasManager {
	
  public ArrayList<Reserva> consulta(int userId) {
	  
    ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    
    try {
        BD bd = new BD();
        Connection connection = bd.getConnection();
        String sql = "SELECT * FROM Reserva WHERE id_cliente = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
    	stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Reserva reserva = new Reserva();
            
            reserva.setId_reserva(rs.getInt("id_reserva"));
            reserva.setId_cliente(rs.getInt("id_cliente"));
            reserva.setValor(rs.getFloat("valor"));
            reserva.setId_filial_retirada(rs.getInt("id_filial_retirada"));
            reserva.setId_filial_devolucao(rs.getInt("id_filial_devolucao"));
            reserva.setDt_inicio_reserva(rs.getDate("dt_inicio_reserva"));
            reserva.setDt_fim_reserva(rs.getDate("dt_fim_reserva"));
            reserva.setId_acessorios(rs.getInt("id_acessorios"));
            reserva.setId_modelo(rs.getInt("id_modelo"));

            reservas.add(reserva);
         }
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    return reservas;
  }
  
  public boolean insere(Reserva reserva) {
	  
	  try {
	      BD bd = new BD();
	      Connection connection = bd.getConnection();
	      String sql = "INSERT INTO Reserva(id_cliente, id_modelo, valor, id_filial_retirada, "
	          + "dt_inicio_reserva, id_filial_devolucao, dt_fim_reserva, id_acessorios)"
	          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	      stmt.setInt(1, reserva.getId_cliente());
	      stmt.setInt(2, reserva.getId_modelo());
	      stmt.setFloat(3, reserva.getValor()); // TODO: hash
	      stmt.setInt(4, reserva.getId_filial_retirada());
	      stmt.setDate(5, new Date(reserva.getDt_inicio_reserva().getTime()));
	      stmt.setInt(6, reserva.getId_filial_devolucao());
	      stmt.setDate(7, new Date(reserva.getDt_fim_reserva().getTime()));
	      stmt.setInt(8, reserva.getId_acessorios());
	      int affectedRows = stmt.executeUpdate();

	      if (affectedRows == 0) {
	        throw new SQLException("Erro ao reservar veículo (affectedRows = 0)");
	      }

	      ResultSet generatedKeys = stmt.getGeneratedKeys();
	      if (!generatedKeys.next()) {
	        throw new SQLException("generatedKeys nao retornou o ID da reserva recem-criada");
	      }

	      reserva.setId_reserva(generatedKeys.getInt(1));
	      

	      stmt.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    }
	  
	  return true;
  }
}
