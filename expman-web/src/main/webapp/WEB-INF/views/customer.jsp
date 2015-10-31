<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Expense Manager</title>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" var="username" />
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<sec:authentication property="principal" var="username" />
	</sec:authorize>
	<h3>Customer profile</h3>
	<p>
		Welcome, ${user}
	</p>
</body>
</html>