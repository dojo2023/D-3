<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.USER_SQ" %>
<% USER_SQ SQ = (USER_SQ)request.getAttribute("SQ"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	接続成功
	<%= SQ.getUSER_SQ_ID() %>
	<%= SQ.getUSER_SQ_NAME() %>
	<a href = "/WebApp_GENDA/OgaminoTestServlet"> >>ログイン画面へ戻る </a>
</body>
</html>