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
                <input type="text" name="ID" required>
                <label>パスワード:</label>
                <input type="password" name="PW" required>
                <input type="hidden" name="login_idf" value="0">
            <button type="submit">ログイン</button>
            <a href="/WebApp_GENDA/IDServlet">IDを忘れた場合</a>
            <a href="/WebApp_GENDA/PWServlet">パスワードを忘れた場合</a>
        </form>
    </div>

    <div id="register" class="tab-content">

        <% String login_idf = (String)request.getAttribute("login_idf");
        String[] sq_list = (String[])request.getAttribute("sq_list");
        if(login_idf.equals("0")) { %>
	        <h3>新規登録</h3>
	        <form action="/WebApp_GENDA/InformationCheckServlet" method="post">
	            <label>氏名:</label>
	            <input type="text" name="registerName" required>
	            <label>ID:</label>
	            <input type="text" name="registerId" required>
	            <label>パスワード:</label>
	            <input type="password" name="registerPassword" required>
	            <label>パスワード確認:</label>
	            <input type="password" name="confirmPassword" required>
	            <label>社員番号:</label>
	            <input type="text" name="employeeNumber" required>
	            <label>秘密の質問:</label>
	            <select name="securityQuestion">

	                <option value="1"><%= sq_list[0] %></option>
	                <option value="2"><%= sq_list[1] %></option>
	                <option value="3"><%= sq_list[2] %></option>
	                <option value="4"><%= sq_list[3] %></option>
	                <option value="5"><%= sq_list[4] %></option>
	            </select>
	            <label>質問の答え:</label>
	            <input type="text" name="securityAnswer" required>
	            <button type="submit" name="submit_button" value="登録">登録</button>
	        </form>
	    <% } else if(!login_idf.equals("0")) { %>
		    <% String registerName = (String) request.getAttribute("registerName");
		    String securityQuestion = (String) request.getAttribute("securityQuestion");
		    String securityAnswer = (String) request.getAttribute("securityAnswer"); %>
			<h3>新規登録</h3>
	        <form action="/WebApp_GENDA/InformationCheckServlet" method="POST">
	            <label>氏名:</label>
	            <input type="text" name="registerName" value="${registerName}" required>

	            <% if(login_idf.equals("1")) {
		    	String registerId = (String) request.getAttribute("registerId");
		    	String employeeNumber = (String) request.getAttribute("employeeNumber"); %>
	            <label>ID:</label>
	            <input type="text" name="registerId" value="${registerId}" required>
	            <label>パスワード:</label>
	            <input type="password" name="registerPassword" required>
	            <label>パスワード確認:</label>
	            <input type="password" name="confirmPassword" required>
	            <label>社員番号:</label>
	            <input type="text" name="employeeNumber" value="${employeeNumber}" required>
	            <% } else if(login_idf.equals("2")) {
			    	String registerPassword = (String) request.getAttribute("registerPassword"); %>
		            <label>ID:</label>
		            <input type="text" name="registerId"  required>
		            <label>パスワード:</label>
		            <input type="password" name="registerPassword" value="${registerPassword}" required>
		            <label>パスワード確認:</label>
		            <input type="password" name="confirmPassword" value="${registerPassword}" required>
		            <label>社員番号:</label>
		            <input type="text" name="employeeNumber" required>
		        <% } else if(login_idf.equals("3")) {
		        		String registerId = (String) request.getAttribute("registerId");
				    	String registerPassword = (String) request.getAttribute("registerPassword"); %>
			            <label>ID:</label>
			            <input type="text" name="registerId" value="${registerId}" required>
			            <label>パスワード:</label>
			            <input type="password" name="registerPassword" value="${registerPassword}" required>
			            <label>パスワード確認:</label>
			            <input type="password" name="confirmPassword" value="${registerPassword}" required>
			            <label>社員番号:</label>
			            <input type="text" name="employeeNumber" required>
				<% } else if(login_idf.equals("4")) {
					    	String registerPassword = (String) request.getAttribute("registerPassword");
					    	String employeeNumber = (String) request.getAttribute("employeeNumber"); %>
			            <label>ID:</label>
			            <input type="text" name="registerId"  required>
			            <label>パスワード:</label>
			            <input type="password" name="registerPassword" value="${registerPassword}" required>
			            <label>パスワード確認:</label>
			            <input type="password" name="confirmPassword" value="${registerPassword}" required>
			            <label>社員番号:</label>
			            <input type="text" name="employeeNumber" value="${employeeNumber}" required>
				<% } %>

	            <label>秘密の質問:</label>
	            <select name="securityQuestion">
	                <option value="1" <% if(securityQuestion.equals("1")) { %> selected <% } %>><%= sq_list[0] %></option>
	                <option value="2" <% if(securityQuestion.equals("2")) { %> selected <% } %>><%= sq_list[1] %></option>
	                <option value="3" <% if(securityQuestion.equals("3")) { %> selected <% } %>><%= sq_list[2] %></option>
	                <option value="4" <% if(securityQuestion.equals("4")) { %> selected <% } %>><%= sq_list[3] %></option>
	                <option value="5" <% if(securityQuestion.equals("5")) { %> selected <% } %>><%= sq_list[4] %></option>
	            </select>
	            <label>質問の答え:</label>
	            <input type="text" name="securityAnswer" value="${securityAnswer}" required>
	            <button type="submit" name="submit_button" value="登録">登録</button>
	        </form>
	    <% } %>
    </div>
</html>