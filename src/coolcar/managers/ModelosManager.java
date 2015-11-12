package coolcar.managers;

import java.util.ArrayList;

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
}
