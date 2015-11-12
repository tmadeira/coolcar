package coolcar.modelos;

import java.util.Date;

public class ClientePF extends Usuario {
  private String cpf;
  private char sexo;
  private Date dtNascimento;

  @Override
  public String getTipo() {
    return "cliente_pf";
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public char getSexo() {
    return sexo;
  }

  public void setSexo(char sexo) {
    this.sexo = sexo;
  }

  public Date getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(Date dtNascimento) {
    this.dtNascimento = dtNascimento;
  }
}
