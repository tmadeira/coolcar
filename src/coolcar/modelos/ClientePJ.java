package coolcar.modelos;

public class ClientePJ extends Usuario {
  public String cnpj;
  
  public ClientePJ() {
  }
  
  public ClientePJ(int id, String nome, String email, String senha, Endereco endereco, String cnpj) {
	  super(id, nome, email, senha, endereco);
	  this.cnpj = cnpj;
  }

  @Override
  public String getTipo() {
    return "cliente_pj";
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
}
