<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.POSTER"%>
<%@ page import="model.CATEGORY"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<style>
html {
	font-family: Senobi Gothic; /*font-family:ページ全体のフォントを指定*/
	color: #294E76; /*文字色*/
}

body {
	background-color: #F3F3F3;
}

/*新着欄*/
.new-message {
	position: relative;
}

.new-text {
	position: absolute;
	top: 12px;
	left: 104px;
	font-size: 40px;
}

.new-content {
	border-image: url(./images/flame1.png);
	border-image-slice: 100 fill;
	border-image-width: 160px;
	border-image-outset: 20px;
	border-image-repeat: stretch;
	height: 630px;
	padding: 100px;
	box-sizing: border-box;
}

ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

ul li {
	/* margin-bottom: 0px; */
	margin-left: 25%;
	margin-top: 4%;
	border-bottom: solid 2px;
	width: 50%;
	font-size: 20px;
}

ul li a {
	display: block;
	text-decoration: none;
	color: #000;
}
/* カテゴリ */
.main-category {
	position: relative;
}

.category {
	position: absolute;
	top: 12px;
	left: 104px;
	font-size: 40px;
}

.new-content2 {
	border-image: url(./images/flame1.png);
	border-image-slice: 100 fill;
	border-image-width: 160px;
	border-image-outset: 20px;
	border-image-repeat: stretch;
	height: 720px;
	padding: 100px;
	box-sizing: border-box;
}

.category-container {
	display: flex;
	justify-content: space-between;
}

.category-container a {
	text-decoration: none;
	color: #000;
}

.category-item {
	position: relative;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.category-item img {
	width: 100%;
	margin-top: 30%;
}

.category-item span {
	margin-top: -240px;
	margin-left: -20px;
	background-color: #f3e4cf;
	padding: 10px;
	font-size: 35px;
	box-shadow: 6px 7px 4px rgba(0, 0, 0, 0.3);
}
</style>
<link rel="stylesheet" href="./css/afterLogin.css">
</head>
<body>
	<div class="header">
		<img class="logo" src="images/logo.png" alt="Logo">
		<div class="header-subtext">
			<%
				int userMode = (int) request.getAttribute("userMode");
			if (userMode == 1) {
			%>
			Mode：一般ユーザ
			<%
				} else if (userMode == 2) {
			%>
			Mode：管理者
			<%
				}
			%>
		</div>
		<p class="header-text">HOME</p>
		<div class="header-icon">
			<%
				if (userMode == 2) {
			%>
			<a href="ReportServlet"> <img class="report-icon"
				src="./images/reportIcon.png" alt="">
			</a>
			<%
				}
			%>
			<a href="SettingServlet"> <img class="setting-icon"
				src="./images/settingIcon.png" alt="">
			</a> <a href="LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>
	</div>


	<div class="new-message">
		<%
			List<POSTER> posterList = (List<POSTER>) request.getAttribute("posterList");
		%>
		<p class="new-text">New</p>
		<div class="new-content">
			<%
				if (posterList.size() == 0) {
			%>
			新着の投稿はありません。
			<%
				} else {
			%>
			<ul>
				<%
					POSTER poster = posterList.get(0);
				%>
				<form method="POST" name="posterForm1"
					action="/WebApp_GENDA/ReplyServlet">
					<input type="hidden" name="posterId"
						value="${poster.getPOSTER_ID()}">
				</form>
				新着1
				<li><a href="javascript:posterForm1.submit()"><%=poster.getTITLE()%></a>
					| <%=poster.getPOSTED_DATE()%></li>
				<%
					}
				if (posterList.size() > 1) {
				%>
				<%
					POSTER poster = posterList.get(1);
				%>
				<form method="POST" name="posterForm2"
					action="/WebApp_GENDA/ReplyServlet">
					<input type="hidden" name="posterId"
						value="${poster.getPOSTER_ID()}">
				</form>
				新着2
				<li><a href="javascript:posterForm2.submit()"><%=poster.getTITLE()%></a>
					| <%=poster.getPOSTED_DATE()%></li>
				<%
					} else {
				%>
			</ul>
			<%
				}
			if (posterList.size() > 2) {
			%>
			<%
				POSTER poster = posterList.get(2);
			%>
			<form method="POST" name="posterForm3"
				action="/WebApp_GENDA/ReplyServlet">
				<input type="hidden" name="posterId"
					value="${poster.getPOSTER_ID()}">
			</form>
			新着3
			<li><a href="javascript:posterForm3.submit()"><%=poster.getTITLE()%></a>
				| <%=poster.getPOSTED_DATE()%></li>
			<%
				} else {
			%>
			</ul>
			<%
				}
			if (posterList.size() > 3) {
			%>
			<%
				POSTER poster = posterList.get(3);
			%>
			<form method="POST" name="posterForm4"
				action="/WebApp_GENDA/ReplyServlet">
				<input type="hidden" name="posterId"
					value="${poster.getPOSTER_ID()}">
			</form>
			新着4
			<li><a href="javascript:posterForm4.submit()"><%=poster.getTITLE()%></a>
				| <%=poster.getPOSTED_DATE()%></li>
			<%
				} else {
			%>
			</ul>
			<%
				}
			if (posterList.size() > 4) {
			POSTER poster = posterList.get(4);
			%>
			<form method="POST" name="posterForm5"
				action="/WebApp_GENDA/ReplyServlet">
				<input type="hidden" name="posterId"
					value="${poster.getPOSTER_ID()}">
			</form>
			新着5
			<li><a href="javascript:posterForm5.submit()"><%=poster.getTITLE()%></a>
				| <%=poster.getPOSTED_DATE()%></li>
			</ul>
			<%
				}
			%>
		</div>
	</div>

	<div class="main-category">
		<p class="category">Category</p>
		<%
			List<String> categoryList = (List<String>) request.getAttribute("categoryList");
		%>
		<div class="new-content2">
			<div class="category-container">
				<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id1">
					<input type="hidden" name="categoryId" value="1"> <input
						type="hidden" name="postIdf" value="0">
				</form>
				<a href="javascript:Id1.submit()">
					<div class="category-item">
						<img src="./images/categoryDoor.png" alt=""> <span>質問</span>
					</div>
					<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id2">
						<input type="hidden" name="categoryId" value="2"> <input
							type="hidden" name="postIdf" value="0">
					</form> <a href="javascript:Id2.submit()">
						<div class="category-item">
							<img src="./images/categoryDoor.png" alt=""> <span>趣味</span>
						</div>
				</a>
					<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id3">
						<input type="hidden" name="categoryId" value="3"> <input
							type="hidden" name="postIdf" value="0">
					</form> <a href="javascript:Id3.submit()">
						<div class="category-item">
							<img src="./images/categoryDoor.png" alt=""> <span>相談</span>
						</div>
				</a>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class=copyright>
			©Copyright TeamGenda <br>All rights reserved.
		</div>
		<div>
			<img class="up-icon" src="images/up.png" alt="up-icon">
		</div>
	</div>
</body>
</html>