package coolcar.modelos;

import java.math.BigDecimal;

public class Moto extends Modelo {
  private float cilindradas, tamanhoTanque;

  public Moto() {
	  super();
	  cilindradas = -1;
	  tamanhoTanque = -1;
  }
  
  public Moto(String nome, String fabricante, String combustivel, String tipo, BigDecimal diaria, int idModelo, int idFuncionario, float cilindradas, float tamanhoTanque) {
	  super(nome, fabricante, combustivel, tipo, diaria, idModelo, idFuncionario);
	  this.cilindradas = cilindradas;
	  this.tamanhoTanque = tamanhoTanque;
  }
  
  @Override
  public String getTipoVeiculo() {
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
