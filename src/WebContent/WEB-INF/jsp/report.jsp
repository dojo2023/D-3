<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.REPORT"%>
<%@ page import="model.POSTER"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通報ページ</title>
<link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body>
	<div class="header">
		<img class="logo" src="images/logo.png" alt="Logo">

		<p class="header-text">REPORT</p>
		<div class="header-icon">
			<a href="LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>
	</div>
	<ul>
		<%
		List<REPORT> reportList = (List<REPORT>) request.getAttribute("reportList");
		List<POSTER> posterNewList = (List<POSTER>) request.getAttribute("posterNewList");
		for(int i = 0; i < posterNewList.size(); i++) {
			REPORT report = reportList.get(i);
			POSTER poster = posterNewList.get(i); %>
			<li><%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
				<form method="POST" action="/WebApp_GENDA/ReplyServlet">
					<%
						if (report.getREPLY_ID() == 0) {
					%>
					<input type="hidden" name="reportIdf" value="0">
					<%
						} else if (report.getREPLY_ID() != 0) {
					%>
					<input type="hidden" name="reportIdf" value="1">
					<input type="hidden" name="replyId" value="<%= report.getREPLY_ID() %>">
					<%
						}
					%>
					<input type="hidden" name="posterId" value="<%= poster.getPOSTER_ID() %>">
					<input type="hidden" name="reportReplyId" value="<%= report.getREPLY_ID() %>">
					<input type="hidden" name="reportPosterId" value="<%= report.getPOSTER_ID() %>">
					<input type="hidden" name="replyIdf" value="1">
					<input type="submit" value="詳細" name="submit_button">
				</form>
			</li>
		<% } %>
	</ul>
	<div class="footer">
		<div class=copyright>
			©Copyright TeamGenda <br>All rights reserved.
		</div>

	</div>
</body>
</html>