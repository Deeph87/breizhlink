<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>
<div class="container">
    <h1 class="mt-5">Liste des urls</h1><br/>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success" role="alert"><%= request.getAttribute("success") %></div>
    <% } %>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">URL D'origine</th>
            <th scope="col">URL Raccourcie</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listUrls}" var="item">
                <tr>
                    <td>${item.getDestinationUrl()}</td>
                    <td>${item.getGeneratedUrl()}</td>
                    <td><a href="/statistics/${item.getId()}">Stats</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@include file='../includes/js.html' %>
<%@include file='../layouts/main-end-dom-1.jsp' %>