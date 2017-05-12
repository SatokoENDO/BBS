<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function check1(){
		if(window.confirm('本当によろしいですか？')){
			location.href = "admin";
		} else {
			return false;
		}
	}
	function check2(){
		if(window.confirm('本当に削除してよろしいですか？')){
			location.href = "admin";
		} else {
			return false;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>ユーザー管理</title>
</head>
<body>

	<Marquee onmouseover=this.stop() onmouseout=this.start()>
		<FONT color="#000000" size="5"><STRONG>ユーザー管理</STRONG></FONT>
	</Marquee>
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


	<br>
	<div class="header">
<a href="./">ホーム</a> <a href="login">ログアウト</a> <a href="signup">新規ユーザー登録</a>
	</div>
	<br>

	<div class="admin">
		<table border=1>
			<tr>
				<th>ログインID</th>
				<th>名称</th>
				<th>支店ID</th>
				<th>部署ID</th>
				<th>編集</th>
				<th>停止・復活</th>
				<th>ユーザー削除</th>
			</tr>
			<c:forEach items="${ users }" var="user">
				<tr>
					<td><div class="loginId">
							<c:out value="${ user.loginId }" />
						</div></td>
					<td><div class="name">
							<c:out value="${ user.name }" />
						</div></td>
					<td><div class="branchId">
							<c:out value="${ user.branchId }" />
						</div></td>
					<td><div class="departmentId">
							<c:out value="${ user.departmentId }" />
						</div></td>

					<td>
						<form action="edituser" method="get">
							<input type="hidden" name="userId" value="${user.id}"> <input
								type="submit" value="編集" onclick="location.href = 'edituser'">
						</form>
					</td>

					<td><c:if test="${ user.id != loginUser.id }">
							<form action="admin" method="post" onClick="return check1()">
								<input type="hidden" name="userId" value="${user.id}">
								<c:if test="${ user.isLocked == 0 }">
									<input type="hidden" name="isLocked" value="1">
									<p>
										<input type="submit" value="停止中">
									</p>
								</c:if>
								<c:if test="${ user.isLocked == 1 }">
									<input type="hidden" name="isLocked" value="0">
									<p>
										<input type="submit" value="利用可能">
									</p>
								</c:if>
							</form>
						</c:if></td>

					<td><c:if test="${ user.id != loginUser.id }">
							<form action="delete" method="post" onClick="return check2()">
								<input type="hidden" name="id" value="${user.id}">
								<p>
									<input type="submit" value="ユーザー削除">
								</p>
							</form>
						</c:if></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>