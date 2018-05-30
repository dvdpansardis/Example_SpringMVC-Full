<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Livros - Java, Python, NodeJs e Muito mais</title>
	<c:url value="/" var="contextPath" />
	<link href="${contextPath}resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
</head>



<body>

	<div class="container">
	
		<h1>Login da Casa do Código</h1>
	
		<form:form servletRelativeAction="/login" method="post" >
			<div class="form-group">
				<label>Email</label>
				<input name="username" type="text" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Senha</label>
				<input name="password" type="password" class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Logar</button>
		</form:form>
	
	</div>
</body>
</html>