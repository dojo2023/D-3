<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW再設定画面</title>
<link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body class="flex">
<div class="place">
<img src="./images/beforeLogin-left.png" class="page-left">
<div class="box">
<a href="/WebApp_GENDA/LoginServlet"><img src="./images/backToTop.png" class="logo"></a>

<form action="/WebApp_GENDA/INFODisplayServlet" method="POST">
	<h2>PWの再設定</h2>
	<% String id = (String)request.getAttribute("id"); %>
	<input type="hidden" name="id" value="${id}">
	<% String ans = (String)request.getAttribute("ans"); %>
	<input type="hidden" name="ans" value="${ans}">
	<p class="message">任意のPWを入力してください</p>
	<p class="new-pw-t">新しいPW
	<input type="text" name="newPassword" class="textbox" placeholder="新規パスワード" required></p>
	<p class="check-pw">PW（確認用）
	<input type="text" name="confirmPassword" class="textbox" placeholder="パスワードの確認" required></p>
	<input type="hidden" name="idf" value="2">
	<input type="submit" name="reset" class="button" value="送信">
</form>
</div>
</div>
</body>
</html>
