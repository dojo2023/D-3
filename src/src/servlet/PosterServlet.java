package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CATEGORYDao;
import dao.HASHTAGSDao;
import dao.POSTERDao;
import model.LOGIN_USER;
import model.POSTER;

/**
 * Servlet implementation class PosterServlet
 */
@WebServlet("/PosterServlet")
public class PosterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 必要な処理や画面遷移などを行う
    	//カテゴリ名を決める
    	String CATEGORY_NAME = "カテゴリ名1";

        // カテゴリ名の一覧を取得する処理
        CATEGORYDao categoryDao = new CATEGORYDao();
        String categoryName = categoryDao.get_categoryname(0);



        // 検索処理を行う、質問だけ取り出す。応募だけ取り出す処理
        POSTERDao dao = new POSTERDao();
        List<POSTER> post = dao.select(0, 1);


        // 取得した投稿をリクエストスコープに保存
        request.setAttribute("posterList", post);
        request.setAttribute("categoryname", CATEGORY_NAME);
        request.setAttribute("categoryName", categoryName);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poster.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");
    	String postIdf = request.getParameter("postIdf");
    	POSTERDao pDao = new POSTERDao();

    	if(postIdf.equals("0")){

	    	String categoryIdString = request.getParameter("categoryId");
	    	int categoryId = Integer.parseInt(categoryIdString);
	    	CATEGORYDao cDao = new CATEGORYDao();
	    	List<POSTER> posterList = pDao.select(0, categoryId);
	    	String categoryName = cDao.get_categoryname(categoryId);

	    	request.setAttribute("posterList", posterList);
	    	request.setAttribute("categoryName", categoryName);
	    	request.setAttribute("categoryId", categoryIdString);
    	}
    	else if(postIdf.equals("1")) {
    		String keyword = request.getParameter("keyword");
    		String categoryName = request.getParameter("categoryName");
    		String categoryId = request.getParameter("categoryId");
    		List<POSTER> posterList = pDao.searchWord(keyword);
    		List<POSTER> newPosterList = new ArrayList<>();
    		for(int i = 0; i < posterList.size(); i++) {
    			POSTER poster = posterList.get(i);
    			if(poster.getCATEGORY_ID() == Integer.parseInt(categoryId)) {
    				newPosterList.add(poster);
    			}
    		}
    		request.setAttribute("posterList", newPosterList);
	    	request.setAttribute("categoryName", categoryName);
	    	request.setAttribute("categoryId", categoryId);
    	}
    	else if(postIdf.equals("2")) {
    		HttpSession session = request.getSession();
    		String id = ((LOGIN_USER)(session.getAttribute("LOGIN_USER"))).getId();
    		Random rand = new Random();
            String animalId = String.valueOf(rand.nextInt(100) + 1);
    		String title = request.getParameter("title");
    		String sentence = request.getParameter("sentence");
    		String name = request.getParameter("name");
    		int nameSwitch = 0;
    		if(name.equals("匿名")) {
    			nameSwitch = 0;
    		} else if(name.equals("実名")) {
    			nameSwitch = 1;
    		}
    		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

    		String categoryName = request.getParameter("categoryName");
    		String hashtag1 = request.getParameter("hashtag1");
    		String hashtag2 = request.getParameter("hashtag2");
    		String hashtag3 = request.getParameter("hashtag3");
    		String hashtag4 = request.getParameter("hashtag4");
    		String hashtag5 = request.getParameter("hashtag5");
    		int hashtagId1 = 0;
    		int hashtagId2 = 0;
    		int hashtagId3 = 0;
    		int hashtagId4 = 0;
    		int hashtagId5 = 0;
    		HASHTAGSDao hDao = new HASHTAGSDao();
    		if(hashtag1 != null && !hashtag1.equals("")) {
    			hDao.insert(hashtag1);
    			hashtagId1 = hDao.getHtagId(hashtag1);
    		}
    		if(hashtag2 != null && !hashtag2.equals("")) {
    			hDao.insert(hashtag2);
    			hashtagId2 = hDao.getHtagId(hashtag2);
    		}
    		if(hashtag3 != null && !hashtag3.equals("")) {
    			hDao.insert(hashtag3);
    			hashtagId3 = hDao.getHtagId(hashtag3);
    		}
    		if(hashtag4 != null && !hashtag4.equals("")) {
    			hDao.insert(hashtag4);
    			hashtagId4 = hDao.getHtagId(hashtag4);
    		}
    		if(hashtag5 != null && !hashtag5.equals("")) {
    			hDao.insert(hashtag5);
    			hashtagId5 = hDao.getHtagId(hashtag5);
    		}
    		POSTER poster = new POSTER(title, categoryId, sentence, hashtagId1, hashtagId2, hashtagId3, hashtagId4, hashtagId5, "", animalId, id, nameSwitch);
    		pDao.insert(poster);
    		List<POSTER> posterList = pDao.select(0, categoryId);

    		request.setAttribute("categoryName", categoryName);
    		request.setAttribute("categoryId", request.getParameter("categoryId"));
    		request.setAttribute("posterList", posterList);
    	}

    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poster.jsp");
        dispatcher.forward(request, response);
    }
}
