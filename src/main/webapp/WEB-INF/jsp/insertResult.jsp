<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>書籍新規登録</title>
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
		<h2>書籍新規登録</h2>
		<p>書籍を登録しました</p><br>

		<a class="btn btn--orange btn--radius" href="/bookManager/Main">メインに戻る</a>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>

</body>

</html>