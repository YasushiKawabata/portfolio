<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>タイムアウト</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <header>
        <nav>
            <ul>
                <li><a href="index.jsp">トップ</a></li>
            </ul>
        </nav>
        <h1>書籍管理</h1>
    </header>
    <div id="wrap">
        <h2>タイムアウト</h2>
        <p>長時間操作が行われなかったため自動的にログアウトされました。<br>
            30分間操作が行われないと自動的にログアウトします。</p>

        <a class="btn btn--orange btn--radius" href="/bookManager/Login">ログイン画面へ</a>
    </div>
    <footer>
        <%@ include file="/WEB-INF/jsp/copyright.jsp" %>
    </footer>

</body>

</html>