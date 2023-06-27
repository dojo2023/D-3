<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.POSTER"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./css/afterLogin.css">
<body>
	<div class="header">

		<a href="TopServlet"><img class="logo" src="images/logo.png"
			alt="Logo"> </a>
		<div class="header-subtext">
			<%
				String categoryName = (String) request.getAttribute("categoryName");
			String categoryId = (String) request.getAttribute("categoryId");
			%>
			<p>
				Category：<%=categoryName%>
			</p>
		</div>
		<p class="header-text">BOARD</p>
		<div class="header-icon">
			<a href="LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>

	</div>

	<br>

	<form action="/WebApp_GENDA/PosterServlet" method="POST">
		<input type="text" name="keyword" placeholder="キーワードを入力してください">
		<input type="hidden" name="categoryId" value="<%=categoryId%>">
		<input type="hidden" name="categoryName" value="<%=categoryName%>">
		<input type="hidden" name="postIdf" value="1"> <input
			type="submit" name="searchButton" value="検索">
	</form>

	<button id="modalOpen" class="button">新規投稿</button>
	<div id="easyModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<h1>新規投稿</h1>
				<span class="modalClose">×</span>
			</div>
			<div class="modal-body">
				<form method="POST" action="/WebApp_GENDA/PosterServlet">
					タイトル<input type="text" name="title" required><br> 本文<input
						type="text" name="sentence" required><br> <input
						type="radio" name="name" value="匿名" checked>匿名 <input
						type="radio" name="name" value="実名">実名<br> #<input
						type="text" name="hashtag1"> #<input type="text"
						name="hashtag2"> #<input type="text" name="hashtag3">
					#<input type="text" name="hashtag4"> #<input type="text"
						name="hashtag5"><br> <input type="hidden"
						name="categoryId" value="<%=categoryId%>"> <input
						type="hidden" name="categoryName" value="<%=categoryName%>">
					<input type="hidden" name="postIdf" value="2"> <input
						type="submit" name="newFormSubmit" value="送信">
				</form>
			</div>
		</div>
	</div>




	<style>
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	height: 100%;
	width: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	background-color: #f4f4f4;
	margin: 20% auto;
	width: 50%;
	animation-name: modalopen;
	animation-duration: 1s;
}

.modal-header {
	padding: 3px 15px;
	display: flex;
	justify-content: space-between;
}

.modalClose:hover {
	cursor: pointer;
}
</style>

	<ul>
		<%
			List<POSTER> posterList = (List<POSTER>) request.getAttribute("posterList");
		for (int i = 0; i < posterList.size(); i++) {
			POSTER poster = posterList.get(i);
		%>
		<li><%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%>
			<form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
				<input type="hidden" name="posterId"
					value="<%=poster.getPOSTER_ID()%>"> <input type="hidden"
					name="replyIdf" value="0"> <input type="submit" value="詳細"
					name="submit_button">
			</form></li>
		<%
			}
		%>
	</ul>
	<script>
		"use strict";
		const buttonOpen = document.getElementById("modalOpen");
		const modal = document.getElementById("easyModal");
		const buttonClose = document.getElementsByClassName("modalClose")[0];

		// ボタンがクリックされた時
		buttonOpen.addEventListener("click", modalOpen);
		function modalOpen() {
			modal.style.display = "block";
		}

		// バツ印がクリックされた時
		buttonClose.addEventListener("click", modalClose);
		function modalClose() {
			modal.style.display = "none";
		}

		// モーダルコンテンツ以外がクリックされた時
		addEventListener("click", outsideClose);
		function outsideClose(e) {
			if (e.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
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
