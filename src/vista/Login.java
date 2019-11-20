package vista;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Control;
import control.LogSingleton;
import modelo.JDBCSingleton;
import vista.html.LoginPage;

/**
 * Servlet para hacer login
 * @author tofol
 *
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Muestra el formulario de login
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		LogSingleton log = LogSingleton.getInstance();
		String errorDB = request.getParameter("errorDB");
		String errorUsuario = request.getParameter("errorUsuario");
		String usuarioNoExiste = request.getParameter("usuarioNoExiste");
		
		LoginPage pag = null;
		
		if(errorDB != null) {
			pag = Control.crearPagLogin("errorInterno");
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

	/**
	 * Hace login
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		LogSingleton log = LogSingleton.getInstance();
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		boolean esAdmin = false;
		boolean loginCorrecto = false;
		
		// Comprobamos que nos han pasado bien los datos
		if((!usuario.equals("")) && (!password.equals(""))) {
			JDBCSingleton.getInstance();
			boolean existe = false;
			try {
				Control.getConexion("java:/comp/env","jdbc/gameland");
				ResultSet usuarios = Control.getUsuariosDeBD();
				while(usuarios.next()) {
					if(usuarios.getString("usuario").equals(usuario)) {
						existe = true;
						if(usuarios.getString("passwd").equals(password)) {
							loginCorrecto = true;
						}
						if(usuarios.getString("administrador").equals("1")) {
							esAdmin = true;	
						}
						break;
					}
				}
				//Guarda el log si es usuario administrador
				if(esAdmin) {
					String fechaActual = Control.fechaActual();
					log.getLoggerLogin().info("Se ha logueado un usuario administrador de nombre: " + usuario
							+"\nDesde la ip:\t"+request.getRemoteAddr()
							+"\nHora de inicio de sesión:\t"+fechaActual);
				}
				if(existe) {
					if(loginCorrecto) {
						HttpSession session = request.getSession(true);
						session.setAttribute("usuario", usuario);
						response.sendRedirect("Main");
					} else {
						response.sendRedirect("Login?errorUsuario=si");
					}
				} else {
					response.sendRedirect("Login?usuarioNoExiste=si");
				}
			} catch (ClassNotFoundException | SQLException | NamingException | IOException |NullPointerException e) {
				log.getLoggerLogin().error("Se ha producido un error en post Login: ",e);
				try {
					response.sendRedirect("Login?errorInterno=si");
				} catch (IOException e1) {
					log.getLoggerLogin().error("Se ha producido un error en post Login: ",e);
				}
			}
			
		} else {
			try {
				response.sendRedirect("Login?errorUsuario=si");
			} catch (IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en post Login: ",e);
			}
		}
	}

}
