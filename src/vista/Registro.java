package vista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Control;
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
//	url="jdbc:mysql://localhost:3306/aplicacion?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC" />
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
		String usuario = request.getParameter("nombre");
		String password = request.getParameter("password");
		String fPerfil = "";
		
	}

}
