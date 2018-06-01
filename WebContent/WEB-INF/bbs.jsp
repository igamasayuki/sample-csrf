<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBS</title>
</head>
<body>
	<h3>掲示板</h3>
	<form action="/cross-site-request-forgeries/BbsServlet" method="post">
		名前：<input type="text" name="name"><br> 
		本文：<textarea rows="5" cols="20" name="body"></textarea><br> 
			<input type="submit" value="投稿">
	</form>
	<hr>
	<c:forEach items="${articleList}" var="article">
		<c:out value="${article.name}" />
		<pre>${article.body}</pre><!-- あえてJSTLを使わない -->
		<hr>	
	</c:forEach>
</body>
</html>