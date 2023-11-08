<%--
  Created by IntelliJ IDEA.
  User: bozel
  Date: 07/11/2023
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <form action="profil-servlet" method="post">
                <button type="submit" class="btn btn-primary" name="action" value="navProfil">Profil</button>
            </form>
        </li>
        <li class="nav-item">
            <form action="profil-servlet" method="post">
                <button type="submit" class="btn btn-primary" name="action" value="navProfilProf">Gestion des professeurs</button>
            </form>
        </li>
        <li class="nav-item">
            <form action="profil-servlet" method="post">
                <button type="submit" class="btn btn-primary" name="action" value="navProfilRec">Gestion des recruteurs</button>
            </form>
        </li>
        <li class="nav-item">
            <form action="offre-servlet" method="post">
                <button type="submit" class="btn btn-primary" name="action" value="navOffre">Offre</button>
            </form>
        </li>
    </ul>
    <ul class="nav-links">
        <li><a href="index.jsp" class="btn btn-primary">Déconnexion</a></li>
    </ul>
</nav>
<h2>Recruteur</h2>
<br>
<div id="ajouterRecruteurModal">
    <div>
        <h2>Ajouter un Recruteur</h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo" required><br>
            <input type="password" name="motDePasse" placeholder="Mot de passe" required><br>
            <input type="text" name="nom" placeholder="Nom" required><br>
            <input type="text" name="prenom" placeholder="Prénom" required><br>
            <input type="text" name="email" placeholder="Email"><br>
            <input type="text" name="telephone" placeholder="Téléphone"><br>
            <input type="submit" name="action" value="ajouter_recruteur" class="btn btn-primary"/>
        </form>
    </div>
</div>
<c:if test="${empty listeRecruteurs}">
    <p>Aucun Recruteur trouvé.</p>
</c:if>

<c:forEach var="recrut" items="${listeRecruteurs}">
    <form action="profil-servlet" method="post">
        <input type="text" class="form-control" name="recruteurPseudo" value="${recrut.pseudo}">
        <input type="text" class="form-control" name="recruteurMdp" value="${recrut.motDePasse}">
        <input type="text" class="form-control" name="recruteurNom" value="${recrut.nom}">
        <input type="text" class="form-control" name="recruteurPrenom" value="${recrut.prenom}">
        <input type="text" class="form-control" name="recruteurMail" value="${recrut.email}">
        <input type="text" class="form-control" name="recruteurTel" value="${recrut.telephone}">
        <input type="text" class="form-control" name="recruteurSite" value="${recrut.siteWeb}">
        <input type="hidden" name="recruteurId" value="${recrut.idUtilisateur}" />
        <button type="submit" name="action" value="sauvegardeRec" class="btn btn-primary">Sauvegarder</button>
        <button type="submit" name="action" value="supprimerRec" class="btn btn-danger">Supprimer</button>
        <br>
    </form>
</c:forEach>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>