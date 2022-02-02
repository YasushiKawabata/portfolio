<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>${book.title}の詳細</title>
	<link rel="stylesheet" href="css/style.css">
</head>

<body id="detail">
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
		<h2>書籍「${book.title}」の詳細</h2>
		<c:if test="${book.imageURL != null }">
			<img src="${book.imageURL}" width="192" height="247"><br>
		</c:if>
		<c:if test="${book.description != null }">
			<p><b>内容</b></p>
			<c:out value="${book.description }" />
		</c:if>
		<table class="clear">
			<tr>
				<th>No.</th>
				<td>
					<c:out value="${bookNo}" />
				</td>
			</tr>
			<tr>
				<th>タイトル</th>
				<td>
					<c:out value="${book.title}" />
				</td>
			</tr>
			<tr>
				<th>著者</th>
				<td>
					<c:out value="${book.author}" />
				</td>
			</tr>
			<tr>
				<th>出版社</th>
				<td>
					<c:out value="${book.publisher}" />
				</td>
			</tr>
			<tr>
				<th>進捗状況</th>
				<td>
					<c:out value="${book.readStatus}" />
				</td>
			</tr>
			<tr>
				<th>評価</th>
				<td>
					<c:if test="${book.evaluation != 0 }">
						<c:forEach var="j" begin="0" end="${book.evaluation-1 }" step="1">
							★
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>メモ</th>
				<td>
					<c:out value="${book.memo}" />
				</td>
			</tr>
		</table>

		<form action="/bookManager/Main?action=update" method="post">
			<input type="hidden" name="bookId" value="${book.ID}">
			<input type="hidden" name="bookNo" value="${bookNo}">
			<input class="btn btn--orange btn--radius" type="submit" value="編集">
		</form>

		<form action="/bookManager/Main?action=delete" method="post">
			<input type="hidden" name="bookId" value="${book.ID}">
			<input class="btn btn--orange btn--radius" type="submit" value="削除">
		</form><br>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>