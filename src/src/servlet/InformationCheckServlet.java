package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.USER_SQDao;

/**
 * Servlet implementation class InformationCheckServlet
 */
@WebServlet("/InformationCheckServlet")
public class InformationCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // 文字コードの設定

		String registerName = request.getParameter("registerName"); // 「registerName」というname属性をもつテキストボックスから氏名を取得し、String型の変数registerNameに代入
		String registerId = request.getParameter("registerId"); // 「registerId」というname属性をもつテキストボックスからidを取得し、String型の変数registerIdに代入
		String registerPassword = request.getParameter("registerPassword"); // 「registerPassword」というname属性をもつテキストボックスからパスワードを取得し、String型の変数registerPasswordに代入
		String confirmPassword = request.getParameter("confirmPassword");
		String employeeNumber = request.getParameter("employeeNumber"); // 「employeeNumber」というname属性をもつテキストボックスから社員番号を取得し、String型の変数employeeNumberに代入
		String securityQuestion = request.getParameter("securityQuestion"); // 「securityQuestion」というname属性をもつテキストボックスから秘密の質問を取得し、String型の変数securityQuestionに代入
		String securityAnswer = request.getParameter("securityAnswer"); // 「securityAnswer」というname属性をもつテキストボックスから秘密の質問の回答を取得し、String型の変数securityAnswerに代入

		USER_SQDao sq_dao = new USER_SQDao();
		String SQsentence = sq_dao.SQ_return(securityQuestion);

		if(!registerPassword.equals(confirmPassword)) {
			request.setAttribute("registerName", registerName);
			request.setAttribute("registerId", registerId);
			request.setAttribute("employeeNumber", employeeNumber);
			request.setAttribute("securityQuestion", securityQuestion);
			request.setAttribute("securityAnswer", securityAnswer);
			request.setAttribute("err_idf", "5");
			request.setAttribute("err_sen", "入力した2つのパスワードが一致していません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}


		/* リクエストスコープにregisterName(氏名)、registerId(ID)、registerPassword(パスワード)、employeeNumber(社員番号)、
		 * securityQuestion(秘密の質問）、securityAnswer（秘密の質問の回答）を格納（INFODisplay.jspでリクエストスコープを利用して表示させるため） */
		request.setAttribute("registerName", registerName);
		request.setAttribute("registerId", registerId);
		request.setAttribute("registerPassword", registerPassword);
		request.setAttribute("employeeNumber", employeeNumber);
		request.setAttribute("SQsentence", SQsentence);
		request.setAttribute("securityQuestion", securityQuestion);
		request.setAttribute("securityAnswer", securityAnswer);
		request.setAttribute("idf", "0");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/info_check.jsp");
		dispatcher.forward(request, response);
	}

}
