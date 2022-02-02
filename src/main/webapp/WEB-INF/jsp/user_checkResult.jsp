<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>照合結果</title>
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
		<h2>ログイン情報照合結果</h2>
		<p>メールアドレス：</p>
		<c:out value="${user.mail }" />
		<p>パスワード：</p>
		<c:out value="${user.pass }" />
		<br>

		<a class="btn btn--orange btn--radius" href="/bookManager/Login">ログイン画面へ</a>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>

</body>

</html>