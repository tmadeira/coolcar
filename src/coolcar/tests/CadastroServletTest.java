package coolcar.tests;

import static org.mockito.Mockito.*;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coolcar.bd.BD;

import org.junit.*;
import javax.servlet.http.*;
import javax.servlet.*;

import coolcar.servlets.CadastroServlet;

public class CadastroServletTest {

  HttpServletRequest request;
  HttpServletResponse response;
  RequestDispatcher dispatcher;
  PrintWriter out;
  
  @Before
  public void setUp() {
    request = criaRequestCorreto();
    response = mock(HttpServletResponse.class);
    dispatcher = mock(RequestDispatcher.class);
    out = mock(PrintWriter.class);
  }
  
  private HttpServletRequest criaRequestCorreto() {
    HttpServletRequest request = mock(HttpServletRequest.class);

    when(request.getParameter("selectbasic")).thenReturn("1");
    when(request.getParameter("nome")).thenReturn("Jonas");
    when(request.getParameter("sobrenome")).thenReturn("da Silva");
    when(request.getParameter("dtNascimento")).thenReturn("1990-12-25");
    when(request.getParameter("cpf")).thenReturn("123.456.789-00");
    when(request.getParameter("telefone")).thenReturn("");
    when(request.getParameter("celular")).thenReturn("(11) 98888-7777");
    when(request.getParameter("email")).thenReturn("teste@coolcar.com");
    when(request.getParameter("confirmacaoDeEmail")).thenReturn("teste@coolcar.com");
    when(request.getParameter("pwd")).thenReturn("0000000000");
    when(request.getParameter("confirmacaoDePassword")).thenReturn("0000000000");
    when(request.getParameter("logradouro")).thenReturn("Rua X");
    when(request.getParameter("complemento")).thenReturn("");
    when(request.getParameter("cep")).thenReturn("00000-000");
    when(request.getParameter("cidade")).thenReturn("São Paulo");
    when(request.getParameter("estado")).thenReturn("MG");
    when(request.getParameter("numero")).thenReturn("1");
    when(request.getParameter("sexo")).thenReturn("F");

    return request;
  }

  @Test
  public void testDadosCorretos() throws Exception {
    new CadastroServlet().doPost(request, response);
    verify(response).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testEmailDeConfirmacaoDiferente() throws Exception {
    when(request.getParameter("email")).thenReturn("abc@coolcar.com");
    when(request.getParameter("confirmacaoDeEmail")).thenReturn("def@coolcar.com");
    when(request.getRequestDispatcher("/cadastro.jsp")).thenReturn(dispatcher);
    when(response.getWriter()).thenReturn(out);

    new CadastroServlet().doPost(request, response);

    verify(request, atLeast(1)).getParameter("email");
    verify(request, atLeast(1)).getParameter("confirmacaoDeEmail");

    verify(response, never()).sendRedirect("contaCriada.jsp");

  }
  
  @Test
  public void testConfirmacaoPasswordDiferente() throws Exception {
	when(request.getParameter("pwd")).thenReturn("0000000000");
	when(request.getParameter("confirmacaoDePassword")).thenReturn("0000000001");
    when(request.getRequestDispatcher("/cadastro.jsp")).thenReturn(dispatcher);
    when(response.getWriter()).thenReturn(out);

    new CadastroServlet().doPost(request, response);

    verify(request, atLeast(1)).getParameter("email");
    verify(request, atLeast(1)).getParameter("confirmacaoDeEmail");

    verify(response, never()).sendRedirect("contaCriada.jsp");

  }

  @Test
  public void testNomeErradoComCaracteresEspeciais() throws Exception {
  when(request.getParameter("nome")).thenReturn("João\\%32^!~`'");
  
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("nome");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testSobrenomeErradoComCaracteresEspeciais() throws Exception {
  when(request.getParameter("sobrenome")).thenReturn("Y1a;ma\"\n\0");
  
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("sobrenome");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testSDataDeNascimentoInvalido() throws Exception {
  when(request.getParameter("dtNascimento")).thenReturn("12-25-1992");
  
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("dtNascimento");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testSDataDeNascimentoInvalidoInexistente() throws Exception {
  when(request.getParameter("dtNascimento")).thenReturn("25-12-2020");
  
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("dtNascimento");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }
  
  @Test
  public void testSDataDeNascimentoInvalidoCaracteresEspeciais() throws Exception {
  when(request.getParameter("dtNascimento")).thenReturn("12!25:2020");
  
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("dtNascimento");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testCpfErrado() throws Exception {
	when(request.getParameter("cpf")).thenReturn("XXX.XXX.XXX-XX");
	
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("cpf");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }
  
  @Test
  public void testSexoErrado() throws Exception {
	when(request.getParameter("sexo")).thenReturn("Y");
	
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("sexo");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }
  
  @Test
  public void testCepErrado() throws Exception {
	when(request.getParameter("cep")).thenReturn("18086-32A");
	
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("cep");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testEstadoErrado() throws Exception {
    when(request.getParameter("estado")).thenReturn("\\!");
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("estado");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testCelularErrado() throws Exception {
    when(request.getParameter("celular")).thenReturn("(as) eceev-wjnw");
    new CadastroServlet().doPost(request, response);
    
    verify(request, atLeast(1)).getParameter("celular");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }

  @Test
  public void testTelefoneErrado() throws Exception {
    when(request.getParameter("telefone")).thenReturn("12345");

    new CadastroServlet().doPost(request, response);

    verify(request, atLeast(1)).getParameter("telefone");
    verify(response, never()).sendRedirect("contaCriada.jsp");
  }
  
  @After
  public void tearDown() {
	 try{
	  	BD bd = new BD();
	    Connection connection = bd.getConnection();
	    
	    String sql = "DELETE FROM Usuario WHERE email like 'teste@coolcar.com'";
	    
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.executeUpdate();
	    
	 } catch (Exception e) {
	    e.printStackTrace();
	 }
  }
}
