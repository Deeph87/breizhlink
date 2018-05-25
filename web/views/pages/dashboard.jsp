<%--
  Created by IntelliJ IDEA.
  User: virgilmoreau
  Date: 22/05/2018
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<%@include file='../includes/navbar.html'%>
<div class="container">
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Dashboard</a>
            <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Statistique</a>
            <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Mot de passe</a>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <h2>Gestion des urls</h2>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">URL Original</th>
                    <th scope="col">URL Modifier</th>
                    <th scope="col">Date de création</th>
                    <th scope="col">Durée</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">https://google.com/</th>
                    <td>https://SHjfdh27.com</td>
                    <td>23/05/2018</td>
                    <td>1 semaine</td>
                    <td><a href="" class="btn btn-primary">Plus d'action</a> <a href="" class="btn btn-danger">Supprimer</a></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
            Les statistiques
        </div>
        <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
            Modifier mon mot de passe
            <form class="col-lg-5">
                <div class="form-group">
                <input type="password" name="password_old" placeholder="Votre mot de passe" class="form-control">
                </div>
                <div class="form-group">
                <input type="password" name="new_passsword" placeholder="Votre nouveau mot de passe" class="form-control">
                </div>
                <div class="form-group">
                <input type="password" name="new_password2" placeholder="Confirmer" class="form-control">
                </div>
                <div class="form-group">
                <input type="submit" name="modifier" value="Modifier" class="btn btn-warning">
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file='../includes/js.html'%>
</body>
</html>
