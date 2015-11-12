package coolcar.modelos;

public class Moto extends Modelo {
  private int cilindradas, tamanhoTanque;

  @Override
  public String getTipo() {
    return "moto";
  }

  public int getCilindradas() {
    return cilindradas;
  }

  public void setCilindradas(int cilindradas) {
    this.cilindradas = cilindradas;
  }

  public int getTamanhoTanque() {
    return tamanhoTanque;
  }

  public void setTamanhoTanque(int tamanhoTanque) {
    this.tamanhoTanque = tamanhoTanque;
  }
}
