package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlataformaCRUD {

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
