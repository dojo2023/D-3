<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String err_sen = (String)request.getAttribute("err_sen"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> エラー </title>
	</head>
	<body>
		<h2> エラーが発生しました </h2>
		<p> <%= err_sen %> </p>
		<a href="/WebApp_GENDA/LoginServlet">ログイン画面へ戻る</a>
	</body>
</html>