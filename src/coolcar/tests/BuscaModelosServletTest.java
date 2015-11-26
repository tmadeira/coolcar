package coolcar.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import coolcar.modelos.Veiculo;
import coolcar.servlets.BuscaModelosServlet;

public class BuscaModelosServletTest extends Mockito {
  @Captor
  private ArgumentCaptor<ArrayList<Veiculo>> veiculos;

  BuscaModelosServlet servlet;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    servlet = new BuscaModelosServlet();
  }

  @Test
  public void testFilialNaoExistente() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(request.getParameter("local-retirada")).thenReturn("-1");

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request, never()).setAttribute(eq("filial"), any());
  }

  @Test
  public void testFilialCorreta() throws IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher rd = mock(RequestDispatcher.class);

    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("tipo-veiculo")).thenReturn("Carro");
    when(request.getRequestDispatcher("/buscaModelos.jsp")).thenReturn(rd);

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request).setAttribute(eq("filial"), eq(1));
    verify(request).setAttribute(eq("veiculos"), veiculos.capture());

    for (Veiculo veiculo : veiculos.getValue()) {
      assertEquals(veiculo.getFilialAlojada(), 1);
    }
  }

  @Test
  public void testApenasCarros() throws IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher rd = mock(RequestDispatcher.class);

    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("tipo-veiculo")).thenReturn("Carro");
    when(request.getRequestDispatcher("/buscaModelos.jsp")).thenReturn(rd);

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request).setAttribute(eq("veiculos"), veiculos.capture());

    for (Veiculo veiculo : veiculos.getValue()) {
      assertEquals(veiculo.getTipoDoVeiculo(), "Carro");
    }
  }

  @Test
  public void testApenasMotos() throws IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher rd = mock(RequestDispatcher.class);

    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("tipo-veiculo")).thenReturn("Moto");
    when(request.getRequestDispatcher("/buscaModelos.jsp")).thenReturn(rd);

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request).setAttribute(eq("veiculos"), veiculos.capture());

    for (Veiculo veiculo : veiculos.getValue()) {
      assertEquals(veiculo.getTipoDoVeiculo(), "Moto");
    }
  }

}
