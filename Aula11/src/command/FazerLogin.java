package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import pacote.Usuario;
import service.UsuarioService;

public class FazerLogin implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("username");
		String senha = request.getParameter("password");

		Usuario usuario = new Usuario();
		usuario.setUsuario(nome);
		usuario.setPassword(senha);
		UsuarioService service = new UsuarioService();
		
		if(service.validar(usuario)){
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
			System.out.println("Logou "+usuario);
			
		} else {
			System.out.println("NÃ£o Logou "+usuario);
			System.out.println("Usuario ou senha incorreto");
			
			request.setAttribute("mensagem", "Login/Senha está errada!");
			HttpSession session = request.getSession();
			session.setAttribute("lista", null);
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
			
			
		}
		response.sendRedirect("index.jsp");
	}
}
