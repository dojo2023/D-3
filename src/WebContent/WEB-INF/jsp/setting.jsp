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
    <form action="" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required>
        <input type="submit" value="送信">
    </form>

    <form action="" method="post">
        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="送信">
    </form>

    <form action="" method="post">
        <label for="new">新着:</label>
        <input type="text" id="new" name="new" placeholder="フリーワード" required>
        <input type="submit" value="送信">
    </form>

    <form action="" method="post">
        <input type="radio" id="radio" name="radio" value="フリーワード">フリーワード
        <input type="radio" id="radio" name="radio" value="カテゴリー">カテゴリー
        <input type="radio" id="radio" name="radio" value="タグ">タグ
    </form>

    <form action="" method="post">
        <label for="admin">管理者権限:</label>
        <input type="text" id="admin" name="admin" required>
        <input type="submit" name="grant" value="付与">
        <input type="submit" name="revoke" value="解除">
    </form>
</body>
</html>
