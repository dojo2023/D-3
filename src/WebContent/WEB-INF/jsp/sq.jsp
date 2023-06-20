<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	<p>秘密の質問</p><br><%=sq_name%>
	<form method="POST">
		 秘密の回答 <input type="text" name="answer"><br>

		<% String idf = (String)request.getAttribute("idf");
		if(idf.equals("1")) { %>
			<!-- id表示画面へ送信する -->
			<% String en = (String)request.getAttribute("en"); %>
			<input type="hidden" name="idf" value="1">
			<input type="hidden" name="en" value=${en}>
			<input type="submit" name="submit_button" value="送信"
			formaction="/WebApp_GENDA/INFODisplayServlet">
		<% }else if (idf.equals("2")) {%>
			<!-- pw再設定画面へ送信する -->
			<% String id = (String)request.getAttribute("id"); %>
			<input type="hidden" name="idf" value="2">
			<input type="hidden" name="id" value=${id}>
			<input type="submit" name="submit_button" value="送信"
			formaction="/WebApp_GENDA/PWResetServlet">
		<% } %>
	</form>
	<a href="/WebApp_GENDA/LoginServlet">ログイン画面に戻る</a>
</body>
</html>