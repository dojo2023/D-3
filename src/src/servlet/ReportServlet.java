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

import dao.REPORTDao;
import model.REPORT;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/LoginServlet");
			return;
		}
		// 通報ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // もしもログインしていなかったらログインサーブレットにリダイレクトする
	    HttpSession session = request.getSession();
	    if (session.getAttribute("user_id") == null) {
	        response.sendRedirect("/LoginServlet");
	        return;
	    }

	    // リクエストパラメータを取得する
	    request.setCharacterEncoding("UTF-8");
	    int REPORT_ID = Integer.parseInt(request.getParameter("REPORT_ID"));
	    int REPLY_ID = Integer.parseInt(request.getParameter("REPLY_ID"));
	    int POSTER_ID = Integer.parseInt(request.getParameter("POSTER_ID"));

	    // 検索処理を行う
	    REPORTDao RDao = new REPORTDao();
	    List<REPORT> REPORTList = RDao.select(new REPORT(REPORT_ID, REPLY_ID, POSTER_ID));


	    // 検索結果をリクエストスコープに格納する
	    request.setAttribute("REPORTList", REPORTList);

	    // 通報ページにフォワードする
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report.jsp");
	    dispatcher.forward(request, response);
	}


}