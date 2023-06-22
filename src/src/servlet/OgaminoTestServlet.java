package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.USER_INFODao;
import model.LOGIN_USER;
import model.USER_INFO;

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

		String id = "idd";
		HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", new LOGIN_USER(id));
		USER_INFODao userDao = new USER_INFODao();
		USER_INFO user = userDao.select("", id);
		request.setAttribute("userMode", user.getUser_mode_switch());
		request.setAttribute("message", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
        dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		USER_INFODao u_dao = new USER_INFODao();
		USER_INFO result = u_dao.select("ds", "");


		request.setAttribute("SQ", result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_success.jsp");
		dispatcher.forward(request, response);


	}
}
