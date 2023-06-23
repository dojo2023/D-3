<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン / 新規登録</title>
    <link rel="stylesheet" href="./css/beforeLogin.css">
</head>

<body class="flex">
<div class="login-wrap">
        <div class="login-html">
                <img src="./images/beforeLogin-left.png" class="pageLeft">
   <div class="right-content">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">ログイン</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">新規登録</label>

    <div class="login-form">
            <div class="sign-in-htm">
                <form action="/WebApp_GENDA/LoginServlet" method="post">
            <div class="group">
                <label class="label">ID</label>
                <input type="text" name="ID" class="textbox" required>
                 </div>
               <div class="group">
                <label for="pass" class="label">パスワード</label>
                <input type="password" name="PW" class="textbox" required>
                <input type="hidden" name="login_idf" value="0">
                </div>
                <div class="group">
            <button type="submit" class="button">ログイン</button>
            </div>
            <a href="/WebApp_GENDA/IDServlet">IDをお忘れの方はこちら</a>
            <a href="/WebApp_GENDA/PWServlet">パスワードをお忘れの方はこちら</a>
        </form>
    </div>

    <div class="sign-up-htm">
            <div class="group">
        <% String login_idf = (String)request.getAttribute("login_idf");
        String[] sq_list = (String[])request.getAttribute("sq_list");
        if(login_idf.equals("0")) { %>
	        <h3>新規登録</h3>
	        <form action="/WebApp_GENDA/InformationCheckServlet" method="post">
	        	<div class="group">
	            <label  for="user" class="label">氏名:</label>
	            <input type="text" name="registerName" class="textbox" required>
	            </div>
	            <div class="group">
	            <label class="label">ID:</label>
	            <input type="text" name="registerId"  class="textbox" required>
	            </div>
	            <div class="group">
	            <label for="pass" class="label">パスワード:</label>
	            <input type="password" name="registerPassword" class="textbox" required>
	            </div>
	            <div class="group">
	            <label for="pass" class="label">パスワード確認:</label>
	            <input type="password" name="confirmPassword" class="textbox" required>
	            </div>
	            <div class="group">
	            <label for="pass" class="label">社員番号:</label>
	            <input type="text" name="employeeNumber" class="textbox" required>
	            </div>
	            <label class="label">秘密の質問:</label>
	            <select name="securityQuestion">

	                <option value="1"><%= sq_list[0] %></option>
	                <option value="2"><%= sq_list[1] %></option>
	                <option value="3"><%= sq_list[2] %></option>
	                <option value="4"><%= sq_list[3] %></option>
	                <option value="5"><%= sq_list[4] %></option>
	            </select>
	            <label class="label">質問の答え:</label>
	            <input type="text" name="securityAnswer" class="textbox" required>
	            <div class="group">
	            <button type="submit" name="submit_button" class="button" value="確認">確認</button>
	            </div>
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
        </div>
        </div>
    </div>
    </div>
    </div>
</html>