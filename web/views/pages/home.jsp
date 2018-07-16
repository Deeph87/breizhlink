<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file='../layouts/main-start-dom-1.jsp' %>
<%@include file='../includes/navbar.jsp' %>
<div class="container">
    <h1 class="mt-5">Raccourcir une URL :</h1><br/>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("error") %></div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success" role="alert"><%= request.getAttribute("success") %></div>
    <% } %>
    <form action="/shorten" method="post" id="formUrl">
        <div class="form-group" id="urlFieldContainer">
            <label for="url">URL</label>
            <input placeholder="Entrez votre URL &agrave raccourcir" class="form-control" id="url" type="text" name="originUrl">
        </div>
        <c:if test="${!empty sessionScope.userEmail}">
            <div class="form-group" id="validityContainer">
                <label for="validity">Ajouter une durée de validité</label>
                <input type="text" name="validity" class="form-control" id="validity" placeholder="Entrez une duree en minute">
            </div>
        </c:if>
        <div class="form-check" id="passwordCheckboxContainer">
            <input type="checkbox" name="hasPasswordSecure" class="form-check-input" id="hasPassword" value="1">
            <label class="form-check-label" for="hasPassword">S&eacutecuriser avec mot de passe</label>
        </div>
        <button type="submit" class="btn btn-primary">Raccourcir</button>
    </form>
</div>
<%@include file='../includes/js.html' %>
<script>
    $(document).ready(function(){
        var passwordCheckboxContainer = $("#passwordCheckboxContainer");
        var passwordCheckboxInput = $("#passwordCheckboxContainer #hasPassword");
        var isPasswdSecurURL = false;
        var passwordElement;

        // Au chargement du dom
        conditionToDisplayThePasswordField(hasDisplayPasswdField());

        // Lors de l'evenement changement de valeur de la checkbox
        passwordCheckboxInput.on("change", function(){
            isPasswdSecurURL = hasDisplayPasswdField();
            conditionToDisplayThePasswordField(isPasswdSecurURL);
        });

        function hasDisplayPasswdField()
        {
            return $("#passwordCheckboxContainer #hasPassword:checked").length > 0;
        }

        function getPasswordField()
        {
            passwordElement = '<div class="form-group" id="passwordContainer">';
            passwordElement += '<label class="form-check-label" for="password">Mot de passe</label>';
            passwordElement += '<input placeholder="Entrez votre mot de passe" class="form-control" type="password" name="password" id="password">';
            passwordElement += '</div>';

            return passwordElement;
        }

        function conditionToDisplayThePasswordField(isPasswdSecurURL)
        {
            if(isPasswdSecurURL){
                passwordCheckboxContainer.before(getPasswordField());
            } else {
                $("#passwordContainer").remove();
            }
        }
    })
</script>
<%@include file='../layouts/main-end-dom-1.jsp' %>