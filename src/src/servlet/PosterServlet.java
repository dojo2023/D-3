package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.POSTERDao;
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
        // TODO Auto-generated method stub




        // 必要な処理や画面遷移などを行う
    	String CATEGORY_NAME = "カテゴリ名1";

    	// 検索処理を行う、質問だけ取り出す。応募だけ取り出す処理
    			POSTERDao dao = new POSTERDao();

    			 List<POSTER> post = dao.select(0, 1);


    			// 取得した投稿をリクエストスコープに保存
    			// 検索結果をリクエストスコープに格納する
    			 request.setAttribute("posterList", post);
    			 request.setAttribute("categoryname", CATEGORY_NAME);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/poster.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }
}