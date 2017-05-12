<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<%@ page isELIgnored = "false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>新規投稿</title>

</head>
<body>

<Marquee onmouseover=this.stop() onmouseout=this.start()>
<FONT color="#000000" size="5"><STRONG>新規投稿</STRONG></FONT>
</Marquee>
	<div class="loginUser">
		<span class="name"><c:out value="${loginUser.name}" />がログイン中</span>
	</div><br/><br/>

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

	<div class = "input-text">
		<form action = "message" method = "post">
			<input type = "hidden" name = "id" value = "${ user.user_id }">
			<p>
				<label for = "title">件名(50文字以下)</label>
			</p>
			<textarea name="title" cols="50" rows="1" class="text-box"></textarea>
			<br>

			<p>
				<label for = "category">カテゴリー(10文字以下)</label>
			</p>
			<input name = "category" /><br>
			<br>

			<p>
				<label for = "text">本文(1000文字以下)</label>
			</p>
			<textarea name="text" cols="50" rows="5" class="text-box"></textarea>
			<p>
				<input type = "submit" value = "投稿">
			</p>
		</form>

		<a href = "./">戻る</a>
		<c:remove var = "title" scope = "session" />
		<c:remove var = "category" scope = "session" />
		<c:remove var = "text" scope = "session" />

	</div>

</body>
</html>