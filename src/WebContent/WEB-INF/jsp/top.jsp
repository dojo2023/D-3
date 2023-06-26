<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "model.POSTER" %>
<%@ page import = "model.CATEGORY" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>トップページ</title>
	</head>
	<body>
		中庭掲示板（左上のやつ）
		<% int userMode = (int)request.getAttribute("userMode");
		if(userMode == 1) { %>
			Mode：一般ユーザ
		<% } else if(userMode == 2) {%>
			Mode：管理者
		<% } %>
		<p> HOME </p>
		<% if(userMode == 2) { %>
			<a href="ReportServlet">通報一覧</a>
		<% } %>
		<a href="SettingServlet">設定</a>
		<a href="LogoutServlet">ログアウト</a>


		<p> New </p>
		<ul>
		    <% List<POSTER> posterList = (List<POSTER>)request.getAttribute("posterList");
		    for (int i = 0; i < posterList.size(); i++) {
		        POSTER poster = posterList.get(i); %>
			    <li>
			        <%= poster.getTITLE() %>
			        <%= poster.getPOSTED_DATE() %>
			        <form method="POST" name="idForm" action="/WebApp_GENDA/ReplyServlet">
			    		<input type="hidden" name="posterId" value="<%=  poster.getPOSTER_ID() %>">
			    		<input type="hidden" name="replyIdf" value="0">
			    		<input type="submit" value="詳細" name="submit_button">
			    	</form>
			    </li>
		    <% } %>
		</ul>



		<p> Category </p>
		<% List<String> categoryList = (List<String>)request.getAttribute("categoryList"); %>
		<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id1">
			<input type="hidden" name="categoryId" value="1">
			<input type="hidden" name="postIdf" value="0">
		</form>
		<a href="javascript:Id1.submit()"> <%= categoryList.get(0) %> </a>
		<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id2">
			<input type="hidden" name="categoryId" value="2">
			<input type="hidden" name="postIdf" value="0">
		</form>
		<a href="javascript:Id2.submit()"> <%= categoryList.get(1) %> </a>
		<form method="POST" action="/WebApp_GENDA/PosterServlet" name="Id3">
			<input type="hidden" name="categoryId" value="3">
			<input type="hidden" name="postIdf" value="0">
		</form>
		<a href="javascript:Id3.submit()"> <%= categoryList.get(2) %> </a>


	</body>
</html>