<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>
<div class="container">
    <h1 class="mt-5">Connexion</h1><br/>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="email">Email</label>
            <input name="email" type="email" class="form-control" id="email" placeholder="Entrez votre email">
        </div>
        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input name="password" type="password" class="form-control" id="password" placeholder="Entrez votre mot de passe">
        </div>
        <button type="submit" class="btn btn-primary mb-2">Se connecter</button>
    </form>
</div>
<%@include file='../includes/js.html' %>
<%@include file='../layouts/main-end-dom-1.jsp' %>
