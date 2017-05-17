<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>掲示板ホーム</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function deleteMessage(){
	window.alert('この投稿を削除しますか？');
}
function deleteComment(){
	window.alert('このコメントを削除しますか？');
}

</script>

</head>
<body>
<Marquee onmouseover=this.stop() onmouseout=this.start()>
<FONT color="#000000" size="5"><STRONG>掲示板ホーム</STRONG></FONT>
</Marquee>

<c:if test = "${not empty errorMessages}">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${errorMessages}" var = "errorMessage">
				<li><c:out value = "${errorMessage}"/><br><br>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>

	<div class="main-contents">
		<div class="header">

			<a href="message">新規投稿</a>
			<c:if
				test="${loginUser.branchId == 1 && loginUser.departmentId == 1}">
				<a href="admin">ユーザー管理</a>
			</c:if><div align="right"><a href="logout">ログアウト</a></div>
		</div>
		<div class="loginUser">
			<span class="name"><c:out value="${loginUser.name}" />がログイン中</span>
		</div>
		<br /><form action="./" method = "Get">
カテゴリ:&nbsp;<select name="category" size = "1">
		<c:if test = "${selectedCategory == null}">
			<option value = "" selected >  </option>
			<c:forEach items = "${categories}" var = "category">
				<option value="${category}"><c:out value = "${category}"/></option>
			</c:forEach>
			</c:if>
		<c:if test = "${selectedCategory != null }">
			<option value = ""></option>
			<c:forEach items = "${categories}" var = "category">
				<option value="${category}" <c:if test = "${selectedCategory.equals(category)}">selected</c:if>><c:out value = "${category}"/></option>
			</c:forEach>
			</c:if>
		</select><br/><br>
		日付:&nbsp;<input type = "date" name = "startDate">&nbsp;から&nbsp;<input type = "date" name = "endDate">&nbsp;まで
		&nbsp;&nbsp;<input type = "submit" value = "絞込み">
	</form> <br />

		<div class="messages">

			<c:forEach items="${messages}" var="message">
				<hr size="10" width="500" color="black " align="left">
				<br />
				<div class="mainMessage">
				<div class="name">
					投稿者
					<c:out value="${message.name}" />
				</div><br>
				<div class="title">
					件名：
					<c:out value="${message.title}" />
				</div><br>
				<div class="text">
					本文：
					<c:forEach var="s" items="${fn:split(message.text, '
')}">
    <div>${s}</div>
</c:forEach>
				</div>
				<br><div class="category">
					カテゴリー：
					<c:out value="${message.category}" />
				</div>
				<div class="date">
					投稿日時：
					<fmt:formatDate value="${message.insertDate}"
						pattern="yyyy/MM/dd HH:mm:ss" />
				</div>
				<c:if
					test="${(loginUser.branchId == 1 && loginUser.departmentId == 2) || (loginUser.departmentId == 3 && message.departmentId == 4 && loginUser.branchId == message.branchId)}">
				<td colspan="2"><div align="left">
						<form action="deleteMessage" method="post">

								<input type="hidden" name="deletedId" value="${message.id}" />
								<div class="deleteMessage"><input type = "submit" value = "投稿削除" onClick="deleteMessage()"></div>
						</form>
					</div></td>
				</c:if>
				</div>


				<br />

				<c:forEach items="${comments}" var="comment">
					<c:if test="${comment.messageId==message.id}">

						<div class="comments"><div class="comment">
							コメント：
							<c:forEach var="s" items="${fn:split(comment.text, '
')}">
    <div>${s}</div><br>
</c:forEach>
						</div><br>
						<div class="name">
							投稿者：
							<c:out value="${comment.name}" />
						</div>
						<div class="date">
							投稿日時：
							<fmt:formatDate value="${comment.insertDate}"
								pattern="yyyy/MM/dd HH:mm:ss" />
						</div>

						<c:if
							test="${ loginUser.branchId == 1 && loginUser.departmentId == 2 || (loginUser.departmentId == 3 && message.departmentId == 4 && loginUser.branchId == message.branchId)}">
							<form action = "deleteComment" method = "post">
							<input type = "hidden" name = "deletedId" value = "${ comment.id }">
								<div class="deleteComment"><input type = "submit" value = "コメント削除" onClick="deleteComment()"></div>
						</form>
						</c:if>

						</div><br>
					</c:if>

				</c:forEach>

				<br><div class="comments-form">
					<form action="comment" method="post">
					<input type ="hidden" name = "messageId" value = "${message.id}">
				<c:if test = "${errorComment.messageId != message.id}">
				<textarea name = "text" cols ="50" rows = "5" class = "comment-box" id = "comment-box"></textarea><br/>
				</c:if>
				<c:if test = "${errorComment.messageId == message.id}">
				<textarea name = "text" cols ="50" rows = "5" class = "comment-box" id = "comment-box">
				<c:out value = "${errorComment.text}" />
				</textarea>
				</c:if>
				<br><div class="comments-form-end"><input type = "submit" value = "コメント"></div>

					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>