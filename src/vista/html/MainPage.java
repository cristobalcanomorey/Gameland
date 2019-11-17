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
		if(nombre != null && foto != null) {
			String nuevaCabecera = super.getContenidoHeader();
			nuevaCabecera += "<div id='"+nombre+"'>\r\n" + 
					"		<div>Prueba</div>\r\n" + 
					"		<img src='"+foto+"' alt='perfil'>\r\n" + 
					"	    </div>\r\n" + 
					"	    <div id='opcionesUsuario'>\r\n" + 
					"		<a href='Login'>Login</a>\r\n" + 
					"		<a href='Registro'>Registro</a>\r\n" + 
					"	    </div>";
			super.setContenidoHeader(nuevaCabecera);
			super.setContenidoBody(abreNavBar + contenidoNavBar + cierraNavBar + buscador);
		}
	}
}
