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
 * Servlet implementation class Defender
 */
@WebServlet("/defender")
public class Defender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Defender() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlCombate ctrl=(CtrlCombate) request.getSession(false).getAttribute("ctrlCombate");
		
		try {
			ctrl.defender();
		} catch (ApplicationException ae) {
			request.setAttribute("Error", ae.getMessage());
		}
		
		request.getRequestDispatcher("juego.jsp").forward(request, response);
	}

}
