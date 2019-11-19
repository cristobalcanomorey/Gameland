package vista.html;

public class RegistroPage extends HtmlConstructor {

	private String abreDiv = "";
	private String cabecera = "";
	private String form = "";
	private String cierraDiv = "";

	public RegistroPage() {
		super();
		this.abreDiv = "<div id='registro'>";
		this.cabecera = "<h1>Regístrate en Gameland</h1>";
		this.form = "<form action='Registro' method='post' enctype='multipart/form-data'>\r\n"
				+ "                <div>Nombre: <input type='text' name='nombre'> </div>\r\n"
				+ "                <div>Usuario: <input type='text' name='usuario'> </div>\r\n"
				+ "                <div>Contraseña: <input type='password' name='password'> </div>\r\n"
				+ "                <div>Foto: <input type='file' name='avatar' accept='image/png,image/jpeg,image/jpg'> </div>\r\n"
				+ "                <div>\r\n" + "                    <button type='submit'>Registrarse</button>\r\n"
				+ "                    <a href='Main'>Cancelar</a>\r\n" + "                </div>\r\n"
				+ "            </form>";
		this.cierraDiv = "</div>";
		super.setContenidoBody(abreDiv + cabecera + form + cierraDiv);
	}

	@Override
	public void setExcepcion(String excepcion) {
		switch (excepcion) {
			case "errorDB":
				super.setExcepcion("<p class='excepcion'>Ha ocurrido un error en la base de datos</p>");
				break;
			case "errorUsuario":
				super.setExcepcion("<p class='excepcion'>No puedes dejar campos en blanco</p>");
				break;
			case "usuarioExiste":
				super.setExcepcion("<p class='excepcion'>El nombre de usuario ya existe</p>");
				break;
		}
	}

}
