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
					<div class="create-title">タイトル<input type="text" name="title" class="create-title-box" required></div>
					<div class="check"><input
						type="radio" name="name"  value="匿名" checked>匿名 <input
						type="radio" name="name"   value="実名">実名</div><br>
						<div class="create-sentence">本文<br><input
						type="text" name="sentence" class="create-sentence-box" required></div> <div class="create-hashtag">#<input
						type="text" class="create-hashtag-box" name="hashtag1"> #<input type="text" class="create-hashtag-box"
						name="hashtag2"><br> #<input type="text" class="create-hashtag-box" name="hashtag3">
					#<input type="text" class="create-hashtag-box" name="hashtag4"><br> #<input type="text" class="create-hashtag-box"
						name="hashtag5"></div> <input type="hidden"
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
	height: 100%;
	width: 100%;
	overflow: auto;
	background-color: #00000;
	z-index:400000;
}
.modal-content {
	background-color: #F4F4F4;
    margin: 8% 0 0 15%;
    animation-name: modalopen;
    animation-duration: 1s;
    width: 70%;
    height: 70%;
    border-radius: 67px;
    position: relative;
}
.modal-header {
	padding: 3px 10px;
	display: flex;
	justify-content: space-between;
}
.modalClose{
    font-size: 45px;
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
	width: 456px;
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
	margin: 0% 0% 0% 16%;
	width: 456px;
}
.poster {
  font-size: 30px;
  margin: 0% 0% 0% 0%;
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
ul {
  list-style-type: none;
  padding: 10px;
  z-index: 300;
  margin-left: 10%;
  position: relative;
  height: 300px;
  overflow-x: hidden; /* 横方向のスクロールバーを非表示 */
}
ul li {
  display: flex;
  align-items: center;
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
.create-title, .create-sentence, .create-hashtag,.create-sentence-box {
	display:  inline-block;
}
.check {
	display:  inline-block;
	font-size: 25px;
	margin: 0% 0% 0% 10%;
}
.create-title, .create-check, .create-sentence,.create-sentence-box, .create-hashtag, .create-input {
	font-size: 25px;
}
.create-title-box {
	font-size: 25px;
    width: 250px;
	border: none;
	outline: none;
	background-color: #F3F3F3;
	font-family:Senobi Gothic;
}
.create-title {
	margin: 0% 0% 0% 10%;
	border-bottom: solid 1.5px #294E76;
}
.create-sentence {
	margin: 0% 0% 0% 10%;
}
.create-input {
	width: 10%;
	display: inline-block;
	position: absolute;
	top: 80%;
	left: 70%;
}
.create-sentence-box {
	width: 350px;
    height: 200px;
    top: 375px;
    left: 173px;
    border: 1px
}
.create-sentence-box {
	border: solid 1.5px #294E76;
	outline: none;
	background-color: #F3F3F3;
	font-size: 27.5px;
	font-family:Senobi Gothic;
}
.create-hashtag-box {
	border: none;
	border-bottom: solid 1.5px #294E76;
	outline: none;
	font-size: 14px;
	background-color: #F3F3F3;
	font-family:Senobi Gothic;
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
  max-height: 1000px;
}
newItems{
z-index:40;
}
</style>
<div class="flameContainer">
	<div class="scrollableContainer">
    <ul id="newItems">
		<%
			List<POSTER> posterList = (List<POSTER>) request.getAttribute("posterList");
		for (int i = 0; i < posterList.size(); i++) {
			POSTER poster = posterList.get(i);
		%>
		<li class="poster"><div class="poster-line"><%=poster.getTITLE()%> <%=poster.getPOSTED_DATE()%></div>
			<form method="POST" name="idForm" class="detail-line" action="/WebApp_GENDA/ReplyServlet">
				<input type="hidden" name="posterId"
					value="<%=poster.getPOSTER_ID()%>"> <input type="hidden"
					name="replyIdf" value="0"> <input type="hidden"
					name="posterIdf" value="1"><input type="submit" class="input detail" value="詳細"
					name="submit_button">
			</form></li>
		<%
			}
		%>
	</ul>
	</div>
	</div>
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
		var newItemsContainer = document.getElementById("newItems");
		  var scrollableContainer = document.querySelector(".scrollableContainer");
		  // フレームからはみ出た場合にスクロールを有効化
		  if (newItemsContainer.offsetHeight > scrollableContainer.offsetHeight) {
		    scrollableContainer.style.overflowY = "scroll";
		  } else {
		    scrollableContainer.style.overflowY = "hidden";
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