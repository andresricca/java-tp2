package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Personaje;
import negocio.CtrlCombate;
import utils.ApplicationException;

/**
 * Servlet implementation class Juego
 */
@WebServlet("/seleccionPersonaje")
public class SeleccionPersonaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionPersonaje() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombreJugador1=request.getParameter("Jugador1").trim();
		String nombreJugador2=request.getParameter("Jugador2").trim();
		Personaje jugador1=new Personaje();
		Personaje jugador2=new Personaje();
		jugador1.setNombre(nombreJugador1);
		jugador2.setNombre(nombreJugador2);
		
		CtrlCombate ctrl=new CtrlCombate();
		
		try {
			ctrl.nuevoCombate(jugador1, jugador2);
			HttpSession session=request.getSession(true);
			session.setAttribute("ctrlCombate", ctrl);
			request.getRequestDispatcher("juego.jsp").forward(request, response);
		} catch (ApplicationException ae) {
			request.setAttribute("Error", ae.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
