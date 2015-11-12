package coolcar.managers;

import java.util.ArrayList;

import coolcar.modelos.Reserva;

public class ReservasManager {
  public ArrayList<Reserva> consulta(int userId) {
    ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    reservas.add(new Reserva());
    reservas.add(new Reserva());
    return reservas;
  }
}
