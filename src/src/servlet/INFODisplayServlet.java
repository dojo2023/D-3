package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.USER_INFODao;
import dao.USER_SQDao;
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


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 決定した個人情報を表示するページにフォワード
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// 新規登録の場合（新規登録画面で登録ボタンを押したときに表示される）
		/*新規登録のタブで登録ボタンが押されたら、新規登録の際に記入された、氏名、ID、パスワード、
		 * 社員番号、秘密の質問とその回答*を取得する*/
		request.setCharacterEncoding("UTF-8");
		String idf = request.getParameter("idf");

		if(idf.equals("0")) {


			String registerName = request.getParameter("registerName"); // 「registerName」というname属性をもつテキストボックスから氏名を取得し、String型の変数registerNameに代入
			String registerId = request.getParameter("registerId"); // 「registerId」というname属性をもつテキストボックスからidを取得し、String型の変数registerIdに代入
			String registerPassword = request.getParameter("registerPassword"); // 「registerPassword」というname属性をもつテキストボックスからパスワードを取得し、String型の変数registerPasswordに代入
			String employeeNumber = request.getParameter("employeeNumber"); // 「employeeNumber」というname属性をもつテキストボックスから社員番号を取得し、String型の変数employeeNumberに代入
			String securityQuestion = request.getParameter("securityQuestion"); // 「securityQuestion」というname属性をもつテキストボックスから秘密の質問を取得し、String型の変数securityQuestionに代入
			String securityAnswer = request.getParameter("securityAnswer"); // 「securityAnswer」というname属性をもつテキストボックスから秘密の質問の回答を取得し、String型の変数securityAnswerに代入

			USER_INFODao dao = new USER_INFODao(); // 「USER_INFODao」という型を作り、「dao1」という変数に新しく空のUSER_INFODao()を定義

			// daoの中に取得した社員番号、氏名、ID、パスワード、秘密の質問、秘密の質問の回答を格納
			String result = dao.insert(employeeNumber, registerName, registerId, registerPassword, securityQuestion, securityAnswer);
			if(result.equals("false")) {
				request.setAttribute("err_sen", "ユーザ情報の登録に失敗しました。");
				request.setAttribute("err_idf", "9");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
			else if(result.equals("入力した社員番号とIDは既に使われています。")) {
				request.setAttribute("err_sen", result);
				request.setAttribute("err_idf", "6");

				request.setAttribute("registerName", registerName);
				request.setAttribute("registerPassword", registerPassword);
				request.setAttribute("securityQuestion", securityQuestion);
				request.setAttribute("securityAnswer", securityAnswer);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
			else if(result.equals("入力した社員番号は既に使われています。")) {
				request.setAttribute("err_sen", result);
				request.setAttribute("err_idf", "7");

				request.setAttribute("registerName", registerName);
				request.setAttribute("registerId", registerId);
				request.setAttribute("registerPassword", registerPassword);
				request.setAttribute("securityQuestion", securityQuestion);
				request.setAttribute("securityAnswer", securityAnswer);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
			else if(result.equals("入力したIDは既に使われています。")) {
				request.setAttribute("err_sen", result);
				request.setAttribute("err_idf", "8");

				request.setAttribute("registerName", registerName);
				request.setAttribute("registerPassword", registerPassword);
				request.setAttribute("employeeNumber", employeeNumber);
				request.setAttribute("securityQuestion", securityQuestion);
				request.setAttribute("securityAnswer", securityAnswer);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
			/* リクエストスコープにregisterName(氏名)、registerId(ID)、registerPassword(パスワード)、employeeNumber(社員番号)、
			 * securityQuestion(秘密の質問）、securityAnswer（秘密の質問の回答）を格納（INFODisplay.jspでリクエストスコープを利用して表示させるため） */

			USER_SQDao sq_dao = new USER_SQDao();
			String SQsentence = sq_dao.SQ_return(securityQuestion);
			request.setAttribute("registerName", registerName);
			request.setAttribute("registerId", registerId);
			request.setAttribute("registerPassword", registerPassword);
			request.setAttribute("employeeNumber", employeeNumber);
			request.setAttribute("SQsentence", SQsentence);
			request.setAttribute("securityAnswer", securityAnswer);
			request.setAttribute("idf", idf);
			request.setAttribute("headline", "以下の情報が登録されました");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/INFODisplay.jsp");
			dispatcher.forward(request, response);

		}


		// IDを忘れた場合に表示（IDを忘れた場合の画面から秘密の質問画面に推移したのち
		//送信ボタンを押したときに表示される）
		/* 秘密の質問画面の送信ボタンを押したらセッションスコープからLOGIN_USER型のLOGIN＿USER属性がもつidを取得*/
		else if(idf.equals("1")) {

			request.setCharacterEncoding("UTF-8");

			//入力した質問の答えとuser_idを元に取り出した質問の答えが一致しているか確認し、合っていればそのuser_idをリクエストスコープに格納し、間違っていいればログインページへリダイレクトする
			String ans = request.getParameter("answer");
			String en = request.getParameter("en");
			String user_sa = "";
			USER_INFODao saDao = new USER_INFODao();
			USER_INFO user = saDao.select(en, "");
			user_sa = user.getUser_sa();

			if (ans.equals(user_sa) && !user.getUser_id().equals("")) {
				 // 文字コードの設定

				// リクエストスコープに取得したIDを格納
				//（INFODisplay.jspでリクエストスコープを利用ため）
				request.setAttribute("id", user.getUser_id());
				request.setAttribute("idf", idf);
				request.setAttribute("headline", "IDの表示");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/INFODisplay.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("err_sen", "秘密の質問の回答が違います。");
				request.setAttribute("err_idf", "2");
				request.setAttribute("en", en);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}


		// PWを忘れた場合に表示（PW再設定画面で送信ボタンを押したときに表示されるもの）
		/* PW再設定画面で送信ボタンを押したら、入力された新しいパスワードを受け取り、新しいパスワードを登録する。*/
		else if(idf.equals("2")) {

			request.setCharacterEncoding("UTF-8"); // 文字コードの設定

			String newPassword = request.getParameter("newPassword"); // 「pwReset」というname属性をもつテキストボックスから新しいパスワードを取得し、String型の変数pwResetに代入
			String confirmPassword = request.getParameter("confirmPassword");
			String id = request.getParameter("id");
			String ans = request.getParameter("ans");
			if(!newPassword.equals(confirmPassword)) {
				request.setAttribute("err_sen", "入力した2つのパスワードが一致していません。");
				request.setAttribute("err_idf", "4");
				request.setAttribute("id", id);
				request.setAttribute("ans", ans);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}

			USER_INFO user = new USER_INFO(); // USER_INFO型の変数userを定義し、空のUSER_INFOを代入
			user.setUser_id(id); // 変数userのもつidにセッションスコープから取得したidを代入
			user.setUser_pw(newPassword); // 変数userのもつpwに更新したいpwResetを代入

			USER_INFODao user_dao = new USER_INFODao(); // USER_INFODao型の変数user_daoを定義し、空のUSER_INFODaoを代入
			boolean result = user_dao.update(user); // user_daoのidとpwを更新（idは誰を更新するのかの識別に使ったため、更新されるのはpwのみ）

			if(result == false) {
				request.setAttribute("err_sen", "ユーザ情報の登録に失敗しました。");
				request.setAttribute("err_idf", "9");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
			// リクエストスコープに取得したpwResetを格納
			//（INFODisplay.jspでリクエストスコープを利用して表示させるため）
			request.setAttribute("pw", newPassword);
			request.setAttribute("idf", idf);
			request.setAttribute("headline", "再設定したパスワード");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/INFODisplay.jsp");
			dispatcher.forward(request, response);
		}
	}

}
