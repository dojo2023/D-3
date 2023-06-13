<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/WebApp_GENDA/OgaminoTestServlet" method="POST">
		name:a<input type="text" name="aa"><br>
		<input type="submit" value="送信" name="a"><br><br>
	</form>
	<form action="/WebApp_GENDA/OgaminoTestServlet" method="POST">
		name:b<input type="text" name="bb"><br>
		<input type="submit" value="送信" name="b"><br><br>
	</form>
	<form action="/WebApp_GENDA/OgaminoTestServlet" method="POST">
		name:c<input type="text" name="c"><br>
		<input type="submit" value="付与" name="fuyo">
		<input type="submit" value="解除" name="kaizyo"><br><br>
	</form>
	<form action="/WebApp_GENDA/OgaminoTestServlet" method="POST">
		value:d<input type="radio" value="dd" name="ra">
		value:e<input type="radio" value="ee" name="ra"><br>
		name:f<input type="text" name="f">
		<input type="submit" value="送信" name="d">
	</form>
</body>
</html>