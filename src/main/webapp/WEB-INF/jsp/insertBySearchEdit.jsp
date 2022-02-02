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
		<form action="/bookManager/Main?action=insertBySearchEdit" method="post">
			<dl>
				<dt>タイトル</dt>
				<dd>
					<c:out value="${book.title}" />
				</dd>
				<dt>著者</dt>
				<dd>
					<c:out value="${book.author}" />
				</dd>
				<dt>出版社</dt>
				<dd>
					<c:out value="${book.publisher}" />
				</dd>
				<dt>進捗状況</dt>
				<dd>
					<label><input type="radio" name="readStatus" value="未読">未読</label>
					<label><input type="radio" name="readStatus" value="読中">読中</label>
					<label><input type="radio" name="readStatus" value="読了">読了</label>
					<label><input type="radio" name="readStatus" value="未所持">未所持</label>
				</dd>
				<dt>評価</dt>
				<dd><select name="evaluation">
						<option value="0">
						<option value="1">☆
						<option value="2">☆☆
						<option value="3">☆☆☆
						<option value="4">☆☆☆☆
						<option value="5">☆☆☆☆☆
					</select>
				</dd>
				<dt>メモ</dt>
				<dd><textarea name="memo" maxlength="100" rows="4" cols="50" placeholder="ご自由にご記入いただけます"></textarea>
				</dd>
			</dl>
			<input type="hidden" name="bookId" value="${book.ID}">
			<input class="btn btn--orange btn--radius" type="submit" value="登録">
		</form>

	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>