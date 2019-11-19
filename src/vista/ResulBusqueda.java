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
import vista.html.ResulBusquedaPage;

@WebServlet("/ResulBusqueda")
public class ResulBusqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		LogSingleton log = LogSingleton.getInstance();
		String seBusca = request.getParameter("busqueda");
		if(seBusca != null) {
			try {
				JDBCSingleton.getInstance();
				Control.getConexion("java:/comp/env","jdbc/gameland");
				ResultSet rs = Control.buscaJuegos(seBusca);
				String usuario = Control.getLoggedUser(request);
				ResultSet usuarios = Control.getUsuariosDeBD();
				boolean esAdmin = false;
				while(usuarios.next()) {
					if(usuarios.getString("usuario").equals(usuario) && usuarios.getString("administrador").equals("1")){
						esAdmin = true;
						break;
					}
				}
				ResulBusquedaPage pag = Control.crearResulBusquedaPage(rs,usuario,esAdmin);
				Control.printResponse(pag,response);
			} catch (SQLException | ClassNotFoundException | NamingException | NullPointerException | IOException e) {
				log.getLoggerResulBusqueda().error("Se ha producido un error buscando juegos en get ResulBusqueda: ",e);
			}
		} else {
			try {
				response.sendRedirect("Main");
			} catch (IOException e) {
				log.getLoggerResulBusqueda().error("Se ha producido un error en get ResulBusqueda: ",e);
			}
		}
		
	}


}
