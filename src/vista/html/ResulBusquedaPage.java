package vista.html;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ResulBusquedaPage(ResultSet rs, ArrayList<Float> valoraciones,String nombre, boolean esAdmin) throws SQLException {
		super();
		this.abreNavBar = "<div id='navBar'><ul>";
		this.contenidoNavBar = "<li>BUSCAR</li>\r\n" + 
				"			<li>Los MEJORES juegos:</li>\r\n" + 
				"			<li><a href='TopGen'>Por género</a></li>\r\n" + 
				"			<li><a href='TopPlat'>Por plataforma</a></li>\r\n";
		if(esAdmin) {
			this.contenidoNavBar += "<li id='admin'><a href='Gestion'>Gestionar juegos</a></li>";
		} 
		
		this.cierraNavBar = "</ul></div>";
		this.abreResul = "<div id='resBusqeda'>";
		this.greet = "<h1>Resultados de búsqueda</h1>";
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
			this.contenidoTbody += "<td>"+rs.getString("juego.titulo")+"</td>";
			this.contenidoTbody += "<td>"+valoraciones.get(posicion-1)+"</td>";
			this.contenidoTbody += "<td>"+rs.getString("genero.nombre")+"</td>";
			this.contenidoTbody += "<td>"+rs.getString("juego.anyo")+"</td>";
			this.contenidoTbody += "<td>"+rs.getString("plataforma.nombre")+"</td>";
			this.contenidoTbody += "</tr>";
		}
		this.cierraTbody = "</tbody>";
		this.cierraTabla = "</table>";
		this.cierraResul = "</div>";
		super.setContenidoBody(abreNavBar+contenidoNavBar+cierraNavBar+abreResul+greet+abreTabla+tHead+abreTbody+contenidoTbody+cierraTbody+cierraTabla+cierraResul);
	}
	
	
	
}
