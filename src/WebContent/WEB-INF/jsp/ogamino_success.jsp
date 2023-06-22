<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.POSTER" %>
<%@ page import = "model.REPLY" %>
<%@ page import = "model.REPORT" %>
<%@ page import = "model.USER_INFO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<% boolean result = (boolean)request.getAttribute("SQ"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= result %><br>

	<a href = "/WebApp_GENDA/OgaminoTestServlet"> >>戻る </a>
</body>
</html>