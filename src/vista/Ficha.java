package vista;

import java.io.File;
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
import vista.html.FichaPage;

/**
 * Servlet para mostrar fichas
 * @author tofol
 *
 */
@WebServlet("/Ficha")
public class Ficha extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Método doGet del servlet Ficha
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		LogSingleton log = LogSingleton.getInstance();
		String idJuego = request.getParameter("idJuego");
		String img = "Imagenes"+File.separator+"default.png";
		String nombreUser = "";
		if(idJuego != null) {
			JDBCSingleton.getInstance();
			try {
				Control.getConexion("java:/comp/env","jdbc/gameland");
				String usuario = Control.getLoggedUser(request);
				ResultSet usuarios = Control.getUsuariosDeBD();
				boolean esAdmin = false;
				while(usuarios.next()) {
					if(usuarios.getString("usuario").equals(usuario)){
						if(usuarios.getString("administrador").equals("1")) {
							esAdmin = true;
						}
						img = "Imagenes"+File.separator;
						img += usuarios.getString("foto");
						nombreUser = usuarios.getString("nombre");
						break;
					}
				}
				ResultSet juego = Control.getJuegoPorID(idJuego);
				FichaPage pag = Control.crearFichaPage(juego,nombreUser,esAdmin,img);
				Control.printResponse(pag,response);
			} catch (ClassNotFoundException | SQLException | NamingException | NullPointerException | IOException e) {
				log.getLoggerFicha().error("Se ha producido un error en get Ficha: ",e);
			}
		} else {
			try {
				response.sendRedirect("Main");
			} catch (IOException e) {
				log.getLoggerFicha().error("Se ha producido un error en get Ficha: ",e);
			}
		}
	}
	
	/**
	 * Introduce la nueva valoración de un juego
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		LogSingleton log = LogSingleton.getInstance();
		String valoracion = request.getParameter("valoracion");
		String idJuego = request.getParameter("idJuego");
		String idUsuario = "";
		JDBCSingleton.getInstance();
		try {
			Control.getConexion("java:/comp/env","jdbc/gameland");
			String usuario = Control.getLoggedUser(request);
			ResultSet usuarios = Control.getUsuariosDeBD();
			while(usuarios.next()) {
				if(usuarios.getString("usuario").equals(usuario)){
					idUsuario = usuarios.getString("id");
					break;
				}
			}
			ResultSet valoraciones = Control.getValoracionesDeJuegoPorUsuario(idJuego,idUsuario);
			if(!valoraciones.next()) {
				boolean errorDB = Control.guardarValoracionEnBD(valoracion,idJuego,idUsuario);
				if(!errorDB) {
					response.sendRedirect("Ficha?yaVal=si&idJuego="+idJuego);
				} else {
					response.sendRedirect("Ficha?errorDB=si&idJuego="+idJuego);
				}
			} else {
				response.sendRedirect("Ficha?yaVal=si&idJuego="+idJuego);
			}
		} catch (ClassNotFoundException | SQLException | NamingException | IOException e) {
			log.getLoggerFicha().error("Se ha producido un error en post Ficha: ",e);
		}
		
	}

}
