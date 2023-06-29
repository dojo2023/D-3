<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./css/afterLogin.css">
<head>
<meta charset="UTF-8">
<title>設定画面</title>
<style>
//設定画面のcss
/* ----------全体設定---------- */
.button {
	width: 4em;
	max-width: 80%;
	border: none;
	outline: none;
	background-color: #F3F3F3;
	font-size: 12.761px;
	flex-shrink: 0;
}
/* プレースホルダーの書式設定 */
::placeholder {
	font-family: Senobi Gothic;
}
.button {
	border-radius: 67.475px;
	border: 3px solid #294E76;
	background: #FFF;
	color: #294E76;
	text-align: center;
	font-size: 12.738px;
	font-family: Senobi Gothic;
}
/*表に下線を引く*/
.table {
	width: 13em;
	max-width: 100%;
	border: none;
	outline: none;
	background-color: #F3F3F3;
	font-size: 12.5px;
	flex-shrink: 0;
	font-family: Senobi Gothic;
}
.favorite {
display:inline-block;
font-family:Senobi Gothic;
}
.register {
 color: #fff;
 text-align: center;
 line-height: 100px;
 width: 100px;
 height: 100px;
 font-family: Senobi Gothic;
 font-size: 80px;
 font-weight: 700;
}
.text {
	border: none;　/* 枠線消す設定 */
	outline: none; /* クリック時の枠線消す設定 */
	background-color: #F3F3F3;　/* 背景色の設定 */　
	font-family:Senobi Gothic;　/* フォントの設定 */
}
.line {
border-bottom: solid 2px #294E76;
line-height: 48.622px;
letter-spacing: 2.768px;
flex-shrink: 0;
	width: 641.857px;
}
/*ヘッダーの設定 */
.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	text-align: center;
	background-color: #EAEAEA;
	height: 100px;
	margin-top: 0; /* 上部の隙間をなくす */
	position: relative;
}
.header a {
	text-decoration: none;
}
.logo {
	width: auto;
	height: 100px;
	text-align: right;
}
/*フッターの設定 */
.footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: #294E76;
	height: 70px;
	margin: 0;
	padding: 0;
	position: absolute;
	bottom: 0;
}
.copyright {
	color: #EAEAEA;
	text-align: center;
	font-weight: 700;
	font-family: "Senobi Gothic";
	margin: auto;
}
.up-icon {
	width: auto;
	height: 70px;
	padding: 10px 20px 10px 10px;
}
html{
  font-family:Senobi Gothic;
  font-size: 17px;
  font-weight:  bold;
}
.center {
    width: 1015px;

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
.center-admin{
margin-left: 10%;
}
h2{
    margin-top: -4%;
    margin-bottom: 7%;
    margin-left:-12%;
}
.center-content{
margin-left: 10%;
}
</style>
</head>
<body>
	<div class="header">
		<a href="TopServlet"><img class="logo" src="images/logo.png"
			alt="Logo"> </a>
		<% String id = (String)request.getAttribute("id"); %>
		ID:<%= id %>
		<p class="header-text">SETTING</p>
		<div class="header-icon">
			<a href="LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>
	</div>
	<% String message = (String)request.getAttribute("message"); %>
	<%= message %>
	<div class="center">

	<div class="center-content">
		<h2>Setting (変更/登録用のメッセージボックス)</h2>
		<form action="/WebApp_GENDA/SettingServlet" method="POST">
			<div class = "favorite line">お気に入りワードの登録
		   <input type="radio" name="item" value="タグ" checked
			class="text">ハッシュタグ <input type="radio" name="item"
			value="カテゴリー" class="text">カテゴリー <input type="radio"
			name="item" value="フリーワード" class="text">フリーワード </div>
			<div class = "line">登録ワード：<input type="text" name="newsContent" required class="text">
			<input type="submit" class="button" value="登録" name="newsChange"
			class="text"></div></form>
			<form action="/WebApp_GENDA/SettingServlet" method="POST"
			name="pwChangeForm" onsubmit="return checkPw()">
				<div class = "line">PWの変更</div>
		   <div class = "line">現在のPW：<input type="password" name="nowPassword" required
			class="text"></div><div class = "line">新しいPW：<input type="password"
			name="newPassword" required class="text"> <input
			type="submit" class="button" value="変更" name="passwordChange"
			class="text"> </div></form>
			</div>


<div class="center-admin">
			<% int userMode = (int)request.getAttribute("userMode");
		if(userMode == 2) { %>

			<form action="/WebApp_GENDA/SettingServlet" method="POST"
				name="adminForm"><br>
				<div class = "line">管理者権限</div> <div class = "line">権限の与奪 対象ID：<input type="text" name="userId" required
					class="text"> <input type="submit" name="grant"
					class="button" value="付与" onsubmit="return checkAdminGrant()"
					class="text"> <input type="submit" name="revoke"
					class="button" value="剥奪" onsubmit="return checkAdminRevoke()"
					class="text"></div>
			</form>

			<form action="/WebApp_GENDA/SettingServlet" method="POST"
				name="enForm" onsubmit="return checkEn()">
				<div class = "line">社員番号の変更 対象ID：<input type="text" name="userId" required class="text"></div><br>
				<div class = "line">現在の社員番号：<input type="text" name="nowEn" required class="text"></div><br>
				<div class = "line">新しい社員番号：<input type="text" name="newEn" required class="text">
				<input type="submit" class="button" value="変更" name="enChange"
					class="text"></div>
		</form>
		</div>
		  <% } %>
		</div>

	<script>
	    	"use strict";
	    	function checkPw() {
				const nowPassword = pwChangeForm.nowPassword.value;
				const newPassword = pwChangeForm.newPassword.value;
				if(window.confirm("パスワードを「" + nowPassword + "」から「" + newPassword + "」に変更します。よろしいですか？")) {
					return true;
				} else {
					return false;
				}
	    	}
	    	const grantButton = adminForm.grant;
	    	const revokeButton = adminForm.revoke;
	    	grantButton.addEventListener("click", () => {
	    		const userId = adminForm.userId.value;
	    		if(window.confirm("「" + userId + "」に管理者権限を付与します。よろしいですか？")) {
					return true;
				} else {
					return false;
				}
	    	});
	    	revokeButton.addEventListener("click", () => {
	    		const userId = adminForm.userId.value;
	    		if(window.confirm("「" + userId + "」の管理者権限を剥奪します。よろしいですか？")) {
					return true;
				} else {
				}					return false;
	    	});
	    	function checkEn() {
				const userId = enForm.userId.value;
				const nowEn = enForm.nowEn.value;
				const newEn = enForm.newEn.value;
				if(window.confirm("「" + userId + "」の社員番号を「" + nowEn + "」から「" + newEn + "」に変更します。よろしいですか？")) {
					return true;
				} else {
					return false;
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