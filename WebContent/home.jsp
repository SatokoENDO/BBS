<%@ page language = "java" contentType = "text/html; charset  =  UTF-8"
	pageEncoding = "UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.io.*"%>
<%@ page isELIgnored = "false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
<title>掲示板ホーム</title>
<link href = "CSS/style.css" rel = "stylesheet" type = "text/css">
</head>

<body>
<Marquee onmouseover=this.stop() onmouseout=this.start()>
<FONT color="#000000" size="5"><STRONG>掲示板ホーム</STRONG></FONT>
</Marquee>

	<div class = "head-text">
	<br><a href = "logout">ログアウト</a>
	
	
			<br><a href = "newmessage">新規投稿</a>
			<c:if test = "${ login_id.department_id == 1 }">
				<br><a href = "management">ユーザー管理</a>
			</c:if>
			<br><a href = "logout">ログアウト</a>
	</div>
	<br>
	</body>
</html>