package coolcar.modelos;

import java.util.Date;

public abstract class Usuario {
  private int id;
  private String nome, email, senha;
  private Endereco endereco;
  private Telefone telefone, celular;

  public Usuario() {
  }
  
  public Usuario(String email, String senha) {
    this.email = email;
    this.senha = senha;
  }

  public Usuario(int id, String nome, String email, String senha, Endereco endereco) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.endereco = endereco;
  }

  public abstract String getTipo();

  public String getCpf() {
    return "";
  };

  public void setCpf(String cpf) {
  };

  public char getSexo() {
    return ' ';
  };

  public void setSexo(char sexo) {

  };

  public Date getDtNascimento() {
    return new Date();
  };

  public void setDtNascimento(Date dtNascimento) {

  };

  public String getCnpj() {
    return "";
  };

  public void setCnpj(String cnpj) {
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = Integer.toString(Math.abs(senha.hashCode()));
    //this.senha = senha;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public Telefone getTelefone() {
    return telefone;
  }

  public void setTelefone(Telefone telefone) {
    this.telefone = telefone;
  }

  public Telefone getCelular() {
    return celular;
  }

  public void setCelular(Telefone celular) {
    this.celular = celular;
  }
}
