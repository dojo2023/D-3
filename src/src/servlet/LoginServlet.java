//GENDA
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
//49行目を作った時に一緒に作成、ログインユーザーのモデルをインポート
import model.LOGIN_USER;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

	/**    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
​
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		USER_SQDao sq_dao = new USER_SQDao();
		String[] sq_list = {sq_dao.SQ_return("1"), sq_dao.SQ_return("2"), sq_dao.SQ_return("3"), sq_dao.SQ_return("4"), sq_dao.SQ_return("5")};
		request.setAttribute("sq_list", sq_list);

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String id = request.getParameter("ID");
				String pw = request.getParameter("PW");
		// セッションスコープにIDを格納する
				HttpSession session = request.getSession();
		//ログインのためのクラスの生成
		        session.setAttribute("LOGIN_USER", new LOGIN_USER(id));

	    //以下は全部6/14以降に書き足した分
	    // ログイン処理を行う
				USER_INFODao iDao = new USER_INFODao();
				if (iDao.isLoginOK(id, pw)) {	// ログイン成功
					// トップサーブレットにリダイレクトする
					response.sendRedirect("/WebApp_GENDA/TopServlet");
				}else {
					request.setAttribute("err_sen","IDまたはパスワードが違います。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
					dispatcher.forward(request, response);
				}
	}
}