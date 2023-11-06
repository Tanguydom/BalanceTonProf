<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<h2>Profil de l'Utilisateur</h2>

<div class="information-primaire-utilisateur">
    <form class="form" action="profil-servlet" method="post">
        <h2 class="mt-5">Profil de l'Utilisateur</h2>
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
            <input type="text" class="form-control" id="prenom" name="prenom" value="${utilisateur.prenom}">
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
<div class="modal" id="ajouterAdminModal">
    <div class="modal-content">
        <h2>Ajouter un admin</h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo" required><br>
            <input type="password" name="motDePasse" placeholder="Mot de passe" required><br>
            <input type="text" name="nom" placeholder="Nom" required><br>
            <input type="text" name="prenom" placeholder="Prenom" required><br>
            <input type="text" name="email" placeholder="Email" ><br>
            <input type="text" name="telephone" placeholder="Telephone" ><br>
            <input type="submit" name="action" value="ajouter_admin" class="btn btn-primary"/>
        </form>
    </div>
</div>
<c:if test="${empty listeAdministrateurs}">
    <p>Aucun administrateur trouvé.</p>
</c:if>
<form action="profil-servlet" method="post">
    <c:forEach var="admin" items="${listeAdministrateurs}" varStatus="loop">
        <input type="text" class="form-control" name="pseudo" value="${admin.pseudo}">
        <input type="text" class="form-control" name="motDePasse" value="${admin.motDePasse}">
        <input type="text" class="form-control" name="nom" value="${admin.nom}">
        <input type="text" class="form-control" name="prenom" value="${admin.prenom}">
        <input type="text" class="form-control" name="email" value="${admin.email}">
        <input type="text" class="form-control" name="telephone" value="${admin.telephone}">
        <input type="text" class="form-control" name="site" value="${admin.siteWeb}">
        <input type="hidden" name="id" value="${admin.idUtilisateur}" />
        <button type="submit" name="action" value="sauvegardeUtilisateurs">Sauvegarder</button>
        <button type="submit" name="action" value="supprimer_utilisateur">Supprimer</button>
        <br>
    </c:forEach>
</form>

<h2>Professeur</h2>
<div class="modal" id="ajouterAdminModal">
    <div class="modal-content">
        <h2>Ajouter un Professeur</h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo" required><br>
            <input type="password" name="motDePasse" placeholder="Mot de passe" required><br>
            <input type="text" name="nom" placeholder="Nom" required><br>
            <input type="text" name="prenom" placeholder="Prenom" required><br>
            <input type="text" name="email" placeholder="email" ><br>
            <input type="text" name="telephone" placeholder="Telephone" ><br>
            <input type="submit" name="action" value="ajouter_prof" class="btn btn-primary"/>
        </form>
    </div>
</div>
<c:if test="${empty listeProfesseurs}">
    <p>Aucun professeur trouvé.</p>
</c:if>
<c:forEach var="prof" items="${listeProfesseurs}">
    <input type="text" class="form-control" name="pseudo" value="${prof.pseudo}">
    <input type="text" class="form-control" name="pseudo" value="${prof.motDePasse}">
    <input type="text" class="form-control" name="nom" value="${prof.nom}">
    <input type="text" class="form-control" name="prenom" value="${prof.prenom}">
    <input type="text" class="form-control" name="email" value="${prof.email}">
    <input type="text" class="form-control" name="telephone" value="${prof.telephone}">
    <input type="text" class="form-control" name="site" value="${prof.site}">

    <form action="profil-servlet" method="post">
        <input type="hidden" name="id" value="${prof.idUtilisateur}" />
        <input type="hidden" name="action" value="" />
        <input type="submit" value="sauvegarder" />
    </form>

    <form action="profil-servlet" method="post">
        <input type="hidden" name="id" value="${prof.idUtilisateur}" />
        <input type="hidden" name="action" value="supprimer_utilisateur" />
        <input type="submit" value="Supprimer" />
    </form>
</c:forEach>

<h2>Recruteur</h2>
<br>
<div class="modal" id="ajouterAdminModal">
    <div class="modal-content">
        <h2>Ajouter un Recruteur</h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo" required><br>
            <input type="password" name="motDePasse" placeholder="Mot de passe" required><br>
            <input type="text" name="nom" placeholder="Nom" required><br>
            <input type="text" name="prenom" placeholder="Prenom" required><br>
            <input type="text" name="email" placeholder="Email" ><br>
            <input type="text" name="telephone" placeholder="Telephone" ><br>
            <input type="submit" name="action" value="ajouter_recruteur" class="btn btn-primary"/>
        </form>
    </div>
</div>
<c:if test="${empty listeRecruteurs}">
    <p>Aucun professeur trouvé.</p>
</c:if>
<c:forEach var="recrut" items="${listeRecruteurs}">
    <input type="text" class="form-control" name="pseudo" value="${recrut.pseudo}">
    <input type="text" class="form-control" name="pseudo" value="${recrut.motDePasse}">
    <input type="text" class="form-control" name="nom" value="${recrut.nom}">
    <input type="text" class="form-control" name="prenom" value="${recrut.prenom}">
    <input type="text" class="form-control" name="email" value="${recrut.email}">
    <input type="text" class="form-control" name="telephone" value="${recrut.telephone}">
    <input type="text" class="form-control" name="site" value="${recrut.site}">

    <form action="profil-servlet" method="post">
        <input type="hidden" name="id" value="${recrut.idUtilisateur}" />
        <input type="hidden" name="action" value="" />
        <input type="submit" value="sauvegarder" />
    </form>

    <form action="profil-servlet" method="post">
        <input type="hidden" name="id" value="${recrut.idUtilisateur}" />
        <input type="hidden" name="action" value="supprimer_utilisateur" />
        <input type="submit" value="Supprimer" />
    </form>
</c:forEach>


<h2>Offre</h2>
<button>Ajouter Offre</button>
<c:if test="${empty listeOffres}">
    <p>Aucune Offre trouvé.</p>
</c:if>
<c:forEach var="offre" items="${listeOffres}">
    <tr>
        <td>${offre.intitule}</td>
        <td>${offre.idEntreprise}</td>
        <td>${offre.description}</td>
    </tr>
</c:forEach>

</body>
</html>
