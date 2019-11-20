package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contiene los métodos CRUD utilizados para la tabla juego
 * @author tofol
 *
 */
public class JuegoCRUD {

	/**
	 * Obtiene generos, plataformas y juegos donde el título del juego contenga el parametro de búsqueda
	 * @param seBusca Parametro de búsqueda
	 * @return Resultado de la query
	 * @throws SQLException
	 */
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
	
	/**
	 * Obtiene el juego por su id
	 * @param idJuego Id del juego
	 * @return Juego
	 * @throws SQLException
	 */
	public static ResultSet selectPorID(String idJuego) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		String query = "SELECT * "
				+ "FROM juego "
				+ "WHERE id="+idJuego;
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
}
