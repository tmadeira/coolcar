package coolcar.modelos;

import java.math.BigDecimal;

public class Carro extends Modelo {
  private int numPortas, numAssentos, tamanhoPortaMalas, id_caracteristicas;
  

  public Carro() {
	  super();
	  numPortas = -1;
	  numAssentos = -1;
	  tamanhoPortaMalas = -1;
	  id_caracteristicas = -1;
  }
  
  public Carro(String nome, String fabricante, String combustivel, String tipo, BigDecimal diaria, int idModelo, int idFuncionario, int numPortas, int numAssentos, int tamanhoPortaMalas, int id_caracteristicas) {
	  super(nome, fabricante, combustivel, tipo, diaria, idModelo, idFuncionario);
	  this.numPortas = numPortas;
	  this.numAssentos = numAssentos;
	  this.tamanhoPortaMalas = tamanhoPortaMalas;
	  this.id_caracteristicas = id_caracteristicas;
  }
  
  @Override
  public String getTipoVeiculo() {
    return "carro";
  }

  public int getNumPortas() {
    return numPortas;
  }

  public void setNumPortas(int numPortas) {
    this.numPortas = numPortas;
  }

  public int getNumAssentos() {
    return numAssentos;
  }

  public void setNumAssentos(int numAssentos) {
    this.numAssentos = numAssentos;
  }

  public int getTamanhoPortaMalas() {
    return tamanhoPortaMalas;
  }

  public void setTamanhoPortaMalas(int tamanhoPortaMalas) {
    this.tamanhoPortaMalas = tamanhoPortaMalas;
  }

public int getId_caracteristicas() {
	return id_caracteristicas;
}

public void setId_caracteristicas(int id_caracteristicas) {
	this.id_caracteristicas = id_caracteristicas;
}
}
