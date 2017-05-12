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
<div class= "main-contents">
<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>


<form action = "edituser" method = "post"><br/>
	<input type ="hidden" name = "userId" value = "${editUser.id}">

	<label for = "loginId">ログインID</label>
	<input name = "loginId" value = "${editUser.loginId}" />(半角英数字6～20字)<br/>

	<label for = "password">パスワード</label>
	<input type="password" name = "password"/>(記号含む半角文字6～255字)<br/>

	<label for = "checkPassword">パスワードの確認</label>
	<input type="password" name = "checkPassword"/><br/>

	<label for = "name">名前</label>
	<input name = "name" value = "${editUser.name }"/>(10字以内)<br/>

	<label for="departmentId">所属支店</label>

						<select name="branchId">
				<c:forEach items="${branches}" var="branch">
						<option value="${branch.id}">
							<c:out value="${branch.name}" />
						</option>
				</c:forEach>
			</select><br />

	<label for="departmentId">部署・役職</label>

						<select name="departmentId">
				<c:forEach items="${departments}" var="department">
						<option value="${department.id}">
							<c:out value="${department.name}" />
						</option>
				</c:forEach>
			</select><br />

	<input type = "submit" value = "更新"/>

	<br/>
</form>

<a href = "admin">戻る</a>
</div>
</body>
</html>