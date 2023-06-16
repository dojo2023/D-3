<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 新規登録完了時に表示する氏名、ID、PW、社員番号、秘密の質問とその回答をリクエストスコープから取得（INFODisplayServlet.javaで格納してある）
String registerName = (String)request.getAttribute("registerName");
String registerId = (String)request.getAttribute("registerId");
String registerPassword = (String)request.getAttribute("registerPassword");
String employeeNumber = (String)request.getAttribute("employeeNumber");
String securityQuestion = (String)request.getAttribute("securityQuestion");
String securityAnswer = (String)request.getAttribute("securityAnswer");

// ID忘れの際に表示するIDをリクエストスコープから取得（INFODisplayServlet.javaで格納してある）
String id = (String)request.getAttribute("id");

// PW忘れの際に表示するPWをリクエストスコープから取得（INFODisplayServlet.javaで格納してある）
String pw = (String)request.getAttribute("pw");
%>

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
<!-- 新規登録の場合 -->
<div class="newDisplay">
<p>氏名<%= registerName %></p>

<p>ID<%= registerId %></p>

<p>パスワード<%= registerPassword %></p>

<p>社員番号<%= employeeNumber %></p>

<p>秘密の質問<%= securityQuestion %></p>

<p>秘密の質問の回答<%= securityAnswer %></p>
</div>

<!-- IDを忘れた場合 -->
<div class="idDisplay">
<p>ID<%= id %></p>
</div>

<!-- PWを忘れた場合 -->
<div class="pwDisplay">
<p>再設定したパスワード<%= pw %></p>
</div>
<a href="/WEB-INF/jsp/login.jsp">ログインへ戻る</a>
</div>
</body>
</html>