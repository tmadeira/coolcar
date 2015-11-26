package coolcar.tests;

import static org.mockito.Mockito.*;

import org.junit.Test;
import javax.servlet.http.*;

import coolcar.servlets.CadastroServlet;

public class CadastroServletTest {

  private HttpServletRequest criaRequestCorreto() {
    HttpServletRequest request = mock(HttpServletRequest.class);

    when(request.getParameter("selectbasic")).thenReturn("1");
    when(request.getParameter("nome")).thenReturn("João");
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
  public void testTelefoneErrado() throws Exception {
    HttpServletRequest request = criaRequestCorreto();
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getParameter("telefone")).thenReturn("12345");

    new CadastroServlet().doPost(request, response);

    verify(request, atLeast(1)).getParameter("telefone");

    verify(response, never()).sendRedirect("contaCriada.jsp");

  }

  @Test
  public void testEmailDeConfirmacaoDiferente() throws Exception {
    HttpServletRequest request = criaRequestCorreto();
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getParameter("email")).thenReturn("abc@coolcar.com");
    when(request.getParameter("confirmacaoDeEmail")).thenReturn("def@coolcar.com");

    new CadastroServlet().doPost(request, response);

    verify(request, atLeast(1)).getParameter("email");
    verify(request, atLeast(1)).getParameter("confirmacaoDeEmail");

    verify(response, never()).sendRedirect("contaCriada.jsp");

  }

}
