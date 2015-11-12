package coolcar.modelos;

import java.util.Date;

public abstract class Usuario {
  private int id;
  private String nome, email, senha;
  private Endereco endereco;
  private Telefone telefone, celular;

  public abstract String getTipo();

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
    this.senha = senha;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }
  
  public void setCpf(String cpf) {
	  }
  
  public void setSexo(char sexo) {
  }
  
  public Date getDtNascimento() {
	    return null;
  }
  
  public void setDtNascimento(Date dtNascimento) {
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
  
  public String getCnpj() {
	  return "";
  }
 
  public void setCnpj(String cnpj) {
  }
}
