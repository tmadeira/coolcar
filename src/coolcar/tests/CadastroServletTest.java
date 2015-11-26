package coolcar.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import javax.servlet.http.*;

import coolcar.servlets.CadastroServlet;

public class CadastroServletTest{

  @Test
  public void testTelefoneErrado() throws Exception{
	  HttpServletRequest request = mock(HttpServletRequest.class);       
      HttpServletResponse response = mock(HttpServletResponse.class);
      
      when(request.getParameter("telefone")).thenReturn("12345");

      new CadastroServlet().doPost(request, response);
      
      verify(request, atLeast(1)).getParameter("telefone");
      
      verify(response, never()).sendRedirect("contaCriada.jsp");
      
  }

}
