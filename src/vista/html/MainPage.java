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
		DisplayDeUsuario dis = new DisplayDeUsuario(nombre,esAdmin,foto,super.getContenidoHeader());
		this.abreNavBar = dis.getAbreNavBar();
		this.contenidoNavBar = dis.getContenidoNavBar();
		this.contenidoNavBar += dis.getContenidoNavBar();
		this.cierraNavBar = dis.getCierraNavBar();
		
		this.buscador = "<div id='buscador'>\r\n" + 
				"                <img src='imgs/logo.png' alt='Gameland'>\r\n" + 
				"		<h1>Gameland</h1>\r\n" + 
				"		<form action='ResulBusqueda' method='get'>\r\n" + 
				"			<input type='text' name='busqueda'>\r\n" + 
				"			<button type='submit'>Buscar</button>\r\n" + 
				"		</form>\r\n" + 
				"	</div>";
		
		super.setContenidoHeader(dis.getNuevoContenidoHeader());
		super.setContenidoBody(abreNavBar + contenidoNavBar + cierraNavBar + buscador);
	}
}
