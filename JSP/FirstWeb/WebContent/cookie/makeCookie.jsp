<%@page import="util.CookieBox"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* Cookie c = new Cookie("uid", "COOL");
	c.setMaxAge(60); // 초단위로 설정
	response.addCookie(c);
	
	Cookie c2 = new Cookie("uname", "송가람");
	response.addCookie(c2); */
	
	response.addCookie(CookieBox.createCookie("uid", "COOL", "/web/", 60));
	response.addCookie(CookieBox.createCookie("uname", "쿠키"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>쿠키 생성</h1> <br>
	<a href="viewCookie.jsp">쿠키값 확인</a>
</body>
</html>