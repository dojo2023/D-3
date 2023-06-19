<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%
	//秘密の質問の内容をリクエストスコープから取ってくる
	String sq_name = (String) request.getAttribute("sq_name");
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>秘密の質問回答画面</title>
</head>
<body>
	<header> 中庭掲示板 </header>
	<form method="POST">
		<div id="question">
		<p>秘密の質問<%=sq_name%>
		</div><br>
		 秘密の回答 <input type="text" name="answer"><br>

		<% String idf = (String)request.getAttribute("idf");
		if(idf.equals("1")) { %>
		<!-- id表示画面へ送信する -->
		<input type="submit" name="submit_button" value="送信"
			formaction="/WebApp_GENDA/INFODisplayServlet">
		<% }else if (idf.equals("2")) {%>
		<!-- pw再設定画面へ送信する -->
		<input type="submit" name="submit_button" value="送信"
			formaction="/WebApp_GENDA/PWResetServlet">
		<% } %>
	</form>
	<a href="/WebApp_GENDA/LoginServlet">ログイン画面に戻る</a>
</body>
</html>