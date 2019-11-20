package modelo.entidad;

/**
 * Clase utilizada para insertar los datos de valoracion en la base de datos
 * 
 * @author tofol
 *
 */
public class Valoracion {

	private String valoracion = "";
	private String idJuego = "";
	private String idUsuario = "";

	/**
	 * Constructor de la clase Valoracion
	 * 
	 * @param valoracion Valoracion del usuario
	 * @param idJuego    Id del juego valorado
	 * @param idUsuario  Id del usuario que lo valora
	 */
	public Valoracion(String valoracion, String idJuego, String idUsuario) {
		this.valoracion = valoracion;
		this.idJuego = idJuego;
		this.idUsuario = idUsuario;
	}

	public String getIdJuego() {
		return idJuego;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public String getValoracion() {
		return valoracion;
	}
}
