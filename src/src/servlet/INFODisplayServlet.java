package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		// 新規登録の場合に表示されるもの（新規登録画面で登録ボタンを押したときに表示される）
		if(request.getParameter(" ")!=null) {
			String registerName = request.getParameter("registerId");
			String registerId = request.getParameter("registerId");
			String registerPassword = request.getParameter("registerPassword");
			String employeeNumber = request.getParameter("employeeNumber");
			String securityQuestion = request.getParameter("securityQuestion");
			String securityAnswer = request.getParameter("securityAnswer");
		}
		// IDを忘れた場合に表示されるもの（IDを忘れた場合の画面から秘密の質問画面に推移したのち
		//送信ボタンを押したときに表示される）
		else if(request.getParameter(" ")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/INFODisplay.jsp");
			dispatcher.forward(request, response);
		}
		// PWを忘れた場合に表示されるもの（PW再設定画面で送信ボタンを押したときに表示されるもの）
		else if(request.getParameter(" ")!=null) {
			String pwReset = request.getParameter("pwReset");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/INFODisplay.jsp");
		dispatcher.forward(request, response);

	}

}
