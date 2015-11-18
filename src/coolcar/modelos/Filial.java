package coolcar.modelos;

public class Filial {
	  private int id, id_funcionario_cadastrou;
	  private String nome;
	  private Endereco endereco;
	  private float longitude, latitude;
	  
	  public Filial(){
		  id = -1;
	  }
	  
	  public Filial(int id, int id_func, String nome, Endereco endereco, float longitude, float latitude) {
		  this.id = id;
		  this.id_funcionario_cadastrou = id_func;
		  this.nome = nome;
		  this.endereco = endereco;
		  this.longitude = longitude;
		  this.latitude = latitude;
	  }
	  
	  public void setId(int id){
		  this.id = id;
	  }
	  
	  public int getId(){
		  return id;
	  }
	  
	  public int getid_funcionario_cadastrou(){
		  return id_funcionario_cadastrou;
	  }
	  
	  public void setId(String nome){
		  this.nome = nome;
	  }
	  
	  public String getNome(){
		  return nome;
	  }
	  
	  public Endereco getEndereco() {
		  return endereco;
	  }
	  
	  public double getLongitude() {
		  return longitude;
	  }
	  
	  public double getLatitude() {
		  return latitude;
	  }

}
