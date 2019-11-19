package vista.html;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResulBusquedaPage extends HtmlConstructor {

	private String abreResul = "";
	private String greet = "";
	private String abreTabla = "";
	private String tHead = "";
	private String abreTbody = "";
	private String contenidoTbody = "";
	private String cierraTbody = "";
	private String cierraTabla = "";
	private String cierraResul = "";
	
	public ResulBusquedaPage(ResultSet rs, ArrayList<Float> valoraciones) throws SQLException {
		super();
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
		super.setContenidoBody(abreResul+greet+abreTabla+tHead+abreTbody+contenidoTbody+cierraTbody+cierraTabla+cierraResul);
	}
	
	
	
}
