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
 * Servlet implementation class INFODisplayServlet
 */
@WebServlet("/INFODisplayServlet")
public class INFODisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public INFODisplayServlet() {
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
		doGet(request, response);

		// 新規登録の場合（新規登録画面で登録ボタンを押したときに表示される）
		/*新規登録のタブで登録ボタンが押されたら、新規登録の際に記入された、氏名、ID、パスワード、
		 * 社員番号、秘密の質問とその回答*を取得する*/
		if(request.getParameter("submit_button").equals("登録")) {

			request.setCharacterEncoding("UTF-8"); // 文字コードの設定

			String registerName = request.getParameter("registerName"); // 「registerName」というname属性をもつテキストボックスから氏名を取得し、String型の変数registerNameに代入
			String registerId = request.getParameter("registerId"); // 「registerId」というname属性をもつテキストボックスからidを取得し、String型の変数registerIdに代入
			String registerPassword = request.getParameter("registerPassword"); // 「registerPassword」というname属性をもつテキストボックスからパスワードを取得し、String型の変数registerPasswordに代入
			String employeeNumber = request.getParameter("employeeNumber"); // 「employeeNumber」というname属性をもつテキストボックスから社員番号を取得し、String型の変数employeeNumberに代入
			String securityQuestion = request.getParameter("securityQuestion"); // 「securityQuestion」というname属性をもつテキストボックスから秘密の質問を取得し、String型の変数securityQuestionに代入
			String securityAnswer = request.getParameter("securityAnswer"); // 「securityAnswer」というname属性をもつテキストボックスから秘密の質問の回答を取得し、String型の変数securityAnswerに代入


			USER_INFODao dao = new USER_INFODao(); // 「USER_INFODao」という型を作り、「dao1」という変数に新しく空のUSER_INFODao()を定義

			// daoの中に取得した社員番号、氏名、ID、パスワード、秘密の質問、秘密の質問の回答を格納
			dao.insert(employeeNumber, registerName, registerId, registerPassword, securityQuestion, securityAnswer);
			/* リクエストスコープにregisterName(氏名)、registerId(ID)、registerPassword(パスワード)、employeeNumber(社員番号)、
			 * securityQuestion(秘密の質問）、securityAnswer（秘密の質問の回答）を格納（INFODisplay.jspでリクエストスコープを利用して表示させるため） */
			request.setAttribute("registerName", registerName);
			request.setAttribute("registerId", registerId);
			request.setAttribute("registerPassword", registerPassword);
			request.setAttribute("employeeNumber", employeeNumber);
			request.setAttribute("securityQuestion", securityQuestion);
			request.setAttribute("securityAnswer", securityAnswer);
		}


		// IDを忘れた場合に表示（IDを忘れた場合の画面から秘密の質問画面に推移したのち
		//送信ボタンを押したときに表示される）
		/* 秘密の質問画面の送信ボタンを押したらセッションスコープからLOGIN_USER型のLOGIN＿USER属性がもつidを取得*/
		else if(request.getParameter("submit_button").equals("送信")) {
			HttpSession session = request.getSession();
			String id = ((LOGIN_USER)session.getAttribute("LOGIN_USER")).getId();

			String ans = request.getParameter("ansewr");

			request.setCharacterEncoding("UTF-8"); // 文字コードの設定

			// リクエストスコープに取得したIDを格納
			//（INFODisplay.jspでリクエストスコープを利用して表示させるため）
			request.setAttribute("id", id);

		}
		// PWを忘れた場合に表示（PW再設定画面で送信ボタンを押したときに表示されるもの）
		/* PW再設定画面で送信ボタンを押したら、入力された新しいパスワードを受け取り、新しいパスワードを登録する。*/
		else if(request.getParameter("reset").equals("送信")) {

			request.setCharacterEncoding("UTF-8"); // 文字コードの設定

			String pwReset = request.getParameter("pwReset"); // 「pwReset」というname属性をもつテキストボックスから新しいパスワードを取得し、String型の変数pwResetに代入

			USER_INFO user = new USER_INFO(); // USER_INFO型の変数userを定義し、空のUSER_INFOを代入
			HttpSession session = request.getSession(); // セッションスコープにアクセス
			String id = ((LOGIN_USER)session.getAttribute("LOGIN_USER")).getId(); // セッションスコープからLOGIN_USER型のLOGIN＿USER属性がもつidを取得し、String型の変数idに代入（誰がパスワードを変えるのか特定）
			user.setUser_id(id); // 変数userのもつidにセッションスコープから取得したidを代入
			user.setUser_pw(pwReset); // 変数userのもつpwに更新したいpwResetを代入

			USER_INFODao user_dao = new USER_INFODao(); // USER_INFODao型の変数user_daoを定義し、空のUSER_INFODaoを代入
			user_dao.update(user); // user_daoのidとpwを更新（idは誰を更新するのかの識別に使ったため、更新されるのはpwのみ）

			// リクエストスコープに取得したpwResetを格納
			//（INFODisplay.jspでリクエストスコープを利用して表示させるため）
			request.setAttribute("pw", pwReset);
		}


	}

}
