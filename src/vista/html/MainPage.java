package vista.html;

public class MainPage extends HtmlConstructor {

	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String buscador = "";
	
	/***
	 * Añade código html específico de esta página
	 * @param usuario Si hay usuario lo añade en el contenido header que hereda de la clase padre
	 * @param esAdmin Si es true añade el link al servlet AddJuego en el
	 */
	public MainPage(String usuario, boolean esAdmin) {
		super();
	}
}
