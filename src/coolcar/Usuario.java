package coolcar;

public class Usuario {

	String nome, sobrenome, dataDeNascimento, cpf, telefone, celular, email, password;
	
	Usuario(String nome, String sobrenome, String dataDeNascimento, String cpf, String telefone,
			String celular, String email, String password) {
				
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.password = password;
		
		System.out.println("Dados do usuario: " + nome + " " + sobrenome);
		System.out.println("Data de nascimento : " + dataDeNascimento);
		System.out.println("cpf " + cpf);
		System.out.println("telefone " + telefone);
		System.out.println("celular " + celular);
		System.out.println("email " + email);
		System.out.println("password " + password);
	}
	
	String getNome() {
		return nome;
	}
}
