package coolcar.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.managers.FiliaisManager;
import coolcar.managers.VeiculosManager;
import coolcar.modelos.Filial;
import coolcar.modelos.Veiculo;

@WebServlet("/BuscaModelosServlet")
public class BuscaModelosServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private Filial getFilial(int filialId) throws Exception {
    FiliaisManager filial_manager = new FiliaisManager();

    Filial filial = new Filial();
    filial.setId(filialId);

    ArrayList<Filial> resultados = filial_manager.consulta(filial);
    if (resultados.size() != 1) {
      throw new Exception("A filial não foi encontrada.");
    }

    return resultados.get(0);
  }

  private ArrayList<Veiculo> buscaCarros(HttpServletRequest request) {
    Veiculo veiculo_search = new Veiculo();
    veiculo_search.setTipoDoVeiculo(request.getParameter("tipo-veiculo"));
    veiculo_search.setFilialAlojada(Integer.parseInt(request.getParameter("local-retirada")));
    veiculo_search.setStatus("Disponível");

    if (request.getParameter("arcond") != null)
      veiculo_search.getCarro().getCarac().setArCond(true);
    else
      veiculo_search.getCarro().getCarac().setArCond(false);
    if (request.getParameter("dirhidri") != null)
      veiculo_search.getCarro().getCarac().setDirHid(true);
    else
      veiculo_search.getCarro().getCarac().setDirHid(false);
    if (request.getParameter("cambauto") != null)
      veiculo_search.getCarro().getCarac().setCambAuto(true);
    else
      veiculo_search.getCarro().getCarac().setCambAuto(false);

    VeiculosManager veiculos_manager = new VeiculosManager();
    return veiculos_manager.consulta(veiculo_search);
  }

  private ArrayList<Veiculo> buscaMotos(HttpServletRequest request) {
    Veiculo veiculo_search = new Veiculo();
    veiculo_search.setTipoDoVeiculo(request.getParameter("tipo-veiculo"));
    veiculo_search.setFilialAlojada(Integer.parseInt(request.getParameter("local-retirada")));
    veiculo_search.setStatus("Disponível");

    VeiculosManager veiculos_manager = new VeiculosManager();
    return veiculos_manager.consulta(veiculo_search);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Filial filialRetirada;

    try {
      filialRetirada = getFilial(Integer.parseInt(request.getParameter("local-retirada")));
    } catch (Exception e) {
      e.printStackTrace();
      // TODO: imprime erro
      return;
    }

    ArrayList<Veiculo> veiculos;

    if (request.getParameter("tipo-veiculo").equals("Carro")) {
      veiculos = buscaCarros(request);
    } else { // tipo == "Moto"
      veiculos = buscaMotos(request);
    }

    request.setAttribute("filial", filialRetirada);
    request.setAttribute("veiculos", veiculos);
    RequestDispatcher rd = getServletContext().getRequestDispatcher("/buscaModelos.jsp");
    rd.forward(request, response);
  }
}
