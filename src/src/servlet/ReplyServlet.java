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

import dao.ANIMALDao;
import dao.POSTERDao;
import dao.REPLYDao;
import dao.REPORTDao;
import model.LOGIN_USER;
import model.POSTER;
import model.REPLY;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

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


		// 誰がどの状態（一般・管理者）でログインしているか確認する
		/*HttpSession session = request.getSession(); //セッションスコープにアクセス
		USER_INFO userInfo = (USER_INFO)session.getAttribute("USER_INFO"); // セッションスコープに格納されているUSER_INFOの値をPOSTER型の変数userInfoに代入
		int userModeSwitch = userInfo.getUser_mode_switch();// int型の変数userModeSwitchにuserInfoが持っているuser_mode_switchの値を代入


		//匿名か実名かを確認する
		POSTER poster0 = (POSTER)session.getAttribute("POSTER");
		REPLY reply0 = (REPLY)session.getAttribute("REPLY");
		int userNameSwitch = poster0.getUSER_NAME_SWITCH();
		request.setAttribute("userNameSwitch", userNameSwitch);
		//現在ログインしているidと投稿者のidと返信者のidを取得し、リクエストスコープに格納
		String id = session.getId();
		String postUserId = poster0.getUSER_ID();
		String replyUserId = reply0.getUSER_ID();
		request.setAttribute("id", id);
		request.setAttribute("postUserId", postUserId);
		request.setAttribute("replyUserId", replyUserId);

		// 以下の分岐は一般・一般管理者・ガチ管理者のどの状態で動かしているかで分岐
		if(userModeSwitch == 1) {
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

		else if(userModeSwitch == 2) {
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
			List<REPLY> replyList = rDao.select(0, poster_id);// poster_idを利用し、rDaoのselectでposter_idの値と同じ値を持つPOSTER＿IDのリストを検索・取得し、変数replyListに格納

			// 取得した返信内容（replyList）を属性名replyListとしてリクエストスコープに格納する
			// (reply.jspで利用するため）
			request.setAttribute("replyList", replyList);
		}*/

		request.setCharacterEncoding("UTF-8");
		String replyIdf = request.getParameter("replyIdf");

		//掲示板一覧もしくはTOPページの新着から投稿詳細を開いた場合
		if(replyIdf.equals("0")) {
			int posterId = Integer.parseInt(request.getParameter("posterId"));
			POSTERDao pDao = new POSTERDao();
			List<POSTER> posterList = pDao.select(posterId, 0);
			POSTER poster = posterList.get(0);
			REPLYDao replyDao = new REPLYDao();
			List<REPLY> replyList = replyDao.select(0, posterId);

			request.setAttribute("poster", poster);
			request.setAttribute("replyList", replyList);
		}
		//通報画面から掲示板詳細を開いた場合
		else if(replyIdf.equals("1")) {
			int posterId = Integer.parseInt(request.getParameter("posterId"));
			POSTERDao pDao = new POSTERDao();
			List<POSTER> posterList = pDao.select(posterId, 0);
			POSTER poster = posterList.get(0);
			REPLYDao replyDao = new REPLYDao();
			List<REPLY> replyList = replyDao.select(0, posterId);

			//replyIdfが0なら投稿が、1なら返信が通報されたということ
			String reportIdf = request.getParameter("reportIdf");
			request.setAttribute("reportIdf", reportIdf);
			if(reportIdf.equals("1")) {
				request.setAttribute("replyId", request.getParameter("replyId"));
			}
			request.setAttribute("poster", poster);
			request.setAttribute("replyList", replyList);
		}
		//返信した場合
		else if(replyIdf.equals("2")) {
			int posterId = Integer.parseInt(request.getParameter("posterId"));
			String sentence = request.getParameter("sentence");
			HttpSession session = request.getSession();
    		String id = ((LOGIN_USER)(session.getAttribute("LOGIN_USER"))).getId();
			String name = request.getParameter("name");
    		int nameSwitch = 0;
    		if(name.equals("匿名")) {
    			nameSwitch = 0;
    		} else if(name.equals("実名")) {
    			nameSwitch = 1;
    		}
    		ANIMALDao aDao = new ANIMALDao();
    		String animalId = aDao.get_animalID(posterId, id);
    		REPLYDao replyDao = new REPLYDao();
    		REPLY reply = new REPLY(posterId, sentence, nameSwitch, id, animalId);
    		replyDao.insert(reply);

    		POSTERDao pDao = new POSTERDao();
    		List<POSTER> posterList = pDao.select(posterId, 0);
    		POSTER poster = posterList.get(0);
    		List<REPLY> replyList = replyDao.select(0, posterId);

    		request.setAttribute("poster", poster);
    		request.setAttribute("replyList", replyList);
		}
		//投稿か返信を通報した場合
		else if(replyIdf.equals("3")) {
			//replyIdfが0なら投稿が、1なら返信が通報されたということ
			String reportIdf = request.getParameter("replyIdf");
			int reportId = Integer.parseInt(request.getParameter("reportId"));
			REPORTDao rDao = new REPORTDao();
			if(reportIdf.equals("0")) {
				rDao.insert(0, reportId);
			} else if(reportIdf.equals("1")) {
				rDao.insert(reportId, 0);
			}

			int posterId = Integer.parseInt(request.getParameter("posterId"));
			POSTERDao pDao = new POSTERDao();
    		List<POSTER> posterList = pDao.select(posterId, 0);
    		POSTER poster = posterList.get(0);
    		REPLYDao replyDao = new REPLYDao();
    		List<REPLY> replyList = replyDao.select(0, posterId);

    		request.setAttribute("poster", poster);
    		request.setAttribute("replyList", replyList);
		}
		//投稿か返信を削除した場合
		else if(replyIdf.equals("4")) {
			//replyIdfが0なら投稿が、1なら返信が削除されたということ
			String deleteIdf = request.getParameter("deleteIdf");
			int deleteId = Integer.parseInt(request.getParameter("deleteId"));
			POSTERDao pDao = new POSTERDao();
			REPLYDao replyDao = new REPLYDao();
			if(deleteIdf.equals("0")) {
				List<REPLY> replyList = replyDao.select(0, deleteId);
				for (int  i = 0; i < replyList.size(); i++) {
					REPLY reply = replyList.get(i);
					int reDeleteId = reply.getREPLY_ID();
					replyDao.delete(reDeleteId);
				}
				pDao.delete(deleteId);
			} else if(deleteIdf.equals("1")) {
				replyDao.delete(deleteId);
			}

			int posterId = Integer.parseInt(request.getParameter("posterId"));
    		List<POSTER> posterList = pDao.select(posterId, 0);
    		POSTER poster = posterList.get(0);
    		List<REPLY> replyList = replyDao.select(0, posterId);

    		request.setAttribute("poster", poster);
    		request.setAttribute("replyList", replyList);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reply.jsp");
	    dispatcher.forward(request, response);


	}
}
