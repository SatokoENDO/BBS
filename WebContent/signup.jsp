<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録</title>
</head>
<body>
<div class="menu">
	<a href="control">戻る</a>
</div>
<div class="user_name">
	<c:out value="${ loginUser.name }" />
</div>
<hr />
<c:if test="${ not empty errorMessages }">
	<ul>
		<c:forEach items="${ errorMessages }" var="message">
			<li><span><c:out value="${message}" /></span></li>
		</c:forEach>
	</ul>
	<c:remove var="errorMessages" scope="session" />
</c:if>
<div class="main">
<Marquee onmouseover=this.stop() onmouseout=this.start()>
<FONT color="#000000" size="5"><STRONG>ユーザー新規登録</STRONG></FONT>
</Marquee>
<form action="signup" method="post">
<table class="signup">
	<tr>
		<th>ログインID</th><td><input type="text" name="login_id" value="${login_id}" /></td>
	</tr>
	<tr>
		<th>パスワード</th><td><input type="password" name="password" /></td>
	</tr>
	<tr>
		<th>パスワード(確認)</th><td><input type="password" name="password_check" /></td>
	</tr>
	<tr>
		<th>名前</th><td><input type="text" name="name" value="${users.name }" /></td>
	</tr>
	<tr>
		<th>所属支店ID</th><td><input type="text" name="branch_id" value="${branch_id }" /></td>
	</tr>
	<tr>
		<th>所属部署ID</th><td><input type="text" name="department_id" value="${department_id }" /></td>
	</tr>
	<tr>

</table>
<div class="submit"><input type="submit" value="登録"></div>
</form>
</div>
</body>
</html>