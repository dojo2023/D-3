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

import dao.ANIMALDao;
import dao.CATEGORYDao;
import dao.HASHTAGSDao;
import dao.POSTERDao;
import dao.REPLYDao;
import dao.REPORTDao;
import dao.USER_INFODao;
import model.LOGIN_USER;
import model.POSTER;
import model.REPLY;
import model.USER_INFO;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 決定した個人情報を表示するページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reply.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String replyIdf = request.getParameter("replyIdf");
		HttpSession session = request.getSession();
		String id = ((LOGIN_USER)(session.getAttribute("LOGIN_USER"))).getId();
		REPLYDao replyDao = new REPLYDao();
		POSTERDao pDao = new POSTERDao();
		REPORTDao rDao = new REPORTDao();
		ANIMALDao aDao = new ANIMALDao();
		CATEGORYDao cDao = new CATEGORYDao();
		HASHTAGSDao hDao = new HASHTAGSDao();
		USER_INFODao uDao = new USER_INFODao();

		//掲示板一覧もしくはTOPページの新着から投稿詳細を開いた場合
		if(replyIdf.equals("0")) {
			int posterId = Integer.parseInt(request.getParameter("posterId"));
			List<POSTER> posterList = pDao.select(posterId, 0);
			POSTER poster = posterList.get(0);
			List<REPLY> replyList = replyDao.select(0, posterId);
			int categoryIdInt = poster.getCATEGORY_ID();
			String categoryId = String.valueOf(categoryIdInt);

			String categoryName = cDao.get_categoryname(categoryIdInt);
			String[] hashtagList = {"", "", "", "", ""};
			if(poster.getHASHTAGS_ID1() != 0) {
				hashtagList[0] = hDao.get_htagname(poster.getHASHTAGS_ID1());
			}
			if(poster.getHASHTAGS_ID2() != 0) {
				hashtagList[1] = hDao.get_htagname(poster.getHASHTAGS_ID2());
			}
			if(poster.getHASHTAGS_ID3() != 0) {
				hashtagList[2] = hDao.get_htagname(poster.getHASHTAGS_ID3());
			}
			if(poster.getHASHTAGS_ID4() != 0) {
				hashtagList[3] = hDao.get_htagname(poster.getHASHTAGS_ID4());
			}
			if(poster.getHASHTAGS_ID5() != 0) {
				hashtagList[4] = hDao.get_htagname(poster.getHASHTAGS_ID5());
			}

			String posterAnimal = aDao.get_animalname(poster.getANIMAL_ID());
			List<String> replyAnimal = new ArrayList<>();
			List<String> replyName = new ArrayList<>();
			for(int i = 0; i < replyList.size(); i++) {
				REPLY replyInfo = replyList.get(i);
				String animalName = aDao.get_animalname(replyInfo.getANIMAL_ID());
				replyAnimal.add(animalName);
				USER_INFO replyInfoData = uDao.select("", replyInfo.getUSER_ID());
				replyName.add(replyInfoData.getUser_name());
			}

			USER_INFO posterInfo = uDao.select("", poster.getUSER_ID());

			request.setAttribute("posterIdf", request.getParameter("posterIdf"));
			request.setAttribute("posterName", posterInfo.getUser_name());
			request.setAttribute("replyName", replyName);
			request.setAttribute("replyAnimal", replyAnimal);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("hashtagList", hashtagList);
			request.setAttribute("posterAnimal", posterAnimal);
			request.setAttribute("poster", poster);
			request.setAttribute("replyList", replyList);
		}
		//通報画面から掲示板詳細を開いた場合
		else if(replyIdf.equals("1")) {
			int posterId = Integer.parseInt(request.getParameter("posterId"));
			List<POSTER> posterList = pDao.select(posterId, 0);
			POSTER poster = posterList.get(0);
			List<REPLY> replyList = replyDao.select(0, posterId);

			//replyIdfが0なら投稿が、1なら返信が通報されたということ
			String reportIdf = request.getParameter("reportIdf");
			request.setAttribute("reportIdf", reportIdf);
			if(reportIdf.equals("1")) {
				request.setAttribute("replyId", request.getParameter("replyId"));
			}

			String categoryName = cDao.get_categoryname(poster.getCATEGORY_ID());
			String[] hashtagList = {"", "", "", "", ""};
			if(poster.getHASHTAGS_ID1() != 0) {
				hashtagList[0] = hDao.get_htagname(poster.getHASHTAGS_ID1());
			}
			if(poster.getHASHTAGS_ID2() != 0) {
				hashtagList[1] = hDao.get_htagname(poster.getHASHTAGS_ID2());
			}
			if(poster.getHASHTAGS_ID3() != 0) {
				hashtagList[2] = hDao.get_htagname(poster.getHASHTAGS_ID3());
			}
			if(poster.getHASHTAGS_ID4() != 0) {
				hashtagList[3] = hDao.get_htagname(poster.getHASHTAGS_ID4());
			}
			if(poster.getHASHTAGS_ID5() != 0) {
				hashtagList[4] = hDao.get_htagname(poster.getHASHTAGS_ID5());
			}

			String posterAnimal = aDao.get_animalname(poster.getANIMAL_ID());
			List<String> replyAnimal = new ArrayList<>();
			List<String> replyName = new ArrayList<>();
			for(int i = 0; i < replyList.size(); i++) {
				REPLY replyInfo = replyList.get(i);
				String animalName = aDao.get_animalname(replyInfo.getANIMAL_ID());
				replyAnimal.add(animalName);
				USER_INFO replyInfoData = uDao.select("", replyInfo.getUSER_ID());
				replyName.add(replyInfoData.getUser_name());
			}

			USER_INFO posterInfo = uDao.select("", poster.getUSER_ID());

			request.setAttribute("reportReplyId", request.getParameter("reportReplyId"));
			request.setAttribute("reportPosterId", request.getParameter("reportPosterId"));
			request.setAttribute("posterName", posterInfo.getUser_name());
			request.setAttribute("replyName", replyName);
			request.setAttribute("replyAnimal", replyAnimal);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("hashtagList", hashtagList);
			request.setAttribute("posterAnimal", posterAnimal);
			request.setAttribute("poster", poster);
			request.setAttribute("replyList", replyList);
		}
		//返信した場合
		else if(replyIdf.equals("2")) {
			int posterId = Integer.parseInt(request.getParameter("posterId"));
			String sentence = request.getParameter("sentence");
			String name = request.getParameter("name");
    		int nameSwitch = 0;
    		if(name.equals("匿名")) {
    			nameSwitch = 1;
    		} else if(name.equals("実名")) {
    			nameSwitch = 2;
    		}
    		String animalId = aDao.get_animalID(posterId, id);
    		REPLY reply = new REPLY(posterId, sentence, nameSwitch, id, animalId);
    		replyDao.insert(reply);

    		List<POSTER> posterList = pDao.select(posterId, 0);
    		POSTER poster = posterList.get(0);
    		List<REPLY> replyList = replyDao.select(0, posterId);

    		int categoryIdInt = poster.getCATEGORY_ID();
    		String categoryId = String.valueOf(categoryIdInt);
    		String categoryName = cDao.get_categoryname(categoryIdInt);
			String[] hashtagList = {"", "", "", "", ""};
			if(poster.getHASHTAGS_ID1() != 0) {
				hashtagList[0] = hDao.get_htagname(poster.getHASHTAGS_ID1());
			}
			if(poster.getHASHTAGS_ID2() != 0) {
				hashtagList[1] = hDao.get_htagname(poster.getHASHTAGS_ID2());
			}
			if(poster.getHASHTAGS_ID3() != 0) {
				hashtagList[2] = hDao.get_htagname(poster.getHASHTAGS_ID3());
			}
			if(poster.getHASHTAGS_ID4() != 0) {
				hashtagList[3] = hDao.get_htagname(poster.getHASHTAGS_ID4());
			}
			if(poster.getHASHTAGS_ID5() != 0) {
				hashtagList[4] = hDao.get_htagname(poster.getHASHTAGS_ID5());
			}

			String posterAnimal = aDao.get_animalname(poster.getANIMAL_ID());
			List<String> replyAnimal = new ArrayList<>();
			List<String> replyName = new ArrayList<>();
			for(int i = 0; i < replyList.size(); i++) {
				REPLY replyInfo = replyList.get(i);
				String animalName = aDao.get_animalname(replyInfo.getANIMAL_ID());
				replyAnimal.add(animalName);
				USER_INFO replyInfoData = uDao.select("", replyInfo.getUSER_ID());
				replyName.add(replyInfoData.getUser_name());
			}

			USER_INFO posterInfo = uDao.select("", poster.getUSER_ID());

			request.setAttribute("posterName", posterInfo.getUser_name());
			request.setAttribute("replyName", replyName);
			request.setAttribute("replyAnimal", replyAnimal);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("categoryIdI", categoryId);
			request.setAttribute("hashtagList", hashtagList);
			request.setAttribute("posterAnimal", posterAnimal);
			request.setAttribute("poster", poster);
			request.setAttribute("replyList", replyList);
		}
		//投稿か返信を通報した場合
		else if(replyIdf.equals("3")) {
			//reportIdfが0なら投稿が、1なら返信が通報されたということ
			String reportIdf = request.getParameter("reportIdf");
			int reportId = Integer.parseInt(request.getParameter("reportId"));
			int posterId = 0;
			if(reportIdf.equals("0")) {
				rDao.insert(reportId, 0);
				posterId = reportId;
				request.setAttribute("resultSentence", "投稿を通報しました");
			} else if(reportIdf.equals("1")) {
				rDao.insert(0, reportId);
				 posterId = Integer.parseInt(request.getParameter("posterId"));
				 request.setAttribute("resultSentence", "返信を通報しました");
			}

    		List<POSTER> posterList = pDao.select(posterId, 0);
    		POSTER poster = posterList.get(0);
    		List<REPLY> replyList = replyDao.select(0, posterId);

    		int categoryIdInt = poster.getCATEGORY_ID();
    		String categoryId = String.valueOf(categoryIdInt);
    		String categoryName = cDao.get_categoryname(categoryIdInt);
			String[] hashtagList = {"", "", "", "", ""};
			if(poster.getHASHTAGS_ID1() != 0) {
				hashtagList[0] = hDao.get_htagname(poster.getHASHTAGS_ID1());
			}
			if(poster.getHASHTAGS_ID2() != 0) {
				hashtagList[1] = hDao.get_htagname(poster.getHASHTAGS_ID2());
			}
			if(poster.getHASHTAGS_ID3() != 0) {
				hashtagList[2] = hDao.get_htagname(poster.getHASHTAGS_ID3());
			}
			if(poster.getHASHTAGS_ID4() != 0) {
				hashtagList[3] = hDao.get_htagname(poster.getHASHTAGS_ID4());
			}
			if(poster.getHASHTAGS_ID5() != 0) {
				hashtagList[4] = hDao.get_htagname(poster.getHASHTAGS_ID5());
			}

			String posterAnimal = aDao.get_animalname(poster.getANIMAL_ID());
			List<String> replyAnimal = new ArrayList<>();
			List<String> replyName = new ArrayList<>();
			for(int i = 0; i < replyList.size(); i++) {
				REPLY replyInfo = replyList.get(i);
				String animalName = aDao.get_animalname(replyInfo.getANIMAL_ID());
				replyAnimal.add(animalName);
				USER_INFO replyInfoData = uDao.select("", replyInfo.getUSER_ID());
				replyName.add(replyInfoData.getUser_name());
			}

			USER_INFO posterInfo = uDao.select("", poster.getUSER_ID());

			request.setAttribute("posterName", posterInfo.getUser_name());
			request.setAttribute("replyName", replyName);
			request.setAttribute("replyAnimal", replyAnimal);
			request.setAttribute("categoryName", categoryName);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("hashtagList", hashtagList);
			request.setAttribute("posterAnimal", posterAnimal);
			request.setAttribute("poster", poster);
			request.setAttribute("replyList", replyList);
		}
		//投稿か返信を削除した場合
		else if(replyIdf.equals("4")) {
			//replyIdfが0なら投稿が、1なら返信が削除されたということ
			String deleteIdf = request.getParameter("deleteIdf");
			int deleteId = Integer.parseInt(request.getParameter("deleteId"));
			if(deleteIdf.equals("0")) {
				List<REPLY> replyList = replyDao.select(0, deleteId);
				for (int  i = 0; i < replyList.size(); i++) {
					REPLY reply = replyList.get(i);
					int reDeleteId = reply.getREPLY_ID();
					replyDao.delete(reDeleteId);
				}
				List<POSTER> posterList = pDao.select(deleteId, 0);
				POSTER poster = posterList.get(0);
				String categoryId = String.valueOf(poster.getCATEGORY_ID());
				pDao.delete(deleteId);
				replyIdf = "-1";
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("replyIdf", replyIdf);
			} else if(deleteIdf.equals("1")) {
				replyDao.delete(deleteId);

				int posterId = Integer.parseInt(request.getParameter("posterId"));
	    		List<POSTER> posterList = pDao.select(posterId, 0);
	    		POSTER poster = posterList.get(0);
	    		List<REPLY> replyList = replyDao.select(0, posterId);

	    		int categoryIdInt = poster.getCATEGORY_ID();
	    		String categoryId = String.valueOf(categoryIdInt);
	    		String categoryName = cDao.get_categoryname(categoryIdInt);
				String[] hashtagList = {"", "", "", "", ""};
				if(poster.getHASHTAGS_ID1() != 0) {
					hashtagList[0] = hDao.get_htagname(poster.getHASHTAGS_ID1());
				}
				if(poster.getHASHTAGS_ID2() != 0) {
					hashtagList[1] = hDao.get_htagname(poster.getHASHTAGS_ID2());
				}
				if(poster.getHASHTAGS_ID3() != 0) {
					hashtagList[2] = hDao.get_htagname(poster.getHASHTAGS_ID3());
				}
				if(poster.getHASHTAGS_ID4() != 0) {
					hashtagList[3] = hDao.get_htagname(poster.getHASHTAGS_ID4());
				}
				if(poster.getHASHTAGS_ID5() != 0) {
					hashtagList[4] = hDao.get_htagname(poster.getHASHTAGS_ID5());
				}

				String posterAnimal = aDao.get_animalname(poster.getANIMAL_ID());
				List<String> replyAnimal = new ArrayList<>();
				List<String> replyName = new ArrayList<>();
				for(int i = 0; i < replyList.size(); i++) {
					REPLY replyInfo = replyList.get(i);
					String animalName = aDao.get_animalname(replyInfo.getANIMAL_ID());
					replyAnimal.add(animalName);
					USER_INFO replyInfoData = uDao.select("", replyInfo.getUSER_ID());
					replyName.add(replyInfoData.getUser_name());
				}

				USER_INFO posterInfo = uDao.select("", poster.getUSER_ID());

				request.setAttribute("resultSentence", "返信を削除しました");
				request.setAttribute("posterName", posterInfo.getUser_name());
				request.setAttribute("replyName", replyName);
				request.setAttribute("replyAnimal", replyAnimal);
				request.setAttribute("categoryName", categoryName);
				request.setAttribute("categoryId", categoryId);
				request.setAttribute("hashtagList", hashtagList);
				request.setAttribute("posterAnimal", posterAnimal);
				request.setAttribute("poster", poster);
				request.setAttribute("replyList", replyList);
			}
		}



		request.setAttribute("replyIdf", replyIdf);
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reply.jsp");
	    dispatcher.forward(request, response);


	}
}
