package vista.html;

public class RegistroPage extends HtmlConstructor {

	private String abreDiv = "";
	private String cabecera="";
	private String form = "";
	private String cierraDiv = "";
	
	public RegistroPage() {
		super();
		this.abreDiv = "<div id='registro'>";
		this.cabecera = "<h1>Regístrate en Gameland</h1>";
		this.form = "<form action='Registro' method='post'>\r\n" + 
				"                <div>Nombre: <input type='text' name='nombre'> </div>\r\n" + 
				"                <div>Usuario: <input type='text' name='usuario'> </div>\r\n" + 
				"                <div>Contraseña: <input type='password' name='password'> </div>\r\n" + 
				"                <div>Foto: <input type='file' name='avatar' accept='image/png,image/jpeg'> </div>\r\n" + 
				"                <div>\r\n" + 
				"                    <button type='submit'>Registrarse</button>\r\n" + 
				"                    <a href='Main'>Cancelar</a>\r\n" + 
				"                </div>\r\n" + 
				"            </form>";
		this.cierraDiv = "</div>";
		super.setContenidoBody(abreDiv + cabecera + form + cierraDiv);
	}
	
	public String toString() {
		return super.toString();
	}
	
	public String getAbreDiv() {
		return abreDiv;
	}

	public String getCabecera() {
		return cabecera;
	}

	public String getForm() {
		return form;
	}

	public String getCierraDiv() {
		return cierraDiv;
	}
	
	
}
