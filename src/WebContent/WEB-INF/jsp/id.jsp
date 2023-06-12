<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID忘れた人用の画面</title>
</head>
<body>
<div class="logo"><img src="https://placehold.jp/300x50.png"></div>
<div class="idReset">
<form action="WebApp_GENDA/SQServlet.java" method="POST">
<p>社員番号を入力してください</p>
<input type="text" id="id" name="id" placeholder="社員番号を入力してください">
<input type="submit" value="送信">
<a href="/WEB-INF/jsp/login.jsp">ログインへ戻る</a>
</form>
</div>
</body>
</html>