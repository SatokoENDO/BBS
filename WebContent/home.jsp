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

			<a href="message">新規投稿</a> <a href="login">ログアウト</a>
		</div>

		<div class="messages">
		<c:forEach items="${messages}" var="messages">

				<div class="name">
					投稿者：
					<c:out value="${messages.name}" />
				</div>
				<div class="title">
					件名：
					<c:out value="${messages.title}" />
				</div>
				<div class="text">
					本文：
					<c:out value="${messages.text}" />
				</div>
				<div class="category">
					カテゴリー：
					<c:out value="${messages.category}" />
				</div>
				<div class="date">
					投稿日時：
					<fmt:formatDate value="${messages.insert_date}"
						pattern="yyyy/MM/dd HH:mm:ss" />
				</div>


		</c:forEach>
	</div>
	</div>
</body>
</html>
