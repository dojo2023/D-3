<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン / 新規登録</title>
    <link rel="stylesheet" href="./css/afterLogin.css">
</head>

<body>
    <h2>ログイン / 新規登録</h2>
    <div class="tab-container">
        <button class="tab-button active" >ログイン</button>
        <button class="tab-button">新規登録</button>
    </div>

    <div id="login" class="tab-content">
        <h3>ログイン</h3>
        <form action="/WebApp_GENDA/LoginServlet" method="post">
                <label>ID:</label>
                <input type="text" name="ID">
                <label>パスワード:</label>
                <input type="password" name="PW">
            <button type="submit">ログイン</button>
            <a href="/WebApp_GENDA/IDServlet">IDを忘れた場合</a>
            <a href="/WebApp_GENDA/PWServlet">パスワードを忘れた場合</a>
        </form>
    </div>

    <div id="register" class="tab-content">
        <h3>新規登録</h3>
        <form action="/WebApp_GENDA/InformationCheckServlet" method="post">
            <label>氏名:</label>
            <input type="text" name="registerName">
            <label>ID:</label>
            <input type="text" name="registerId">
            <label>パスワード:</label>
            <input type="password" name="registerPassword">
            <label>パスワード確認:</label>
            <input type="password" name="confirmPassword">
            <label>社員番号:</label>
            <input type="text" name="employeeNumber">
            <label>秘密の質問:</label>
            <select name="securityQuestion">
            <% String[] sq_list = (String[])request.getAttribute("sq_list"); %>
                <option value="1"><%= sq_list[0] %></option>
                <option value="2"><%= sq_list[1] %></option>
                <option value="3"><%= sq_list[2] %></option>
                <option value="4"><%= sq_list[3] %></option>
                <option value="5"><%= sq_list[4] %></option>
            </select>
            <label>質問の答え:</label>
            <input type="text" name="securityAnswer">
            <button type="submit" name="submit_button" value="登録">登録</button>
        </form>
    </div>
</html>