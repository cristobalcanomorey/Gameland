package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JuegoCRUD {

	public static ResultSet selectLikeTitulo(String seBusca) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		String query = "SELECT juego.id, juego.titulo, genero.nombre, juego.anyo, plataforma.nombre "
				+ "FROM juego,genero,plataforma "
				+ "WHERE juego.idGenero = genero.id "
				+ "AND juego.idPlataforma = plataforma.id "
				+ "AND juego.titulo LIKE '%"+seBusca+"%'";
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
}
