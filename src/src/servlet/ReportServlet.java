package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.POSTERDao;
import dao.REPORTDao;
import model.POSTER;
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

	    // 検索処理を行う
	    REPORTDao rDao = new REPORTDao();
	    POSTERDao pDao = new POSTERDao();
	    List<REPORT> reportList = rDao.select();
	    List<POSTER> posterNewList = new ArrayList<>();
	    for(int i = 0; i < reportList.size(); i++) {
	    	REPORT report = reportList.get(i);
	    	List<POSTER> posterList = pDao.select(report.getPOSTER_ID(), 0);
	    	POSTER poster = posterList.get(0);
	    	posterNewList.add(poster);
	    }


	    // 検索結果をリクエストスコープに格納する
	    request.setAttribute("reportList", reportList);
	    request.setAttribute("posterNewList", posterNewList);

	    // 通報ページにフォワードする
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/report.jsp");
	    dispatcher.forward(request, response);
	}


}