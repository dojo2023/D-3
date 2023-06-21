<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// ReportServletでリクエストスコープに格納した「viewerName（閲覧者氏名）」を取得
 String viewerName = (String) session.getAttribute("viewerName");
//ReportServletでリクエストスコープに格納した「posterName（投稿者氏名）」を取得
String posterName = (String) session.getAttribute("posterName");
//ReportServletでリクエストスコープに格納した「posterName（返信者氏名）」を取得
String replyerName = (String)session.getAttribute("replyerName");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通報ページ</title>
<!--
	通報されたデータ一覧が表示されるページ
	管理者のみ閲覧可能
	-->
<!--スタイルシートの挿入-->
<link rel="stylesheet" href="./css/beforeLogin.css">
</head>
<body>
	  <header>
        <div class="logo">
            <img src="https://placehold.jp/300x50.png" alt="ロゴ">
      <h1>通報一覧</h1>
        </div>
        <div class="icons">
            <a href="top.jsp"><img src="https://placehold.jp/50x50.png" alt="ホーム"></a>
            <a href="login.jsp"><img src="https://placehold.jp/50x50.png" alt="ログアウト"></a>
        </div>
    </header>
      <table border="1">
      <tr>
        <th>タイトル</th>
        <th>時刻</th>
      </tr>
	  <c:forEach var="i" begin="1" end="7">
        <tr>
        	<td><a href="#">通報記事${i}:${sessionScope.post_title}</a></td>
        	<td>${sessionScope.post_time}</td>
      	</tr>
      </c:forEach>

    </table>
<!-- ページの表記-->
</body>
</html>

</html>