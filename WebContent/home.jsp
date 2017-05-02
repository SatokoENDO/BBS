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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div class="main-contents">
		<div class="header">

			<a href="message">新規投稿</a> <a href="login">ログアウト</a> <a href = "admin">ユーザー管理（本社総務部専用）</a>
		</div>
	<div class="login_user">
		<span class="name"><c:out value="${loginUser.name}" />がログイン中</span>
	</div><br/><br/>

		<div class="messages">
			<c:forEach items="${messages}" var="message">

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
				</div><br/><br/>


			</c:forEach>
		</div>
	</div>
</body>
</html>
