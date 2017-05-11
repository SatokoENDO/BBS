<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>新規登録</title>

</head>
<body>
	<div class="menu">
		<a href="./">戻る</a>
	</div>
	<hr />
	<c:if test="${ not empty errorMessages }">
		<ul>
			<c:forEach items="${ errorMessages }" var="messages">
				<li><span><c:out value="${errorMessages}" /></span></li>
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
		<th>ログインID</th><td><input type="text" name="loginId" value="${loginId}" /></td>
	</tr>
	<tr>
		<th>パスワード</th><td><input type="password" name="password" /></td>
	</tr>
	<tr>
		<th>パスワード(確認)</th><td><input type="password" name="checkPassword" /></td>
	</tr>
	<tr>
		<th>名前</th><td><input type="text" name="name" value="${name}" /></td>
	</tr>
	</table>
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


<div class="submit"><input type="submit" value="登録"></div>
</form>
	</div>
</body>
</html>