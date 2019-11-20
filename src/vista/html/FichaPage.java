package vista.html;

import java.sql.ResultSet;
import java.sql.SQLException;

import control.Control;

/**
 * Clase que crea la página Ficha
 * 
 * @author tofol
 *
 */
public class FichaPage extends HtmlConstructor {

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

	/**
	 * Construye el resto de la página Ficha sobre la página base
	 * 
	 * @param juego       Juego de la ficha
	 * @param valPromedio Valoración promedio del juego
	 * @param nombre      Nombre del usuario
	 * @param esAdmin     True si el usuario es administrador
	 * @param foto        Ruta a la foto de perfíl
	 * @throws SQLException
	 */
	public FichaPage(ResultSet juego, float valPromedio, String nombre, boolean esAdmin, String foto)
			throws SQLException {
		super();
		juego.next();
		ResultSet gen = Control.getGeneroPorID(juego.getString("idGenero"));
		gen.next();
		ResultSet plat = Control.getPlataformaPorID(juego.getString("idPlataforma"));
		plat.next();
		DisplayDeUsuario dis = new DisplayDeUsuario(nombre, esAdmin, foto, super.getContenidoHeader());
		this.abreNavBar = dis.getAbreNavBar();
		this.contenidoNavBar = dis.getContenidoNavBar();
		this.cierraNavBar = dis.getCierraNavBar();
		this.abreFicha = "<div id='ficha'>";
		this.imagenJuego = "<img src='Imagenes/Juegos/" + juego.getString("foto") + "' alt='juego'>";
		this.titulo = "<p><span style='font-weight:bold;'>Nombre:<span> " + juego.getString("titulo") + "</p>";
		this.genero = "<p><span style='font-weight:bold;'>Genero:<span> " + gen.getString("nombre") + "</p>";
		this.anyo = "<p><span style='font-weight:bold;'>Año:<span> " + juego.getString("anyo") + "</p>";
		this.plataforma = "<p><span style='font-weight:bold;'>Plataforma:<span> " + plat.getString("nombre") + "</p>";
		this.valoracion = "<p><span style='font-weight:bold;'>Valoración:<span> " + valPromedio + "</p>";
		this.descripcion = "<p><span style='font-weight:bold;'>Descripcion:<span> " + juego.getString("descripcion")
				+ "</p>";
		if (nombre == null) {
			nombre = "";
		}
		if (!nombre.equals("")) {
			this.formValorar = "<form action='Ficha' method='post'>\r\n" + "                	<div>\r\n"
					+ "				Valorar:<input type='number' name='valoracion'>\r\n"
					+ "				<input type='submit' value='Valorar'>"
					+ "<input type='hidden' name='idJuego' value='" + juego.getString("id") + "'>" + "</div>\r\n"
					+ "            	</form>";
		}
		this.cierraFicha = "</div>";
		super.setContenidoHeader(dis.getNuevoContenidoHeader());
		super.setContenidoBody(abreNavBar + contenidoNavBar + cierraNavBar + abreFicha + imagenJuego + titulo + genero
				+ anyo + plataforma + valoracion + descripcion + formValorar + cierraFicha);
	}

}
