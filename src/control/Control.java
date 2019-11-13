package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import vista.html.RegistroPage;

public class Control {

	public static RegistroPage crearPagRegistro() {
		RegistroPage reg = new RegistroPage();
		return reg;
	}

	public static void printResponse(RegistroPage pagina, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(pagina.toString());
	}

}
