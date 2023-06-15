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

<div class="pwReset">
	<form action="/WebApp_GENDA/SQServlet" method="POST">
	<p>新規パスワードを入力して下さい</p>
	<input type="text" id="pwReset" name="pwReset" placeholder="新規パスワード">

	<p>確認</p>
	<input type="text" id="pwConfirm" name="pwConfirm" placeholder="パスワードの確認">
	<input type="submit" name="reset" value="送信">
	<a href="/WEB-INF/jsp/login.jsp">ログインへ戻る</a>
	</form>
</div>

</body>
</html>
