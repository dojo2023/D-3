<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <header>
        <div class="logo">
            <img src="https://placehold.jp/300x50.png" alt="ロゴ">
        </div>
    </header>

    <div class="password-reset">
        <form action="resetPassword" method="post">
            <label for="id">IDを入力してください:</label>
            <input type="text" id="id" name="id" placeholder="IDを入力してください" required>

            <input type="submit" value="送信">

            <a href="login.jsp">ログイン画面に戻る</a>
        </form>
    </div>

</body>
</html>