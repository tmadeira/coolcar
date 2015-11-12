package coolcar;

import javax.servlet.http.Cookie;

import coolcar.modelos.ClientePF;
import coolcar.modelos.Usuario;

public class Session {
  private static Session singleton = new Session();
  private boolean logged;
  private Usuario user;
  private Cookie userCookie;

  public static Session getInstance() {
    return singleton;
  }

  private Session() {
    logged = false;
    user = null;
  }

  public boolean logIn(String email, String password) {
    if (email.equals("teste") && password.equals("123")) {
      userCookie = new Cookie("userEmail", email);
      logged = true;
      user = new ClientePF();
      user.setNome("João da Silva");
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
    user = null;

    return loginCookie;
  }

  public boolean isLogged() {
    return logged;
  }

  public String getNomeUsuario() {
    return user.getNome();
  }

  public Cookie getCookie() {
    return userCookie;
  }

}
