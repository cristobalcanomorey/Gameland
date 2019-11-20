package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contiene los métodos CRUD utilizados para la tabla plataforma
 * @author tofol
 *
 */
public class PlataformaCRUD {

	/**
	 * Obtiene la plataforma con esa id
	 * @param idPlataforma Id de plataforma
	 * @return Plataforma
	 * @throws SQLException
	 */
	public static ResultSet selectPorID(String idPlataforma) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		String query = "SELECT * "
				+ "FROM plataforma "
				+ "WHERE id="+idPlataforma;
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

}
