package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SQServlet
 */
@WebServlet("/SQServlet")
public class SQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SQServlet() {
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
		String itf = request.getParameter("itf");
		/*
		SQServletは
		1.PWを忘れた→ID入力→秘密の質問回答→PW再設定
		2.IDを忘れた→社員番号入力→秘密の質問回答→ID表示
		という2つの流れで呼び出されるため
		それぞれをitfが0(PW再設定)の時と1(ID表示)の時で区別する
		*/
		if(itf.equals("0")) {
			request.setAttribute("itf", itf);
		} else if(itf.equals("1")) {
			request.setAttribute("itf", itf);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sq.jsp");
		dispatcher.forward(request, response);
	}

}