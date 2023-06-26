<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "model.REPORT" %>
<%@ page import = "model.POSTER" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通報ページ</title>
<link rel="stylesheet" href="./css/beforeLogin.css">
</head>
	<body>
		 <header>
			<a href="TopServlet"> 中庭掲示板ロゴ </a>
			<h1> REPORT </h1>
			<a href="LogoutServlet"> ログアウト </a>
	    </header>
	    <ul>
		    <% List<REPORT> reportList = (List<REPORT>)request.getAttribute("reportList");
		    List<POSTER> posterNewList = (List<POSTER>)request.getAttribute("posterNewList");
		    for(int i = 0; i < posterNewList.size(); i++) {
		    	REPORT report = reportList.get(i);
		    	POSTER poster = posterNewList.get(i); %>
				<li>
					<%= poster.getTITLE() %>
					<%= poster.getPOSTED_DATE() %>
					<form method="POST" action="/WebApp_GENDA/ReplyServlet">
						<% if(report.getREPLY_ID() == 0) { %>
							<input type="hidden" name="reportIdf" value="0">
						<% } else if(report.getREPLY_ID() != 0) { %>
							<input type="hidden" name="reportIdf" value="1">
							<input type="hidden" name="replyId" value="<%= report.getREPLY_ID() %>">
						<% } %>
			    		<input type="hidden" name="posterId" value="<%=  poster.getPOSTER_ID() %>">
			    		<input type="hidden" name="replyIdf" value="1">
			    		<input type="submit" value="詳細" name="submit_button">
			    	</form>
				</li>
		    <% } %>
	    </ul>
	</body>
</html>