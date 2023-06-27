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

		//秘密の質問のリストを取得し、そのデータをlogin.jspへ送っている
		USER_SQDao sq_dao = new USER_SQDao();
		String[] sq_list = {sq_dao.SQ_return("1"), sq_dao.SQ_return("2"), sq_dao.SQ_return("3"), sq_dao.SQ_return("4"), sq_dao.SQ_return("5")};
		request.setAttribute("sq_list", sq_list);
		request.setAttribute("login_idf", "0");

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String login_idf = request.getParameter("login_idf");
		USER_SQDao sq_dao = new USER_SQDao();
		String[] sq_list = {sq_dao.SQ_return("1"), sq_dao.SQ_return("2"), sq_dao.SQ_return("3"), sq_dao.SQ_return("4"), sq_dao.SQ_return("5")};
		request.setAttribute("sq_list", sq_list);

		//ログイン画面での処理をlogin_idfで場合分けし、それに応じて対応している
		//ログインボタンが押された時
		if(login_idf.equals("0")) {
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
				request.setAttribute("err_idf","10");
				request.setAttribute("err_sen","IDまたはパスワードが違います。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		//新規登録の際に何かしらのエラーが生きてlogin.jspへ返されたとき
		else if(!login_idf.equals("0")) {

			String registerName = request.getParameter("registerName");
			String securityQuestion = request.getParameter("securityQuestion");
			String securityAnswer = request.getParameter("securityAnswer");
			request.setAttribute("registerName", registerName);
			request.setAttribute("securityQuestion", securityQuestion);
			request.setAttribute("securityAnswer", securityAnswer);

			//新規登録の際に入力した2つのPWが一致していなかったとき
			if(login_idf.equals("1")) {
				String registerId = request.getParameter("registerId");
				String employeeNumber = request.getParameter("employeeNumber");
				request.setAttribute("registerId", registerId);
				request.setAttribute("employeeNumber", employeeNumber);
			}
			//新規登録の際に入力した社員番号とIDが重複していたとき
			else if(login_idf.equals("2")) {
				String registerPassword = request.getParameter("registerPassword");
				request.setAttribute("registerPassword", registerPassword);
			}
			//新規登録の際に入力した社員番号が重複していたとき
			else if(login_idf.equals("3")) {
				String registerId = request.getParameter("registerId");
				String registerPassword = request.getParameter("registerPassword");
				request.setAttribute("registerId", registerId);
				request.setAttribute("registerPassword", registerPassword);
			}
			//新規登録の際に入力したIDが重複していたとき
			else if(login_idf.equals("4")) {
				String registerPassword = request.getParameter("registerPassword");
				String employeeNumber = request.getParameter("employeeNumber");
				request.setAttribute("registerPassword", registerPassword);
				request.setAttribute("employeeNumber", employeeNumber);
			}

			request.setAttribute("login_idf", login_idf);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}