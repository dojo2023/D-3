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
		<% String err_idf = (String)request.getAttribute("err_idf");
		if(err_idf.equals("0")) { %>
			<a href="/WebApp_GENDA/IDServlet"> 社員番号入力画面へ戻る </a><br>
		<% } else if(err_idf.equals("1")){%>
			<a href="/WebApp_GENDA/PWServlet"> ID入力画面へ戻る </a><br>
		<% } else if(err_idf.equals("2")) {
		 	String en = (String)request.getAttribute("en"); %>
			<form method="POST" name="sq_en" action="/WebApp_GENDA/SQServlet">
				<input type="hidden" name="en" value="${en}">
				<input type="hidden" name="idf" value="1">
			</form>
			<a href="javascript:sq_en.submit()"> 秘密の質問回答画面へ戻る </a><br>
		<% } else if(err_idf.equals("3")) {
			String id = (String)request.getAttribute("id"); %>
			<form method="POST" name="sq_id" action="/WebApp_GENDA/SQServlet">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="idf" value="2">
			</form>
			<a href="javascript:sq_id.submit()"> 秘密の質問回答画面へ戻る </a><br>
		<% } else if(err_idf.equals("4")) {
			String id = (String)request.getAttribute("id");
			String ans = (String)request.getAttribute("ans"); %>
			<form method="POST" name="sq_pw" action="/WebApp_GENDA/PWServlet">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="answer" value="${ans}">
			</form>
			<a href="javascript:sq_pw.submit()"> パスワード再設定画面へ戻る </a><br>
		<% } else if(err_idf.equals("5")) {
			String registerName = (String)request.getAttribute("registerName");
			String registerId = (String)request.getAttribute("registerId");
			String employeeNumber = (String)request.getAttribute("employeeNumber");
			String securityQuestion = (String)request.getAttribute("securityQuestion");
			String securityAnswer = (String)request.getAttribute("securityAnswer"); %>
			<form method="POST" name="sq_pw" action="/WebApp_GENDA/LoginServlet">
				<input type="hidden" name="registerName" value="${registerName}">
				<input type="hidden" name="registerId" value="${registerId}">
				<input type="hidden" name="employeeNumber" value="${employeeNumber}">
				<input type="hidden" name="securityQuestion" value="${securityQuestion}">
				<input type="hidden" name="securityAnswer" value="${securityAnswer}">
				<input type="hidden" name="login_idf" value="1">
			</form>
			<a href="javascript:sq_pw.submit()"> 新規登録画面へ戻る </a><br>
		<% } %>
		<a href="/WebApp_GENDA/LoginServlet"> ログイン画面へ戻る </a>
	</body>
</html>