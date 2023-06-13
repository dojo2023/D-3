<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>設定画面	</title>
	</head>
	<body>
	    <header>
	        <div class="logo">
	            <img src="https://placehold.jp/300x50.png" alt="ロゴ">
	        </div>
	        <div class="botton-icon">
	            <a><img src="https://placehold.jp/50x50.png" alt="ロゴ"></a>
	            <a><img src="https://placehold.jp/50x50.png" alt="ロゴ"></a>
	        </div>
	    </header>

	    <form action="/WebApp_GENDA/SettingServlet" method="post">
	        <label>パスワード:</label>
	        <input type="password" name="password" required>
	        <input type="submit" value="送信" name="password_change">
	    </form>

	    <form action="/WebApp_GENDA/SettingServlet" method="post">
	        <label>新着:</label>
	        <input type="radio" name="radio" value="フリーワード">フリーワード
	        <input type="radio" name="radio" value="カテゴリー">カテゴリー
	        <input type="radio" name="radio" value="タグ">タグ
	        <input type="text" name="news_content" required>
	        <input type="submit" value="送信" name="news_change">
	    </form>

	    <form action="/WebApp_GENDA/SettingServlet" method="post">
	        <label>社員番号:</label>
	        <input type="text" name="user_en" required>
	        <input type="submit" value="送信" name="en_change">
	    </form>

	    <form action="/WebApp_GENDA/SettingServlet" method="post">
	        <label>管理者権限:</label>
	        <input type="text" name="admin" required>
	        <input type="submit" name="grant" value="付与">
	        <input type="submit" name="revoke" value="解除">
	    </form>
	</body>
</html>
