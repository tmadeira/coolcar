package coolcar.modelos;

public class ClientePJ extends Usuario {
  public String cnpj;

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
