package coolcar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coolcar.managers.UsuariosManager;
import coolcar.modelos.ClientePF;
import coolcar.modelos.ClientePJ;
import coolcar.modelos.Endereco;
import coolcar.modelos.Telefone;
import coolcar.modelos.Usuario;

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected boolean validaEmailSenha(String email, String email2, String pwd, String pwd2) {
    return (email.equals(email2) && pwd.equals(pwd2));
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String nome, sobrenome, cpf, telefone, celular;
    String email, email2, password, password2;
    String logradouro, complemento, cep, cidade, estado;
    String cnpj, tipoPessoa;
    char sexo;
    int numero;
    boolean validado;
    Date dataDeNascimento = null;

    numero = 0;
    nome = sobrenome = cpf = cnpj = telefone = celular = tipoPessoa = "";
    email = email2 = password = password2 = "";
    logradouro = complemento = cep = cidade = estado = "";
    sexo = ' ';
    validado = true;

    if (request.getParameter("selectbasic") != null && !request.getParameter("selectbasic").isEmpty()) {
      tipoPessoa = request.getParameter("selectbasic");
    }
    if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {
      nome = new String(request.getParameter("nome").getBytes("iso-8859-1"), "UTF-8");
    }
    if (request.getParameter("sobrenome") != null && !request.getParameter("sobrenome").isEmpty()) {
      sobrenome = new String(request.getParameter("sobrenome").getBytes("iso-8859-1"), "UTF-8");
    }
    if (request.getParameter("dtNascimento") != null && !request.getParameter("dtNascimento").isEmpty()) {
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      try {
        dataDeNascimento = df.parse(request.getParameter("dtNascimento"));
        System.out.println(request.getParameter("dtNascimento"));
        System.out.println(dataDeNascimento);
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
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
    if (request.getParameter("logradouro") != null && !request.getParameter("logradouro").isEmpty()) {

      logradouro = new String(request.getParameter("logradouro").getBytes("iso-8859-1"), "UTF-8");

    }
    if (request.getParameter("complemento") != null && !request.getParameter("complemento").isEmpty()) {
      complemento = new String(request.getParameter("complemento").getBytes("iso-8859-1"), "UTF-8");
    }
    if (request.getParameter("cep") != null && !request.getParameter("cep").isEmpty()) {
      cep = request.getParameter("cep");
    }
    if (request.getParameter("cidade") != null && !request.getParameter("cidade").isEmpty()) {
      cidade = new String(request.getParameter("cidade").getBytes("iso-8859-1"), "UTF-8");
    }
    if (request.getParameter("estado") != null && !request.getParameter("estado").isEmpty()) {
      estado = request.getParameter("estado");
    }
    if (request.getParameter("numero") != null && !request.getParameter("numero").isEmpty()) {
      numero = Integer.parseInt(request.getParameter("numero"));
    }
    if (request.getParameter("sexo") != null && !request.getParameter("sexo").isEmpty()) {
      sexo = request.getParameter("sexo").charAt(0);

      if (sexo == '1')
        sexo = 'M';
      else
        sexo = 'F';
    }
    if (request.getParameter("cnpj") != null && !request.getParameter("cnpj").isEmpty()) {
      cnpj = request.getParameter("cnpj");
    }

    if (!validaEmailSenha(email, email2, password, password2)) {
      validado = false;
    }

    if (validado) {
      UsuariosManager manager = new UsuariosManager();
      Usuario usuario;
      if (tipoPessoa.equals("1")) {
        usuario = new ClientePF();
        usuario.setNome(nome + " " + sobrenome);
        usuario.setCpf(cpf.substring(0, 3) + cpf.substring(4, 7) + cpf.substring(8, 11) + cpf.substring(12));
        usuario.setSexo(sexo);
        usuario.setDtNascimento(dataDeNascimento);
      } else {
        usuario = new ClientePJ();
        usuario.setNome(nome);
        usuario.setCnpj(cnpj);
      }

      // Campos em comum para PF e PJ
      usuario.setEmail(email);
      usuario.setSenha(password);

      Endereco endereco = new Endereco();
      endereco.setLogradouro(logradouro);
      endereco.setComplemento(complemento);
      endereco.setCep(cep.substring(0, 5) + cep.substring(6));
      endereco.setCidade(cidade);
      endereco.setEstado(estado);
      endereco.setNumero(numero);

      usuario.setEndereco(endereco);

      Telefone tel = new Telefone();
      if (!telefone.equals("")) {
        tel.setDdd(telefone.substring(1, 3));
        tel.setNumero(telefone.substring(5, 9) + telefone.substring(10));
      } else {
        tel.setDdd("");
        tel.setNumero("");
      }
      usuario.setTelefone(tel);

      Telefone cel = new Telefone();
      if (!celular.equals("")) {
        cel.setDdd(celular.substring(1, 3));
        cel.setNumero(celular.substring(5, 10) + celular.substring(11));
      } else {
        cel.setDdd("");
        cel.setNumero("");
      }
      usuario.setCelular(cel);
      System.out.println("Tel " + tel.getNumero() + " Cel " + cel.getNumero());

      if (!manager.insere(usuario)) {
        System.out.println("erro de insercao!");
      }

      response.sendRedirect("contaCriada.jsp");
    } else {
      RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
      response.setCharacterEncoding("utf-8");
      PrintWriter out = response.getWriter();
      // TODO: essa mensagem de erro precisa ser corrigida.
      out.println("<font color=red>Email ou senha nao conferem. Por favor, tente novamente!</font>\n");
      rd.include(request, response);
    }

  }
}