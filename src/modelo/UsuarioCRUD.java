package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.entidad.Usuario;

public class UsuarioCRUD {

	public static ResultSet selectTodos() throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
		return rs;
	}

	public static void insert(Usuario u) throws SQLException {
		Statement stmt = null;
		JDBCSingleton.setStatement();
		stmt = JDBCSingleton.getStatement();
		stmt.executeUpdate("INSERT INTO usuario (nombre, usuario, passwd, foto, administrador) VALUES ('"+u.getNombre()+"','"+u.getUsuario()+"','"+u.getPassword()+"','"+u.getfPerfil()+"','"+0+"')");
		
	}

}
