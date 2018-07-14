<%@ page import="beans.UserType" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>
<div class="container">
    <h1 class="mt-5">Inscription</h1><br/>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="/register" method="post">
        <div class="form-group">
            <label for="email">Email</label>
            <input name="email" type="email" class="form-control" id="email" placeholder="Entrez votre email">
        </div>
        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input name="password" type="password" class="form-control" id="password" placeholder="Entrez votre mot de passe">
        </div>
        <div class="form-group">
            <label for="type">Type</label>
            <select name="type" id="type" class="form-control">
                <%
                    ArrayList<UserType> list = (ArrayList<UserType>) request.getAttribute("typesList");

                    for(UserType type : list) { %>
                        <option value="<%= type.getId() %>"><%= type.getName() %></option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-2">S'enregistrer</button>
    </form>
</div>
<%@include file='../includes/js.html' %>
<%@include file='../layouts/main-end-dom-1.jsp' %>
