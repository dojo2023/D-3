package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.USER_INFODao;

/**
 * Servlet implementation class IDServlet
 */
@WebServlet("/PWResetServlet")
public class PWResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PWResetServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 決定した個人情報を表示するページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/INFODisplay.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // フォームからの入力値を取得
	        String user_id = request.getParameter("user_id");
	        String newPassword = request.getParameter("newPassword");

	        // データベース更新の処理
	        USER_INFODao userDao = new USER_INFODao();
	        boolean success = userDao.updatePassword(user_id, newPassword);

	        if (success) {
	            // データベースの更新が成功した場合の処理
	            response.getWriter().println("パスワードの更新に成功しました");
	        } else {
	            // データベースの更新が失敗した場合の処理
	            response.getWriter().println("パスワードの更新に失敗しました");
	        }
	    }

}
