<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>${book.title}の編集</title>
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
		<h2>書籍「${book.title}」の編集</h2>
		<p>No:
			<c:out value="${bookNo}" />
		</p>

		<form action="/bookManager/Main?action=edit" method="post">
			<dl>
				<c:choose>
					<c:when test="${book.ISBN == null }">
						<dt>タイトル</dt>
						<dd><input type="text" maxlength="50" size="50" name="titleName" value="${book.title}" required>
						</dd>
						<dt>著者</dt>
						<dd><input type="text" maxlength="20" size="40" name="author" value="${book.author}"></dd>
						<dt>出版社</dt>
						<dd><input type="text" maxlength="20" size="40" name="publisher" value="${book.publisher}"></dd>
					</c:when>
					<c:otherwise>
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
					</c:otherwise>
				</c:choose>
				<dt>進捗状況</dt>
				<dd><label><input type="radio" name="readStatus" value="未読">未読</label>
					<label><input type="radio" name="readStatus" value="読中">読中</label>
					<label><input type="radio" name="readStatus" value="読了">読了</label>
					<label><input type="radio" name="readStatus" value="未所持">未所持</label>
				</dd>
				<dt>評価</dt>
				<dd><select name="evaluation">
						<option value="0" <c:if test="${book.evaluation==0 }">selected</c:if>>
						<option value="1" <c:if test="${book.evaluation==1 }">selected</c:if>>☆
						<option value="2" <c:if test="${book.evaluation==2 }">selected</c:if>>☆☆
						<option value="3" <c:if test="${book.evaluation==3 }">selected</c:if>>☆☆☆
						<option value="4" <c:if test="${book.evaluation==4 }">selected</c:if>>☆☆☆☆
						<option value="5" <c:if test="${book.evaluation==5 }">selected</c:if>>☆☆☆☆☆
					</select>
				</dd>
				<dt>メモ</dt>
				<dd><textarea name="memo" maxlength="100" rows="4" cols="50"
						placeholder="ご自由にご記入いただけます">${book.memo}</textarea></dd>
			</dl>
			<input type="hidden" name="bookId" value="${book.ID}">
			<input class="btn btn--orange btn--radius" type="submit" value="更新">
		</form>
		<br>
		<form action="/bookManager/Main?action=detail" method="post">
			<input type="hidden" name="bookId" value="${book.ID}">
			<input type="hidden" name="bookNo" value="${bookNo}">
			<input class="btn btn--orange btn--radius" type="submit" value="詳細情報へ戻る">
		</form>

		<br>
		<form action="/bookManager/Main?action=detail" method="post">
			<input type="hidden" name="bookId" value="${book.ID}">
			<input type="hidden" name="bookNo" value="${bookNo}">
			<input class="btn btn--orange btn--radius" type="submit" value="詳細情報へ戻る">
		</form>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>