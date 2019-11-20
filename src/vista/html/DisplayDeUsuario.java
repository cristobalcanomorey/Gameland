package vista.html;

/**
 * Clase que centraliza el display del usuario en las páginas
 * 
 * @author tofol
 *
 */
public class DisplayDeUsuario {
	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String nuevoContenidoHeader = "";

	/**
	 * Constructor para mostrar o no el usuario, la barra de navegación y la opción
	 * de editar juegos
	 * 
	 * @param nombre        Nombre del usuario
	 * @param esAdmin       True si es administrador
	 * @param foto          Ruta a su foto de perfil
	 * @param viejaCabecera La cabecera por defecto
	 */
	public DisplayDeUsuario(String nombre, boolean esAdmin, String foto, String viejaCabecera) {
		this.abreNavBar = "<div id='navBar'><ul>";
		this.contenidoNavBar = "<li>BUSCAR</li>\r\n" + "			<li>Los MEJORES juegos:</li>\r\n"
				+ "			<li><a href='TopGen'>Por género</a></li>\r\n"
				+ "			<li><a href='TopPlat'>Por plataforma</a></li>\r\n";
		if (esAdmin) {
			this.contenidoNavBar += "<li id='admin'><a href='Gestion'>Gestionar juegos</a></li>";
		}

		this.cierraNavBar = "</ul></div>";

		String opcionesUsuario = "<div id='opcionesUsuario'>" + "<a href='Login'>Login</a>"
				+ "<a href='Registro'>Registro</a>" + "</div>";

		if (nombre == null) {
			nombre = "";
		}
		if (!nombre.equals("")) {
			opcionesUsuario = "<div id='opcionesUsuario'>" + "<a href='Logout'>Cerrar sesión</a>" + "</div>";
		}
		String nuevaCabecera = viejaCabecera;
		nuevaCabecera += "<div id='usuario'>\r\n" + "		<div>" + nombre + "</div>\r\n" + "		<img src='" + foto
				+ "' alt='perfil'>\r\n" + opcionesUsuario + "	    </div>";
		this.nuevoContenidoHeader = nuevaCabecera;
	}

	/**
	 * Obtener abreNavBar
	 */
	public String getAbreNavBar() {
		return abreNavBar;
	}

	/**
	 * Obtener contenidoNavBar
	 */
	public String getContenidoNavBar() {
		return contenidoNavBar;
	}

	/**
	 * Obtener getCierraNavBar
	 */
	public String getCierraNavBar() {
		return cierraNavBar;
	}

	/**
	 * Obtener NuevoContenidoHeader
	 */
	public String getNuevoContenidoHeader() {
		return nuevoContenidoHeader;
	}

}
