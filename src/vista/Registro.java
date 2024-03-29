package vista;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import control.Control;
import control.LogSingleton;
import modelo.JDBCSingleton;
import vista.html.RegistroPage;

/**
 * Servlet de Registro de usuarios
 * 
 * @author tofol
 *
 */
@WebServlet("/Registro")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "Imagenes";

	/**
	 * Muestra la página de registro
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		LogSingleton log = LogSingleton.getInstance();
		String errorDB = request.getParameter("errorDB");
		String errorUsuario = request.getParameter("errorUsuario");
		String usuarioExiste = request.getParameter("usuarioRepetido");

		RegistroPage pagina = null;

		if (errorDB != null) {
			pagina = Control.crearPagRegistro("errorDB");
		} else if (errorUsuario != null) {
			pagina = Control.crearPagRegistro("errorUsuario");
		} else if (usuarioExiste != null) {
			pagina = Control.crearPagRegistro("usuarioExiste");
		} else {
			pagina = Control.crearPagRegistro(null);
		}
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			log.getLoggerRegistro().error("Se ha producido un error en get Registro: ", e);
		}
	}

	/**
	 * Introduce un nuevo usuario y redirige al servlet Main
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		LogSingleton log = LogSingleton.getInstance();
		// Obtenemos una ruta en el servidor para guardar el archivo
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String fPerfil = "";

		JDBCSingleton.getInstance();
		boolean encontrado = false;
		try {
			Control.getConexion("java:/comp/env", "jdbc/gameland");
			ResultSet usuarios = Control.getUsuariosDeBD();
			if ((!nombre.equals("")) && (!usuario.equals("")) && (!password.equals(""))) {
				while (usuarios.next()) {
					if (usuarios.getString("usuario").equals(usuario)) {
						encontrado = true;
						break;
					}
				}
				if (!encontrado) {

					// Si la ruta no existe la crearemos
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					// Lo utilizaremos para guardar el nombre del archivo
					String fileName = null;

					// Obtenemos el archivo y lo guardamos a disco
					for (Part part : request.getParts()) {
						fileName = Control.getFileNameDeUsuario(part, usuario);
						part.write(uploadPath + File.separator + fileName);
					}

					// Si es una imagen guardamos la ruta en fPerfil
					if (fileName.matches(".+\\.(jpg|png|jpeg)")) {
						fPerfil = fileName;
					}
					boolean errorDB = Control.guardarUsuarioEnBD(nombre, usuario, password, fPerfil);
					if (!errorDB) {
						HttpSession session = request.getSession(true);
						session.setAttribute("usuario", usuario);
						response.sendRedirect("Main");
					} else {
						response.sendRedirect("Registro?errorDB=si");
					}
				} else {
					response.sendRedirect("Registro?usuarioRepetido=si");
				}
			} else {
				response.sendRedirect("Registro?errorUsuario=si");
			}

		} catch (ClassNotFoundException | SQLException | NamingException e) {
			log.getLoggerRegistro().error("Se ha producido un error en post Registro: ", e);
		} catch (IllegalStateException | IOException | ServletException e) {
			log.getLoggerRegistro().error("Se ha producido un error de imagen de perfil en post Registro: ", e);
		}
	}

}
