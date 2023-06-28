<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.POSTER"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./css/afterLogin.css">
<body>
<div class="wrapper">
	<div class="header">

		<a href="TopServlet"><img class="logo" src="images/logo.png"
			alt="Logo"> </a>
		<div class="header-subtext">
			<%
				String categoryName = (String) request.getAttribute("categoryName");
			String categoryId = (String) request.getAttribute("categoryId");
			%>

				Category：<%=categoryName%>

		</div>
		<p class="header-text">BOARD</p>
		<div class="header-icon">
			<a href="LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>

	</div>

	<br>
	<form action="/WebApp_GENDA/PosterServlet" method="POST" class="form">
	    <div class="search-area">
		<input type="text" name="keyword" class="input search-text" placeholder="    Search">
		<input type="hidden" name="categoryId" value="<%=categoryId%>">
		<input type="hidden" name="categoryName" value="<%=categoryName%>">
		<input type="hidden" name="postIdf" value="1"> <input
			type="image" name="searchButton" src="./images/searchIcon.png" class="search" value="検索">
		</div>
	</form>

	<button id="modalOpen" class="button input create-text"><img src="./images/createIcon.png" alt="" class="pencil">Create New&emsp;</button>
	<div id="easyModal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<h1>Create New</h1>
				<span class="modalClose">×</span>
			</div>
			<div class="modal-body">
				<form method="POST" action="/WebApp_GENDA/PosterServlet">
					<div class="create-title">タイトル<input type="text" name="title"  required></div> <div class="check"><input
						type="radio" name="name"  value="匿名" checked>匿名 <input
						type="radio" name="name"   value="実名">実名</div><br>
						<div class="create-sentence">本文<input
						type="text" name="sentence" class="create-sentence-box" required></div> <div class="create-hashtag">#<input
						type="text" name="hashtag1"> #<input type="text"
						name="hashtag2"><br> #<input type="text" name="hashtag3">
					#<input type="text" name="hashtag4"><br> #<input type="text"
						name="hashtag5"></div><br> <input type="hidden"
						name="categoryId" value="<%=categoryId%>"> <input
						type="hidden" name="categoryName" value="<%=categoryName%>">
					<input type="hidden" name="postIdf" value="2"> <input
						type="submit" name="newFormSubmit" class="input create-input" value="送信">
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
	height: 75%;
	width: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	background-color: #f4f4f4;
    margin: 8% 0 0 15%;
    animation-name: modalopen;
    animation-duration: 1s;
    width: 70%;
    height: 70%;
    border-radius: 67px;
}

.modal-header {
	padding: 3px 15px;
	display: flex;
	justify-content: space-between;
}

.modalClose:hover {
	cursor: pointer;
}

.wrapper {
	width: 100%;
}

.form {
	display: inline-block;
}

.input {
   border-radius: 67.475px;
   border: 5px solid #8BA0B7;
   background: #FFF;
   color: #8BA0B7;
   text-align: center;
   font-family: Senobi Gothic;
}

.search-area {
	position: relative;
	margin: 0% 0% 0% 28%;
	display:  inline-block;
}

.search-text {
	width: 580px;
	heigth: 40px;
	font-size: 35px;
}

::placeholder {
	color: #8BA0B7;
}

.search {
	position: absolute;
	top: 5%;
	left: 2%;
	width: 47px;
	heigth: 45px;
}

.pencil {
	width: 65px;
	heigth: 67px;
}

.create-text {
	font-size: 35px;
	display:  inline-block;
	margin: 0% 0% 0% 15%;
	width: 587px;
}

.poster {
  font-size: 30px;
  margin: 0% 0% 0% 30%;
}

.poster-line {
	display:  inline-block;
}

.detail{
  font-size: 25px;
  width: 100px;
}

.detail-line {
	display:  inline-block;
}

li {
  padding: 3% 0 0 0;
}

.create-title, .create-sentence, .create-hashtag {
	display:  inline-block;
}

.check {
	display:  inline-block;
	font-size: 25px;
}

.create-title, .create-check, .create-sentence,.create-sentence-box, .create-hashtag, .create-input {
	font-size: 25px;
}

.create-sentence {
	margin: 0% 0% 0% 10%;
}

.create-input {
	margin: 7% 0% 0% 50%;
}

.create-sentence-box {
	width: 40%;
	heigth: 40%px;
}

.create-hashtag {
	margin: 0% 0% 0% 30%;
}


</style>

	<ul>
		<%
			List<POSTER> posterList = (List<POSTER>) request.getAttribute("posterList");
		for (int i = 0; i < posterList.size(); i++) {
			POSTER poster = posterList.get(i);
		%>
		<li class="poster"><div class="poster-line"><%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%></div>
			<form method="POST" name="idForm" class="detail-line" action="/WebApp_GENDA/ReplyServlet">
				<input type="hidden" name="posterId"
					value="<%=poster.getPOSTER_ID()%>"> <input type="hidden"
					name="replyIdf" value="0"> <input type="submit" class="input detail" value="詳細"
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
	</div>
</body>
</html>
