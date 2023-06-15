<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>決定した個人情報を表示する画面</title>
</head>
<body>
<div class="logo"><img src="https://placehold.jp/300x50.png"></div>
<div class="info">
<h2>登録完了しました</h2>
<!-- 新規登録 -->
<c:if >
<p>氏名<%= request.getParameter("registerName") %></p>

<p>ID<%= request.getParameter("registerId") %></p>

<p>PW<%= request.getParameter("registerPassword") %></p>

<p>社員番号<%= request.getParameter("employeeNumber") %></p>

<p>秘密の質問<%= request.getParameter("securityQuestion") %></p>
</c:if>

<c:if>

</c:if>

<a href="/WEB-INF/jsp/login.jsp">ログインへ戻る</a>
</div>
</body>
</html>