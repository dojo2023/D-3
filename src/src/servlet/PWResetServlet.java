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
import model.USER_INFO;
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
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
	    String newPassword = request.getParameter("newPassword");
	    String pwConfirm = request.getParameter("pwConfirm");

	    // 新しいパスワードと確認用パスワードの一致を確認
	    if (!newPassword.equals(pwConfirm)) {
	        response.getWriter().println("パスワードが一致しません");
	        return;
	    }

	    // データベース更新の処理
	    USER_INFO pwReset = new USER_INFO();//USER_INFOに新しいインスタンスpwResetを作成
	    pwReset.setUser_pw(newPassword);
		USER_INFODao reset = new USER_INFODao();//USER_INFODaoに新しいインスタンスresetを作成
	    boolean success =reset.update(pwReset);

	    if (success) {
	        // データベースの更新が成功した場合の処理
	        response.getWriter().println("パスワードの更新に成功しました");
	    } else {
	        // データベースの更新が失敗した場合の処理
	        response.getWriter().println("パスワードの更新に失敗しました");
	    }
	}
}
