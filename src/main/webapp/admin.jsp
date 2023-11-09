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
        <c:choose>
            <c:when test="${utilisateur.role == 0}">
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
            </c:when>
            <c:when test="${utilisateur.role != 0}">
                <li class="nav-item">
                    <form action="candidature-servlet" method="post">
                        <button type="submit" class="btn btn-primary" name="action" value="navCandidat">Candidature</button>
                    </form>
                </li>
            </c:when>
        </c:choose>
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
<h2 class="mt-5">Profil de l'Utilisateur</h2>

<div class="information-primaire-utilisateur">
    <div style="color:red">
        ${messageErreur}
    </div>
    <form class="form" action="profil-servlet" method="post">
        <div class="form-group">
            <label for="pseudo">Pseudo:</label>
            <input type="text" class="form-control" id="pseudo" name="pseudo" value="${utilisateur.loginSaisi}">
        </div>
        <div class="form-group">
            <label for="nom">Nom:</label>
            <input type="text" class="form-control" id="nom" name="nom" value="${utilisateur.nom}">
        </div>
        <div class="form-group">
            <label for="prenom">Prénom:</label>
            <input type="text" class="form-control" id="prenom" name ="prenom" value="${utilisateur.prenom}">
        </div>
        <div class="form-group">
            <label for="motDePasse">Mot de passe:</label>
            <input type="password" class="form-control" id="motDePasse" name="motDePasse" value="${utilisateur.motDePasseSaisi}">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${utilisateur.email}">
        </div>
        <div class="form-group">
            <label for="telephone">Téléphone:</label>
            <input type="tel" class="form-control" id="telephone" name="telephone" value="${utilisateur.telephone}">
        </div>
        <div class="form-group">
            <label for="site">Site web:</label>
            <input type="text" class="form-control" id="site" name="site" value="${utilisateur.site}">
        </div>
        <button type="submit" class="btn btn-primary" name="action" value="sauvegardeUtilisateur">Sauvegarder</button>
    </form>
</div>

<h2>Administrateurs</h2>
<div id="ajouterAdminModal">
    <div>
        <h2>Ajouter un admin</h2>
        <div style="color:red">
            ${messageErreur}
        </div>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" class="form-control" placeholder="Pseudo" required><br>
            <input type="password" name="motDePasse" class="form-control" placeholder="Mot de passe" required><br>
            <input type="text" name="nom" class="form-control" placeholder="Nom" required><br>
            <input type="text" name="prenom" class="form-control" placeholder="Prenom" required><br>
            <input type="text" name="email" class="form-control" placeholder="Email" ><br>
            <input type="text" name="telephone" class="form-control" placeholder="Telephone" ><br>
            <input type="text" name="site" class="form-control" placeholder="site" ><br>
            <input type="submit" name="action" value="ajouter_admin" class="btn btn-primary"/>
        </form>
    </div>
</div>
<c:if test="${empty listeAdministrateurs}">
    <p>Aucun administrateur trouvé. "${listeAdministrateurs} vbnjk"</p>
</c:if>
<div style="color:red">
    ${messageErreur}
</div>
<c:forEach var="admin" items="${listeAdministrateurs}">
    <form action="profil-servlet" method="post">
        <input type="text" class="form-control" name="pseudo" value="${admin.pseudo}">
        <input type="text" class="form-control" name="motDePasse" value="${admin.motDePasse}">
        <input type="text" class="form-control" name="nom" value="${admin.nom}">
        <input type="text" class="form-control" name="prenom" value="${admin.prenom}">
        <input type="text" class="form-control" name="email" value="${admin.email}">
        <input type="text" class="form-control" name="telephone" value="${admin.telephone}">
        <input type="text" class="form-control" name="site" value="${admin.siteWeb}">
        <input type="hidden" name="idUtilisateur" value="${admin.idUtilisateur}" />
        <button type="submit" name="action" value="sauvegarderUtilisateurs" class="btn btn-primary">Sauvegarder</button>
        <button type="submit" name="action" value="supprimerAdmin" class="btn btn-danger">Supprimer</button>
        <br>
    </form>
</c:forEach>
<br>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
