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
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">Casa do Código</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-link" href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a>
	      <a class="nav-link" href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a>
	    </div>
	  </div>
	</nav>

	<div class="container">
	
		<h1>Cadastro de Produtos</h1>
	
		<form:form action="${s:mvcUrl('PC#gravar').build()}" method="post" commandName="produto" enctype="multipart/form-data">
			<div class="form-group">
				<label>Título</label>
				<form:input path="titulo" cssClass="form-control"/>
				<form:errors path="titulo" />		
			</div>	
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="descricao" cssClass="form-control" />
				<form:errors path="descricao" />	
			</div>
			<div class="form-group">
				<label>Páginas</label>
				<form:input path="paginas" cssClass="form-control" />
				<form:errors path="paginas" />	
			</div>
			<div class="form-group">
				<label>Data de lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control"/>
				<form:errors path="dataLancamento" />	
			</div>
			<c:forEach items="${tipos }" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco }</label>
					<form:input path="precos[${status.index }].valor" cssClass="form-control"/>
					<form:hidden path="precos[${status.index }].tipo" value="${tipoPreco }"/>
				</div>
			</c:forEach>	
			<div class="form-group">
				<label>Sumário</label>
				<input name="sumario" type="file" class="form-control"/>
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	
	</div>
</body>
</html>