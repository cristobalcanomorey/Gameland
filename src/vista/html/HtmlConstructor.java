package vista.html;

/**
 * Crea la base sobre la que se construirán el resto de páginas
 * @author tofol
 *
 */
public class HtmlConstructor {
	
	private String head = "";
	private String abreBody = "";
	private String abreHeader = "";
	private String contenidoHeader = "";
	private String cierraHeader = "";
	private String contenidoBody = "";
	private String excepcion = "";
	private String footer = "";
	private String cierraBody = "";

	/****
	 * Escribe lo que todas las páginas tienen en común
	 */
	public HtmlConstructor() {
		this.head = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"    <head>\r\n" + 
				"        <title>Gameland</title>\r\n" + 
				"        <meta charset='UTF-8'>\r\n" + 
				"        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n" + 
				"        <link  href='css/style.css' rel='stylesheet' type='text/css' title='Color' />\r\n" + 
				"    </head>";
		this.abreBody = "<body>";
		this.abreHeader = "<div id='header'>";
		this.contenidoHeader = "<a href='Main'>" + 
				"                <img src='imgs/logo.png' alt='Gameland'>\r\n" + 
				"            </a>";
		this.cierraHeader = "</div>";
		this.footer = "<div id='footer'>\r\n" + 
				"            <pre>web creada por: EMPRESA.sa     copyright: 2019</pre>\r\n" + 
				"        </div>";
		this.cierraBody = "</body></html>";
	}

	/**
	 * Concatena todas las variables en orden y las devuelve
	 */
	public String toString() {
		String resul = "";
		resul += head;
		resul += abreBody;
		resul += abreHeader;
		resul += contenidoHeader;
		resul += cierraHeader;
		resul += contenidoBody;
		resul += excepcion;
		resul += footer;
		resul += cierraBody;
		return resul;
	}
	
	/**
	 * Añade un mensaje de error a la página
	 * @param excepcion Mensaje de error
	 */
	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}

	/**
	 * Añade un contenidoBody
	 * @param contenidoBody Contenido del body (sin contar footer ni cabecera)
	 */
	public void setContenidoBody(String contenidoBody) {
		this.contenidoBody = contenidoBody;
	}

	/**
	 * Añade un contenido para la cabecera (no confundir con "<head>")
	 * @param contenidoHeader Cabecera (incluye opciones de usuario y barra de navegación)
	 */
	public void setContenidoHeader(String contenidoHeader) {
		this.contenidoHeader = contenidoHeader;
	}

	/**
	 * Obtener contenido de la cabecera (no confundir con "<head>")
	 * @return Cabecera (incluye opciones de usuario y barra de navegación)
	 */
	public String getContenidoHeader() {
		return contenidoHeader;
	}
	
	
}
