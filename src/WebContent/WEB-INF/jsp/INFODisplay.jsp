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
氏名<%= %>
<!-- 送信ボタンを押されたときに、
イベント（id、pw、社員番号、秘密の質問の質問回答を表示させるようにする）を実行する関数をjavascriptで記述する -->
<p id="id"></p>
PW
<p id="pw"></p>
<p>社員番号</p>
<p id="en"></p>
<p>秘密の質問</p>
<p id="sq"></p>
<p id="sa"></p>
<a href="/WEB-INF/jsp/login.jsp">ログインへ戻る</a>
</div>
</body>
</html>