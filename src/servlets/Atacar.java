package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.CtrlCombate;
import utils.ApplicationException;

/**
 * Servlet implementation class Atacar
 */
@WebServlet("/atacar")
public class Atacar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atacar() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlCombate ctrl=(CtrlCombate) request.getSession(false).getAttribute("ctrlCombate");
		try {
			int puntosAtaque=Integer.parseInt(request.getParameter("PuntosAtaque"));
			ctrl.atacar(puntosAtaque);
		} catch (ApplicationException ae) {
			request.setAttribute("Error", ae.getMessage());
		} catch (Exception e) {
			request.setAttribute("Error", "Los puntos de ataque deben ser un numero entero");
		}
		if(ctrl.getFinCombate()) {
			request.setAttribute("Ganador", ctrl.getTurno().toString());
			request.getRequestDispatcher("felicitar.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("juego.jsp").forward(request, response);
		}
		
	}

}
