<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW忘れ</title>
</head>
<body>
	<div class="logo">
		<img src="https://placehold.jp/300x50.png">
	</div>

		<form action="/WebApp_GENDA/SQServlet" method="POST">
			<p>IDを入力してください</p>
			<input type="text" name="id" placeholder="IDを入力してください">
			<input type="hidden" name="idf" value="2">
			<input type="submit" value="送信">
			<a href="/WebApp_GENDA/LoginServlet">ログインへ戻る</a>
		</form>
</body>
</html>