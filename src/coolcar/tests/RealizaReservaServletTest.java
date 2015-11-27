package coolcar.tests;

import static org.junit.Assert.fail;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import coolcar.servlets.RealizaReservaServlet;

public class RealizaReservaServletTest extends Mockito {
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  RequestDispatcher rd;

  @InjectMocks
  RealizaReservaServlet servlet;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testReservaValida() {
    when(request.getParameter("idModelo")).thenReturn("1");
    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("local-devolucao")).thenReturn("1");
    when(request.getParameter("dtRetirada")).thenReturn("2017-01-01");
    when(request.getParameter("dtDevolucao")).thenReturn("2017-01-01");
    when(request.getRequestDispatcher("/realizaReserva.jsp")).thenReturn(rd);

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request).getRequestDispatcher("/realizaReserva.jsp");
  }

}
