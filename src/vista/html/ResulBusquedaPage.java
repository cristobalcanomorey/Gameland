package vista.html;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que crea la página de resultados de búsqueda
 * @author tofol
 *
 */
public class ResulBusquedaPage extends HtmlConstructor {

	private String abreNavBar = "";
	private String contenidoNavBar = "";
	private String cierraNavBar = "";
	private String abreResul = "";
	private String greet = "";
	private String abreTabla = "";
	private String tHead = "";
	private String abreTbody = "";
	private String contenidoTbody = "";
	private String cierraTbody = "";
	private String cierraTabla = "";
	private String cierraResul = "";
	
	/**
	 * Construye el resto de la página ResulBusquedaPage sobre la página base
	 * @param rs Resultados de la búsqueda
	 * @param valoraciones Lista con las valoraciones promedio de cada juego de la búsqueda
	 * @param nombre Nombre de usuario
	 * @param esAdmin True si el usuario es administrador
	 * @param foto Ruta a la foto de perfil
	 * @param seBusca Lo que busca el usuario
	 * @throws SQLException
	 */
	public ResulBusquedaPage(ResultSet rs, ArrayList<Float> valoraciones,String nombre, boolean esAdmin,String foto,String seBusca) throws SQLException {
		super();
		DisplayDeUsuario dis = new DisplayDeUsuario(nombre,esAdmin,foto,super.getContenidoHeader());
		this.abreNavBar = dis.getAbreNavBar();
		this.contenidoNavBar = dis.getContenidoNavBar();
		this.cierraNavBar = dis.getCierraNavBar();
		
		this.abreResul = "<div id='resBusqeda'>";
		this.greet = "<h1>Resultados de búsqueda de \""+seBusca+"\"</h1>";
		this.abreTabla = "<table>";
		this.tHead = "<thead>\r\n" + 
				"                    <tr>\r\n" + 
				"                        <th>Nombre</th>\r\n" + 
				"                        <th>Valoración</th>\r\n" + 
				"                        <th>Género</th>\r\n" + 
				"                        <th>Año</th>\r\n" + 
				"                        <th>Plataforma</th>\r\n" + 
				"                    </tr>\r\n" + 
				"                </thead>";
		this.abreTbody = "<tbody>";
		while(rs.next()) {
			int posicion = rs.getRow();
			this.contenidoTbody += "<tr>";
			this.contenidoTbody += "<td><a href='Ficha?idJuego="+rs.getString("juego.id")+"'>"+rs.getString("juego.titulo")+"</a></td>";
			this.contenidoTbody += "<td>"+valoraciones.get(posicion-1)+"</td>";
			this.contenidoTbody += "<td>"+rs.getString("genero.nombre")+"</td>";
			this.contenidoTbody += "<td>"+rs.getString("juego.anyo")+"</td>";
			this.contenidoTbody += "<td>"+rs.getString("plataforma.nombre")+"</td>";
			this.contenidoTbody += "</tr>";
		}
		this.cierraTbody = "</tbody>";
		this.cierraTabla = "</table>";
		this.cierraResul = "</div>";
		super.setContenidoHeader(dis.getNuevoContenidoHeader());
		super.setContenidoBody(abreNavBar+contenidoNavBar+cierraNavBar+abreResul+greet+abreTabla+tHead+abreTbody+contenidoTbody+cierraTbody+cierraTabla+cierraResul);
	}
	
	
	
}
