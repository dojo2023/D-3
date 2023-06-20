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
import dao.USER_SQDao;
import model.USER_INFO;

/**
 * Servlet implementation class SQServlet
 */
@WebServlet("/SQServlet")
public class SQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/WebApp_GENDA/LoginServlet");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idf = request.getParameter("idf");
		String sq_id = "";
		//検索処理

		if(idf.equals("1")){
			String en = request.getParameter("en");
			USER_INFODao sqDao = new USER_INFODao();
			USER_INFO user = sqDao.select(en, "");
			if(user.getUser_id().equals("")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}
			sq_id = user.getUser_sq_id();
			request.setAttribute("en", en);
		}
		else if(idf.equals("2")) {
			String id = request.getParameter("id");
			USER_INFODao sqDao = new USER_INFODao();
			USER_INFO user = sqDao.select("", id);
			if(user.getUser_en().equals("")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}
			sq_id = user.getUser_sq_id();
			request.setAttribute("id", id);
		}

		USER_SQDao sq = new USER_SQDao();
		String sq_name = sq.SQ_return(sq_id);


		request.setAttribute("sq_name", sq_name); // sq_id をリクエストスコープに設定
		request.setAttribute("idf", idf); // idf をリクエストスコープに設定

		//結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sq.jsp");
		dispatcher.forward(request, response);
	}
}

