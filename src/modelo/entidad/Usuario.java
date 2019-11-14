package modelo.entidad;

public class Usuario {

	private String nombre;
	private String usuario;
	private String password;
	private String fPerfil;
	
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
