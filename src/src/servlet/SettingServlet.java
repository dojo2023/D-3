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
		HttpSession session = request.getSession();
		String id = session.getId();
		String message = "";

		if(request.getParameter("passwordChange") != null) {

			String nowPassword = request.getParameter("nowPassword");
			String newPassword = request.getParameter("newPassword");

			USER_INFODao userDao = new USER_INFODao();
			USER_INFO user = userDao.select("", id);
			if(!nowPassword.equals(user.getUser_pw())) {
				message = "入力されたパスワードが違います。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			}

			USER_INFO newUser = new USER_INFO();
			newUser.setUser_id(id);
			newUser.setUser_pw(newPassword);

			boolean result = userDao.update(newUser);
			if(result) {
				message = "パスワードを" + newPassword + "に変更しました。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			} else {
				message = "パスワードの変更に失敗しました。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			}
		}

		else if(request.getParameter("news_change") != null) {

			if (request.getParameter("item").equals("タグ")){
				String newTag = request.getParameter("newsContent");



				USER_INFO user = new USER_INFO();
				user.setUser_id(id);
				USER_INFODao user_dao = new USER_INFODao();
				boolean result = user_dao.update(user);
				if(result) {
				} else {

				}
			}

			else if (request.getParameter("item").equals("カテゴリー")){
				String newcategory = request.getParameter("news_content");
				USER_INFO data2 = new USER_INFO();
				data2.setUser_id(id);
				data2.setFree_word(newcategory);

				USER_INFODao search = new USER_INFODao();
				boolean result = search.update(data2);
			}

			else if (request.getParameter("item").equals("フリーワード")){
				String newtag = request.getParameter("news_content");
				USER_INFO data3 = new USER_INFO();
				data3.setUser_id(id);
				data3.setFree_word(newtag);

				USER_INFODao search = new USER_INFODao();
				boolean result = search.update(data3);
			}
		}

		else if(request.getParameter("enChange") != null) {
			//ユーザの社員番号を変更する処理

			String userId = request.getParameter("userId");
			String nowEn = request.getParameter("nowEn");
			USER_INFODao userDao = new USER_INFODao();
			USER_INFO user = userDao.select(userId, "");

			if(user.getUser_id().equals("")) {
				message = "入力されたIDは未登録です。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			}
			if(!nowEn.equals(user.getUser_en())) {
				message = "入力された社員番号は未登録です。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			}
			String newEn = request.getParameter("newEn");
			USER_INFO newUser = new USER_INFO();
			newUser.setUser_id(userId);
			newUser.setUser_en(newEn);
			boolean result = userDao.update(newUser);

			if(result) {
				message = "ID：" + userId + " の社員番号を" + newEn + "に変更しました。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			} else {
				message = "社員番号の変更に失敗しました。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
			}

		}

		if (request.getParameter("grant") != null) {

		    String userId = request.getParameter("userId");

		    USER_INFODao userDao = new USER_INFODao();
		    USER_INFO user = new USER_INFO();
		    user.setUser_id(userId);
		    user.setUser_mode_switch(2);
		    boolean result = userDao.update(user);

		    if(result) {
		    	message = "ID：" + userId + " に管理者権限を付与しました。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
		    } else {
		    	message = "管理者権限の付与に失敗しました。IDを間違えている可能性があります。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
		    }

		} else if (request.getParameter("revoke") != null) {

			String userId = request.getParameter("userId");

		    USER_INFODao userDao = new USER_INFODao();
		    USER_INFO user = new USER_INFO();
		    user.setUser_id(userId);
		    user.setUser_mode_switch(1);
		    boolean result = userDao.update(user);

		    if(result) {
		    	message = "ID：" + userId + " の管理者権限を剥奪しました。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
		    } else {
		    	message = "管理者権限の剥奪に失敗しました。IDを間違えている可能性があります。";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
				dispatcher.forward(request, response);
		    }

		}
	}
}
