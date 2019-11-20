package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import modelo.JDBCSingleton;
import modelo.JuegoCRUD;
import modelo.UsuarioCRUD;
import modelo.ValoracionCRUD;
import modelo.entidad.Usuario;
import vista.html.FichaPage;
import vista.html.HtmlConstructor;
import vista.html.LoginPage;
import vista.html.MainPage;
import vista.html.RegistroPage;
import vista.html.ResulBusquedaPage;

public class Control {

	public static String getFileNameDeUsuario(Part part, String usuario) {
		String resul = "default.jpeg";
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				resul= content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		String nombreOriginal = resul.substring(0,resul.indexOf("."));
		resul = resul.replace(nombreOriginal, usuario);
		return resul;
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

	public static RegistroPage crearPagRegistro(String excepcion) {
		RegistroPage reg = new RegistroPage();
		if(excepcion != null) {
			reg.setExcepcion(excepcion);
		}
		return reg;
	}

	public static void printResponse(HtmlConstructor pagina, HttpServletResponse response) throws IOException, NullPointerException{
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

	/***
	 * Guarda los datos de un usuario en la base de datos
	 * @param nombre Nombre del usuario
	 * @param usuario Nombre de usuario del usuario
	 * @param password Contraseña del usuario
	 * @param fPerfil Nombre de la foto de perfíl
	 * @return boolean true si hay error, false si no hay error.
	 */
	public static boolean guardarUsuarioEnBD(String nombre, String usuario, String password, String fPerfil){
		Usuario u = new Usuario(nombre, usuario, password, fPerfil);
		LogSingleton log = LogSingleton.getInstance();
		try {
			UsuarioCRUD.insert(u);
			return false;
		} catch (SQLException e) {
			log.getLoggerControl().error("Se ha producido un error en guardarUsuarioEnBD de Control: ",e);
			return true;
		}
	}

	public static MainPage crearPagMain(String registrado, boolean esAdmin, String foto) {
		MainPage pag = new MainPage(registrado,esAdmin,foto);
		return pag;
	}

	public static LoginPage crearPagLogin(String error) {
		LoginPage logpag = new LoginPage();
		if(error!=null) {
			logpag.setExcepcion(error);
		}
		return logpag;
	}
	
	public static String fechaActual() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	public static ResultSet buscaJuegos(String seBusca) throws SQLException {
		ResultSet rs = null;
		rs = JuegoCRUD.selectLikeTitulo(seBusca);
		return rs;
	}
	
	public static ResultSet buscaValoracionesDeJuego(String idJuego) throws SQLException {
		ResultSet rs = null;
		rs = ValoracionCRUD.selectPorIdJuego(idJuego);
		return rs;
	}
	
	public static float promedio(ResultSet valoraciones) throws SQLException {
		float resul = 0;
		int numVals = 0;
		if(valoraciones.last()) {
			numVals = valoraciones.getRow();
			valoraciones.beforeFirst();
		}
		while(valoraciones.next()) {
			resul += Float.parseFloat(valoraciones.getString("valoracion"));
		}
		resul = resul/numVals;
		return resul;
	}

	public static ResulBusquedaPage crearResulBusquedaPage(ResultSet rs,String nombre, boolean esAdmin,String foto) throws SQLException {
		ResulBusquedaPage pag = null;
		ArrayList<Float> valPromedio = new ArrayList<Float>();
		while(rs.next()) {
			String idJuego = rs.getString("juego.id");
			ResultSet vals = buscaValoracionesDeJuego(idJuego);
			valPromedio.add(promedio(vals));
		}
		rs.beforeFirst();
		pag = new ResulBusquedaPage(rs,valPromedio,nombre,esAdmin,foto);
		return pag;
	}

	public static ResultSet getJuegoPorID(String idJuego) throws SQLException {
		ResultSet rs = null;
		rs = JuegoCRUD.selectPorID(idJuego);
		return rs;
	}

	public static FichaPage crearFichaPage(ResultSet juego, String nombreUser, boolean esAdmin, String foto) throws SQLException {
		FichaPage pag = null;
		juego.next();
		ResultSet vals = buscaValoracionesDeJuego(juego.getString("id"));
		float valPromedio = promedio(vals);
		juego.beforeFirst();
		pag = new FichaPage(juego,valPromedio,nombreUser,esAdmin,foto);
		return pag;
	}

}
