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

//replyIdf
//-1 → 投稿とそれに紐づいている返信の削除
//0 → 掲示板一覧とTOPの新着画面から投稿詳細を開いた場合
//1 → 通報画面から掲示板詳細を開いた場合
//2 → 返信した場合
//3 → 投稿か返信を通報した場合
//4 → 返信を削除した場合
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>返信ページ</title>
		<link rel="stylesheet" href="./css/afterLogin.css">

		<style>
			.identify{
			    display: inline-block;
			    background: #b6beff;
			    padding: 5px 10px;
			    cursor: pointer;
			}
			.content {
				display:none
			}
			.flameContainer {
    width: 1015px;
    height: 566px;
    box-sizing: border-box;
    padding: 80px;
    border: 30px solid #ccc;
    border-image-source: url(./images/flame.png);
    border-image-slice: 300 fill;
    border-image-width: 212px 236px;
    border-image-outset: 0;
    border-image-repeat: round;
    margin-top: 5%;
    margin-left: 4%;
    display: inline-block;
  max-height: 1000px; /* リストの最大高さを指定 */

}
.newItems {
    list-style-type: none;
    padding: 10px;
    /* top: 84px; */
    z-index: 300;
    margin-left: 19%;
    position: relative;
    height: 300px;
    overflow: auto;
}

		</style>
