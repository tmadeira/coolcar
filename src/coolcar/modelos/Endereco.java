package coolcar.modelos;

public class Endereco {
  private String logradouro, complemento, cep, cidade, estado;
  private int numero;

  public Endereco() {}
  
  public Endereco(String logradouro, String complemento, String cep, String cidade, String estado, int numero) {
	  this.logradouro = logradouro;
	  this.complemento = complemento;
	  this.cep = cep;
	  this.cidade = cidade;
	  this.estado = estado;
	  this.numero = numero;
  }
  
  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }
}
