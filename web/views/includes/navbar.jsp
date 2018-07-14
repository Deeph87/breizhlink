<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/home">Breizhlink</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/home">Home</a>
            </li>
        </ul>
    </div>
    <ul class="nav justify-content-end">
        <c:choose>
            <c:when test="${!empty sessionScope.userEmail}">
                <li class="nav-item ml-3 mr-1">
                    <span class="nav-link"><%= request.getSession().getAttribute("userEmail") %></span>
                </li>
                <li class="nav-item ml-3 mr-1">
                    <a type="button" class="nav-link btn btn-outline-primary" href="/logout">Se déconnecter</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item ml-3 mr-1">
                    <a type="button" class="nav-link btn btn-outline-success" href="/login">Connexion</a>
                </li>
                <li class="nav-item ml-3 mr-1">
                    <a type="button" class="nav-link btn btn-outline-primary" href="/register">Inscription</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>