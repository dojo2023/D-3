<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="./css/afterLogin.css">
<head>
<meta charset="UTF-8">
<title>設定画面</title>
<style>
//
設定画面のcss /* ----------全体設定---------- */

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
	font-family: "Senobi Gothic";
}

.button {
	border-radius: 67.475px;
	border: 3px solid #294E76;
	background: #FFF;
	color: #294E76;
	text-align: center;
	font-size: 12.738px;
	font-family: "Senobi Gothic";
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
	font-family: "Senobi Gothic";
}


</style>
</head>
<body>
	<div class="header">
		<a href="TopServlet"><img class="logo" src="images/logo.png"
			alt="Logo"> </a>
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
		<h2>Setting (変更/登録用のメッセージボックス)</h2>


		<form action="/WebApp_GENDA/SettingServlet" method="POST">
			お気に入りワードの登録
		<u> <input type="radio" name="item" value="タグ" checked
			class="text">ハッシュタグ <input type="radio" name="item"
			value="カテゴリー" class="text">カテゴリー <input type="radio"
			name="item" value="フリーワード" class="text">フリーワード<br> <input
			type="text" name="newsContent" required class="text"> <input
			type="submit" class="button" value="登録" name="newsChange"
			class="text"></form> <br>
		<br>


			<form action="/WebApp_GENDA/SettingServlet" method="POST"
				name="pwChangeForm" onsubmit="return checkPw()">
				PWの変更<br class="text">
			現在のPW：<input type="password" name="nowPassword" required
			class="text"><br> 新しいPW：<input type="password"
			name="newPassword" required class="text"> <input
			type="submit" class="button" value="変更" name="passwordChange"
			class="text"> </form><br>
		<br>
		<% int userMode = (int)request.getAttribute("userMode");
		if(userMode == 2) { %>
			<form action="/WebApp_GENDA/SettingServlet" method="POST"
				name="adminForm">
				管理者権限<br> 権限の与奪 対象ID：<input type="text" name="userId" required
					class="text"> <input type="submit" name="grant"
					class="button" value="付与" onsubmit="return checkAdminGrant()"
					class="text"> <input type="submit" name="revoke"
					class="button" value="剥奪" onsubmit="return checkAdminRevoke()"
					class="text">
			</form>
			<br>
			<br>
			<form action="/WebApp_GENDA/SettingServlet" method="POST"
				name="enForm" onsubmit="return checkEn()">
				社員番号の変更 対象ID：<input type="text" name="userId" required class="text"><br>
				現在の社員番号：<input type="text" name="nowEn" required class="text"><br>
				新しい社員番号：<input type="text" name="newEn" required class="text">
				<input type="submit" class="button" value="変更" name="enChange"
					class="text"></u>
			</form>
		<% } %>
		<br>
		<br>
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
