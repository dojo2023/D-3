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

	    <form action="/WebApp_GENDA/SettingServlet" method="POST">
	        お気に入りワードの登録
	        <input type="radio" name="item" value="タグ">ハッシュタグ
	        <input type="radio" name="item" value="カテゴリー">カテゴリー
	        <input type="radio" name="item" value="フリーワード">フリーワード<br>
	        <input type="text" name="newsContent" required>
	        <input type="submit" value="登録" name="newsChange">
	    </form><br><br>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST">
	        PWの変更<br>
	        現在のPW：<input type="password" name="nowPassword" required><br>
	        新しいPW：<input type="password" name="newPassword" required>
	        <input type="submit" value="変更" name="passwordChange">
	    </form><br><br>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST">
	        管理者権限<br>
	        権限の与奪 対象ID：<input type="text" name="userId" required>
	        <input type="submit" name="grant" value="付与">
	        <input type="submit" name="revoke" value="剥奪">
	    </form><br><br>

	    <form action="/WebApp_GENDA/SettingServlet" method="POST">
	        社員番号の変更 対象ID：<input type="text" name="userId" required><br>
	        現在の社員番号：<input type="text" name="nowEn" required><br>
	        新しい社員番号：<input type="text" name="newEn" required>
	        <input type="submit" value="変更" name="enChange">
	    </form><br><br>s
	</body>
</html>
