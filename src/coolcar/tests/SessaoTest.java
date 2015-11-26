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
  public void testIsLogged() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetNomeUsuario() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetId() {
    fail("Not yet implemented");
  }
}
