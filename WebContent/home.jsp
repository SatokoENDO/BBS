<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function disp1() {
		if (window.confirm('停止しますか？')) {
			location.href = "admin";
		} else {
			return false;
		}
	}
	function disp2() {
		if (window.confirm('復活しますか？')) {
			location.href = "admin";
		} else {
			return false;
		}
	}
	function goDeleteServlet() {
		if (window.confirm('本当に削除しますか？')) {
			location.href = "admin";
		} else {
			return false;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div class="main-contents">
		<div class="header">

			<a href="message">新規投稿</a> <a href="login">ログアウト</a>
			<c:if
				test="${loginUser.branchId == 1 && loginUser.departmentId == 1}">
				<a href="admin">ユーザー管理</a>
			</c:if>
		</div>
		<div class="loginUser">
			<span class="name"><c:out value="${loginUser.name}" />がログイン中</span>
		</div>
		<br /> <br />

		<div class="messages">
			<c:forEach items="${messages}" var="message">
				<hr size="10" width="500" color="black "align="left">
				<br />
					<div class="name">
						投稿者：
						<c:out value="${message.name}" />
					</div>
					<div class="title">
						件名：
						<c:out value="${message.title}" />
					</div>
					<div class="text">
						本文：
						<c:out value="${message.text}" />
					</div>
					<div class="category">
						カテゴリー：
						<c:out value="${message.category}" />
					</div>
					<div class="date">
						投稿日時：
						<fmt:formatDate value="${message.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
					<br />
 					<hr size="2" width="400" color="black "align="left">
					<c:forEach items="${comments}" var="comment">
						<c:if test="${comment.messageId==message.id}">
							<div class="text">
								コメント：
								<c:out value="${comment.text}" />
							</div>
							<div class="name">
								投稿者：
								<c:out value="${comment.name}" />
							</div>
							<div class="date">
								投稿日時：
								<fmt:formatDate value="${comment.insertDate}"
									pattern="yyyy/MM/dd HH:mm:ss" />
							</div>
							<br />
						</c:if>

			</c:forEach>

			<div class="comments-form">
				<form action="comment" method="post">
					<input type="hidden" name="messageId" value="${message.id}">
					<textarea name="text" cols="50" rows="5" class="comment-box"></textarea>
					<br /> <input type="submit" value="コメント">
				</form>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>