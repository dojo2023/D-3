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

	<div class="pwReset">
		<form action="WebApp_GENDA/SQServlet.java" method="POST">
			<p>IDを入力してください</p>
			<input type="text" name="id" placeholder="IDを入力してください"> <input
				type="hidden" name="idf" value=2> <input type="submit"
				value="送信"> <a href="/WEB-INF/jsp/login.jsp">ログインへ戻る</a>
		</form>
	</div>
</body>
</html>