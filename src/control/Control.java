package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

import modelo.JDBCSingleton;
import modelo.UsuarioCRUD;
import modelo.entidad.Usuario;
import vista.html.RegistroPage;

public class Control {

	public static RegistroPage crearPagRegistro() {
		RegistroPage reg = new RegistroPage();
		return reg;
	}
	
	public static RegistroPage crearPagRegistro(String excepcion) {
		RegistroPage reg = new RegistroPage();
		reg.setExcepcion(excepcion);
		return reg;
	}

	public static void printResponse(RegistroPage pagina, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(pagina.toString());
	}

	public static void getConexion(String string, String string2) throws ClassNotFoundException, SQLException, NamingException {
		
		JDBCSingleton.setConnection(string, string2);
	}

	public static ResultSet getUsuariosDeBD() throws SQLException {
		ResultSet nombres = null;
		nombres = UsuarioCRUD.selectTodos();
		return nombres;
	}

	public static boolean guardarUsuarioEnBD(String nombre, String usuario, String password, String fPerfil) throws SQLException {
		Usuario u = new Usuario(nombre,usuario,password,fPerfil);
		try {
			UsuarioCRUD.insert(u);
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return true;
		}
	}

}
