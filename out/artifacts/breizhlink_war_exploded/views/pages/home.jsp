<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Breizhlink - Home</title>
    <%@include file='../includes/css.html'%>
</head>
<body>
<%@include file='../includes/navbar.html'%>
<div class="row">
    <div class="col s6 push-s3">
        <%
            if(request.getAttribute("error") != null){
                out.println(request.getAttribute("error"));
            }
        %>
        <%@include file='../includes/urlForm.html'%>
    </div>
</div>
<%@include file='../includes/js.html'%>
</body>
</html>
