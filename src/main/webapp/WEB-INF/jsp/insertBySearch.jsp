<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>書籍新規登録</title>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript">
		function check() {
			if (!document.form1.isbn.value && !document.form1.title.value) {
				window.alert('どちらにも入力されていません。');
				return false;
			} else {
				return true;
			}
		}
	</script>
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
		<b>ISBN</b>あるいは<b>タイトル</b>で検索します。両方入力した場合は、ISBNで検索します。
		<hr>
		<form method="POST" action="/bookManager/Main?action=insertBySearch" name="form1" onSubmit="return check()">
			ISBN: <input type="text" name="isbn" size=30><br><br>
			タイトル: <input type="text" name="title" size=30><br><br>
			<input class="btn btn--orange btn--radius" type="submit" value="検索" name="button_name">
			<input class="btn btn--orange btn--radius" type="reset" value="リセット">
		</form>
		<c:out value="${errorMsg}" />
		<c:if test="${list != null && list.size() != 0}">
			<table border="1">
				<tr>
					<th>No</th>
					<th>画像</th>
					<th width="150px">タイトル</th>
					<th>著者</th>
					<th width="70px">出版社</th>
					<th>詳細</th>
					<th>登録</th>
				</tr>
				<c:forEach var="i" begin="0" end="${list.size()-1 }" step="1">
					<tr>
						<td>
							<c:out value="${i+1}" />
						</td>
						<td><img src="${list[i].imageURL}"></td>
						<td>
							<c:out value="${list[i].title}" />
						</td>
						<td>
							<c:out value="${list[i].author}" />
						</td>
						<td>
							<c:out value="${list[i].publisher}" />
						</td>
						<td>
							<c:out value="${list[i].description}" />
						</td>
						<td>
							<form action="/bookManager/Main?action=insertBySearch" method="post">
								<input type="hidden" name="no" value="${i+1}">
								<input class="btn--blue" type="submit" value="登録" name="button_name">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<footer>
		<%@ include file="/WEB-INF/jsp/copyright.jsp" %>
	</footer>
</body>

</html>