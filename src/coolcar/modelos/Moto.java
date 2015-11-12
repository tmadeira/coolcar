package coolcar.modelos;

public class Moto extends Modelo {
  private float cilindradas, tamanhoTanque;

  @Override
  public String getTipo() {
    return "moto";
  }

  public float getCilindradas() {
    return cilindradas;
  }

  public void setCilindradas(float cilindradas) {
    this.cilindradas = cilindradas;
  }

  public float getTamanhoTanque() {
    return tamanhoTanque;
  }

  public void setTamanhoTanque(float tamanhoTanque) {
    this.tamanhoTanque = tamanhoTanque;
  }
}
