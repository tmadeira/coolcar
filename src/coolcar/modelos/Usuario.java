package coolcar.modelos;

public abstract class Usuario {
  private int id;
  private String nome, email, senha;
  private Endereco endereco;

  public Usuario(){}
  
  public Usuario(int id, String nome, String email, String senha, Endereco endereco){
	  this.id = id;
	  this.nome = nome;
	  this.email = email;
	  this.senha = senha;
	  this.endereco = endereco;
  }
  
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
    this.senha = Integer.toString(Math.abs(senha.hashCode()));
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }
}
