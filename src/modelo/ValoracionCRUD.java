package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ValoracionCRUD {

	public static ResultSet selectPorIdJuego(String idJuego) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM valoracion WHERE idJuego='"+idJuego+"'");
		return rs;
	}
}
