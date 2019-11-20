package vista;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.LogSingleton;

/**
 * Servlet de logout
 * @author tofol
 *
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Hace logout
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		LogSingleton log = LogSingleton.getInstance();
		// Eliminamos la sesión del usuario
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		// Redirigimos a la página principal
		try {
			response.sendRedirect("Main");
		} catch (IOException e) {
			log.getLoggerLogout().error("Se ha producido un error en get Logout: ",e);
		}
	}

}
