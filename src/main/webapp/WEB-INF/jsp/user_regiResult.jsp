<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>ユーザー登録完了</title>
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
		<h2>ユーザー登録完了</h2>
		<p>新規ユーザーを登録しました</p><br>
		<a class="btn btn--orange btn--radius" href="/bookManager/Main">書籍メイン画面へ</a>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>

</body>

</html>