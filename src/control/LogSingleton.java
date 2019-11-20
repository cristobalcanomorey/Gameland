package control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vista.Ficha;
import vista.Login;
import vista.Logout;
import vista.Main;
import vista.Registro;
import vista.ResulBusqueda;

public class LogSingleton {
	private static final LogSingleton INSTANCE = new LogSingleton();
	private Logger loggerMain = (Logger) LoggerFactory.getLogger(Main.class);
	private Logger loggerFicha = (Logger) LoggerFactory.getLogger(Ficha.class);
	private Logger loggerLogin = (Logger) LoggerFactory.getLogger(Login.class);
	private Logger loggerRegistro = (Logger) LoggerFactory.getLogger(Registro.class);
	private Logger loggerResulBusqueda = (Logger) LoggerFactory.getLogger(ResulBusqueda.class);
	private Logger loggerLogout = (Logger) LoggerFactory.getLogger(Logout.class);
	private Logger loggerControl = (Logger) LoggerFactory.getLogger(Control.class);

	private LogSingleton() {
	}

	public static LogSingleton getInstance() {
		return INSTANCE;
	}

	public Logger getLoggerMain() {
		return loggerMain;
	}

	public Logger getLoggerFicha() {
		return loggerFicha;
	}

	public Logger getLoggerLogin() {
		return loggerLogin;
	}

	public Logger getLoggerRegistro() {
		return loggerRegistro;
	}

	public Logger getLoggerResulBusqueda() {
		return loggerResulBusqueda;
	}

	public Logger getLoggerLogout() {
		return loggerLogout;
	}

	public Logger getLoggerControl() {
		return loggerControl;
	}

}