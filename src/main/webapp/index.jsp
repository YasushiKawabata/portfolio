<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>トップ画面</title>
	<!-- <link rel="stylesheet" href="css/all.css"> -->
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<header>
		<h1>書籍管理</h1>
	</header>
	<div id="wrap">
		<h2>トップ</h2>
		<a class="btn btn--orange btn--radius" href="/bookManager/Login">ログイン</a>
		<br>
		<a class="btn btn--orange btn--radius" href="/bookManager/Login?action=register">ユーザー登録</a>
		<div class="attention">
			<p>
			以下の技術を使用しました。<br>
			Java(Servlet/JSP), MySQL, AWS(EC2, RDS), HTML, CSS, JavaScript, Google Books APIs
			<p>
			<a href="https://github.com/YasushiKawabata/portfolio"><button class="btn--blue">GitHubでソースコードを見る</button></a>
		</div>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>


</html>