package coolcar.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coolcar.bd.BD;
import coolcar.servlets.CadastroReservaServlet;

public class CadastroReservaServletTest {
	
  HttpServletRequest request;
  HttpServletResponse response;
  RequestDispatcher dispatcher;
  PrintWriter out;
  //Sessao s;
	  
  @Before
  public void setUp() {
    request = criaRequestCorreto();
    response = mock(HttpServletResponse.class);
    dispatcher = mock(RequestDispatcher.class);
    out = mock(PrintWriter.class);
    //s = mock(Sessao.class);
  }

  private HttpServletRequest criaRequestCorreto() {
	HttpServletRequest request = mock(HttpServletRequest.class);
	when(request.getParameter("reservaDataDevolucao")).thenReturn("1971-01-01");
	when(request.getParameter("reservaDataRetirada")).thenReturn("1971-01-02");
	when(request.getParameter("reservaModelo")).thenReturn("1");
	when(request.getParameter("reservaFilialRetirada")).thenReturn("3");
	when(request.getParameter("reservaFilialDevolucao")).thenReturn("3");
	when(request.getParameter("reservaValor")).thenReturn("100.00");

	return request;
		  
  }
  
  @Test
  public void testReservaNaoLogado() throws Exception{
	  	
	when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
    when(response.getWriter()).thenReturn(out);
	
	new CadastroReservaServlet().doPost(request, response);
	
	verify(response, never()).sendRedirect("index.jsp");  
  }

@Test
  public void testReservaLogado() throws Exception{
	
	Cookie[] cookies = new Cookie[2];
	Cookie emailCookie, pwdCookie;
	
    emailCookie = new Cookie("userEmail", "es@coolcar.com");
    pwdCookie = new Cookie("userPwd", Integer.toString(Math.abs("senha123".hashCode())));
    
    cookies[0] = emailCookie;
    cookies[1] = pwdCookie;	
    
	when(request.getCookies()).thenReturn(cookies);
	new CadastroReservaServlet().doPost(request, response);
	  
	verify(response).sendRedirect("index.jsp");  
  }

@Test
public void testDataDevolucaoAntesDataRetirada() throws Exception{
	
	Cookie[] cookies = new Cookie[2];
	Cookie emailCookie, pwdCookie;
	
	emailCookie = new Cookie("userEmail", "es@coolcar.com");
	pwdCookie = new Cookie("userPwd", Integer.toString(Math.abs("senha123".hashCode())));
  
	cookies[0] = emailCookie;
	cookies[1] = pwdCookie;

	when(request.getParameter("reservaDataDevolucao")).thenReturn("2011-01-01");
	when(request.getParameter("reservaDataRetirada")).thenReturn("2011-01-10");
	
	when(request.getCookies()).thenReturn(cookies);
	new CadastroReservaServlet().doPost(request, response);
	  
	verify(response, never()).sendRedirect("index.jsp"); 
	}

@Test
public void testDevolucaoLongaData() throws Exception{
	
	Cookie[] cookies = new Cookie[2];
	Cookie emailCookie, pwdCookie;
	
	emailCookie = new Cookie("userEmail", "es@coolcar.com");
	pwdCookie = new Cookie("userPwd", Integer.toString(Math.abs("senha123".hashCode())));
  
	cookies[0] = emailCookie;
	cookies[1] = pwdCookie;

	when(request.getParameter("reservaDataDevolucao")).thenReturn("2011-01-01");
	when(request.getParameter("reservaDataRetirada")).thenReturn("2015-01-01");
	
	when(request.getCookies()).thenReturn(cookies);
	new CadastroReservaServlet().doPost(request, response);
	  
	verify(response, never()).sendRedirect("index.jsp"); 
	}
@After
public void tearDown() {
	 try{
	  	BD bd = new BD();
	    Connection connection = bd.getConnection();
	    
	    String sql = "DELETE FROM Reserva WHERE dt_inicio_reserva = '1971-01-01'";
	    
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.executeUpdate();
	    
	 } catch (Exception e) {
	    e.printStackTrace();
	 }
}

}