package vista;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Control;
import control.LogSingleton;
import modelo.JDBCSingleton;
import vista.html.MainPage;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		JDBCSingleton.getInstance();
		LogSingleton log = LogSingleton.getInstance();
		String registrado = request.getParameter("registrado");
		String nombre = null;
		String img = "Imagenes/default.jpeg";
		boolean esAdmin = false;
		MainPage pagina = null;
		if(registrado != null) {
			String usuario = Control.getLoggedUser(request);
			try {
				Control.getConexion("java:/comp/env","jdbc/gameland");
				ResultSet usuarios = Control.getUsuariosDeBD();
				while(usuarios.next()) {
					if(usuarios.getString("usuario").equals(usuario)){
						nombre = usuarios.getString("nombre");
						img = "Imagenes/";
						img += usuarios.getString("foto");
						if(usuarios.getString("administrador").equals("1")) {
							esAdmin = true;	
						}
						break;
					}
				}
			} catch (ClassNotFoundException | SQLException | NamingException e) {
				log.getLoggerRegistro().error("Se ha producido un error en get Main: ", e);
			}
			pagina = Control.crearPagMain(nombre, esAdmin, img);
		} else {
			pagina = Control.crearPagMain(null,false,img);
		}
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			log.getLoggerRegistro().error("Se ha producido un error en get Main: ", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}

}
