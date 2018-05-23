<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Breizhlink - Shorten</title>
    <%@include file='../includes/css.html'%>
</head>
<body>
<%@include file='../includes/navbar.html'%>
<div class="row">
    <div class="col s6 push-s3">
        <h1>Votre URL Raccourcie :</h1><br/>
        <p><%= request.getAttribute("shortenUrl") %></p>
    </div>
</div>
<%@include file='../includes/js.html'%>
</body>
</html>
