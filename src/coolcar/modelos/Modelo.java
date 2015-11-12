package coolcar.modelos;

import java.math.BigDecimal;

public abstract class Modelo {
  private String nome, fabricante, combustivel;
  private BigDecimal diaria;
  private int idModelo, idFuncionario;

  public abstract String getTipo();

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
}
