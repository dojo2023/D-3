<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW再設定画面</title>
</head>
<body>
<div><img src="https://placehold.jp/300x50.png"></div>

<form action="/WebApp_GENDA/INFODisplayServlet" method="POST">
	<% String id = (String)request.getAttribute("id"); %>
	<input type="hidden" name="id" value="${id}">
	<p>新規パスワードを入力して下さい</p>
	<input type="text" name="newPassword" placeholder="新規パスワード">

	<p>確認</p>
	<input type="text" name="confirmPassword" placeholder="パスワードの確認">
	<input type="hidden" name="idf" value="2">
	<input type="submit" name="reset" value="送信">
	<a href="/WebApp_GENDA/LoginServlet">ログイン画面へ戻る</a>
</form>

</body>
</html>
