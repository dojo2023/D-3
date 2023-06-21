package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.POSTERDao;
import dao.REPLYDao;
import model.POSTER;
import model.REPLY;
import model.REPORT;
import model.USER_INFO;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 決定した個人情報を表示するページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reply.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		// 誰がどの状態（一般・一般管理者・ガチ管理者）でログインしているか確認する
		HttpSession session = request.getSession(); //セッションスコープにアクセス
		USER_INFO userInfo = (USER_INFO)session.getAttribute("USER_INFO"); // セッションスコープに格納されているUSER_INFOの値をPOSTER型の変数userInfoに代入
		int userModeSwitch = userInfo.getUser_mode_switch(); // int型の変数userModeSwitchにuserInfoが持っているuser_mode_switchの値を代入

		// 以下の分岐は一般・一般管理者・ガチ管理者のどの状態で動かしているかで分岐
		if(userModeSwitch == 1 || userModeSwitch == 2) {
			// 掲示板画面から来た場合
			// 投稿内容を受け取る
			POSTERDao pDao = new POSTERDao(); // POSTERDaoを使えるようにする（インスタンス化）
			POSTER poster = (POSTER)session.getAttribute("POSTER"); // セッションスコープに格納されているPOSTERの値をPOSTER型の変数posterに代入
			int poster_id = poster.getPOSTER_ID(); // 変数posterがもつPOSTER＿IDを取得し、int型の変数poster_idに代入
			List<POSTER> posterList = pDao.select(poster_id, 0); // poster_idをを利用し、pDaoのselectでposter_idの値と同じ値を持つPOSTER＿IDのリストを検索・取得し、変数posterListに代入

			// 受け取った投稿内容（posterList）を属性名posterListとしてリクエストスコープに格納する
			// (reply.jspで利用するため）
			request.setAttribute("posterList", posterList);

			// 返信内容を受け取る
			REPLYDao rDao = new REPLYDao(); // REPLYDaoを使えるようにする（インスタンス化）
			List<REPLY> replyList = rDao.select(0, poster_id); // poster_idを利用し、rDaoのselectでposter_idの値と同じ値を持つPOSTER＿IDのリストを検索・取得し、変数replyListに格納

			// 取得した返信内容（replyList）を属性名replyListとしてリクエストスコープに格納する
			// (reply.jspで利用するため）
			request.setAttribute("replyList", replyList);

		}

		else if(userModeSwitch == 3) {
			// 通報画面から来た場合
			// 通報された投稿の内容を受け取る
			POSTERDao pDao = new POSTERDao(); // POSTERDaoを使えるようにする（インスタンス化）
			REPORT report = (REPORT)session.getAttribute("REPORT"); // REPORT型の変数reportにREPORTの値を代入
			int poster_id = report.getPOSTER_ID(); // reportの持っているPOSTER_IDを取得し、変数poster_idに代入
			List<POSTER> posterList = pDao.select(poster_id, 0); // poster_idをを利用し、pDaoのselectでposter_idの値と同じ値を持つPOSTER＿IDのリストを検索・取得し、変数posterListに代入

			// 取得した内容（posterList）をリクエストスコープに格納
			// （reply.jspで利用するため）
			request.setAttribute("posterList", posterList);

			// 返信内容を受け取る
			REPLYDao rDao = new REPLYDao(); // REPLYDaoを使えるようにする（インスタンス化）
			List<REPLY> replyList = rDao.select(0, poster_id); // poster_idを利用し、rDaoのselectでposter_idの値と同じ値を持つPOSTER＿IDのリストを検索・取得し、変数replyListに格納

			// 取得した返信内容（replyList）を属性名replyListとしてリクエストスコープに格納する
			// (reply.jspで利用するため）
			request.setAttribute("replyList", replyList);

		}





	}
}
