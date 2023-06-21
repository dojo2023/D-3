<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID忘れ</title>
</head>
<body>
	<div class="logo">
		<img src="https://placehold.jp/300x50.png">
	</div>

		<form action = "/WebApp_GENDA/SQServlet" method = "POST">
			<p>社員番号を入力してください</p>
			<input type="text" name="en" placeholder="社員番号を入力してください">
			<input type="hidden" name="idf" value="1">
			<input type="submit" value="送信">
			<a href="/WebApp_GENDA/LoginServlet">ログイン画面へ戻る</a>
		</form>
</body>
</html>