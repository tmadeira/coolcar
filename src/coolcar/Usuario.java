package coolcar;

public class Usuario {

	String nome, sobrenome;
	String id;
	String email, password;
	
	Usuario(String nome, String sobrenome, String id, String email, String password) {
		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.id = id;
		this.email = email;
		this.password = password;
		
		System.out.println("Dados do usuario: " + nome + sobrenome);
		System.out.println("id " + id);
		System.out.println("email " + email);
		System.out.println("password " + password);
	}
	
	String getNome() {
		return nome;
	}
}
