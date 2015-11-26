package coolcar.tests;

import static org.junit.Assert.*;

import org.junit.Before;

import coolcar.Sessao;

import org.junit.Test;

public class SessaoTest {
  @Before
  public void setUp() {
	  
  }
  
  @Test
  public void testLogInErrado() {
	  Sessao s = new Sessao(null);
    assertNull(s.logIn("teste@coolcar.teste", "senha123"));
  }

  @Test
  public void testLogInCorreto() {
	  Sessao s = new Sessao(null);
    assertNotNull(s.logIn("es@coolcar.com", "senha123"));
  }

  @Test
  public void testLogOut() {
	  Sessao s = new Sessao(null);
    s.logIn("es@coolcar.com", "senha123");

    assertNotNull(s.logOut());
  }

  @Test
  public void testLogOutSemLogIn() {
    Sessao s = new Sessao(null);
    assertNotNull(s.logOut());
  }

  @Test
  public void testIsLoggedFalso() {
    Sessao s = new Sessao(null);
    assert(s.isLogged() == false);
  }

  @Test
  public void testIsLoggedFalsoDepoisDeLogarEDeslogar() {
    Sessao s = new Sessao(null);
    s.logIn("es@coolcar.com", "senha123");
    s.logOut();

    assert(s.isLogged() == false);
  }
  
  @Test
  public void testIsLoggedLoginSenhaErrada() {
    Sessao s = new Sessao(null);
    s.logIn("es@coolcar.com", "senhaClaramenteErrada");
    assert(s.isLogged() == false);
  }

  @Test
  public void testIsLoggedLoginEmailErrada() {
    Sessao s = new Sessao(null);
    s.logIn("usuarioNaoExistente@coolcar.com", "senha123");
    assert(s.isLogged() == false);
  }

  @Test
  public void testIsLoggedVerdadeiro() {
    Sessao s = new Sessao(null);
    assertNotNull(s.logIn("es@coolcar.com", "senha123"));
    assert(s.isLogged() == true);
  }

  @Test
  public void testGetNomeUsuario() {
    Sessao s = new Sessao(null);
    assertNotNull(s.logIn("es@coolcar.com", "senha123"));
    assert(s.getNomeUsuario() == "es@coolcar.com");
  }

  @Test
  public void testGetId() {
    Sessao s = new Sessao(null);
    assertNotNull(s.logIn("es@coolcar.com", "senha123"));
    assert(s.getId() == 7);
  }
}
