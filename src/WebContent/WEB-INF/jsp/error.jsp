<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String err_sen = (String)request.getAttribute("err_sen"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> エラー </title>
		<link rel="stylesheet" href="./css/beforeLogin.css">
	</head>
	<body class="flex">
	<img src="./images/beforeLogin-left.png" class="pageLeft">
	<div class="box">

		<h2> エラーが発生しました </h2>
		<p class="message"> <%= //受け取ったエラー文を表示
		err_sen %> </p>
		<% //エラーが起きた場合をerr_idfを用いて識別、ケースによって保持するデータ等の対応を場合分けで変える
		String err_idf = (String)request.getAttribute("err_idf");
		//社員番号入力画面(id.jspで未登録の社員番号を入力したとき)
		if(err_idf.equals("0")) { %>
			<a href="/WebApp_GENDA/IDServlet"><img src="./images/return.png" class="pageBack"></a><br>
			<a href="/WebApp_GENDA/LoginServlet"><img src="./images/backToTop.png" class="backLogin"></a>
		<% }
		//ID入力画面(pw.jsp)で未登録のIDを入力したとき
		else if(err_idf.equals("1")){%>
			<a href="/WebApp_GENDA/PWServlet"><img src="./images/return.png" class="pageBack"> </a><br>
			<a href="/WebApp_GENDA/LoginServlet"><img src="./images/backToTop.png" class="backLogin"></a>
		<% }
		//社員番号入力後の秘密の質問回答画面(sq.jsp)で回答が合っていなかったとき
		else if(err_idf.equals("2")) {
		 	String en = (String)request.getAttribute("en"); %>
			<form method="POST" name="sq_en" action="/WebApp_GENDA/SQServlet">
				<input type="hidden" name="en" value="${en}">
				<input type="hidden" name="idf" value="1">
			</form>
			<a href="javascript:sq_en.submit()"> <img src="./images/return.png" class="pageBack"> </a><br>
			<a href="/WebApp_GENDA/LoginServlet"><img src="./images/backToTop.png" class="backLogin"></a>
		<% }
		//ID入力後の秘密の質問回答画面(sq.jsp)で回答が合っていなかったとき
		else if(err_idf.equals("3")) {
			String id = (String)request.getAttribute("id"); %>
			<form method="POST" name="sq_id" action="/WebApp_GENDA/SQServlet">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="idf" value="2">
			</form>
			<a href="javascript:sq_id.submit()"> <img src="./images/return.png" class="pageBack"> </a><br>
			<a href="/WebApp_GENDA/LoginServlet"> <img src="./images/backToTop.png" class="backLogin"> </a>
		<% }
		//PW再設定画面(pwReset.jsp)で入力したPWが一致していなかったとき
		else if(err_idf.equals("4")) {
			String id = (String)request.getAttribute("id");
			String ans = (String)request.getAttribute("ans"); %>
			<form method="POST" name="sq_pw" action="/WebApp_GENDA/PWServlet">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="answer" value="${ans}">
			</form>
			<a href="javascript:sq_pw.submit()"> <img src="./images/return.png" class="pageBack"> </a><br>
			<a href="/WebApp_GENDA/LoginServlet"> <img src="./images/backToTop.png" class="backLogin"> </a>
		<% }
		//新規登録画面(login.jsp)で入力したPWが一致していなかったとき
		else if(err_idf.equals("5")) {
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
			<a href="javascript:sq_pw.submit()"> <img src="./images/return.png" class="registError"> </a><br>
		<% }
		//新規登録情報確認画面(InformationCheck.jsp)で情報をデータベースに登録する際に社員番号とIDが重複していたとき
		else if(err_idf.equals("6")) {
			String registerName = (String)request.getAttribute("registerName");
			String registerPassword = (String)request.getAttribute("registerPassword");
			String securityQuestion = (String)request.getAttribute("securityQuestion");
			String securityAnswer = (String)request.getAttribute("securityAnswer"); %>
			<form method="POST" name="sq_pw" action="/WebApp_GENDA/LoginServlet">
				<input type="hidden" name="registerName" value="${registerName}">
				<input type="hidden" name="registerPassword" value="${registerPassword}">
				<input type="hidden" name="securityQuestion" value="${securityQuestion}">
				<input type="hidden" name="securityAnswer" value="${securityAnswer}">
				<input type="hidden" name="login_idf" value="2">
			</form>
			<a href="javascript:sq_pw.submit()"> <img src="./images/return.png" class="registError"> </a><br>
		<% }
		//新規登録情報確認画面(InformationCheck.jsp)で情報をデータベースに登録する際に社員番号が重複していたとき
		else if(err_idf.equals("7")) {
			String registerName = (String)request.getAttribute("registerName");
			String registerId = (String)request.getAttribute("registerId");
			String registerPassword = (String)request.getAttribute("registerPassword");
			String securityQuestion = (String)request.getAttribute("securityQuestion");
			String securityAnswer = (String)request.getAttribute("securityAnswer"); %>
			<form method="POST" name="sq_pw" action="/WebApp_GENDA/LoginServlet">
				<input type="hidden" name="registerName" value="${registerName}">
				<input type="hidden" name="registerId" value="${registerId}">
				<input type="hidden" name="registerPassword" value="${registerPassword}">
				<input type="hidden" name="securityQuestion" value="${securityQuestion}">
				<input type="hidden" name="securityAnswer" value="${securityAnswer}">
				<input type="hidden" name="login_idf" value="3">
			</form>
			<a href="javascript:sq_pw.submit()"> <img src="./images/return.png" class="registError"> </a><br>
		<% }
		//新規登録情報確認画面(InformationCheck.jsp)で情報をデータベースに登録する際にIDが重複していたとき
		else if(err_idf.equals("8")) {
			String registerName = (String)request.getAttribute("registerName");
			String registerPassword = (String)request.getAttribute("registerPassword");
			String employeeNumber = (String)request.getAttribute("employeeNumber");
			String securityQuestion = (String)request.getAttribute("securityQuestion");
			String securityAnswer = (String)request.getAttribute("securityAnswer"); %>
			<form method="POST" name="sq_pw" action="/WebApp_GENDA/LoginServlet">
				<input type="hidden" name="registerName" value="${registerName}">
				<input type="hidden" name="registerPassword" value="${registerPassword}">
				<input type="hidden" name="employeeNumber" value="${employeeNumber}">
				<input type="hidden" name="securityQuestion" value="${securityQuestion}">
				<input type="hidden" name="securityAnswer" value="${securityAnswer}">
				<input type="hidden" name="login_idf" value="4">
			</form>
			<a href="javascript:sq_pw.submit()"> <img src="./images/return.png" class="registError"> </a><br>
		<% }
		//その他データベース関連のエラーが発生したとき
		else if(err_idf.equals("9")) { %>
			<a href="/WebApp_GENDA/LoginServlet"> <img src="./images/backToTop.png" class="loginError"> </a>
		<% }
		//ログイン時にIDまたはPWを間違えたとき
		else if(err_idf.equals("10")) { %>
			<a href="/WebApp_GENDA/LoginServlet" > <img src="./images/backToTop.png" class="loginError"> </a>
		<% } %>
	</div>
	</body>
</html>