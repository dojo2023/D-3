<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 秘密の質問回答画面 </title>
</head>
<body>
	<header>
		中庭掲示板
	</header>
	<form method="POST">
		秘密の質問 <select name="question">
		<option> 秘密の質問1 </option>
		<option> 秘密の質問2 </option>
		<option> 秘密の質問3 </option>
		<option> 秘密の質問4 </option>
		<option> 秘密の質問5 </option>
		</select><br>
		秘密の回答 <input type="text" name="answer"><br>
		<% String idf = (String)request.getAttribute("idf");
		if(idf.equals("0")) {%>
			<input type="submit" name="submit_button" value="送信" formaction="/WebApp_GENDA/PEResetServlet">
		<% } else if(idf.equals("1")) { %>
			<input type="submit" name="submit_button" value="送信" formaction="/WebApp_GENDA/IDDisplayServlet">
		<% } %>
	</form>
</body>
</html>