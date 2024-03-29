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
import vista.html.ResulBusquedaPage;

/**
 * Servlet para los resultados de la búsqueda
 * 
 * @author tofol
 *
 */
@WebServlet("/ResulBusqueda")
public class ResulBusqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Muestra los resultados de la búsqueda
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		LogSingleton log = LogSingleton.getInstance();
		String seBusca = request.getParameter("busqueda");
		String img = "Imagenes" + File.separator + "default.png";
		String nombreUser = "";
		if (seBusca != null) {
			try {
				JDBCSingleton.getInstance();
				Control.getConexion("java:/comp/env", "jdbc/gameland");
				ResultSet rs = Control.buscaJuegos(seBusca);
				String usuario = Control.getLoggedUser(request);
				ResultSet usuarios = Control.getUsuariosDeBD();

				boolean esAdmin = false;
				while (usuarios.next()) {
					if (usuarios.getString("usuario").equals(usuario)) {
						if (usuarios.getString("administrador").equals("1")) {
							esAdmin = true;
						}
						img = "Imagenes" + File.separator;
						img += usuarios.getString("foto");
						nombreUser = usuarios.getString("nombre");
						break;
					}
				}
				ResulBusquedaPage pag = Control.crearResulBusquedaPage(rs, nombreUser, esAdmin, img, seBusca);
				Control.printResponse(pag, response);
				if (!seBusca.equals("")) {
					String fecha = Control.fechaActual();
					if (nombreUser.equals("")) {
						log.getLoggerResulBusqueda().debug("Registro de búsqueda [" + fecha + "]:\n\tIP: "
								+ request.getRemoteAddr() + "\n\tTérmino de búsqueda: (\"" + seBusca + "\")");
					} else {
						log.getLoggerResulBusqueda()
								.debug("Registro de búsqueda [" + fecha + "]:\n\tNombre de usuario: " + nombreUser
										+ "\n\tIP: " + request.getRemoteAddr() + "\n\tTérmino de búsqueda: (\""
										+ seBusca + "\")");
					}

				}
			} catch (SQLException | ClassNotFoundException | NamingException | NullPointerException | IOException e) {
				log.getLoggerResulBusqueda().error("Se ha producido un error buscando juegos en get ResulBusqueda: ",
						e);
			}
		} else {
			try {
				response.sendRedirect("Main");
			} catch (IOException e) {
				log.getLoggerResulBusqueda().error("Se ha producido un error en get ResulBusqueda: ", e);
			}
		}

	}

}
