<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>更新結果</title>
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<header>
		<nav>
			<ul>
				<li><a href="/bookManager/Login?action=logout">ログアウト</a></li>
				<li><a href="/bookManager/Main">メイン</a></li>
			</ul>
		</nav>
		<h1>
			<a href="/bookManager/Main">
				<c:out value="${user.userName}" />の本棚</a>
		</h1>
	</header>
	<div id="wrap">
		<h2>更新結果</h2>
		<p>内容を更新しました</p><br>

		<form action="/bookManager/Main?action=detail" method="post">
			<input type="hidden" name="bookId" value="${book.ID}">
			<input class="btn btn--orange btn--radius" type="submit" value="詳細情報へ戻る">
		</form>

		<form action="/bookManager/Main" method="get">
			<input class="btn btn--orange btn--radius" type="submit" value="メインへ戻る">
		</form>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>

</body>

</html>