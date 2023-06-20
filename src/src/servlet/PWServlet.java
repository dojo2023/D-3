package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.USER_INFODao;
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pw.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



			//入力した質問の答えとuser_idを元に取り出した質問の答えが一致しているか確認し、合っていればそのuser_idをリクエストスコープに格納し、間違っていいればログインページへリダイレクトする
			request.setCharacterEncoding("UTF-8");
			String ans = request.getParameter("answer");
			String id = request.getParameter("id");
			String user_sa = "";
			USER_INFODao saDao = new USER_INFODao();
			USER_INFO user = saDao.select("", id);
			user_sa = user.getUser_sa();

			if (ans.equals(user_sa)) {
				// PW忘れた人用のページにフォワード
				request.setAttribute("id", user.getUser_id());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pwReset.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}
	}

}
