package coolcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@WebServlet("/ReportaLocalizacaoServlet")
public class ReportaLocalizacaoServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int idVeiculo;
    float lat, lng;

    response.setCharacterEncoding("utf-8");
    PrintWriter out = response.getWriter();

    try {
      System.out.println(request.getParameter("idVeiculo"));
      idVeiculo = Integer.parseInt(request.getParameter("idVeiculo"));
      lat = Float.parseFloat(request.getParameter("lat"));
      lng = Float.parseFloat(request.getParameter("lng"));
    } catch (Exception e) {
      System.out.println("Erro ao parsear latitude e longitude.");
      e.printStackTrace();
      out.println("Erro ao cadastrar localizacao.");
      return;
    }

    MongoClient mongo = new MongoClient();
    @SuppressWarnings("deprecation")
    DB db = mongo.getDB("coolcar");
    DBCollection localizacoes = db.getCollection("localizacoes");
    BasicDBObject entrada = new BasicDBObject();
    entrada.append("idVeiculo", idVeiculo);
    entrada.append("loc", new BasicDBObject().append("lat", lat).append("lng", lng));
    entrada.append("data", new Date());
    localizacoes.insert(entrada);
    mongo.close();
    out.println("Localizacao cadastrada com sucesso.");
  }

}
