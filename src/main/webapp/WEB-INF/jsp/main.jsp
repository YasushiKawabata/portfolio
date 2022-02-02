<%--https://honz.jp/ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>メイン画面</title>
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<header>
		<nav>
			<ul>
				<li><a href="/bookManager/Login?action=logout">ログアウト</a></li>
			</ul>
		</nav>
		<h1>
			<c:out value="${user.userName}" />の本棚
		</h1>
	</header>
	<div id="wrap">
		<form action="/bookManager/Main?action=search" method="post">
			<input type="text" name="text" maxlength="50" size="40" placeholder="タイトルを入力してください" value="${text}">
			<input class="btn btn--orange btn--radius" type="submit" value="検索" name="button_name">
			<a class="btn btn--orange btn--submit" href="/bookManager/Main">全検索</a>
		</form>

		<a class="btn btn--orange btn--radius" href="/bookManager/Main?action=insert">本の新規登録</a>
		<table border="1">
			<tr>
				<th>No</th>
				<th>画像</th>
				<th>タイトル</th>
				<th>著者</th>
				<th>進捗状況</th>
				<th width="110px">評価</th>
				<th>詳細</th>
			</tr>
			<c:if test="${bookList.size() != 0 }">
				<c:forEach var="i" begin="0" end="${bookList.size()-1 }" step="1">
					<tr>
						<td>
							<c:out value="${i+1}" />
						</td>
						<td>
							<c:choose>
								<c:when test="${bookList[i].imageURL == null }">
									<img src="image/book.jpg" width="128" height="178">
								</c:when>
								<c:otherwise>
									<img src="${bookList[i].imageURL}" width="128" height="178">
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:out value="${bookList[i].title}" />
						</td>
						<td>
							<c:out value="${bookList[i].author}" />
						</td>
						<td>
							<c:out value="${bookList[i].readStatus}" />
						</td>
						<td>
							<c:if test="${bookList[i].evaluation != 0 }">
								<c:forEach var="j" begin="0" end="${bookList[i].evaluation-1 }" step="1">
									★
								</c:forEach>
							</c:if>
						</td>
						<td>
							<form action="/bookManager/Main?action=detail" method="post">
								<input type="hidden" name="bookId" value="${bookList[i].ID}">
								<input type="hidden" name="bookNo" value="${i+1}">
								<input class="btn--blue" type="submit" value="詳細">
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table><br>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>