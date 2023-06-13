package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		request.setCharacterEncoding("UTF-8");
		String check = "default";

		if(request.getParameter("a") != null) {
			check = request.getParameter("aa");
		}

		else if(request.getParameter("b") != null) {
			check = request.getParameter("bb");
		}

		else if(request.getParameter("c") != null) {
			if(request.getParameter("fuyo") != null) {
				check = "fuyo";
			}
			if(request.getParameter("kaizyo") != null) {
				check = "kaizyo";
			}
		}

		else if(request.getParameter("f") != null) {
			if(request.getParameter("ra") != null) {
				check = request.getParameter("ra");
			} else {
				check = "dame";
			}
		}

		request.setAttribute("SQ", check);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_success.jsp");
		dispatcher.forward(request, response);
	}
}
