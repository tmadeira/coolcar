package coolcar;

import javax.servlet.http.Cookie;

import coolcar.managers.ClientesPFManager;
import coolcar.modelos.ClientePF;
import java.util.ArrayList;

public class Sessao {
  private boolean logged;
  private ClientePF usuario; // TODO: alterar esse ClientePF para algo genérico,
                             // pois temos que abrait stnger os ClientesPJ
  private Cookie emailCookie;
  private Cookie pwdCookie;

  public Sessao(Cookie[] cookies) {
    if (cookies != null) {
      String email = "";
      String password = "";
      for (Cookie c : cookies) {
        if (c.getName().equals("userEmail")) {
          email = c.getValue();
          emailCookie = c;
        }
        if (c.getName().equals("userPwd")) {
          password = c.getValue();
          pwdCookie = c;
        }
      }
      verificaECadastraUsuario(email, password);
    } else {
      logged = false;
      usuario = null;
    }
  }

  private void verificaECadastraUsuario(String email, String password) {
    usuario = new ClientePF(email, password);

    ClientesPFManager manager = new ClientesPFManager();
    ArrayList<ClientePF> resultados = manager.consulta(usuario);

    if (resultados.size() != 1) {
      usuario = null;
      logged = false;
    } else {
      usuario = resultados.get(0);
      logged = true;
    }
  }

  public Cookie[] logIn(String email, String password) {

    Cookie[] cookies = new Cookie[2];

    verificaECadastraUsuario(email, Integer.toString(Math.abs(password.hashCode())));
    // verificaECadastraUsuario(email, password);

    if (usuario == null)
      return null;

    emailCookie = new Cookie("userEmail", usuario.getEmail());
    pwdCookie = new Cookie("userPwd", usuario.getSenha());

    cookies[0] = emailCookie;
    cookies[1] = pwdCookie;

    return cookies;
  }

  public Cookie[] logOut() {

    Cookie[] cookies = new Cookie[2];

    logged = false;
    usuario = null;

    cookies[0] = emailCookie;
    cookies[1] = pwdCookie;

    emailCookie = null;
    pwdCookie = null;

    return cookies;

  }

  public boolean isLogged() {
    return logged;
  }

  public String getNomeUsuario() {
    return usuario.getNome();
  }

  public int getId() {
    return usuario.getId();
  }

}