</head>
	<body>
	<div class="header">
		<div class="header-left">
			<img class="logo" src="images/logo.png" alt="Logo">

		</div>
		<p class="header-text">Poster</p>
		<div class="header-right">
			<a href="/WebApp_GENDA/LogoutServlet"> <img class="logout-icon"
				src="./images/logoutIcon.png" alt="">
			</a>
		</div>
	</div>


		<% if(replyIdf.equals("-1")) {
		//投稿を削除したなら以下の文だけ表示する%>
			<p> 投稿は削除されました </p>
		<% } else {
		//投稿の内容%>
			<% if(replyIdf.equals("3") || replyIdf.equals("4")) {
				//投稿・返信を削除・通報した場合はその旨を以下の文章で示す
				String resultSentence = (String)request.getAttribute("resultSentence");	%>
				<%= resultSentence %>
			<% } %>
			<div class="flameContainer">
			<div class="scrollableContainer">
			<div id="newItems">
			<p class="title"> Poster </p>
			Category：<%= categoryName %>
			タイトル：<%= poster.getTITLE() %>
			投稿時間：<%= poster.getPOSTED_DATE() %><br>
			<% if(replyIdf.equals("1")) {
				//通報ページから入った際は通報された投稿なのかを判定
				int reportReplyId = Integer.parseInt((String)request.getAttribute("reportReplyId"));
				int reportPosterId = Integer.parseInt((String)request.getAttribute("reportPosterId"));
				if(reportReplyId == 0) { %>
					<div class="identify">IDと氏名の表示/非表示</div>
					<div class="content">氏名：<%= posterName %>,ID：<%= poster.getUSER_ID() %></div>
					<br>
				<% }
			} %>
			本文：<%= poster.getMAIN_SENTENCE() %>
			ハッシュタグ
			#<%= hashtagList[0] %>
			#<%= hashtagList[1] %>
			#<%= hashtagList[2] %>
			#<%= hashtagList[3] %>
			#<%= hashtagList[4] %>
			<% if(id.equals(poster.getUSER_ID()) && !replyIdf.equals("1")) {
			//投稿者のIDとログイン者のIDが同じなら削除ボタンと送信するデータを用意 %>
				<form action="/WebApp_GENDA/ReplyServlet" method="POST">
					<input type="hidden" name="deleteId" value="<%= poster.getPOSTER_ID() %>">
					<input type="hidden" name="deleteIdf" value="0">
					<input type="hidden" name="replyIdf" value="4">
					<input type="submit" value="削除">
				</form>
			<% } else if(!replyIdf.equals("1")){
			//投稿者のIDとログイン者のIDが違うなら通報ボタンと送信するデータを用意 %>
				<form action="/WebApp_GENDA/ReplyServlet" method="POST">
					<input type="hidden" name="reportId" value="<%= poster.getPOSTER_ID() %>">
					<input type="hidden" name="reportIdf" value="0">
					<input type="hidden" name="replyIdf" value="3">
					<input type="submit" value="通報">
				</form>
			<% } %>
			<% if(poster.getUSER_NAME_SWITCH() == 1) {
			//匿名なら動物名を表示 %>
				投稿者：<%= posterAnimal %>
			<% } else if(poster.getUSER_NAME_SWITCH() == 2) {
			//実名なら氏名を表示 %>
				投稿者：<%= posterName %>
			<% } %>

			<% if(!replyIdf.equals("1")) {
			//通報画面から入った際は返信フォームを表示しない%>
				<p> Reply </p>
				<form action="/WebApp_GENDA/ReplyServlet" method="POST">
					<input type="text" name="sentence">
					<input type="radio" name="name" value="匿名" checked>匿名
					<input type="radio" name="name" value="実名">実名
					<input type="hidden" name="posterId" value="<%= poster.getPOSTER_ID() %>">
					<input type="hidden" name="replyIdf" value="2">
					<input type="submit" value="返信">
				</form>
			<% } %>

			<p> Reply-tree </p>
			<% for(int i = 0; i < replyList.size(); i++) {
			//上記の投稿に紐づいた返信を全て表示
				REPLY reply = replyList.get(i); %>
				<%= reply.getREPLY_SENTENCE() %>
				返信時間：<%= reply.getREPLIED_DATE() %>

				<% if(replyIdf.equals("1")) {
				//通報ページから入った際は通報された返信なのかを一つずつ判定
					int reportReplyId = Integer.parseInt((String)request.getAttribute("reportReplyId"));
					int reportPosterId = Integer.parseInt((String)request.getAttribute("reportPosterId"));
					if(reportReplyId == reply.getREPLY_ID()) { %>
						<div class="identify">IDと氏名の表示/非表示</div>
						<div class="content">氏名：<%= replyName.get(i) %>,ID：<%= reply.getUSER_ID() %></div>
						<br>
					<% }
				} %>

				<% if(reply.getUSER_NAME_SWITCH() == 1) {
				//匿名なら動物名を表示 %>
					返信者：<%= replyAnimal.get(i) %>
				<% } else if(reply.getUSER_NAME_SWITCH() == 2) {
				//実名なら氏名を表示 %>
					返信者：<%= replyName.get(i) %>
				<% } %>
				<% if(id.equals(reply.getUSER_ID()) && !replyIdf.equals("1")) {
				//返信者のIDとログイン者のIDが同じなら削除ボタンと送信するデータを用意 %>
				<form action="/WebApp_GENDA/ReplyServlet" method="POST">
					<input type="hidden" name="deleteId" value="<%= reply.getREPLY_ID() %>">
					<input type="hidden" name="posterId" value="<%= poster.getPOSTER_ID() %>">
					<input type="hidden" name="deleteIdf" value="1">
					<input type="hidden" name="replyIdf" value="4">
					<input type="submit" value="削除">
				</form>
				<% } else if(!replyIdf.equals("1")){
				//返信者のIDとログイン者のIDが違うなら通報ボタンと送信するデータを用意 %>
					<form action="/WebApp_GENDA/ReplyServlet" method="POST">
						<input type="hidden" name="reportId" value="<%= reply.getREPLY_ID() %>">
						<input type="hidden" name="posterId" value="<%= poster.getPOSTER_ID() %>">
						<input type="hidden" name="reportIdf" value="1">
						<input type="hidden" name="replyIdf" value="3">
						<input type="submit" value="通報">
					</form>
				<% } %>
			<% } %>
		<% } %>
</div>
</div>
</div>
		<script src="https://code.jquery.com/jquery.min.js"></script>
		<script>
			//氏名・IDの表示・非表示に関するjavascriptとCSS
			$(function() {
			    $(".identify").click(function() {
			        $(".content").slideToggle("");
			    });
			});
		</script>

	</body>
</html>