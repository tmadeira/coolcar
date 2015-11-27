package coolcar.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.http.Cookie;

import org.junit.Before;

import coolcar.Sessao;
import coolcar.managers.ClientesPFManager;
import coolcar.modelos.ClientePF;

import org.junit.Test;

public class SessaoTest {
  Sessao s;
  ClientesPFManager manager;
  
  @Before
  public void setUp() {
    s = new Sessao(null);
    manager = new ClientesPFManager();
  }
  
  @Test
  public void testLogInErrado() {
    assertNull(s.logIn("teste@coolcar.teste", "senha123"));
  }

  @Test
  public void testLogInCorreto() {
    assertNotNull(s.logIn("es@coolcar.com", "senha123"));
  }

  @Test
  public void testLogOut() {
    s.logIn("es@coolcar.com", "senha123");

    assertNotNull(s.logOut());
  }

  @Test
  public void testLogOutSemLogIn() {
    assertNotNull(s.logOut());
  }

  @Test
  public void testIsLoggedFalso() {
    assertFalse(s.isLogged());
  }

  @Test
  public void testIsLoggedFalsoDepoisDeLogarEDeslogar() {
    s.logIn("es@coolcar.com", "senha123");
    s.logOut();

    assertFalse(s.isLogged());
  }
  
  @Test
  public void testIsLoggedLoginSenhaErrada() {
    s.logIn("es@coolcar.com", "senhaClaramenteErrada");
    assertFalse(s.isLogged());
  }

  @Test
  public void testIsLoggedLoginEmailErrada() {
    s.logIn("usuarioNaoExistente@coolcar.com", "senha123");
    assertFalse(s.isLogged());
  }

  @Test
  public void testIsLoggedVerdadeiro() {
    assertNotNull(s.logIn("es@coolcar.com", "senha123"));
    assertTrue(s.isLogged());
  }

  @Test
  public void testGetNomeUsuario() {
    s.logIn("es@coolcar.com", "senha123");
    assertEquals(s.getNomeUsuario(), "Epifânia Santos");
  }

  @Test
  public void testGetId() {
    s.logIn("es@coolcar.com", "senha123");
    assertEquals(s.getId(), 7);
  }
  
  @Test
  public void testConstrutorComParametrosCorretos() {
    ClientePF usuario = new ClientePF("es@coolcar.com", "senha123");
    ArrayList<ClientePF> resultados = new ArrayList<ClientePF>();
    resultados.add(usuario);
    Cookie emailCookie = new Cookie("userEmail", "es@coolcar.com");
    Cookie pwdCookie = new Cookie("userPwd", Integer.toString(Math.abs("senha123".hashCode())));
    Cookie[] cookies = {emailCookie, pwdCookie};
    
    s = new Sessao(cookies);
    
    //when(manager.consulta(usuario)).thenReturn(resultados);
    assertTrue(s.isLogged());
  }
  
  @Test
  public void testConstrutorComParametrosErrados() {
    //ClientePF usuario = new ClientePF("teste@teste.coolcar.com", "senha12345");
    Cookie emailCookie = new Cookie("userEmail", "teste@teste.coolcar.com");
    Cookie pwdCookie = new Cookie("userPwd", "senha12345");
    Cookie[] cookies = {emailCookie, pwdCookie};
    
    s = new Sessao(cookies);
        
    //when(manager.consulta(usuario)).thenReturn(new ArrayList<ClientePF>());
    assertFalse(s.isLogged());
  }
}
