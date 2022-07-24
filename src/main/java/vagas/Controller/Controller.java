package vagas.Controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import DAO.ConexaoBD;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vagas.model.Login;


 @WebServlet(urlPatterns = {"/login"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    ConexaoBD BD = new ConexaoBD();
    
    Login lg = new Login();
     
    public Controller() {
    	
      }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url=request.getServletPath();
		
		if(url.equals("/login")) {
			AdicionarLogin(request, response);
			response.sendRedirect("view/login.html");
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	// Método para adicionar os dados de login
	protected void AdicionarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pegando os dados de email e senha da view de login
		String email=request.getParameter("email");
	    String senha=request.getParameter("senha");
	
	   // setando os dados de login
	      lg.setEmail(email);
	      lg.setSenha(senha);
	    
	      //Passando o objeto de login para o método de adicionar login da classe ConexaoBD
	      BD.adicionarLogin(lg);
	      
		System.out.println("o email é ="+email);
		System.out.println("a senha é ="+senha);
		
	}
	

 }
