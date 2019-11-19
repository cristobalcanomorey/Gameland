package vista.html;

public class DisplayDeUsuario {
	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String nuevoContenidoHeader = "";
	
	public DisplayDeUsuario(String nombre, boolean esAdmin,String foto, String viejaCabecera) {
		this.nuevoContenidoHeader = viejaCabecera;
		this.abreNavBar = "<div id='navBar'><ul>";
		this.contenidoNavBar = "<li>BUSCAR</li>\r\n" + 
				"			<li>Los MEJORES juegos:</li>\r\n" + 
				"			<li><a href='TopGen'>Por género</a></li>\r\n" + 
				"			<li><a href='TopPlat'>Por plataforma</a></li>\r\n";
		if(esAdmin) {
			this.contenidoNavBar += "<li id='admin'><a href='Gestion'>Gestionar juegos</a></li>";
		} 
		
		this.cierraNavBar = "</ul></div>";
		
		String opcionesUsuario = "<div id='opcionesUsuario'>"
				+ "<a href='Login'>Login</a>"
				+ "<a href='Registro'>Registro</a>"
				+ "</div>";
		
		if(nombre == null) {
			nombre = "";
		} else {
			String enlAMain = "<a href='Main?registrado=si'>"; 
			String chConEnlaceAMainCorrecto = enlAMain+"<img src='imgs/logo.png' alt='Gameland'></a>";
			this.nuevoContenidoHeader = chConEnlaceAMainCorrecto;
			opcionesUsuario = "<div id='opcionesUsuario'>"
					+ "<a href='Logout'>Cerrar sesión</a>"
					+ "</div>";
		}
		String nuevaCabecera = viejaCabecera;
		nuevaCabecera += "<div id='usuario'>\r\n" + 
				"		<div>"+nombre+"</div>\r\n" + 
				"		<img src='"+foto+"' alt='perfil'>\r\n" + opcionesUsuario+
				"	    </div>";
		this.nuevoContenidoHeader = nuevaCabecera;
	}

	public String getAbreNavBar() {
		return abreNavBar;
	}

	public String getContenidoNavBar() {
		return contenidoNavBar;
	}

	public String getCierraNavBar() {
		return cierraNavBar;
	}

	public String getNuevoContenidoHeader() {
		return nuevoContenidoHeader;
	}
	
}
