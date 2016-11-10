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
		
		if(request.getParameter("PuntosAtaque").matches("[0-9]*")) {
			
			try {
				ctrl.atacar(Integer.parseInt(request.getParameter("PuntosAtaque")));
			} catch (ApplicationException ae) {
				request.setAttribute("Error", ae.getMessage());
			}
			
		} else {
			request.setAttribute("Error", "Los puntos de ataque deben ser un numero entero positivo");
		}
		
		if(ctrl.getFinCombate()) {
			request.setAttribute("Ganador", ctrl.getTurno().toString());
			request.getRequestDispatcher("felicitar.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("juego.jsp").forward(request, response);
		}
		
	}

}
