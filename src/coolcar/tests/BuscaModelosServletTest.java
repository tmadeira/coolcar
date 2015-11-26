package coolcar.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import coolcar.servlets.BuscaModelosServlet;

public class BuscaModelosServletTest extends Mockito {

  @Test
  public void testBuscaCarros() throws IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("tipo-veiculo")).thenReturn("Carro");

    try {
      new BuscaModelosServlet().doPost(request, response);
    } catch (Exception e) {
      fail("Exceção em doPost");
    }

    // TODO: verify
  }

}
