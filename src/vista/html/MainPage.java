package vista.html;

public class MainPage extends HtmlConstructor {

	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String buscador = "";
	
	/***
	 * Añade código html específico de esta página
	 * @param nombre Si hay usuario lo añade en el contenido header que hereda de la clase padre
	 * @param esAdmin Si es true añade el link al servlet AddJuego en el
	 */
	public MainPage(String nombre, boolean esAdmin,String foto) {
		super();
		this.abreNavBar = "<div id='navBar'><ul>";
		this.contenidoNavBar = "<li>BUSCAR</li>\r\n" + 
				"			<li>Los MEJORES juegos:</li>\r\n" + 
				"			<li><a href='TopGen'>Por género</a></li>\r\n" + 
				"			<li><a href='TopPlat'>Por plataforma</a></li>\r\n";
		if(esAdmin) {
			this.contenidoNavBar += "<li id='admin'><a href='Gestion'>Gestionar juegos</a></li>";
		} 
		
		this.cierraNavBar = "</ul></div>";
		this.buscador = "<div id='buscador'>\r\n" + 
				"                <img src='imgs/logo.png' alt='Gameland'>\r\n" + 
				"		<h1>Gameland</h1>\r\n" + 
				"		<form action='ResulBusqueda' method='get'>\r\n" + 
				"			<input type='text' name='busqueda'>\r\n" + 
				"			<button type='submit'>Buscar</button>\r\n" + 
				"		</form>\r\n" + 
				"	</div>";
		String opcionesUsuario = "<div id='opcionesUsuario'>"
				+ "<a href='Login'>Login</a>"
				+ "<a href='Registro'>Registro</a>"
				+ "</div>";
		String enlAMain = "<a href='Main?registrado=si'>"; 
		if(nombre == null) {
			nombre = "";
		} else {
			opcionesUsuario = "<div id='opcionesUsuario'>"
					+ "<a href='Logout'>Cerrar sesión</a>"
					+ "</div>";
		}
		String nuevaCabecera = super.getContenidoHeader();
		nuevaCabecera += "<div id='usuario'>\r\n" + 
				"		<div>"+nombre+"</div>\r\n" + 
				"		<img src='"+foto+"' alt='perfil'>\r\n" + opcionesUsuario+
				"	    </div>";
		super.setContenidoHeader(nuevaCabecera);
		super.setContenidoBody(abreNavBar + contenidoNavBar + cierraNavBar + buscador);
	}
}
