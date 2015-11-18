package coolcar.modelos;

public class CaracteristicasCarro {
  private boolean ar_condicionado, cambio_automatico, direcao_hidraulica;

  public CaracteristicasCarro() {
    this.ar_condicionado = false;
    this.direcao_hidraulica = false;
    this.cambio_automatico = false;
  }

  public CaracteristicasCarro(boolean ar_condicionado, boolean direcao_hidraulica, boolean cambio_automatico) {
    this.ar_condicionado = ar_condicionado;
    this.direcao_hidraulica = direcao_hidraulica;
    this.cambio_automatico = cambio_automatico;
  }

  public void setArCond(boolean ar) {
    this.ar_condicionado = ar;
  }

  public void setCambAuto(boolean ca) {
    this.cambio_automatico = ca;
  }

  public void setDirHid(boolean di) {
    this.direcao_hidraulica = di;
  }

  public boolean getArCond() {
    return this.ar_condicionado;
  }

  public boolean getCambAuto() {
    return this.cambio_automatico;
  }

  public boolean getDirHid() {
    return this.direcao_hidraulica;
  }
}
