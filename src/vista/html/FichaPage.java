package vista.html;

import java.sql.ResultSet;

public class FichaPage  extends HtmlConstructor {

	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String abreFicha = "";
	private String imagenJuego = "";
	private String titulo = "";
	private String genero = "";
	private String anyo = "";
	private String plataforma = "";
	private String valoracion = "";
	private String descripcion = "";
	private String formValorar = "";
	private String cierraFicha = "";
	
	public FichaPage(ResultSet juego, float valPromedio, String nombre, boolean esAdmin, String foto) {
		super();
		DisplayDeUsuario dis = new DisplayDeUsuario(nombre,esAdmin,foto,super.getContenidoHeader());
		this.abreNavBar = dis.getAbreNavBar();
		this.contenidoNavBar = dis.getContenidoNavBar();
		this.cierraNavBar = dis.getCierraNavBar();
		this.abreFicha = "<div id='ficha'>";
		this.imagenJuego = "<img src='Imagenes/juego.png' alt='juego'>";
		super.setContenidoHeader(dis.getNuevoContenidoHeader());
		super.setContenidoBody(abreNavBar+contenidoNavBar+cierraNavBar+abreFicha+imagenJuego+titulo+genero+anyo+plataforma+valoracion+descripcion+formValorar+cierraFicha);
	}

}
