package vista.html;

public class LoginPage extends HtmlConstructor {

	private String abreDiv = "";
	private String greet = "";
	private String formulario = "";
	private String cierraDiv = "";
	
	public LoginPage() {
		super();
		this.abreDiv = "<div id='login'>";
		this.greet = "<h1>Iniciar sesión en Gameland</h1>";
		this.formulario = "<form action='Login' method='post'>\r\n" + 
				"                <div>Usuario: <input type='text' name='usuario'> </div>\r\n" + 
				"                <div>Contraseña: <input type='password' name='password'> </div>\r\n" + 
				"                <div>\r\n" + 
				"                    <button type='submit'>Registrarse</button>\r\n" + 
				"                    <a href='Main'>Cancelar</a>\r\n" + 
				"                </div>\r\n" + 
				"            </form>";
		this.cierraDiv = "</div>";
		super.setContenidoBody(abreDiv+greet+formulario+cierraDiv);
	}
	
	public String toString() {
		return super.toString();
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
		case "usuarioNoExiste":
			super.setExcepcion("<p class='excepcion'>No hay ningún usuario registrado con ese nombre de usuario</p>");
		}
	}
	
}
