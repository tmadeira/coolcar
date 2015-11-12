package coolcar.modelos;

import java.util.Date;

public class Funcionario extends Usuario {
  private String cpf;
  private Date dtIngresso, dtNascimento;
  /* TODO: filial */

  @Override
  public String getTipo() {
    return "funcionario";
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Date getDtIngresso() {
    return dtIngresso;
  }

  public void setDtIngresso(Date dtIngresso) {
    this.dtIngresso = dtIngresso;
  }

  public Date getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(Date dtNascimento) {
    this.dtNascimento = dtNascimento;
  }
}
