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
    top: 78px;
    left: 200px;
    font-size: 40px;
}
ul {
    list-style-type: none;
    padding: 10px;
    /* top: 84px; */
    z-index: 300;
    margin-left: 19%;
    position: relative;
    height: 300px;
    overflow: auto;
}
 ul li {
    display: flex; /* 詳細ボタンを横に並べるために Flexbox を使用 */
  align-items: center; /* アイテムを垂直方向に中央揃え */
    border-bottom: solid 2px;
    font-size: 20px;
    margin-top: -20px;
    padding: 10px;
    white-space: nowrap;
}
ul li a {
	display: block;
	text-decoration: none;
	color: #000;
}
/* カテゴリ */
.main-category {
    position: relative;
    margin-top: 0%;
}
.category {
    position: absolute;
    top: 75px;
    left: 200px;
    font-size: 40px;
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
    margin-top: 19%;
}
.category-item span {
	margin-top: -190px;
	margin-left: -20px;
	background-color: #F3E4CF;
	padding: 10px;
	font-size: 35px;
	box-shadow: 6px 7px 4px rgba(0, 0, 0, 0.3);
}
.no-post-message {
	text-align: center;
	margin-top: 20%;
}
.flameContainer {
    width: 1015px;
    height: 566px;
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
.flameContainer2 {
    width: 1015px;
    height: 520px;
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
<link rel="stylesheet" href="./css/afterLogin.css">
</head>
<body>
	<div class="header">
		<div class="header-left">
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
		</div>
		<p class="header-text">HOME</p>
		<div class="header-right">
			<%
				if (userMode == 2) {
			%>
			<a href="/WebApp_GENDA/ReportServlet"> <img class="report-icon"
				src="./images/reportIcon.png" alt="">
			</a>
			<%
				}
			%>
			<a href="/WebApp_GENDA/SettingServlet"> <img class="setting-icon"
				src="./images/settingIcon.png" alt="">
			</a> <a href="/WebApp_GENDA/LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>
	</div>
	<div class="new-message">
		<%
			List<POSTER> posterList = (List<POSTER>) request.getAttribute("posterList");
			String userFavoriteId = (String)request.getAttribute("userFavoriteId");
			String userFavoriteContent = (String)request.getAttribute("userFavoriteContent");
		%>
		<p class="new-text">New <%= userFavoriteId %>：<%= userFavoriteContent %></p>
			<div class="flameContainer">
			<%
				if (posterList.size() == 0) {
			%>
			<div id="no-post-message">新着の投稿はありません。</div>
			<%
				} else {
			%>
  <div class="scrollableContainer">
    <ul id="newItems">
				<%
					POSTER poster = posterList.get(0);
				%>
				<p class="text-number">新着1</p>
				<li>
					<%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
					<form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
						<input type="hidden" name="posterId" value="<%=poster.getPOSTER_ID()%>">
						<input type="hidden" name="replyIdf" value="0">
						<input type="hidden" name="posterIdf" value="0">
						<input type="submit" value="詳細" name="submit_button">
					</form>
				</li>
				<%
					}
				if (posterList.size() > 1) {
				%>
				<%
					POSTER poster = posterList.get(1);
				%>
				<p class="text-number">新着2</p>
				<li>
					<%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
					<form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
						<input type="hidden" name="posterId" value="<%=poster.getPOSTER_ID()%>">
						<input type="hidden" name="replyIdf" value="0">
						<input type="hidden" name="posterIdf" value="0">
						<input type="submit" value="詳細" name="submit_button">
					</form>
				</li>
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
			<p class="text-number">新着3</p>
				<li>
					<%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
					<form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
						<input type="hidden" name="posterId" value="<%=poster.getPOSTER_ID()%>">
						<input type="hidden" name="replyIdf" value="0">
						<input type="hidden" name="posterIdf" value="0">
						<input type="submit" value="詳細" name="submit_button">
					</form>
				</li>
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
			<p class="text-number">新着4</p>
			<li>
				<%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
				<form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
					<input type="hidden" name="posterId" value="<%=poster.getPOSTER_ID()%>">
					<input type="hidden" name="replyIdf" value="0">
					<input type="hidden" name="posterIdf" value="0">
					<input type="submit" value="詳細" name="submit_button">
				</form>
			</li>
			<%
				} else {
			%>
			</ul>
			<%
				}
			if (posterList.size() > 4) {
			POSTER poster = posterList.get(4);
			%>
			<p class="text-number">新着5</p>
			<li>
				<%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
				<form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
					<input type="hidden" name="posterId" value="<%=poster.getPOSTER_ID()%>">
					<input type="hidden" name="replyIdf" value="0">
					<input type="hidden" name="posterIdf" value="0">
					<input type="submit" value="詳細" name="submit_button">
				</form>
			</li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
	</div>
	</div>
	</div>
	<div class="main-category">
		<p class="category">Category</p>
		<%
			List<String> categoryList = (List<String>) request.getAttribute("categoryList");
		%>
		<div class="flameContainer2">
		<div class="new-content2">
			<div class="category-container">
				<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id1">
					<input type="hidden" name="categoryId" value="1"> <input
						type="hidden" name="postIdf" value="0">
				</form>
				<a href="javascript:Id1.submit()">
					<div class="category-item">
						<img src="./images/categoryDoor.png" alt=""> <span>質問</span>
					</div></a>
					<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id2">
						<input type="hidden" name="categoryId" value="2"> <input
							type="hidden" name="postIdf" value="0">
					</form>
					<a href="javascript:Id2.submit()">
						<div class="category-item">
							<img src="./images/categoryDoor.png" alt=""> <span>趣味</span>
						</div>
				</a>
					<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id3">
						<input type="hidden" name="categoryId" value="3"> <input
							type="hidden" name="postIdf" value="0">
					</form>
					<a href="javascript:Id3.submit()">
						<div class="category-item">
							<img src="./images/categoryDoor.png" alt=""> <span>相談</span>
						</div>
				</a>
			</div>
			</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class=copyright>
			©Copyright TeamGenda <br>All rights reserved.
		</div>
	</div>
	<script>
  // 新着リストの親要素とスクロール可能なコンテナ要素を取得
  var newItemsContainer = document.getElementById("newItems");
  var scrollableContainer = document.querySelector(".scrollableContainer");
  // フレームからはみ出た場合にスクロールを有効化
  if (newItemsContainer.offsetHeight > scrollableContainer.offsetHeight) {
    scrollableContainer.style.overflowY = "scroll";
  } else {
    scrollableContainer.style.overflowY = "hidden";
  }
</script>
</body>
</html>