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
 * Servlet implementation class PWServlet
 */
@WebServlet("/PWServlet")
public class PWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PWServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PW忘れた人用のページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pwReset.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		if(request.getParameter("submit_button").equals("送信") && request.getParameter("idf").equals("2")) {
			HttpSession session = request.getSession();
			String id = ((LOGIN_USER)session.getAttribute("LOGIN_USER")).getId();

			//入力した質問の答えとuser_idを元に取り出した質問の答えが一致しているか確認し、合っていればそのuser_idをリクエストスコープに格納し、間違っていいればログインページへリダイレクトする
			String ans = request.getParameter("ansewr");
			String user_sa = "";
			USER_INFODao saDao = new USER_INFODao();
			USER_INFO user = saDao.select("", id);
			user_sa = user.getUser_sa() ;

			if (ans == user_sa) {
				// PW忘れた人用のページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pwReset.jsp");
				dispatcher.forward(request, response);
			}
			else {
				response.sendRedirect("login.jsp");
			}
		}
	}

}
