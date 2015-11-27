package coolcar.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

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
import coolcar.modelos.Modelo;
import coolcar.servlets.RealizaReservaServlet;

public class RealizaReservaServletTest extends Mockito {
  @Captor
  ArgumentCaptor<Modelo> modeloCaptor;
  @Captor
  ArgumentCaptor<Long> numDiasCaptor;
  @Captor
  ArgumentCaptor<BigDecimal> precoTotalCaptor;
  @Captor
  ArgumentCaptor<Filial> filialCaptor;

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
    when(request.getRequestDispatcher("/realizaReserva.jsp")).thenReturn(rd);
  }

  private void preencheComDadosValidos() {
    when(request.getParameter("idModelo")).thenReturn("1");
    when(request.getParameter("local-retirada")).thenReturn("1");
    when(request.getParameter("local-devolucao")).thenReturn("1");
    when(request.getParameter("dtRetirada")).thenReturn("2017-01-01");
    when(request.getParameter("dtDevolucao")).thenReturn("2017-01-01");
  }

  private void posta() {
    try {
      servlet.doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exceção em doPost");
    }
  }

  @Test
  public void testReservaValida() {
    preencheComDadosValidos();
    posta();
    verify(request).getRequestDispatcher("/realizaReserva.jsp");
  }

  @Test
  public void testReservaComModeloInvalido() {
    preencheComDadosValidos();
    when(request.getParameter("idModelo")).thenReturn("x");
    posta();
    verify(request, never()).getRequestDispatcher("/realizaReserva.jsp");
  }

  @Test
  public void testReservaComModeloInexistente() {
    preencheComDadosValidos();
    when(request.getParameter("idModelo")).thenReturn("-1");
    posta();
    verify(request, never()).getRequestDispatcher("/realizaReserva.jsp");
  }

  @Test
  public void testNumDiasEmReservaDeUmDia() {
    preencheComDadosValidos();
    when(request.getParameter("dtRetirada")).thenReturn("2017-01-01");
    when(request.getParameter("dtDevolucao")).thenReturn("2017-01-01");
    posta();
    verify(request).setAttribute(eq("numDias"), eq((long) 1));
  }

  @Test
  public void testNumDiasEmReservaDeTresDias() {
    preencheComDadosValidos();
    when(request.getParameter("dtRetirada")).thenReturn("2017-01-01");
    when(request.getParameter("dtDevolucao")).thenReturn("2017-01-03");
    posta();
    verify(request).setAttribute(eq("numDias"), eq((long) 3));
  }

  @Test
  public void testNumDiasEmReservaDeUmAno() {
    preencheComDadosValidos();
    when(request.getParameter("dtRetirada")).thenReturn("2017-01-01");
    when(request.getParameter("dtDevolucao")).thenReturn("2017-12-31");
    posta();
    verify(request).setAttribute(eq("numDias"), eq((long) 365));
  }

  @Test
  public void testCalculoPrecoDiaria() {
    preencheComDadosValidos();
    when(request.getParameter("dtRetirada")).thenReturn("2017-01-01");
    when(request.getParameter("dtDevolucao")).thenReturn("2017-01-03");
    posta();
    verify(request).setAttribute(eq("modelo"), modeloCaptor.capture());
    verify(request).setAttribute(eq("numDias"), numDiasCaptor.capture());
    verify(request).setAttribute(eq("precoTotal"), precoTotalCaptor.capture());

    Modelo modelo = modeloCaptor.getValue();
    long numDias = numDiasCaptor.getValue();
    BigDecimal precoTotal = precoTotalCaptor.getValue();

    assertTrue(precoTotal.equals(modelo.getDiaria().multiply(new BigDecimal(numDias))));
  }

  @Test
  public void testFilialRetirada() {
    preencheComDadosValidos();
    when(request.getParameter("local-retirada")).thenReturn("2");
    posta();
    verify(request).setAttribute(eq("filialRetirada"), filialCaptor.capture());

    Filial filial = filialCaptor.getValue();
    assertTrue(filial.getId() == 2);
  }

  @Test
  public void testFilialDevolucao() {
    preencheComDadosValidos();
    when(request.getParameter("local-devolucao")).thenReturn("2");
    posta();
    verify(request).setAttribute(eq("filialDevolucao"), filialCaptor.capture());

    Filial filial = filialCaptor.getValue();
    assertTrue(filial.getId() == 2);
  }

}
