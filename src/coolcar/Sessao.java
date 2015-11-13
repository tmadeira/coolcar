package coolcar;

import javax.servlet.http.Cookie;

import coolcar.managers.ClientePFManager;
import coolcar.modelos.ClientePF;
import java.util.ArrayList;

public class Sessao {
  private static Sessao singleton = new Sessao();
  private boolean logged;
  private ClientePF usuario; // TODO: alterar esse ClientePF para algo genérico, pois temos que abranger os ClientesPJ
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
    ArrayList<ClientePF> resultados = manager.consulta(usuario);
    
    if (resultados.size() != 1)
    	return false;
    
    usuario = resultados.get(0);
    
    userCookie = new Cookie("userEmail", usuario.getEmail());
    logged = true;
   
    return true;
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
