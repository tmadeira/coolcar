package coolcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.managers.UsuariosManager;
import coolcar.modelos.ClientePF;
import coolcar.modelos.Endereco;

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected boolean validaEmailSenha(String email, String email2, String pwd, String pwd2) {
    return (email.equals(email2) && pwd.equals(pwd2));
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String nome, sobrenome, dataDeNascimento, cpf, telefone, celular;
    String email, email2, password, password2;
    nome = sobrenome = dataDeNascimento = cpf = telefone = celular = "";
    email = email2 = password = password2 = "";
    boolean validado = true;

    if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
      nome = request.getParameter("nome");
    }

    if (request.getParameter("sobrenome") != null && !request.getParameter("sobrenome").isEmpty()) {
      sobrenome = request.getParameter("sobrenome");
    }

    if (request.getParameter("dtNascimento") != null && !request.getParameter("dtNascimento").isEmpty()) {
      dataDeNascimento = (String) request.getParameter("dtNascimento");
    }

    if (request.getParameter("cpf") != null && !request.getParameter("cpf").isEmpty()) {
      cpf = request.getParameter("cpf");
    }
    if (request.getParameter("telefone") != null && !request.getParameter("telefone").isEmpty()) {
      telefone = request.getParameter("telefone");
    }
    if (request.getParameter("celular") != null && !request.getParameter("celular").isEmpty()) {
      celular = request.getParameter("celular");
    }

    if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {
      email = request.getParameter("email");
    }
    if (request.getParameter("confirmacaoDeEmail") != null && !request.getParameter("confirmacaoDeEmail").isEmpty()) {
      email2 = request.getParameter("confirmacaoDeEmail");
    }

    if (request.getParameter("pwd") != null && !request.getParameter("pwd").isEmpty()) {
      password = request.getParameter("pwd");
    }
    if (request.getParameter("confirmacaoDePassword") != null
        && !request.getParameter("confirmacaoDePassword").isEmpty()) {
      password2 = request.getParameter("confirmacaoDePassword");
    }

    if (!validaEmailSenha(email, email2, password, password2)) {
      validado = false;
    }

    if (validado) {
      UsuariosManager manager = new UsuariosManager();
      ClientePF usuario = new ClientePF();

      // Campos de Usuario
      usuario.setNome(nome + " " + sobrenome);
      usuario.setEmail(email);
      usuario.setSenha(password);

      Endereco endereco = new Endereco();
      /*
       * TODO: deve cadastrar endereço no formulário e usar setters aqui
       */
      usuario.setEndereco(endereco);

      // Campos de ClientePF
      usuario.setCpf(cpf);
      /*
       * TODO: deve pegar sexo (M ou F) no formulário e usar setter aqui
       */

      DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
      Date dtNascimento;
      try {
        dtNascimento = format.parse(dataDeNascimento);
        usuario.setDtNascimento(dtNascimento);
      } catch (ParseException e) {
        System.out.println("Data de nascimento invalida.");
        e.printStackTrace();
      }

      manager.insere(usuario);

      /*
       * TODO: cadastrar telefones
       */

      response.sendRedirect("contaCriada.jsp");
    } else {
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
      response.setCharacterEncoding("utf-8");
      PrintWriter out = response.getWriter();
      out.println("<font color=red>Email ou senha nao conferem. Por favor, tente novamente!</font>\n");
      rd.include(request, response);
    }

  }
}