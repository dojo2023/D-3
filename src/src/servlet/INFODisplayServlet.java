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
		if(request.getParameter("submit_button").equals("登録")) {
			String registerName = request.getParameter("registerName");
			String registerId = request.getParameter("registerId");
			String registerPassword = request.getParameter("registerPassword");
			String employeeNumber = request.getParameter("employeeNumber");
			String securityQuestion = request.getParameter("securityQuestion");
			String securityAnswer = request.getParameter("securityAnswer");


		USER_INFODao dao1 = new USER_INFODao();
		if(dao1.insert(registerName, employeeNumber, securityQuestion, securityAnswer, registerId, registerPassword)) {
				request.setAttribute("result_message", "登録完了");
			}
		else {
			request.setAttribute("result_message", "登録失敗");
		}
		}
		// IDを忘れた場合に表示（IDを忘れた場合の画面から秘密の質問画面に推移したのち
		//送信ボタンを押したときに表示される）
		else if(request.getParameter("submit_button").equals("送信")) {
			HttpSession session = request.getSession();
			((LOGIN_USER)session.getAttribute("LOGIN_USER")).getId();

		}
		// PWを忘れた場合に表示（PW再設定画面で送信ボタンを押したときに表示されるもの）
		else if(request.getParameter("reset").equals("送信")) {
			String pwReset = request.getParameter("pwReset");

			USER_INFO user = new USER_INFO();
			HttpSession session = request.getSession();
			String id = ((LOGIN_USER)session.getAttribute("LOGIN_USER")).getId();
			user.setUser_id(id);
			user.setUser_pw(pwReset);

			USER_INFODao user_dao = new USER_INFODao();
			user_dao.update(user);
			}


	}

}
