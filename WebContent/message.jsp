<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<%@ page isELIgnored = "false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
<title>新規投稿</title>
<link href = "CSS/style.css" rel = "stylesheet" type = "text/css">
</head>
<body>

<Marquee onmouseover=this.stop() onmouseout=this.start()>
<FONT color="#000000" size="5"><STRONG>新規投稿</STRONG></FONT>
</Marquee>
	<div class="loginUser">
		<span class="name"><c:out value="${loginUser.name}" />がログイン中</span>
	</div><br/><br/>

	<c:if test = "${ not empty messages }">
		<div class = "messages">
			<ul>
				<c:forEach items = "${ messages }" var = "message">
					<li><c:out value = "${ message }" /><br>
				</c:forEach>
			</ul>
		</div>
		<c:remove var = "messages" scope = "session" />
	</c:if>

	<div class = "input-text">
		<form action = "message" method = "post">
			<input type = "hidden" name = "id" value = "${ users.user_id }">
			<p>
				<label for = "title">件名(50文字以下)</label>
			</p>
			<input name = "title"/><br>
			<br>

			<p>
				<label for = "category">カテゴリー(10文字以下)</label>
			</p>
			<input name = "category" /><br>
			<br>

			<p>
				<label for = "text">本文(1000文字以下)</label>
			</p>
			<textarea name = "text" class = "input-box" ></textarea>
			<p>
				<input type = "submit" value = "投稿">
			</p>
		</form>

		<a href = "./home">戻る</a>
		<c:remove var = "title" scope = "session" />
		<c:remove var = "category" scope = "session" />
		<c:remove var = "text" scope = "session" />

	</div>

</body>
</html>