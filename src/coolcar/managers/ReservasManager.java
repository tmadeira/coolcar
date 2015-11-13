package coolcar.managers;

import java.util.ArrayList;

import coolcar.modelos.Reserva;
import coolcar.modelos.Usuario;

public class ReservasManager {
	
  public ArrayList<Reserva> consulta(int userId) {
	  
    ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    
    reservas.add(new Reserva());
    reservas.add(new Reserva());
    return reservas;
  }
  
  public boolean insere(Usuario usuario) {
	  return true;
  }
}
