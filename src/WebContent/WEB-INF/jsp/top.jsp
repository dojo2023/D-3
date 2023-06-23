<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "model.POSTER" %>
<%@ page import = "model.CATEGORY" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>トップページ</title>
	</head>
	<body>
		中庭掲示板（左上のやつ）
		<% int userMode = (int)request.getAttribute("userMode");
		if(userMode == 1) { %>
			Mode：一般ユーザ
		<% } else if(userMode == 2) {%>
			Mode：管理者
		<% } %>
		<p> HOME </p>
		<% if(userMode == 2) { %>
			<a href="ReportServlet">通報一覧</a>
		<% } %>
		<a href="SettingServlet">設定</a>
		<a href="LogoutServlet">ログアウト</a>


		<% List<POSTER> posterList = (List<POSTER>)request.getAttribute("posterList"); %>
		<p> New </p>
		<% if(posterList.size() == 0) { %>
			新着の投稿はありません。
		<% } else { %>
		    <ul>
		    	<% POSTER poster = posterList.get(0); %>
		    		<form method="POST" name="posterForm1" action="/WebApp_GENDA/ReplyServlet">
		    			<input type="hidden" name="posterId" value="${poster.getPOSTER_ID()}">
		    		</form>
		    		新着1<li><a href="javascript:posterForm1.submit()"><%= poster.getTITLE() %></a> | <%= poster.getPOSTED_DATE() %></li>
		<% }
		if(posterList.size() > 1) { %>
			<% POSTER poster = posterList.get(1); %>
		    		<form method="POST" name="posterForm2" action="/WebApp_GENDA/ReplyServlet">
		    			<input type="hidden" name="posterId" value="${poster.getPOSTER_ID()}">
		    		</form>
		    		新着2<li><a href="javascript:posterForm2.submit()"><%= poster.getTITLE() %></a> | <%= poster.getPOSTED_DATE() %></li>
		<% } else { %>
			</ul>
		<% }
		if(posterList.size() > 2) { %>
		<% POSTER poster = posterList.get(2); %>
	    		<form method="POST" name="posterForm3" action="/WebApp_GENDA/ReplyServlet">
	    			<input type="hidden" name="posterId" value="${poster.getPOSTER_ID()}">
	    		</form>
	    		新着3<li><a href="javascript:posterForm3.submit()"><%= poster.getTITLE() %></a> | <%= poster.getPOSTED_DATE() %></li>
		<% } else { %>
			</ul>
		<% }
		if(posterList.size() > 3) { %>
		<% POSTER poster = posterList.get(3); %>
	    		<form method="POST" name="posterForm4" action="/WebApp_GENDA/ReplyServlet">
	    			<input type="hidden" name="posterId" value="${poster.getPOSTER_ID()}">
	    		</form>
	    		新着4<li><a href="javascript:posterForm4.submit()"><%= poster.getTITLE() %></a> | <%= poster.getPOSTED_DATE() %></li>
		<% } else { %>
			</ul>
		<% }
		if(posterList.size() > 4) {
			POSTER poster = posterList.get(4); %>
	    	<form method="POST" name="posterForm5" action="/WebApp_GENDA/ReplyServlet">
	    		<input type="hidden" name="posterId" value="${poster.getPOSTER_ID()}">
	    	</form>
	    	新着5<li><a href="javascript:posterForm5.submit()"><%= poster.getTITLE() %></a> | <%= poster.getPOSTED_DATE() %></li>
			</ul>
		<% } %>



		<p> Category </p>
		<% List<String> categoryList = (List<String>)request.getAttribute("categoryList"); %>
		<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id1">
			<input type="hidden" name="categoryId" value="1">
		</form>
		<a href="javascript:Id1.submit()"> <%= categoryList.get(0) %> </a>
		<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id2">
			<input type="hidden" name="categoryId" value="2">
		</form>
		<a href="javascript:Id2.submit()"> <%= categoryList.get(1) %> </a>
		<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id3">
			<input type="hidden" name="categoryId" value="3">
		</form>
		<a href="javascript:Id3.submit()"> <%= categoryList.get(2) %> </a>


	</body>
</html>