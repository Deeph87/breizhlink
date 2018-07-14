<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>
<div class="container">
    <h1 class="mt-5">Nombre de rediretions par jour</h1><br/>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success" role="alert"><%= request.getAttribute("success") %></div>
    <% } %>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <canvas id="myChart" width="100" height="100"></canvas>
        </div>
    </div>
</div>
<%@include file='../includes/js.html' %>

<% String days = (String) request.getAttribute("days"); %>
<% String visites = (String) request.getAttribute("visites"); %>

<script>
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: <%= days %> ,
            datasets: [{
                label: ' Visites',
                data: <%= visites %>
            }]
        }
    });
</script>

<%@include file='../layouts/main-end-dom-1.jsp' %>