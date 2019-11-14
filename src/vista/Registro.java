package vista;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Control;
import modelo.JDBCSingleton;
import vista.html.RegistroPage;

@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	Al context.xml del vostre tomcat heu d'afegir
//	
//	<Resource name="jdbc/aplicacion" auth="Container"
//	type="javax.sql.DataSource" maxActive="100" maxIdle="30"
//	maxWait="10000" username="tofol" password="1234"
//	driverClassName="com.mysql.cj.jdbc.Driver"
//	url="jdbc:mysql://localhost:3306/gameland?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC" />
//
//	<!-- Alerta que he substituït & per &amp; a la url -->
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		RegistroPage pagina = Control.crearPagRegistro();
		try {
			Control.printResponse(pagina, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String fPerfil = "";
		JDBCSingleton.getInstance();
		boolean encontrado = false;
		try {
			Control.getConexion("java:/comp/env","jdbc/gameland");
			ResultSet usuarios = Control.getUsuariosDeBD();
			if(nombre != null && usuario != null && password != null) {
				while(usuarios.next()) {
					if(usuarios.getString("usuario").equals(usuario)) {
						encontrado = true;
						break;
					}
				}
				if(!encontrado) {
					boolean errorDB = Control.guardarUsuarioEnBD(nombre,usuario,password,fPerfil);
					HttpSession session = request.getSession(true);
					session.setAttribute("usuario", usuario);
					if(!errorDB) {
						response.sendRedirect("Main?registrado=si");
					} else {
						response.sendRedirect("Registro?errorDB=si");
					}
				}
			} else {
				response.sendRedirect("Registro?errorUsuario=si");
			}
			
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			
			e.printStackTrace();
		}
	}

}
