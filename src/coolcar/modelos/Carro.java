package coolcar.modelos;

public class Carro extends Modelo {
  public Carro() {
  }

  @Override
  public String getTipo() {
    return "carro";
  }

  public int getNumeroAssentos() {
    return 5;
  }

  public boolean getCambio() {
    return true;
  }

  public boolean getArCond() {
    return true;
  }
}
