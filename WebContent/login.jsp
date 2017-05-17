<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>ログイン</title>
</head>
<body>
<div class = "main-contents">
<Marquee onmouseover=this.stop() onmouseout=this.start()>
<FONT color="#000000" size="5"><STRONG>ログイン</STRONG></FONT>
</Marquee>

<c:if test = "${not empty errorMessages}">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${errorMessages}" var = "message">
				<li><c:out value = "${message}"/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>

<form action = "login" method = "post">
<table class="login">
	<tr>
		<th>ログインID</th><td><input type="text" name="loginId"/>&nbsp;(半角英数字6～20字)</td>
	</tr>
	<tr>
		<th>パスワード</th><td><input type="password" name="password" />&nbsp;(記号含む半角文字6～255字)</td>
	</tr>
</table>

	<div class="login"><br><input type = "submit" value ="ログイン"><br/></div>
</form>

</div>

</body>
</html>