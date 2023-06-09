package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OgaminoTESTDao;
import model.OgaminoIdpw;

/**
 * Servlet implementation class OgaminoTestServlet
 */
@WebServlet("/OgaminoTestServlet")
public class OgaminoTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OgaminoTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_test.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// ログイン処理を行う
		OgaminoTESTDao TESTDao = new OgaminoTESTDao();
		if (TESTDao.isLoginOK(new OgaminoIdpw(id,pw))) {	// ログイン成功

			// メニューサーブレットにリダイレクトする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_success.jsp");
			dispatcher.forward(request, response);
		}
		else {									// ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_failed.jsp");
			dispatcher.forward(request, response);
		}
		request.setAttribute("idpw",new OgaminoIdpw(id,pw));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_success.jsp");
		dispatcher.forward(request, response);
	}
}
