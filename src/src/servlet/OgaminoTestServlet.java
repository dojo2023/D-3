package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.USER_SQ;

/**
 * Servlet implementation class OgaminoTestServlet
 */
@WebServlet("/OgaminoTestServlet")
public class OgaminoTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OgaminoTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_test.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String sq_id = request.getParameter("SQ");
		USER_SQ SQ = new USER_SQ();
		SQ.setUSER_SQ_ID(sq_id);
		SQ.setUSER_SQ_NAME(SQ);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_success.jsp");
			dispatcher.forward(request, response);
	}
}
