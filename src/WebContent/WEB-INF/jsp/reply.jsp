<%@page import="model.POSTER"%>
<%@page import="java.util.List"%>
<%@page import="servlet.ReplyServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//リクエストスコープからデータを取得
POSTER posterList = (POSTER) request.getAttribute("posterList");
String id = (String) request.getAttribute("id");
String postUserId = (String) request.getAttribute("postUserId");
String replyUserId = (String) request.getAttribute("replyUserId");
Integer userNameSwitch = (Integer) request.getAttribute("userNameSwitch");
//どこのページから遷移してきたかを確認する際に使用
String referer = request.getHeader("Referer");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>返信ページ</title>
</head>

<body>
	<%
		if (referer != null && referer.contains("/poster.jsp")) {
		//掲示板から遷移してきた場合
	%>
	<div id="newPostModal" class="modal">
		<div class="modal-content">
			<p id="post-title">
				タイトル:<%=posterList.getTITLE()%></p>

			<p id="category">
				カテゴリ:<%=posterList.getCATEGORY_ID()%></p>

			<%
				if (userNameSwitch == 0) {
			%>
			<p id="post-user_name">投稿者名:実名</p>
			<%
				} else if (userNameSwitch == 1) {
			%>
			<p id="post-user_name">投稿者名:匿名</p>
			<%
				}
			%>

			<p id="post-time">
				投稿日時:<%=posterList.getPOSTED_DATE()%></p>

			<%
				if (id.equals(postUserId)) {
			%>
			<form action="ReplyServlet" method="post">
				<input type="hidden" name="delete_post_id" value="削除する投稿ID">
				<button type="submit">削除</button>
			</form>
			<%
				} else if (!id.equals(postUserId)) {
			%>
			<form action="ReportServlet" method="post">
				<input type="hidden" name="report_post_id" value="通報する投稿ID">
				<button type="submit">通報</button>
			</form>
			<%
				}
			%>
			<p id="hashtags">
				ハッシュタグ:<%=posterList.getHASHTAGS_ID1()%><%=posterList.getHASHTAGS_ID2()%><%=posterList.getHASHTAGS_ID3()%><%=posterList.getHASHTAGS_ID4()%><%=posterList.getHASHTAGS_ID5()%></p>

			<p id="post-content">
				本文：<%=posterList.getMAIN_SENTENCE()%></p>
		</div>
	</div>

	<!-- 返信文の入力フォーム・匿名or実名選択ボタン・返信データの送信ボタン -->
	<div class="input_reply">
		<form action="/WebApp_GENDA/ReplyServlet" method="post">
			<div class="reply_main">
				<input type="text" name="reply">
			</div>

			<div class="name_switch_btn">
				<input type="radio" name="name_switch" value="実名"> 実名 <input
					type="radio" name="name_switch" value="匿名"> 匿名
			</div>

			<div class="submit_btn">
				<button type="submit">送信</button>
			</div>
		</form>

		<!-- 返信文の表示・匿名or実名の表示、削除or通報ボタンの表示 -->
		<c:forEach items="${replyList}" var="reply">
			<div class="reply_list">
				<p id="reply_time">
					返信日時：
					<c:out value="${reply.getREPLIED_DATE()}" />
				</p>
				<p id="reply-content">
					<c:out value="${reply.getREPLY_SENTENCE()}" />
				</p>

				<c:choose>
					<c:when test="${userNameSwitch == 0}">
						<p id="post-user_name">投稿者名:実名</p>
					</c:when>
					<c:when test="${userNameSwitch == 1}">
						<p id="post-user_name">投稿者名:匿名</p>
					</c:when>
				</c:choose>

				<c:choose>
					<c:when test="${id.equals(postUserId)}">
						<form action="ReplyServlet" method="post">
							<input type="hidden" name="delete_post_id" value="削除する投稿ID">
							<button type="submit">削除</button>
						</form>
					</c:when>
					<c:otherwise>
						<form action="ReportServlet" method="post">
							<input type="hidden" name="report_post_id" value="通報する投稿ID">
							<button type="submit">通報</button>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>

	</div>
	<%
		} else if (referer != null && referer.contains("/report.jsp")) {
	//通報ページから遷移してきた場合
	%>
	<div id="newPostModal" class="modal">
		<div class="modal-content">
			<p id="post-title">
				タイトル:<%=posterList.getTITLE()%></p>

			<p id="category">
				カテゴリ:<%=posterList.getCATEGORY_ID()%></p>

			<p id="post-user-id">
				投稿者ID：<%=postUserId%></p>

			<%
				if (userNameSwitch == 0) {
			%>
			<p id="post-user_name">投稿者名:実名</p>
			<%
				} else if (userNameSwitch == 1) {
			%>
			<p id="post-user_name">投稿者名:匿名</p>
			<%
				}
			%>

			<p id="post-time">
				投稿日時:<%=posterList.getPOSTED_DATE()%></p>

			<form action="ReportServlet" method="post">
				<input type="hidden" name="Identify_name" value="USER_NAME_SWITCH">
				<button type="submit">匿名はがしちゃうよ</button>
			</form>

			<p id="hashtags">
				ハッシュタグ:<%=posterList.getHASHTAGS_ID1()%><%=posterList.getHASHTAGS_ID2()%><%=posterList.getHASHTAGS_ID3()%><%=posterList.getHASHTAGS_ID4()%><%=posterList.getHASHTAGS_ID5()%></p>


			<p id="post-content"><%=posterList.getMAIN_SENTENCE()%></p>
		</div>

		<c:forEach items="${replyList}" var="reply">
			<div class="reply_list">

				<p id="reply_time">
					返信日時：
					<c:out value="${reply.getREPLYD_DATE()}" />
				</p>

				<p id="reply-content">
					<c:out value="${reply.getREPLY_SENTENCE()}" />
				</p>

				<p id="reply-user-id">
					返信者ID：
					<c:out value="${reply.getUSER_ID()}" />
				</p>

				<c:choose>
					<c:when test="${userNameSwitch == 0}">
						<p id="post-user_name">投稿者名:実名</p>
					</c:when>
					<c:when test="${userNameSwitch == 1}">
						<p id="post-user_name">投稿者名:匿名</p>
					</c:when>
				</c:choose>

				<form action="ReportServlet" method="post">
					<input type="hidden" name="Identify_name" value="USER_NAME_SWITCH">
					<button type="submit">匿名はがしちゃうよ</button>
				</form>

			</div>
		</c:forEach>
	</div>
	<%
		}
	%>
</body>
</html>