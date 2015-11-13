package coolcar;

import javax.servlet.http.Cookie;

import coolcar.managers.ClientePFManager;
import coolcar.modelos.ClientePF;
import coolcar.modelos.Usuario;

public class Sessao {
  private static Sessao singleton = new Sessao();
  private boolean logged;
  private Usuario usuario;
  private Cookie userCookie;

  public static Sessao getInstance() {
    return singleton;
  }

  private Sessao() {
    logged = false;
    usuario = null;
  }

  public boolean logIn(String email, String password) {
	
	usuario = new ClientePF();
	usuario.setEmail(email);
    usuario.setSenha(password);
    
    ClientePFManager manager = new ClientePFManager();
    if (manager.consulta(usuario).size() == 1) {
	    if (email.equals("teste") && password.equals("123")) {
	      userCookie = new Cookie("userEmail", email);
	      logged = true;
	      usuario.setNome("João da Silva");
	      return true;
	    }
	    return false;
    }

  public Cookie logOut(Cookie[] cookies) {

    Cookie loginCookie = null;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("userEmail")) {
          loginCookie = cookie;
          break;
        }
      }
    }

    logged = false;
    usuario = null;

    return loginCookie;
  }

  public boolean isLogged() {
    return logged;
  }

  public String getNomeUsuario() {
    return usuario.getNome();
  }

  public Cookie getCookie() {
    return userCookie;
  }

}
