<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Livros - Java, Python, NodeJs e Muito mais</title>
    <c:url value="/" var="contextPath"/>
    <link href="${contextPath}resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">Casa do Código</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-link" href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a>
            <a class="nav-link" href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a>
        </div>
        <div class="navbar-nav nav-right">
            <a class="nav-link" href="#">
                Usuário <security:authentication property="principal" var="user"/>
                ${user.username}
            </a>
        </div>
        <div class="navbar-nav nav-right">
            <a href="<c:url value="/logout" />">Sair</a></span>
        </div>
    </div>
</nav>

<div class="container">
    <h1>Lista de Produtos</h1>

    <div>
        <c:if test="${! empty sucesso}">
            <h3>${sucesso}</h3>
        </c:if>
        <h3>${falha}</h3>
    </div>

    <table class="table table-hover table-striped">
        <tr>
            <th>Título</th>
            <th>Descrição</th>
            <th>Preços</th>
            <th>Páginas</th>
        </tr>
        <c:forEach items="${produtos }" var="produto">
            <tr>
                <td>
                    <a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">${produto.titulo }</a>
                </td>
                <td>${produto.descricao }</td>
                <td>${produto.precos }</td>
                <td>${produto.paginas }</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>