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
<link rel="stylesheet" href="./css/afterLogin.css">
<style>
.rePoster {
    font-size: 30px;
    margin: 0% 0% 0% 16%;
}
.rePoster-line {
	display: inline-block;
}
.detail-line {
	display: inline-block;
}
li {
	padding: 3% 0 0 0;
}
form.input[type="submit"]  {
	width: 124.337px;
	height: 54.999px;
	flex-shrink: 0;
	border-radius: 52.695px;
	border: 0.775px solid #000;
	background: #8BA0B7;
}
.flameContainer {
    width: 1006px;
    height: 468px;
    box-sizing: border-box;
    padding: 80px;
    border: 30px solid #ccc;
    border-image-source: url(./images/flame.png);
    border-image-slice: 300 fill;
    border-image-width: 212px 236px;
    border-image-outset: 0;
    border-image-repeat: round;
    margin-top: 5%;
    margin-left: 10%;
    display: inline-block;
  max-height: 1000px; /* リストの最大高さを指定 */
}
</style>
</head>
<body>
	<div class="header">
		<div class="header-left">
			<a href="TopServlet"><img class="logo" src="images/logo.png"
			alt="Logo"> </a>
		</div>
		<p class="header-text">REPORT</p>
		<div class="header-right">
			<a href="/WebApp_GENDA/LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>
	</div>
	<div class="flameContainer">
	<div class="scrollableContainer">
    <ul id="newItems">
		<%
		List<REPORT> reportList = (List<REPORT>) request.getAttribute("reportList");
		List<POSTER> posterNewList = (List<POSTER>) request.getAttribute("posterNewList");
		for(int i = 0; i < posterNewList.size(); i++) {
			REPORT report = reportList.get(i);
			POSTER poster = posterNewList.get(i); %>
			<li class="rePoster"><div class="rePoster-line"><%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%></div>
				<form method="POST" class="detail-line" action="/WebApp_GENDA/ReplyServlet">
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
	</div>
	</div>
	<div class="footer">
		<div class=copyright>
			©Copyright TeamGenda <br>All rights reserved.
		</div>
	</div>
</body>
</html>














