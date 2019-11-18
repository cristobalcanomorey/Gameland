package vista.html;

import java.sql.ResultSet;
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
	
	public ResulBusquedaPage(ResultSet rs, ArrayList<Float> valoraciones) {
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
		
	}
	
	
	
}
