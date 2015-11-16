package coolcar.modelos;

import java.math.BigDecimal;

public abstract class Modelo {
  private String nome, fabricante, combustivel, tipo, link;
  private BigDecimal diaria;
  private int idModelo, idFuncionario;

  public Modelo() {
	  idModelo = -1;
  }
  
  public Modelo(String nome, String fabricante, String combustivel, String tipo, BigDecimal diaria, int idModelo, int idFuncionario) {
	  this.nome = nome;
	  this.fabricante = fabricante;
	  this.combustivel = combustivel;
	  this.tipo = tipo;
	  this.diaria = diaria;
	  this.idModelo = idModelo;
	  this.idFuncionario = idFuncionario;
  }
  
  public abstract String getTipoVeiculo();

  public int getIdModelo() {
    return idModelo;
  }

  public void setIdModelo(int idModelo) {
    this.idModelo = idModelo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getFabricante() {
    return fabricante;
  }

  public void setFabricante(String fabricante) {
    this.fabricante = fabricante;
  }

  public String getCombustivel() {
    return combustivel;
  }

  public void setCombustivel(String combustivel) {
    this.combustivel = combustivel;
  }
  
  public String getTipo() {
	return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public BigDecimal getDiaria() {
    return diaria;
  }

  public void setDiaria(BigDecimal diaria) {
    this.diaria = diaria;
  }

  public int getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(int idFuncionario) {
    this.idFuncionario = idFuncionario;
  }
  
  public String getLink() {
	return link;
  }

  public void setLink(String link) {
	this.link = link;
  }
}
