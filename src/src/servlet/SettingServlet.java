package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String check = "NoChange";

		if(request.getParameter("password_change") != null) {
			//ユーザのパスワードを変更する処理
			String new_password = request.getParameter("password"); //このパスワードに変更する
		}
		else if(request.getParameter("news_change") != null) {
			//ユーザの新着に表示するものを変更する処理
			String new_item = request.getParameter("radio"); //これに"フリーワード"か"カテゴリー"か"タグ"が入ってる
			String new_content = request.getParameter("news_content"); //これに登録する単語が入ってる
		}
		else if(request.getParameter("en_change") != null) {
			//ユーザの社員番号を変更する処理
			String new_en = request.getParameter("user_en"); //この社員番号に変更する
		}
		else if(request.getParameter("grant") != null) {
			//ユーザの管理者権限を付与する処理
			String user_id = request.getParameter("admin"); //このIDのユーザに管理者権限を付与する
		}
		else if(request.getParameter("revoke") != null) {
			//ユーザの管理者権限を剥奪する処理
			String user_id = request.getParameter("admin"); //このIDのユーザに管理者権限を剥奪する
		}

		request.setAttribute("SQ", check);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ogamino_success.jsp");
		dispatcher.forward(request, response);

	}

}
