package coolcar.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.managers.FiliaisManager;
import coolcar.managers.ModelosManager;
import coolcar.modelos.Filial;
import coolcar.modelos.Modelo;

@WebServlet("/RealizaReservaServlet")
public class RealizaReservaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final long msPerDay = 1000 * 60 * 60 * 24;

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getParameter("idModelo") == null) {
      System.out.println("ID do modelo nao informado");
      return;
    }
    if (request.getParameter("local-retirada") == null) {
      System.out.println("Local de retirada nao informado");
      return;
    }
    if (request.getParameter("local-devolucao") == null) {
      System.out.println("Local de devolucao nao informado");
      return;
    }
    if (request.getParameter("dtRetirada") == null) {
      System.out.println("Data de retirada nao informada");
      return;
    }
    if (request.getParameter("dtDevolucao") == null) {
      System.out.println("Data de devolucao nao informada");
      return;
    }

    // Pega modelo
    ModelosManager manager = new ModelosManager();
    ArrayList<Modelo> modelos = manager.consulta(Integer.parseInt(request.getParameter("idModelo")));
    if (modelos.size() != 1) {
      System.out.println("Modelo nao encontrado");
      return;
    }
    Modelo modelo = modelos.get(0);

    // Calcula numero de dias
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    long startDateTime, endDateTime;
    try {
      startDateTime = formatter.parse(request.getParameter("dtRetirada")).getTime();
      endDateTime = formatter.parse(request.getParameter("dtDevolucao")).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
      return;
    }
    long numDias = (endDateTime - startDateTime) / msPerDay + 1;

    // Calcula preco total
    BigDecimal precoTotal = modelo.getDiaria().multiply(new BigDecimal(numDias));

    // Pega filiais
    ArrayList<Filial> filiais;
    Filial filialRetirada = new Filial(), filialDevolucao = new Filial();
    filialRetirada.setId(Integer.parseInt(request.getParameter("local-retirada")));
    filialDevolucao.setId(Integer.parseInt(request.getParameter("local-devolucao")));
    FiliaisManager filiais_manager = new FiliaisManager();

    filiais = filiais_manager.consulta(filialRetirada);
    if (filiais.size() != 1) {
      System.out.println("Filial de retirada nao encontrada");
      return;
    }
    filialRetirada = filiais.get(0);

    filiais = filiais_manager.consulta(filialDevolucao);
    if (filiais.size() != 1) {
      System.out.println("Filial de devolucao nao encontrada");
      return;
    }
    filialDevolucao = filiais.get(0);

    // Preenche atributos e encaminha para realizaReserva.jsp
    request.setAttribute("modelo", modelo);
    request.setAttribute("filialRetirada", filialRetirada);
    request.setAttribute("filialDevolucao", filialDevolucao);
    request.setAttribute("dataRetirada", request.getParameter("dtRetirada"));
    request.setAttribute("dataDevolucao", request.getParameter("dtDevolucao"));
    request.setAttribute("numDias", numDias);
    request.setAttribute("precoTotal", precoTotal);
    RequestDispatcher rd = request.getRequestDispatcher("/realizaReserva.jsp");
    rd.forward(request, response);
  }
}
