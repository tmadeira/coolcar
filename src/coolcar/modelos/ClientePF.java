package coolcar.modelos;

import java.util.Date;

public class ClientePF extends Usuario {
  private String cpf;
  private char sexo;
  private Date dtNascimento;

  public ClientePF() {
  }
  
  public ClientePF(String email, String senha) {
    super(email, senha);
  }

  public ClientePF(int id, String nome, String email, String senha, Endereco endereco, String cpf, char sexo,
      Date dtNascimento) {
    super(id, nome, email, senha, endereco);
    this.cpf = cpf;
    this.sexo = sexo;
    this.dtNascimento = dtNascimento;
  }

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
