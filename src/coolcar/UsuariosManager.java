package coolcar;

import java.util.ArrayList;

public class UsuariosManager {
	
	public ArrayList<Usuario> listaUsuarios;
	
	public UsuariosManager() {
		
		System.out.println("Novo manager de usuarios");
		listaUsuarios = new ArrayList<Usuario>();
	}
	
	public void cadastraUsuario(String nome, String sobrenome, String dataDeNascimento, String cpf, String telefone,
			String celular, String email, String password) {
		
		Usuario usuario = new Usuario(nome, sobrenome, dataDeNascimento, cpf, telefone, celular, email, password);
		listaUsuarios.add(usuario);
	}
}
