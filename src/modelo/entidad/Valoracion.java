package modelo.entidad;

public class Valoracion {

	private String valoracion = "";
	private String idJuego = "";
	private String idUsuario = "";
	
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
