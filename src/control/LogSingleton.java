package control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vista.AddJuego;
import vista.DelJuego;
import vista.EditJuego;
import vista.Ficha;
import vista.Gestion;
import vista.Login;
import vista.Logout;
import vista.Main;
import vista.Registro;
import vista.ResulBusqueda;
import vista.TopGen;
import vista.TopPlat;

public class LogSingleton {
	private static final LogSingleton INSTANCE = new LogSingleton();
	private Logger loggerMain = (Logger) LoggerFactory.getLogger(Main.class);
	private Logger loggerAddJuego = (Logger) LoggerFactory.getLogger(AddJuego.class);
	private Logger loggerDelJuego = (Logger) LoggerFactory.getLogger(DelJuego.class);
	private Logger loggerEditJuego = (Logger) LoggerFactory.getLogger(EditJuego.class);
	private Logger loggerFicha = (Logger) LoggerFactory.getLogger(Ficha.class);
	private Logger loggerGestion = (Logger) LoggerFactory.getLogger(Gestion.class);
	private Logger loggerLogin = (Logger) LoggerFactory.getLogger(Login.class);
	private Logger loggerRegistro = (Logger) LoggerFactory.getLogger(Registro.class);
	private Logger loggerResulBusqueda = (Logger) LoggerFactory.getLogger(ResulBusqueda.class);
	private Logger loggerTopGen = (Logger) LoggerFactory.getLogger(TopGen.class);
	private Logger loggerTopPlat = (Logger) LoggerFactory.getLogger(TopPlat.class);
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

	public Logger getLoggerAddJuego() {
		return loggerAddJuego;
	}

	public Logger getLoggerDelJuego() {
		return loggerDelJuego;
	}

	public Logger getLoggerEditJuego() {
		return loggerEditJuego;
	}

	public Logger getLoggerFicha() {
		return loggerFicha;
	}

	public Logger getLoggerGestion() {
		return loggerGestion;
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

	public Logger getLoggerTopGen() {
		return loggerTopGen;
	}

	public Logger getLoggerTopPlat() {
		return loggerTopPlat;
	}

	public Logger getLoggerLogout() {
		return loggerLogout;
	}

	public Logger getLoggerControl() {
		return loggerControl;
	}

	
	
}