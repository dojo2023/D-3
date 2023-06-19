<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>掲示板</title>
    <style>
        /* 共通のスタイル */
        /* 省略 */

        /* モーダルウィンドウのスタイル */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }
        .modal.show {
            display: flex;
            justify-content: center;
            align-items: center;
            opacity: 1;
        }
        .modal-content {
            background-color: #fff;
            margin: 20px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            width: 400px;
            border-radius: 10px;
        }
        .modal input[type="text"],
        .modal textarea {
            width: 100%;
            margin-bottom: 10px;
        }
        .modal input[type="radio"] {
            margin-right: 5px;
        }
        .modal .hashtags input[type="text"] {
            margin-bottom: 10px;
        }
    </style>
    <script>
        function openModal() {
            var modal = document.getElementById("newPostModal");
            modal.classList.add("show");
        }

        function closeModal() {
            var modal = document.getElementById("newPostModal");
            modal.classList.remove("show");
        }

        function setLinkTitle() {
            var linkTitle = document.getElementById("post-title").value;
            var linkElement = document.getElementById("link-title");
            linkElement.textContent = linkTitle;

            var currentTime = new Date();
            var hours = currentTime.getHours();
            var minutes = currentTime.getMinutes();
            var formattedTime = hours + ":" + (minutes < 10 ? "0" : "") + minutes;

            var postTimeElement = document.getElementById("post-time");
            postTimeElement.textContent = formattedTime;

            closeModal();
        }

        document.querySelector('#frm').addEventListener('submit', (event) => {
            const res = window.confirm('本当に投稿してもいいですか？');
            if (res === false) {
                event.preventDefault();
            }
        }, false);
    </script>

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
    </form>
</div>

<div class="search">
    <form action="" method="get">
        <input type="text" name="keyword" placeholder="キーワードを入力してください">
        <button type="submit">検索</button>
    </form>
</div>

<div class="create-post">
    <p><a href="javascript:openModal()">新規投稿</a></p>
</div>

<div class="posts">
    <!-- 投稿の一覧表示 -->
    <div class="post">
        <h3 class="create-post"><a href="" id="link1"><span id="link-title1"></span></a></h3>
        <p> <span id="post-time1"></span></p>
    </div>
   <div class="post">
        <h3 class="create-post"><a href="" id="link2"><span id="link-title2"></span></a></h3>
        <p><span id="post-time2"></span></p>
    </div>
   <div class="post">
        <h3 class="create-post"><a href="" id="link3"><span id="link-title3"></span></a></h3>
        <p><span id="post-time3"></span></p>
    </div>
    <div class="post">
        <h3 class="create-post"><a href="" id="link4"><span id="link-title4"></span></a></h3>
        <p><span id="post-time4"></span></p>
    </div>
    <div class="post">
        <h3 class="create-post"><a href="" id="link5"><span id="link-title5"></span></a></h3>
        <p><span id="post-time5"></span></p>
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

<!-- 新規投稿のモーダルウィンドウ -->
<div id="newPostModal" class="modal">
    <div class="modal-content">
        <form action="" id="frm" method="post" onsubmit="return setLinkTitle()">
            <label for="post-title">タイトル:</label>
            <input type="text" id="post-title" name="title" required>

            <label for="post-category">カテゴリ:</label>

            <p>匿名または実名:</p>
            <input type="radio" id="anonymous" name="author" value="anonymous" checked>
            <label for="anonymous">匿名</label>
            <input type="radio" id="realname" name="author" value="realname">
            <label for="realname">実名</label>

            <div class="hashtags">
                <p>ハッシュタグ :</p>
                <input type="text" name="hashtag1" maxlength="20">
                <input type="text" name="hashtag2" maxlength="20">
                <input type="text" name="hashtag3" maxlength="20">
                <input type="text" name="hashtag4" maxlength="20">
                <input type="text" name="hashtag5" maxlength="20">
            </div>

            <label for="post-content">本文:</label>
            <textarea id="post-content" name="content" rows="5" required></textarea>

            <button type="submit" onclick="setLinkTitle()">投稿</button>
        </form>
        <button type="button" id="btn" onclick="closeModal()">キャンセル</button>
    </div>
</div>

<script>
    function openModal() {
        var modal = document.getElementById("newPostModal");
        modal.style.display = "flex";
        setTimeout(function () {
            modal.classList.add("show");
        }, 10);
    }

    function closeModal() {
        var modal = document.getElementById("newPostModal");
        modal.classList.remove("show");
        setTimeout(function () {
            modal.style.display = "none";
        }, 300);
    }

    function setLinkTitle() {
        var linkTitle = document.getElementById("post-title").value;
        var linkElement = document.getElementById("link-title1");
        linkElement.textContent = linkTitle;

        var currentTime = new Date();
        var hours = currentTime.getHours();
        var minutes = currentTime.getMinutes();
        var formattedTime = hours + ":" + (minutes < 10 ? "0" : "") + minutes;

        var postTimeElement = document.getElementById("post-time1");
        postTimeElement.textContent = formattedTime;

        closeModal(); // 追加: 投稿後にモーダルを閉じる

        return false; // 追加: フォームのデフォルトの送信をキャンセル
    }

    document.querySelector("#btn").addEventListener("click", () => {
        document.querySelector("#frm").requestSubmit();
    }, false);

    document.querySelector("#frm").addEventListener("submit", (event) => {
        const res = window.confirm("本当に投稿してもいいですか？");
        if (res === false) {
            event.preventDefault();
        }
    }, false);
</script>

</body>
</html>