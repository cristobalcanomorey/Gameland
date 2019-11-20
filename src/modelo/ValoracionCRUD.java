package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.entidad.Valoracion;

/**
 * Contiene los métodos CRUD utilizados para la tabla plataforma
 * 
 * @author tofol
 *
 */
public class ValoracionCRUD {

	/**
	 * Obtiene las valoraciones de un juego
	 * @param idJuego Id del Juego
	 * @return Valoraciones
	 * @throws SQLException
	 */
	public static ResultSet selectPorIdJuego(String idJuego) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM valoracion WHERE idJuego='" + idJuego + "'");
		return rs;
	}

	/**
	 * Obtiene la valoracion que le puso un usuario a un juego
	 * @param idJuego Id del juego
	 * @param idUsuario Id del usuario
	 * @return Valoracion
	 * @throws SQLException
	 */
	public static ResultSet selectPorJuegoYUsuario(String idJuego, String idUsuario) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT * FROM valoracion WHERE idJuego='" + idJuego + "' AND idUsuario='" + idUsuario + "'");
		return rs;
	}

	/**
	 * Introduce una valoracion en la base de datos
	 * @param v Valoracion
	 * @throws SQLException
	 */
	public static void insert(Valoracion v) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		stmt.executeUpdate("INSERT INTO valoracion (idJuego,idUsuario,valoracion) values ('" + v.getIdJuego() + "','"
				+ v.getIdUsuario() + "','" + v.getValoracion() + "')");
	}
}
