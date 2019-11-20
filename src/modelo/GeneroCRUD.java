package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneroCRUD {

	public static ResultSet selectPorID(String idGenero) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		String query = "SELECT * "
				+ "FROM genero "
				+ "WHERE id="+idGenero;
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

}
