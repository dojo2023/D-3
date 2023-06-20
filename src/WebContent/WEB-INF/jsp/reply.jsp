<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
</head>

<body>
    <div class="reply_block1">
        <form action="/WebApp_GENDA/ReplyServlet" method="post">
            <div class="reply_main">
                <input type="text" name="reply">
            </div>

            <div class="name_switch_btn">
                <input type="radio" name="name_switch" value="実名"> 実名
                <input type="radio" name="name_switch" value="匿名"> 匿名
            </div>



            <div class="submit_btn">
                <button type="submit">送信(画像)</button>
            </div>
        </form>
    </div>

    <div class="reply_block2">
            <div class="reply_time"></div>
            <div class=""></div>
            <div class=""></div>
            <div class=""></div>
    </div>
</body>

</html>