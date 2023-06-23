<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>設定画面	</title>
	</head>
	<body>
	    <header>
	        <div class="logo">
	            <img src="https://placehold.jp/300x50.png" alt="ロゴ">
	        </div>
	            <a href="/WebApp_GENDA/TopServlet">中庭掲示板ロゴ（ページ左上のやつ）</a>
	            <a href="/WebApp_GENDA/LogoutServlet">ログアウト</a>
	    </header>
	    <% String message = (String)request.getAttribute("message"); %>
	    <%= message %>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST">
	        お気に入りワードの登録
	        <input type="radio" name="item" value="タグ" checked>ハッシュタグ
	        <input type="radio" name="item" value="カテゴリー">カテゴリー
	        <input type="radio" name="item" value="フリーワード">フリーワード<br>
	        <input type="text" name="newsContent" required>
	        <input type="submit" value="登録" name="newsChange">
	    </form><br><br>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST" name="pwChangeForm" onsubmit="return checkPw()">
	        PWの変更<br>
	        現在のPW：<input type="password" name="nowPassword" required><br>
	        新しいPW：<input type="password" name="newPassword" required>
	        <input type="submit" value="変更" name="passwordChange">
	    </form><br><br>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST" name="adminForm">
	        管理者権限<br>
	        権限の与奪 対象ID：<input type="text" name="userId" required>
	        <input type="submit" name="grant" value="付与" onsubmit="return checkAdminGrant()">
	        <input type="submit" name="revoke" value="剥奪" onsubmit="return checkAdminRevoke()">
	    </form><br><br>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST" name="enForm" onsubmit="return checkEn()">
	        社員番号の変更 対象ID：<input type="text" name="userId" required><br>
	        現在の社員番号：<input type="text" name="nowEn" required><br>
	        新しい社員番号：<input type="text" name="newEn" required>
	        <input type="submit" value="変更" name="enChange">
	    </form><br><br>
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
					return false;
				}
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
	</body>
</html>
