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

import dao.CATEGORYDao;
import dao.POSTERDao;
import dao.USER_INFODao;
import model.LOGIN_USER;
import model.POSTER;
import model.USER_INFO;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = ((LOGIN_USER)(session.getAttribute("LOGIN_USER"))).getId();

		USER_INFODao userDao = new USER_INFODao();
		USER_INFO user = userDao.select("", id);
		List<POSTER> posterList = new ArrayList<>();
		POSTERDao pDao = new POSTERDao();

		if(user.getFavorite_switch() == 2) {
			posterList = pDao.select(0, user.getCategory_id());
		} else if(user.getFavorite_switch() == 3) {
			posterList = pDao.searchHashtags(user.getHashtags_id());
		} else if(user.getFavorite_switch() == 4) {
			posterList = pDao.searchWord(user.getFree_word());
		}

		CATEGORYDao cDao = new CATEGORYDao();
		List<String> categoryList = cDao.select();

		request.setAttribute("categoryList", categoryList);
		request.setAttribute("posterList", posterList);
		request.setAttribute("userMode", user.getUser_mode_switch());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}