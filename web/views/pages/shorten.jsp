<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>
<div class="container">
    <h1>Votre URL Raccourcie :</h1><br/>
    <%--<p><%= request.getAttribute("shortenUrl") %></p>--%>
    <div class="input-group">
        <input type="text" value="<%= request.getAttribute("shortenUrl") %>" class="form-control" id="urlShortened">
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" onclick="copyToClipboard()">
                <span class="oi oi-clipboard"></span>
            </button>
        </div>
    </div>
</div>
<%@include file='../includes/js.html' %>
<script>
    function copyToClipboard() {
        /* Get the text field */
        var copyText = document.getElementById("urlShortened");

        /* Select the text field */
        copyText.select();

        /* Copy the text inside the text field */
        document.execCommand("copy");
    }
</script>
<%@include file='../layouts/main-end-dom-1.jsp' %>