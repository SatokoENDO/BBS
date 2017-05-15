<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>ユーザー編集</title>

</head>
<body>
	<Marquee onmouseover=this.stop() onmouseout=this.start()>
		<FONT color="#000000" size="5"><STRONG>ユーザー編集</STRONG></FONT>
	</Marquee>
<div class= "main-contents">
<c:if test = "${not empty errorMessages}">
		<div class ="errorMessages">
			<ul>
				<c:forEach items = "${errorMessages}" var = "message">
					<li><c:out value = "${message}"/>
				</c:forEach>
			</ul>
		</div>
	<c:remove var = "errorMessages" scope = "session"/>
	</c:if>
	<c:if test = "${empty errorMessages}">
		<div class ="validationMessage">
			<p><c:out value = "${validationMessage}"/></p>
		</div>
	<c:remove var = "validationMessage" scope = "session"/>
	</c:if>


<form action = "edituser" method = "post"><br/>
<input type ="hidden" name = "userId" value = "${editUser.id}">
	<table class="signup">

	<tr>
		<th>ログインID</th><td><input type="text" name="loginId" value="${editUser.loginId}" />(半角英数字6～20字)</td>
	</tr>
	<tr>
		<th>パスワード</th><td><input type="password" name="password" />(記号含む半角文字6～255字)</td>
	</tr>
	<tr>
		<th>パスワード(確認)</th><td><input type="password" name="checkPassword" /></td>
	</tr>
	<tr>
		<th>名前</th><td><input type="text" name="name" value="${editUser.name}" />(10字以内)</td>
	</tr>
	<c:if test="${editUser.id != loginUser.getId()}">
	<tr>
	<th>所属支店</th><td><select name="branchId">
				<c:forEach items="${branches}" var="branch">
						<option value="${branch.id}">
							<c:out value="${branch.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	</c:if>
	<c:if test="${editUser.id != loginUser.getId()}">
	<tr>
	<th>所属部署・役職</th><td><select name="departmentId">
				<c:forEach items="${departments}" var="department">
						<option value="${department.id}">
							<c:out value="${department.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	</c:if>
	</table>

	<input type = "submit" value = "更新"/>

	<br/>
</form>

<br><br><a href = "admin">戻る</a>
</div>
</body>
</html>