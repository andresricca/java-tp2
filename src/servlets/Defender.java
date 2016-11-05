package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession session=request.getSession(false);
		try {
			((CtrlCombate) session.getAttribute("ctrlCombate")).defender();
		} catch (ApplicationException ae) {
			request.setAttribute("Error", ae.getMessage());
		}
		request.getRequestDispatcher("juego.jsp").forward(request, response);
	}

}
