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
<link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body class="flex">
<div class="place">
<img src="./images/beforeLogin-left.png" class="page-left">
<div class="box">
<a href="/WebApp_GENDA/LoginServlet"><img src="./images/backToTop.png" class="logo"></a>
	<h2>秘密の質問の回答</h2>
	<p class="message">以下の質問に回答してください</p>
	<p  class="sq-text">秘密の質問<%=sq_name%><br></p>
	<form method="POST">
		<p  class="sa-text">秘密の回答 <input type="text" class="textbox"  name="answer" required><br></p>

		<% String idf = (String)request.getAttribute("idf");
		if(idf.equals("1")) { %>
			<!-- id表示画面へ送信する -->
			<% String en = (String)request.getAttribute("en"); %>
			<input type="hidden" name="idf" value="1">
			<input type="hidden" name="en" value="${en}">
			<input type="submit"   name="submit_button"  class="button" value="送信"
			formaction="/WebApp_GENDA/INFODisplayServlet">
		<% }else if (idf.equals("2")) {%>
			<!-- pw再設定画面へ送信する -->
			<% String id = (String)request.getAttribute("id"); %>
			<input type="hidden" name="id" value="${id}">
			<input type="submit" name="submit_button" class="button" value="送信"
			formaction="/WebApp_GENDA/PWServlet">
		<% } %>
	</form>
	</div>
</div>
</body>
</html>