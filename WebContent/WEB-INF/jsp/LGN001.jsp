<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dao.CourseDAO"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/theme/css/test.css" var="testCss" />
<link href="${testCss}" rel="stylesheet">
<title>Student Registration LGN001</title>
</head>
<body class="login-page-body">

	<div class="login-page">
		<div class="form">
			<div class="login">
				<div class="login-header">
					<h1>Welcome!</h1>


					<p>${error}</p>


				</div>
			</div>
			<form:form class="login-form" action="login" method="post" >
				<input type="text" placeholder="UserMail( defult = admin@gmail.com"  name="loginName" />
				<input type="password" placeholder="Password( defult = 123)"  name="loginPassword" />
				<button type="submit">login</button>
				<p class="message">
					Not registered? <a href="#">Create an account</a>
				</p>
			</form:form>
		</div>
	</div>
</body>

</html>