<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 新規登録完了時に表示する氏名、ID、PW、社員番号、秘密の質問とその回答をリクエストスコープから取得（INFODisplayServlet.javaで格納してある）
String registerName = (String) request.getAttribute("registerName"); // INFODisplayServletでリクエストスコープに格納した「registerName（氏名）」を取得
String registerId = (String) request.getAttribute("registerId"); // INFODisplayServletでリクエストスコープに格納した「registerId（ID）」を取得
String registerPassword = (String) request.getAttribute("registerPassword"); // INFODisplayServletでリクエストスコープに格納した「registerPassword（PW）」を取得
String employeeNumber = (String) request.getAttribute("employeeNumber"); // INFODisplayServletでリクエストスコープに格納した「employeeNumber（社員番号）」を取得
String SQsentence = (String) request.getAttribute("SQsentence"); // INFODisplayServletでリクエストスコープに格納した「securityQuestion（秘密の質問）」を取得
String securityAnswer = (String) request.getAttribute("securityAnswer"); // INFODisplayServletでリクエストスコープに格納した「securityAnswer（秘密の質問の回答）」を取得
String idf = (String) request.getAttribute("idf");
String headline = (String) request.getAttribute("headline");

// ID忘れの際に表示するIDをリクエストスコープから取得（INFODisplayServlet.javaで格納してある）
String id = (String) request.getAttribute("id"); // INFODisplayServletでリクエストスコープに格納した「id（ID）」を取得

// PW忘れの際に表示するPWをリクエストスコープから取得（INFODisplayServlet.javaで格納してある）
String pw = (String) request.getAttribute("pw"); // INFODisplayServletでリクエストスコープに格納した「pw（PW）」を取得
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ユーザ情報の表示 </title>
<link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body class="flex">
<img src="./images/beforeLogin-left.png" class="pageLeft">
<div class="box">
	<a href="/WebApp_GENDA/LoginServlet"><img src="./images/backToTop.png" class="logo"></a>
		<h2><%=headline%></h2>
		<!-- 新規登録の場合に表示するもの -->
		<% if (idf.equals("0")) { %>
		<div id="newDisplay">
			<p class="name">氏名：<%=registerName%></p>

			<p class="userId">ID：<%=registerId%></p>

			<p class="pw">パスワード：<%=registerPassword%></p>

			<p class="en">社員番号：<%=employeeNumber%></p>

			<p class="sq"> 秘密の質問：<%=SQsentence%> </p>

			<p  class="sa">秘密の質問の回答：<%=securityAnswer%></p>
		</div>
		<% } %>

		<% if (idf.equals("1")) { %>
		<!-- IDを忘れた場合に表示するもの -->
		<div id="idDisplay">
			<p class="text">ID：<%=id%></p>
		</div>
		<% } %>

		<% if (idf.equals("2")) { %>
		<!-- PWを忘れた場合に表示するもの -->
		<div id="pwDisplay">
			<p class="text">再設定したパスワード：<%=pw%></p>
		</div>
		<% } %>
</div>
</body>
</html>