package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.entidad.Valoracion;

public class ValoracionCRUD {

	public static ResultSet selectPorIdJuego(String idJuego) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM valoracion WHERE idJuego='"+idJuego+"'");
		return rs;
	}

	public static ResultSet selectPorJuegoYUsuario(String idJuego, String idUsuario) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM valoracion WHERE idJuego='"+idJuego+"' AND idUsuario='"+idUsuario+"'");
		return rs;
	}

	public static void insert(Valoracion v) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		stmt.executeUpdate("INSERT INTO valoracion (idJuego,idUsuario,valoracion) values ('"+v.getIdJuego()+"','"+v.getIdUsuario()+"','"+v.getValoracion()+"')");
	}
}
