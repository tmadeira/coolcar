package coolcar;

import java.util.ArrayList;

public class UsuariosManager {
	
	public ArrayList<Usuario> listaUsuarios;
	
	public UsuariosManager() {
		
		System.out.println("Novo manager de usuarios");
		listaUsuarios = new ArrayList<Usuario>();
	}
	
	public void cadastraUsuario(String nome, String sobrenome, String id, String email, String password) {
		
		Usuario usuario = new Usuario(nome, sobrenome, id, email, password);
		listaUsuarios.add(usuario);
	}
}
