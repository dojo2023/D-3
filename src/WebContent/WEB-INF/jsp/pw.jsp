<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW忘れ</title>
 <link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body>
<div class="box">
	<div class="logo">
		<img src="https://placehold.jp/300x50.png">
	</div>


		<form action="/WebApp_GENDA/SQServlet" method="POST">
			<h2>PWをお忘れの方</h2>
			<p class="message">IDを入力してください</p>
			<p class="text">ID<input type="text" name="id" class="textbox" placeholder="IDを入力してください" required></p>
			<input type="hidden" name="idf" value="2">
			<input type="submit" class="bottom" value="送信">
			<a href="/WebApp_GENDA/LoginServlet" class="loginBack">ログイン画面へ戻る</a>
		</form>
	</div>
</body>
</html>