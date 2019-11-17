package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import modelo.JDBCSingleton;
import modelo.UsuarioCRUD;
import modelo.entidad.Usuario;
import vista.html.HtmlConstructor;
import vista.html.MainPage;
import vista.html.RegistroPage;

public class Control {

	public static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "desconocido.txt";
	}
	
	/**
	 * Método que devuelve el nombre de usuario si está logeado
	 * 
	 * @param request request del servlet
	 * @return El nombre de usuario si se ha logeado o null si no.
	 */
	public static String getLoggedUser(HttpServletRequest request) {
		String user = null;

		// Comprobamos si tenemos una sesión y obtenemos su nombre de usuario.
		HttpSession session = request.getSession(false);

		if (session != null) {
			user = (String) session.getAttribute("usuario");
		}

		return user;
	}

	public static RegistroPage crearPagRegistro() {
		RegistroPage reg = new RegistroPage();
		return reg;
	}

	public static RegistroPage crearPagRegistro(String excepcion) {
		RegistroPage reg = new RegistroPage();
		reg.setExcepcion(excepcion);
		return reg;
	}

	public static void printResponse(HtmlConstructor pagina, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(pagina.toString());
	}

	public static void getConexion(String string, String string2)
			throws ClassNotFoundException, SQLException, NamingException {
		JDBCSingleton.setConnection(string, string2);
	}

	public static ResultSet getUsuariosDeBD() throws SQLException {
		ResultSet nombres = null;
		nombres = UsuarioCRUD.selectTodos();
		return nombres;
	}

	public static boolean guardarUsuarioEnBD(String nombre, String usuario, String password, String fPerfil)
			throws SQLException {
		Usuario u = new Usuario(nombre, usuario, password, fPerfil);
		try {
			UsuarioCRUD.insert(u);
			return false;
		} catch (SQLException e) {

			e.printStackTrace();
			return true;
		}
	}

	public static MainPage crearPagMain(String registrado, boolean esAdmin, String foto) {
		MainPage pag = new MainPage(registrado,esAdmin,foto);
		return pag;
	}

}
