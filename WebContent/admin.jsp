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
		<font size="6" color="#008080"><b>ユーザー管理</b></font>
	</Marquee>

	<br><br><a href = "./">戻る</a>


	<c:if test = "${not empty errorMessages}">
		<div class ="errorMessages">
			<ul>
				<c:forEach items = "${errorMessages}" var = "message">
					<li><c:out value = "${message}"/><br><br>
				</c:forEach>
			</ul>
		</div>
	<c:remove var = "errorMessages" scope = "session"/>
	</c:if>
	<c:if test = "${empty errorMessages}">
		<div class ="validationMessage">
			<p><c:out value = "${validationMessage}"/></p><br><br>
		</div>
	<c:remove var = "validationMessage" scope = "session"/>
	</c:if>


	<br>
	<div class="header">
<a href="./">ホーム</a>  <a href="signup">新規ユーザー登録</a><div align="right"><a href="logout">ログアウト</a></div>
	</div>
	<br>

	<div class="admin">
		<table border=1>
			<tr>
				<th>ログインID</th>
				<th>名称</th>
				<th>支店</th>
				<th>所属部署</th>
				<th>編集</th>
				<th>停止・復活</th>
				<th><b>ユーザー削除</b></th>
			</tr>
			<c:forEach items="${ users }" var="user">
				<tr>
					<td><div class="loginId">
							<c:out value="${ user.loginId }" />
						</div></td>
					<td>
							<c:out value="${ user.name }" />
					</td>
					<td>
						<c:if test="${ user.branchId == 1 }">
							<c:out value=" 本社 " />
						</c:if>
						<c:if test="${ user.branchId == 2 }">
							<c:out value=" A支店 " />
						</c:if>
						<c:if test="${ user.branchId == 3 }">
							<c:out value=" B支店 " />
						</c:if>
						<c:if test="${ user.branchId == 4 }">
							<c:out value=" C支店 " />
						</c:if>
					</td>
					<td><c:if test="${ user.departmentId == 1 }">
							<c:out value=" 総務部 " />
						</c:if>
						<c:if test="${ user.departmentId == 2 }">
							<c:out value=" 情報部 " />
						</c:if>
						<c:if test="${ user.departmentId == 3 }">
							<c:out value=" 支店長 " />
						</c:if>
						<c:if test="${ user.departmentId == 4 }">
							<c:out value=" 社員 " />
						</c:if>
					</td>

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
										<input type="submit" value="復活させる">
									</p>
								</c:if>
								<c:if test="${ user.isLocked == 1 }">
									<input type="hidden" name="isLocked" value="0">
									<p>
										<input type="submit" value="停止する">
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