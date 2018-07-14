<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>

<div class="container">
    <h1 class="mt-5">Confirmer mot de passe pour accéder à la redirection</h1><br/>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="/redirect" method="post">
        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input name="password" type="password" class="form-control" id="password" placeholder="Entrez votre mot de passe">
        </div>
        <input name="urlId" type="hidden" value="<%= request.getAttribute("urlId") %>">
        <button type="submit" class="btn btn-primary mb-2">Confirmer</button>
    </form>
</div>

<%@include file='../includes/js.html' %>
<%@include file='../layouts/main-end-dom-1.jsp' %>
