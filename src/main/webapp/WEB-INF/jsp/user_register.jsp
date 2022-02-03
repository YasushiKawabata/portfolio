<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>ユーザー登録</title>
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
		<h2>ユーザー登録</h2>
		<c:out value="${errorMsg}" />

		<form action="/bookManager/Login?action=register" method="post">
			<dl>
				<dt><span class="required">ユーザー名</span></dt>
				<dd><input type="text" maxlength="20" size="20" name="user_regiUser" required></dd>
				<dt><span class="required">メールアドレス</span></dt>
				<dd><input type="email" maxlength="50" size="50" name="user_regiID" required></dd>
				<dt><span class="required">パスワード</span></dt>
				<dd><input type="password" maxlength="10" size="10" name="user_regiPass" required></dd>
				<dt>秘密の質問</dt>
				<dd><select name="user_secretQ">
						<option value="0">質問を選択
						<option value="1">お住まいの市町村は？
						<option value="2">ペットの名前は？
						<option value="3">父親か母親の旧姓は？
					</select></dd>
				<dt>秘密の回答</dt>
				<dd><input type="text" maxlength="50" size="50" name="user_secretA" placeholder="照合確認時に必要となります"></dd>
			</dl>

			<input class="btn btn--orange btn--radius" type="submit" value="登録">
		</form>

		<div class="attention">
			<p>＊「<span class="required"></span>」の付いている項目は必須項目です。<br>
				＊メールアドレスはログイン機能でのみの使用で、セキュリティーで問題がありそうなので<br>　実際に使っているものは使用しないでください。<br>
				＊秘密の質問と回答はパスワードを忘れた時の照合で必要です。
			</p>
		</div>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>