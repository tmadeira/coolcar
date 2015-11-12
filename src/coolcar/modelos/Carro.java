package coolcar.modelos;

public class Carro extends Modelo {
  private int numPortas, numAssentos, tamanhoPortaMalas, id_caracteristicas;
  private String tipoCarro;

  @Override
  public String getTipo() {
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

  public String getTipoCarro() {
    return tipoCarro;
  }

  public void setTipoCarro(String tipoCarro) {
    this.tipoCarro = tipoCarro;
  }

public int getId_caracteristicas() {
	return id_caracteristicas;
}

public void setId_caracteristicas(int id_caracteristicas) {
	this.id_caracteristicas = id_caracteristicas;
}
}
