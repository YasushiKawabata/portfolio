<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>ログイン情報照合</title>
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
		<h2>ログイン情報照合</h2>
		<c:out value="${errorMsg}" />

		<form action="/bookManager/Login?action=check" method="post">
			<dl>
				<dt>ID</dt>
				<dd><input type="email" maxlength="50" size="50" name="user_regiID" placeholder="必須項目"></dd>
				<dt>秘密の質問</dt>
				<dd>
					<select name="user_secretQ">
						<option value="1">お住まいの市町村は？
						<option value="2">ペットの名前は？
						<option value="3">父親か母親の旧姓は？
					</select>
				</dd>
				<dt>秘密の回答</dt>
				<dd><input type="text" maxlength="50" size="50" name="user_secretA" placeholder="照合確認時に必要となります"></dd>
			</dl>
			<input class="btn btn--orange btn--radius" type="submit" value="照合確認">
		</form>

		<a href="index.jsp">トップ画面へ</a>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>

</body>

</html>