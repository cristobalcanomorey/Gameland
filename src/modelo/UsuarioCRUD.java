package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.entidad.Usuario;

/**
 * Contiene los métodos CRUD utilizados para la tabla plataforma
 * @author tofol
 *
 */
public class UsuarioCRUD {

	/**
	 * Obtiene todos los usuarios
	 * @return Todos los usuarios
	 * @throws SQLException
	 */
	public static ResultSet selectTodos() throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
		return rs;
	}

	/**
	 * Introduce un usuario en la base de datos
	 * @param u
	 * @throws SQLException
	 */
	public static void insert(Usuario u) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		stmt.executeUpdate("INSERT INTO usuario (nombre, usuario, passwd, foto, administrador) VALUES ('"+u.getNombre()+"','"+u.getUsuario()+"','"+u.getPassword()+"','"+u.getfPerfil()+"','"+0+"')");
		
	}

}
