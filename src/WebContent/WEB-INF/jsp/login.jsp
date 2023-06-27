<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン / 新規登録</title>
    <link rel="stylesheet" href="./css/beforeLogin.css">
</head>

<body class="flex">
<div class="place">
        <div class="login-html">
                <img src="./images/beforeLogin-left.png" class="login-page-left">
   <div class="login-form">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">ログイン</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">新規登録</label>

    <div class="login-form">
            <div class="sign-in-htm">
                <form action="/WebApp_GENDA/LoginServlet" method="post">
            <div class="group">
                <label class="login-id">ID
                <input type="text" name="ID" class="textbox" placeholder="IDを入力してください" required></label>
                 </div>
               <div class="group">
                <label for="pass" class="login-pw">PW
                <input type="password" name="PW" class="textbox" placeholder="PWを入力してください" required></label>
                <input type="hidden" name="login_idf" value="0">
                </div>
                <div class="group">
            <button type="submit" class="login-button">ログイン</button>
            </div>
            <a href="/WebApp_GENDA/IDServlet" class="id-message">IDをお忘れの方はこちら</a>
            <a href="/WebApp_GENDA/PWServlet" class="pw-message">パスワードをお忘れの方はこちら</a>
        </form>
    </div>

    <div class="sign-up-htm">
            <div class="group">
        <% String login_idf = (String)request.getAttribute("login_idf");
        String[] sq_list = (String[])request.getAttribute("sq_list");
        if(login_idf.equals("0")) { %>
	        <form action="/WebApp_GENDA/InformationCheckServlet" method="post">
	        	<div class="group">
	            <label  for="user" class="new-name login-text">氏名:
	            <input type="text" name="registerName" class="login-textbox" required></label>
	            </div>
	            <div class="group">
	            <label class="new-id login-text">ID:
	            <input type="text" name="registerId"  class="login-textbox" required></label>
	            </div>
	            <div class="group">
	            <label for="pass" class="new-pw login-text">パスワード:
	            <input type="password" name="registerPassword" class="login-textbox" required></label>
	            </div>
	            <div class="group">
	            <label for="pass" class="new-pw-co login-text">パスワード確認:
	            <input type="password" name="confirmPassword" class="login-textbox" required></label>
	            </div>
	            <div class="group">
	            <label for="pass" class="new-en login-text">社員番号:
	            <input type="text" name="employeeNumber" class="login-textbox" required></label>
	            </div>
	            <label class="new-sq login-text">秘密の質問:
	            <select name="securityQuestion" class="select-sq">

	                <option value="1"><%= sq_list[0] %></option>
	                <option value="2"><%= sq_list[1] %></option>
	                <option value="3"><%= sq_list[2] %></option>
	                <option value="4"><%= sq_list[3] %></option>
	                <option value="5"><%= sq_list[4] %></option>
	            </select></label>
	            <label class="new-sa login-text">質問の答え:
	            <input type="text" name="securityAnswer" class="login-textbox" required></label>
	            <div class="group">
	            <button type="submit" name="submit_button" class="button" value="確認">確認</button>
	            </div>
	        </form>
	    <% } else if(!login_idf.equals("0")) { %>
		    <% String registerName = (String) request.getAttribute("registerName");
		    String securityQuestion = (String) request.getAttribute("securityQuestion");
		    String securityAnswer = (String) request.getAttribute("securityAnswer"); %>
	        <form action="/WebApp_GENDA/InformationCheckServlet" method="POST">
	            <label class="new-name login-text">氏名:
	            <input type="text" name="registerName" class="login-textbox" value="${registerName}"  required></label>

	            <% if(login_idf.equals("1")) {
		    	String registerId = (String) request.getAttribute("registerId");
		    	String employeeNumber = (String) request.getAttribute("employeeNumber"); %>
	            <label class="new-id login-text">ID:
	            <input type="text" name="registerId" class="login-textbox" value="${registerId}"  required></label>
	            <label class="new-pw login-text">パスワード:
	            <input type="password" name="registerPassword" class="login-textbox" required></label>
	            <label class="new-pw-co login-text">パスワード確認:
	            <input type="password" name="confirmPassword" class="login-textbox" required></label>
	            <label class="new-en login-text">社員番号:
	            <input type="text" name="employeeNumber" class="login-textbox" value="${employeeNumber}" required></label>
	            <% } else if(login_idf.equals("2")) {
			    	String registerPassword = (String) request.getAttribute("registerPassword"); %>
		            <label class="new-id login-text">ID:
		            <input type="text" name="registerId" class="login-textbox"  required></label>
		            <label class="new-pw login-text">パスワード:
		            <input type="password" name="registerPassword" class="login-textbox" value="${registerPassword}" required></label>
		            <label class="new-pw-co login-text">パスワード確認:
		            <input type="password" name="confirmPassword" class="login-textbox" value="${registerPassword}" required></label>
		            <label class="new-en login-text">社員番号:
		            <input type="text" name="employeeNumber" class="login-textbox" required></label>
		        <% } else if(login_idf.equals("3")) {
		        		String registerId = (String) request.getAttribute("registerId");
				    	String registerPassword = (String) request.getAttribute("registerPassword"); %>
			            <label class="new-id login-text">ID:
			            <input type="text" name="registerId" class="login-textbox" value="${registerId}" required></label>
			            <label class="new-pw login-text">パスワード:
			            <input type="password" name="registerPassword" class="login-textbox" value="${registerPassword}" required></label>
			            <label class="new-pw-co login-text">パスワード確認:
			            <input type="password" name="confirmPassword" class="login-textbox" value="${registerPassword}" required></label>
			            <label class="new-en login-text">社員番号:
			            <input type="text" name="employeeNumber"  class="login-textbox" required></label>
				<% } else if(login_idf.equals("4")) {
					    	String registerPassword = (String) request.getAttribute("registerPassword");
					    	String employeeNumber = (String) request.getAttribute("employeeNumber"); %>
			            <label class="new-id login-text">ID:
			            <input type="text" name="registerId"  class="login-textbox" required></label>
			            <label class="new-pw login-text">パスワード:
			            <input type="password" name="registerPassword" class="login-textbox" value="${registerPassword}" required></label>
			            <label class="new-pw-co login-text">パスワード確認:
			            <input type="password" name="confirmPassword" class="login-textbox" value="${registerPassword}" required></label>
			            <label class="new-en login-text">社員番号:
			            <input type="text" name="employeeNumber" class="login-textbox" value="${employeeNumber}" required></label>
				<% } %>

	            <label class="new-sq login-text">秘密の質問:
	            <select name="securityQuestion" class="select-sq">
	                <option value="1" <% if(securityQuestion.equals("1")) { %> selected <% } %>><%= sq_list[0] %></option>
	                <option value="2" <% if(securityQuestion.equals("2")) { %> selected <% } %>><%= sq_list[1] %></option>
	                <option value="3" <% if(securityQuestion.equals("3")) { %> selected <% } %>><%= sq_list[2] %></option>
	                <option value="4" <% if(securityQuestion.equals("4")) { %> selected <% } %>><%= sq_list[3] %></option>
	                <option value="5" <% if(securityQuestion.equals("5")) { %> selected <% } %>><%= sq_list[4] %></option>
	            </select></label>
	            <label class="new-sa login-text">質問の答え:
	            <input type="text" name="securityAnswer"  class="login-textbox" value="${securityAnswer}" required></label>
	            <button type="submit" class="button" value="登録">登録</button>
	        </form>
	    <% } %>
	    </div>
        </div>
        </div>
    </div>
    </div>
    </div>
</html>