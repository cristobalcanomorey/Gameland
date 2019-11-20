package modelo.entidad;

/**
 * Clase utilizada para insertar los datos de usuario en la base de datos
 * @author tofol
 *
 */
public class Usuario {

	private String nombre;
	private String usuario;
	private String password;
	private String fPerfil;
	
	/**
	 * Constructor de la clase Usuario
	 * @param nombre Nombre del usuario
	 * @param usuario Nombre de usuario
	 * @param password Contraseña del usuario
	 * @param fPerfil Nombre de su foto de perfíl
	 */
	public Usuario(String nombre, String usuario, String password, String fPerfil) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = password;
		this.fPerfil = fPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public String getfPerfil() {
		return fPerfil;
	}

	
	
}
