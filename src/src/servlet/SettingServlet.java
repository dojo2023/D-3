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
import model.USER_INFO;

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
		HttpSession session = request.getSession();
		String id = session.getId();
		if(request.getParameter("password_change") != null) {//password_changeというパラメーターがリクエストに含まれているかのチェック
			//含まれていれば、ユーザーがパスワードの変更を要求している

			String new_password = request.getParameter("password");//ユーザのパスワードを変更する処理
			//新しいパスワードを取得
			//リクエストパラメーターからpasswordを取得して、new_passwordに格納

			//このパスワードに変更する

			USER_INFO data = new USER_INFO();//USER_INFOに新しいインスタンスdataを作成
			data.setUser_id(id);//セッションIDを data オブジェクトのユーザIDとして設定
			data.setUser_pw(new_password);
			USER_INFODao search = new USER_INFODao();//USER_INFODaoに新しいインスタンスsearchを作成
			boolean result = search.update(data);


			}

		else if(request.getParameter("news_change") != null) {
			//ユーザの新着に表示するものを変更する処理
			if (request.getParameter("radio").equals("フリーワード")){
				String newfreeword = request.getParameter("news_content");
				USER_INFO data1 = new USER_INFO();
				data1.setUser_id(id);
				data1.setFree_word(newfreeword);

				USER_INFODao search = new USER_INFODao();
				boolean result = search.update(data1);
			}

			else if (request.getParameter("radio").equals("カテゴリー")){
				String newcategory = request.getParameter("news_content");
				USER_INFO data2 = new USER_INFO();
				data2.setUser_id(id);
				data2.setFree_word(newcategory);

				USER_INFODao search = new USER_INFODao();
				boolean result = search.update(data2);
			}

			else if (request.getParameter("radio").equals("タグ")){
				String newtag = request.getParameter("news_content");
				USER_INFO data3 = new USER_INFO();
				data3.setUser_id(id);
				data3.setFree_word(newtag);

				USER_INFODao search = new USER_INFODao();
				boolean result = search.update(data3);
			}
		}

		else if(request.getParameter("en_change") != null) {
			//ユーザの社員番号を変更する処理

			String new_en = request.getParameter("user_en");

			//この社員番号に変更する

			USER_INFO data4 = new USER_INFO();//USER_INFOに新しいインスタンスdataを作成
			data4.setUser_id(id);//セッションIDを data オブジェクトのユーザIDとして設定
			data4.setUser_en(new_en);
			USER_INFODao search = new USER_INFODao();//USER_INFODaoに新しいインスタンスsearchを作成
			boolean result = search.update(data4);
		}

		else if(request.getParameter("grant") != null) {
			//ユーザの管理者権限を付与する処理
			String user_id = request.getParameter("admin");//このIDのユーザに管理者権限を付与する

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
