package coolcar.modelos;

public abstract class Modelo {
  public Modelo() {
  }

  public String getNome() {
    return "Honda";
  }

  public float getPreco() {
    return (float) 100.49;
  }

  public String getFabricante() {
    return "Honda";
  }

  public abstract String getTipo();

  public int getNumeroAssentos() {
    return 1;
  }
}
