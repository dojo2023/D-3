<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>秘密の質問回答画面</title>
</head>
<body>
	<header> 中庭掲示板 </header>
	<form method="POST">
		秘密の質問 <select name="question">
			<option value="1">秘密の質問1</option>
			<option value="2">秘密の質問2</option>
			<option value="3">秘密の質問3</option>
			<option value="4">秘密の質問4</option>
			<option value="5">秘密の質問5</option>
		</select><br> 秘密の回答 <input type="text" name="answer"><br>

		<% String idf = (String)request.getAttribute("idf");
		if(idf.equals("0")) {%>
		<!-- pw再設定画面へ送信する -->
		<input type="submit" name="submit_button" value="送信"
			formaction="/WebApp_GENDA/PWResetServlet">
		<% } else if(idf.equals("1")) { %>
		<!-- id表示画面へ送信する -->
		<input type="submit" name="submit_button" value="送信"
			formaction="/WebApp_GENDA/INFODisplayServlet">
		<% } %>
	</form>
	<a href="/WebApp_GENDA/LoginServlet">ログイン画面に戻る</a>
</body>
</html>