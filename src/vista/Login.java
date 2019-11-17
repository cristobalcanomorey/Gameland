package vista;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Control;
import control.LogSingleton;
import vista.html.LoginPage;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		LogSingleton log = LogSingleton.getInstance();
		String errorDB = request.getParameter("errorDB");
		String errorUsuario = request.getParameter("errorUsuario");
		String usuarioNoExiste = request.getParameter("usuarioNoExiste");
		
		LoginPage pag = null;
		
		if(errorDB != null) {
			pag = Control.crearPagLogin("errorDB");
		} else if(errorUsuario != null) {
			pag = Control.crearPagLogin("errorUsuario");
		} else if(usuarioNoExiste != null) {
			pag = Control.crearPagLogin("usuarioNoExiste");
		} else {
			pag = Control.crearPagLogin(null);
		}
		try {
			Control.printResponse(pag, response);
		} catch (IOException e) {
			log.getLoggerLogin().error("Se ha producido un error en get Login: ",e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){

	}

}
