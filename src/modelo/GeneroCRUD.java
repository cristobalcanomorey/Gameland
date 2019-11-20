package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Contiene los métodos CRUD utilizados para la tabla genero
 * 
 * @author tofol
 *
 */
public class GeneroCRUD {

	/**
	 * Seleciona el genero por su ID
	 * 
	 * @param idGenero Id del genero que se busca
	 * @return ResultSet con el genero encontrado
	 * @throws SQLException
	 */
	public static ResultSet selectPorID(String idGenero) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		String query = "SELECT * " + "FROM genero " + "WHERE id=" + idGenero;
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

}
