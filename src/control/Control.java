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

import modelo.GeneroCRUD;
import modelo.JDBCSingleton;
import modelo.JuegoCRUD;
import modelo.PlataformaCRUD;
import modelo.UsuarioCRUD;
import modelo.ValoracionCRUD;
import modelo.entidad.Usuario;
import modelo.entidad.Valoracion;
import vista.html.FichaPage;
import vista.html.HtmlConstructor;
import vista.html.LoginPage;
import vista.html.MainPage;
import vista.html.RegistroPage;
import vista.html.ResulBusquedaPage;

/**
 * Clase para los métodos de control de datos
 * 
 * @author tofol
 *
 */
public class Control {

	/**
	 * Obtiene el nombre original de la foto de perfíl y lo sibstituye por el nombre
	 * de usuario
	 * 
	 * @param part    Fichero obtenido
	 * @param usuario Nombre de usuario
	 * @return Nombre del fichero modificado
	 */
	public static String getFileNameDeUsuario(Part part, String usuario) {
		String resul = "default.jpeg";
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				resul = content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		String nombreOriginal = resul.substring(0, resul.indexOf("."));
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

	/**
	 * Crea la página de registro
	 * 
	 * @param excepcion Error a mostrar en la página
	 * @return Página de registro
	 */
	public static RegistroPage crearPagRegistro(String excepcion) {
		RegistroPage reg = new RegistroPage();
		if (excepcion != null) {
			reg.setExcepcion(excepcion);
		}
		return reg;
	}

	/**
	 * Dibuja la página
	 * 
	 * @param pagina   Página a dibujar
	 * @param response Respuesta
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public static void printResponse(HtmlConstructor pagina, HttpServletResponse response)
			throws IOException, NullPointerException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(pagina.toString());
	}

	/**
	 * Obtiene la conexión con la base de datos
	 * 
	 * @param initC Contexto inicial
	 * @param envC  Base de datos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public static void getConexion(String initC, String envC)
			throws ClassNotFoundException, SQLException, NamingException {
		JDBCSingleton.setConnection(initC, envC);
	}

	/**
	 * Obtiene los usuarios de la base de datos
	 * 
	 * @return Usuarios
	 * @throws SQLException
	 */
	public static ResultSet getUsuariosDeBD() throws SQLException {
		ResultSet nombres = null;
		nombres = UsuarioCRUD.selectTodos();
		return nombres;
	}

	/***
	 * Guarda los datos de un usuario en la base de datos
	 * 
	 * @param nombre   Nombre del usuario
	 * @param usuario  Nombre de usuario del usuario
	 * @param password Contraseña del usuario
	 * @param fPerfil  Nombre de la foto de perfíl
	 * @return boolean true si hay error, false si no hay error.
	 */
	public static boolean guardarUsuarioEnBD(String nombre, String usuario, String password, String fPerfil) {
		Usuario u = new Usuario(nombre, usuario, password, fPerfil);
		LogSingleton log = LogSingleton.getInstance();
		try {
			UsuarioCRUD.insert(u);
			return false;
		} catch (SQLException e) {
			log.getLoggerControl().error("Se ha producido un error en guardarUsuarioEnBD de Control: ", e);
			return true;
		}
	}

	/**
	 * Guarda una valoracion en la base de datos
	 * 
	 * @param valoracion Valoracion
	 * @param idJuego    Id del juego
	 * @param idUsuario  Id del usuario
	 * @return
	 */
	public static boolean guardarValoracionEnBD(String valoracion, String idJuego, String idUsuario) {
		Valoracion v = new Valoracion(valoracion, idJuego, idUsuario);
		LogSingleton log = LogSingleton.getInstance();
		try {
			ValoracionCRUD.insert(v);
			return false;
		} catch (SQLException e) {
			log.getLoggerControl().error("Se ha producido un error en guardarValoracionEnBD de Control: ", e);
			return true;
		}
	}

	/**
	 * Crea la página Main
	 * 
	 * @param registrado Nombre de usuario
	 * @param esAdmin    True si el usuario es administrador
	 * @param foto       Ruta a su foto de perfil
	 * @return Página para Main
	 */
	public static MainPage crearPagMain(String registrado, boolean esAdmin, String foto) {
		MainPage pag = new MainPage(registrado, esAdmin, foto);
		return pag;
	}

	/**
	 * Crea la página Login
	 * 
	 * @param error Error a mostrar
	 * @return Página para Login
	 */
	public static LoginPage crearPagLogin(String error) {
		LoginPage logpag = new LoginPage();
		if (error != null) {
			logpag.setExcepcion(error);
		}
		return logpag;
	}

	/**
	 * Obtiene la fecha actual
	 * 
	 * @return Fecha actual
	 */
	public static String fechaActual() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'a las' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	/**
	 * Busca juegos en la base de datos cuyo título contenga el parámetro
	 * 
	 * @param seBusca Parámetro a buscar
	 * @return Juegos
	 * @throws SQLException
	 */
	public static ResultSet buscaJuegos(String seBusca) throws SQLException {
		ResultSet rs = null;
		rs = JuegoCRUD.selectLikeTitulo(seBusca);
		return rs;
	}

	/**
	 * Busca las valoraciones de un juego
	 * 
	 * @param idJuego Id del juego
	 * @return Valoraciones
	 * @throws SQLException
	 */
	public static ResultSet buscaValoracionesDeJuego(String idJuego) throws SQLException {
		ResultSet rs = null;
		rs = ValoracionCRUD.selectPorIdJuego(idJuego);
		return rs;
	}

	/**
	 * Calcula el promedio de un grupo de valoraciones
	 * 
	 * @param valoraciones Grupo de valoraciones
	 * @return El promedio
	 * @throws SQLException
	 */
	public static float promedio(ResultSet valoraciones) throws SQLException {
		float resul = 0;
		int numVals = 0;
		if (valoraciones.last()) {
			numVals = valoraciones.getRow();
			valoraciones.beforeFirst();
		}
		while (valoraciones.next()) {
			resul += Float.parseFloat(valoraciones.getString("valoracion"));
		}
		resul = resul / numVals;
		return resul;
	}

	/**
	 * Crea la página para ResulBusqueda
	 * 
	 * @param rs      Resultados
	 * @param nombre  Nombre de usuario
	 * @param esAdmin True si el usuario es administrador
	 * @param foto    Ruta de su foto de perfíl
	 * @param seBusca Lo que ha buscado el usuario
	 * @return Página ResulBusqueda
	 * @throws SQLException
	 */
	public static ResulBusquedaPage crearResulBusquedaPage(ResultSet rs, String nombre, boolean esAdmin, String foto,
			String seBusca) throws SQLException {
		ResulBusquedaPage pag = null;
		ArrayList<Float> valPromedio = new ArrayList<Float>();
		while (rs.next()) {
			String idJuego = rs.getString("juego.id");
			ResultSet vals = buscaValoracionesDeJuego(idJuego);
			valPromedio.add(promedio(vals));
		}
		rs.beforeFirst();
		pag = new ResulBusquedaPage(rs, valPromedio, nombre, esAdmin, foto, seBusca);
		return pag;
	}

	/**
	 * Obtiene un juego de la base de datos por su id
	 * 
	 * @param idJuego Id del juego
	 * @return Juego
	 * @throws SQLException
	 */
	public static ResultSet getJuegoPorID(String idJuego) throws SQLException {
		ResultSet rs = null;
		rs = JuegoCRUD.selectPorID(idJuego);
		return rs;
	}

	/**
	 * Crea la página para Ficha
	 * 
	 * @param juego      Juego
	 * @param nombreUser Nombre de usuario
	 * @param esAdmin    True si es administrador
	 * @param foto       Ruta a la foto de perfíl del usuario
	 * @return Página Ficha
	 * @throws SQLException
	 */
	public static FichaPage crearFichaPage(ResultSet juego, String nombreUser, boolean esAdmin, String foto)
			throws SQLException {
		FichaPage pag = null;
		juego.next();
		ResultSet vals = buscaValoracionesDeJuego(juego.getString("id"));
		float valPromedio = promedio(vals);
		juego.beforeFirst();
		pag = new FichaPage(juego, valPromedio, nombreUser, esAdmin, foto);
		return pag;
	}

	/**
	 * Obtiene un género por su Id
	 * 
	 * @param idGenero Id del genero
	 * @return Genero
	 * @throws SQLException
	 */
	public static ResultSet getGeneroPorID(String idGenero) throws SQLException {
		ResultSet rs = null;
		rs = GeneroCRUD.selectPorID(idGenero);
		return rs;
	}

	/**
	 * Obtiene una plataforma por su Id
	 * 
	 * @param idPlataforma Id de la plataforma
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getPlataformaPorID(String idPlataforma) throws SQLException {
		ResultSet rs = null;
		rs = PlataformaCRUD.selectPorID(idPlataforma);
		return rs;
	}

	/**
	 * Obtiene la valoración de un juego por un usuario
	 * 
	 * @param idJuego   Id del juego
	 * @param idUsuario Id del usuario
	 * @return Valoracion
	 * @throws SQLException
	 */
	public static ResultSet getValoracionDeJuegoPorUsuario(String idJuego, String idUsuario) throws SQLException {
		ResultSet rs = null;
		rs = ValoracionCRUD.selectPorJuegoYUsuario(idJuego, idUsuario);
		return rs;
	}

}
