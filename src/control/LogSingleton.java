package control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vista.Ficha;
import vista.Login;
import vista.Logout;
import vista.Main;
import vista.Registro;
import vista.ResulBusqueda;

/**
 * Clase singleton para los loggers
 * 
 * @author tofol
 *
 */
public class LogSingleton {
	private static final LogSingleton INSTANCE = new LogSingleton();
	private Logger loggerMain = (Logger) LoggerFactory.getLogger(Main.class);
	private Logger loggerFicha = (Logger) LoggerFactory.getLogger(Ficha.class);
	private Logger loggerLogin = (Logger) LoggerFactory.getLogger(Login.class);
	private Logger loggerRegistro = (Logger) LoggerFactory.getLogger(Registro.class);
	private Logger loggerResulBusqueda = (Logger) LoggerFactory.getLogger(ResulBusqueda.class);
	private Logger loggerLogout = (Logger) LoggerFactory.getLogger(Logout.class);
	private Logger loggerControl = (Logger) LoggerFactory.getLogger(Control.class);

	/**
	 * Constructor privado
	 */
	private LogSingleton() {
	}

	/**
	 * Obtener la conexión
	 * 
	 * @return
	 */
	public static LogSingleton getInstance() {
		return INSTANCE;
	}

	/**
	 * Obtener el logger para Main
	 * 
	 * @return Logger
	 */
	public Logger getLoggerMain() {
		return loggerMain;
	}

	/**
	 * Obtener el logger para Ficha
	 * 
	 * @return Logger
	 */
	public Logger getLoggerFicha() {
		return loggerFicha;
	}

	/**
	 * Obtener el logger para Login
	 * 
	 * @return Logger
	 */
	public Logger getLoggerLogin() {
		return loggerLogin;
	}

	/**
	 * Obtener el logger para Registro
	 * 
	 * @return Logger
	 */
	public Logger getLoggerRegistro() {
		return loggerRegistro;
	}

	/**
	 * Obtener el logger para ResulBusqueda
	 * 
	 * @return Logger
	 */
	public Logger getLoggerResulBusqueda() {
		return loggerResulBusqueda;
	}

	/**
	 * Obtener el logger para Logout
	 * 
	 * @return Logger
	 */
	public Logger getLoggerLogout() {
		return loggerLogout;
	}

	/**
	 * Obtener el logger para Control
	 * 
	 * @return Logger
	 */
	public Logger getLoggerControl() {
		return loggerControl;
	}

}