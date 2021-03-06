<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<c:url value="/css/default.css"/>" type="text/css">

<style>
</style>
</head>
<body>
	<!-- header module화 -->
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
	
	<div>
		<h1 id=header>회원 가입</h1>
		<hr>
		<h3>
		<c:if test="${result gt 0 && member ne null }">
			<div>
				회원가입 완료
			</div>
			${member };
		</c:if>
		<c:if test="${not (result gt 0 && member ne null) }">
			회원가입 실패
		</c:if>
		</h3>
	</div>
	
	<!-- footer module화 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>