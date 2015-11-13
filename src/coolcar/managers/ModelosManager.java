package coolcar.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Carro;
import coolcar.modelos.Modelo;
import coolcar.modelos.Moto;

public class ModelosManager {
  public ArrayList<Modelo> buscaModelos() {
    ArrayList<Modelo> modelos = new ArrayList<Modelo>();

    modelos.add(new Carro());
    modelos.add(new Carro());
    modelos.add(new Moto());

    return modelos;
  }
  
  public ArrayList<Modelo> consulta(int id_modelo)
  {
	Modelo modelo;
    ArrayList<Modelo> modelos = new ArrayList<Modelo>();
	
	try {
        BD bd = new BD();
        Connection connection = bd.getConnection();
        String sql = "SELECT * FROM Carro NATURAL JOIN Modelo WHERE id_modelo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
    	stmt.setInt(1, id_modelo);
        ResultSet rs = stmt.executeQuery();
        modelo = null;
                
        while(rs.next()) {
	        
	    	modelo = new Carro();
	    	((Carro) modelo).setNumPortas(rs.getInt("num_portas"));
	    	((Carro) modelo).setNumAssentos(rs.getInt("num_assentos"));
	    	((Carro) modelo).setTamanhoPortaMalas(rs.getInt("tamanho_portas_malas"));
	    	((Carro) modelo).setId_caracteristicas(rs.getInt("id_caracteristicas"));
	    	 modelo.setNome(rs.getString("nome"));
	         modelo.setDiaria(rs.getBigDecimal("diaria"));
	         modelo.setFabricante(rs.getString("fabricante"));
	         modelo.setCombustivel(rs.getString("combustivel"));
	         modelo.setIdFuncionario(rs.getInt("id_funcionario"));
	    	modelos.add(modelo);
	    }
        if(modelos.isEmpty()){
        	sql = "SELECT * FROM Moto NATURAL JOIN Modelo WHERE id_modelo = ?";
        	stmt = connection.prepareStatement(sql);
        	stmt.setInt(1, id_modelo);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
		       	modelo = new Moto();
		        ((Moto) modelo).setCilindradas(rs.getFloat("cilindradas"));
		        ((Moto) modelo).setTamanhoTanque(rs.getFloat("tamanho_tanque"));
		        modelo.setNome(rs.getString("nome"));
		        modelo.setDiaria(rs.getBigDecimal("diaria"));
		        modelo.setFabricante(rs.getString("fabricante"));
		        modelo.setCombustivel(rs.getString("combustivel"));
		        modelo.setIdFuncionario(rs.getInt("id_funcionario"));
		          
		        modelos.add(modelo);
            }
        }    
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }
	  return modelos;
  }
}
