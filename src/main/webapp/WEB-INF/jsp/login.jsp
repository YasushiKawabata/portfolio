<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
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
		<h2>ログインメニュー</h2>
		<c:out value="${errorMsg}" />
		<form action="/bookManager/Login?action=login" method="post">
			<dl>
				<dt>ID</dt>
				<dd><input type="email" maxlength="50" size="50" name="user_regiID"></dd>
				<dt>パスワード</dt>
				<dd><input type="password" maxlength="10" size="10" name="user_regiPass"></dd>
			</dl>
			<input class="btn btn--orange btn--radius" type="submit" value="ログイン">
		</form>
		<hr>
		<a href="/bookManager/Login?action=check">パスワードを忘れた方はこちら</a>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>