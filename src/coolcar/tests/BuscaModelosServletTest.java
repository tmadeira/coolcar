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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import coolcar.modelos.Filial;
import coolcar.modelos.Veiculo;
import coolcar.servlets.BuscaModelosServlet;

public class BuscaModelosServletTest extends Mockito {
  @Captor
  private ArgumentCaptor<Filial> filial;
  @Captor
  private ArgumentCaptor<ArrayList<Veiculo>> veiculos;

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  RequestDispatcher rd;

  @InjectMocks
  BuscaModelosServlet servlet;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFilialNaoExistente() {
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
    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("tipo-veiculo")).thenReturn("Carro");
    when(request.getRequestDispatcher("/buscaModelos.jsp")).thenReturn(rd);

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request).setAttribute(eq("filial"), filial.capture());
    verify(request).setAttribute(eq("veiculos"), veiculos.capture());

    assertEquals(filial.getValue().getId(), 1);

    for (Veiculo veiculo : veiculos.getValue()) {
      assertEquals(veiculo.getFilialAlojada(), 1);
    }
  }

  @Test
  public void testExistemVeiculos() {
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
    assertTrue(veiculos.getValue().size() > 0);
  }

  @Test
  public void testApenasCarros() throws IOException {
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

  @Test
  public void testArCond() {
    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("tipo-veiculo")).thenReturn("Carro");
    when(request.getParameter("arcond")).thenReturn("1");
    when(request.getRequestDispatcher("/buscaModelos.jsp")).thenReturn(rd);

    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }

    verify(request).setAttribute(eq("veiculos"), veiculos.capture());

    for (Veiculo veiculo : veiculos.getValue()) {
      assertTrue(veiculo.getCarro().getCarac().getArCond());
    }
  }

}
