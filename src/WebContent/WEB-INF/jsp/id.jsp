<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID忘れ</title>
 <link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body class="flex">
<img src="./images/beforeLogin-left.png" class="pageLeft">
<div class="box">
	<div class="logo">
		<img src="./images/logo.png">
	</div>


		<form action = "/WebApp_GENDA/SQServlet" method = "POST">
			<h2>IDをお忘れの方</h2>
			<p class="message">社員番号を入力してください</p>
			<p class="text">社員番号<input type="text" name="en" class="textbox" placeholder="社員番号を入力してください" required></p>
			<input type="hidden" name="idf" value="1">
			<input type="submit"  class="bottom" value="送信">
			<a href="/WebApp_GENDA/LoginServlet" class="loginBack">ログイン画面へ戻る</a>
		</form>
</div>
</body>
</html>