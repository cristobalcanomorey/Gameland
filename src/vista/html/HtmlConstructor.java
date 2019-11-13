package vista.html;

public class HtmlConstructor {
	
	private String head = "";
	private String abreBody = "";
	private String abreHeader = "";
	private String contenidoHeader = "";
	private String cierraHeader = "";
	private String contenidoBody = "";
	private String abreFooter = "";
	private String contenidoFooter = "";
	private String cierraFooter = "";
	private String cierraBody = "";

	/****
	 * escribe lo que todas las páginas tienen en común
	 */
	public HtmlConstructor() {
		this.abreBody = "<body>";
		this.cierraBody = "</body>";
		//etc.
	}

}
