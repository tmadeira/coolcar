package coolcar;

import java.util.ArrayList;

public class ModelosManager {
  public ArrayList<Modelo> buscaModelos() {
    ArrayList<Modelo> modelos = new ArrayList<Modelo>();
    
    modelos.add(new Carro());
    modelos.add(new Carro());
    modelos.add(new Moto());
    
    return modelos;
  }
}
