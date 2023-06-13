<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン / 新規登録</title>
</head>
<body>
    <h2>ログイン / 新規登録</h2>
    <div class="tab-container">
        <button class="tab-button active" >ログイン</button>
        <button class="tab-button">新規登録</button>
    </div>

    <div id="login" class="tab-content">
        <h3>ログイン</h3>
        <form action="loginServlet" method="post">
            <div>
                <label for="loginId">ID:</label>
                <input type="text" id="loginId" name="loginId">
            </div>
            <div>
                <label for="loginPassword">パスワード:</label>
                <input type="password" id="loginPassword" name="loginPassword">
            </div>
            <button type="submit">ログイン</button>
            <a href="id.jsp">IDを忘れた場合</a>
            <a href="pw.jsp">パスワードを忘れた場合</a>
        </form>
    </div>

    <div id="register" class="tab-content">
        <h3>新規登録</h3>
        <form action="registerServlet" method="post">
        <div>
                <label for="registerId">氏名:</label>
                <input type="text" id="registerId" name="registerId">
            </div>
            <div>
                <label for="registerId">ID:</label>
                <input type="text" id="registerId" name="registerId">
            </div>
            <div>
                <label for="registerPassword">パスワード:</label>
                <input type="password" id="registerPassword" name="registerPassword">
            </div>
            <div>
                <label for="confirmPassword">パスワード確認:</label>
                <input type="password" id="confirmPassword" name="confirmPassword">
            </div>
            <div>
                <label for="employeeNumber">社員番号:</label>
                <input type="text" id="employeeNumber" name="employeeNumber">
            </div>
            <div>
                <label for="securityQuestion">秘密の質問:</label>
                <select id="securityQuestion" name="securityQuestion">
                    <option value="1">母の旧姓は？</option>
                    <option value="2">初めて飼ったペットの名前は？</option>
                    <option value="3">初めて作った料理の名前は？</option>
                </select>
            </div>
            <div>
                <label for="securityAnswer">質問の答え:</label>
                <input type="text" id="securityAnswer" name="securityAnswer">
            </div>
            <button type="submit">登録</button>
        </form>
    </div>
</html>