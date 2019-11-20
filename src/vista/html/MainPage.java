package vista.html;

/**
 * Clase que crea la página principal
 * 
 * @author tofol
 *
 */
public class MainPage extends HtmlConstructor {

	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String buscador = "";

	/***
	 * Construye el resto de la página Main sobre la página base
	 * 
	 * @param nombre  Nombre de usuario
	 * @param esAdmin True si el usuario es administrador
	 * @param foto    Ruta de la foto de perfíl
	 */
	public MainPage(String nombre, boolean esAdmin, String foto) {
		super();
		DisplayDeUsuario dis = new DisplayDeUsuario(nombre, esAdmin, foto, super.getContenidoHeader());
		this.abreNavBar = dis.getAbreNavBar();
		this.contenidoNavBar = dis.getContenidoNavBar();
		this.cierraNavBar = dis.getCierraNavBar();

		this.buscador = "<div id='buscador'>\r\n" + "                <img src='Imagenes/logo.png' alt='Gameland'>\r\n"
				+ "		<h1>Gameland</h1>\r\n" + "		<form action='ResulBusqueda' method='get'>\r\n"
				+ "			<input type='text' name='busqueda'>\r\n"
				+ "			<button type='submit'>Buscar</button>\r\n" + "		</form>\r\n" + "	</div>";

		super.setContenidoHeader(dis.getNuevoContenidoHeader());
		super.setContenidoBody(abreNavBar + contenidoNavBar + cierraNavBar + buscador);
	}
}
