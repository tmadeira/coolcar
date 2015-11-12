package coolcar.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import coolcar.bd.BD;
import coolcar.modelos.Endereco;
import coolcar.modelos.Filial;

public class FilialManager {

	public ArrayList<Filial> consulta(Filial filial) {
		  ArrayList<Filial> filiais = new ArrayList<Filial>();
		  
		  try {
			BD bd = new BD();
			Connection connection = bd.getConnection();
		    String sql = "SELECT * FROM Filial WHERE nome LIKE ?";
		    
		    PreparedStatement stmt = connection.prepareStatement(sql);
		    
		    if (filial.getNome() != null)
		    	stmt.setString(1, filial.getNome());
		    else
		    	stmt.setString(1, "%");

		    
		    ResultSet resultados = stmt.executeQuery();
		    while (resultados.next()) {	    	  
		    	Endereco endereco_select = new Endereco(resultados.getString("endereco_logradouro"),
				    									resultados.getString("endereco_complemento"),
				    									resultados.getString("endereco_cep"),
				    									resultados.getString("endereco_cidade"),
				    									resultados.getString("endereco_estado"),
				    									resultados.getInt("endereco_numero"));
		    	
		    	Filial filial_select = new Filial(resultados.getInt("id_filial"),
			    								  resultados.getInt("id_funcionario_cadastrou"),
			    								  resultados.getString("nome"),
			    								  endereco_select,
			    								  resultados.getFloat("coordenadas_longitude"),
			    								  resultados.getFloat("coordenadas_latitude"));
		    	filiais.add(filial_select);
		    }

		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	    return filiais;
	  }
}