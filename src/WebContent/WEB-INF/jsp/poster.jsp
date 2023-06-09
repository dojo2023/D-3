<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>掲示板</title>
    <style>
        /* CSS スタイルをここに追加 */
    </style>
</head>
<body>
    <header>
        <div class="logo">
            <img src="https://placehold.jp/300x50.png" alt="ロゴ">
        </div>
        <div class="icons">
            <a href="top.jsp"><img src="https://placehold.jp/50x50.png" alt="ホーム"></a>
            <a href="login.jsp"><img src="https://placehold.jp/50x50.png" alt="ログアウト"></a>
        </div>
    </header>

    <div class="category">

        <form action="category.jsp" method="post">
            <label for="category-select">カテゴリ:</label>
            <select id="category-select" name="category">
                <option value="question">質問</option>
                <option value="application">応募</option>
            </select>
        </form>
    </div>

    <div class="search">
        <form action="searchServlet" method="get">
            <input type="text" name="keyword" placeholder="キーワードを入力してください">
            <button type="submit">検索</button>
        </form>
    </div>

    <div class="create-post">
        <p>新規投稿</p>
    </div>

    <div class="posts">
        <!-- 投稿の一覧表示 -->
        <div class="post">
            <h3><a href="">リンクタイトル</a></h3>
            <p>投稿時刻: </p>
        </div>
        <div class="post">
            <h3><a href="">リンクタイトル</a></h3>
            <p>投稿時刻: </p>
        </div>
        <div class="post">
            <h3><a href="">リンクタイトル</a></h3>
            <p>投稿時刻: </p>
        </div>
        <div class="post">
            <h3><a href="">リンクタイトル</a></h3>
            <p>投稿時刻: </p>
        </div>
        <div class="post">
            <h3><a href="">リンクタイトル</a></h3>
            <p>投稿時刻: </p>
        </div>
        <!-- 追加の投稿をここに繰り返し表示 -->
    </div>

    <div class="pagination">
        <!-- ページボタンの表示 -->
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <!-- 必要な数のページボタンを表示 -->
    </div>
</body>
</html>