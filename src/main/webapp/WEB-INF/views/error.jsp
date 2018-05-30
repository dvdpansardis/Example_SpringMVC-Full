<%@page pageEncoding="UTF-8" language="java" contentType="text/html; UTF-8" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>


<%@taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTamplate titulo="Erro">

    <section id="index-section" class="container middle">

        <h2>Falha interna</h2>

        <h4>${exception}</h4>

    </section>

</tags:pageTamplate>