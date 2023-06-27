<%@page import="model.POSTER"%>
<%@page import="java.util.List"%>
<%@ page import = "model.REPLY" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//リクエストスコープからデータを取得
POSTER poster = (POSTER) request.getAttribute("poster");
String id = (String) request.getAttribute("id");
List<REPLY> replyList = (List<REPLY>)request.getAttribute("replyList");
String replyIdf = (String)request.getAttribute("replyIdf");
String categoryName = (String)request.getAttribute("categoryName");
String[] hashtagList = (String[])request.getAttribute("hashtagList");
String posterAnimal = (String)request.getAttribute("posterAnimal");
List<String> replyAnimal = (List<String>)request.getAttribute("replyAnimal");
String posterName = (String)request.getAttribute("posterName");
List<String> replyName = (List<String>)request.getAttribute("replyName");

String test = (String)request.getAttribute("test");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>返信ページ</title>
	</head>
	<body>
		<a href="/TopServlet"> 中庭掲示板(左上) </a>
		<p> Poster </p>
		<a href="/LogoutServlet"> logout </a>

		<!-- 投稿の内容 -->
		<p> Poster </p>
		Category：<%= categoryName %>
		タイトル：<%= poster.getTITLE() %>
		投稿時間：<%= poster.getPOSTED_DATE() %>
		本文：<%= poster.getMAIN_SENTENCE() %>
		ハッシュタグ
		#<%= hashtagList[0] %>
		#<%= hashtagList[1] %>
		#<%= hashtagList[2] %>
		#<%= hashtagList[3] %>
		#<%= hashtagList[4] %>
		<% if(id.equals(poster.getUSER_ID())) { %>
			<form action="/WebApp_GENDA/ReplyServlet" method="POST">
				<input type="hidden" name="deleteId" value="<%= poster.getPOSTER_ID() %>">
				<input type="hidden" name="deleteIdf" value="0">
				<input type="hidden" name="replyIdf" value="4">
				<input type="submit" value="削除">
			</form>
		<% } else { %>
			<form action="/WebApp_GENDA/ReplyServlet" method="POST">
				<input type="hidden" name="reportId" value="<%= poster.getPOSTER_ID() %>">
				<input type="hidden" name="reportIdf" value="0">
				<input type="hidden" name="replyIdf" value="3">
				<input type="submit" value="通報">
			</form>
		<% } %>
		<% if(poster.getUSER_NAME_SWITCH() == 0) { %>
			投稿者：<%= posterAnimal %>
		<% } else if(poster.getUSER_NAME_SWITCH() == 1) { %>
			投稿者：<%= posterName %>
		<% } %>
		<br><%= test %><br>

		<p> Reply </p>
		<form action="/WebApp_GENDA/ReplyServlet" method="POST">
			<input type="text" name="sentence">
			<input type="radio" name="name" value="匿名" checked>匿名
			<input type="radio" name="name" value="実名">実名
			<input type="hidden" name="posterId" value="<%= poster.getUSER_ID() %>">
			<input type="submit" value="返信">
		</form>

		<p> Reply-tree </p>
		<% for(int i = 0; i < replyList.size(); i++) {
			REPLY reply = replyList.get(i); %>
			<%= reply.getREPLY_SENTENCE() %>
			返信時間：<%= reply.getREPLIED_DATE() %>
			<% if(reply.getUSER_NAME_SWITCH() == 0) { %>
				返信者：<%= replyAnimal.get(i) %>
			<% } else if(reply.getUSER_NAME_SWITCH() == 1) { %>
				返信者：<%= replyName.get(i) %>
			<% } %>
			<% if(id.equals(reply.getUSER_ID())) { %>
			<form action="/WebApp_GENDA/ReplyServlet" method="POST">
				<input type="hidden" name="deleteId" value="<%= reply.getREPLY_ID() %>">
				<input type="hidden" name="deleteIdf" value="1">
				<input type="hidden" name="replyIdf" value="4">
				<input type="submit" value="削除">
			</form>
			<% } else { %>
				<form action="/WebApp_GENDA/ReplyServlet" method="POST">
					<input type="hidden" name="reportId" value="<%= reply.getREPLY_ID() %>">
					<input type="hidden" name="reportIdf" value="1">
					<input type="hidden" name="replyIdf" value="3">
					<input type="submit" value="通報">
				</form>
			<% } %>
		<% } %>

	</body>
</html>