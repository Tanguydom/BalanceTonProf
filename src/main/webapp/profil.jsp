<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
    <!-- Include Bootstrap CSS (you should provide the actual URL to Bootstrap CSS) -->
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
            <form action="offre-servlet" method="post">
                <button type="submit" class="btn btn-primary" name="action" value="navOffre">Offre</button>
            </form>
        </li>
        <li class="nav-item">
            <form action="candidature-servlet" method="post">
                <button type="submit" class="btn btn-primary" name="action" value="navCandidat">Candidature</button>
            </form>
        </li>
    </ul>
    <ul class="nav-links">
        <li><a href="index.jsp" class="btn btn-primary">Déconnexion</a></li>
    </ul>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-6">
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
        </div>
        <div class="col-md-6">
            <div class="information-secondaire-utilisateur">
                <c:choose>
                    <c:when test="${utilisateur.role == 1}">
                        <!-- Affichage du profil de l'enseignant -->
                        <form class="form" action="profil-servlet" method="post">
                            <h2 class="mt-4">Profil d'Enseignant</h2>

                            <div class="form-group">
                                <label for="experience">Expérience:</label>
                                <input type="text" class="form-control" id="experience" name="experience" value="${enseignant.experience}">
                            </div>

                            <div class="form-group">
                                <label for="competence">Compétence:</label>
                                <input type="text" class="form-control" id="competence" name="competence" value="${enseignant.competence}">
                            </div>

                            <div class="form-group">
                                <label for="interet">Intérêt:</label>
                                <input type="text" class="form-control" id="interet" name="interet" value="${enseignant.interet}">
                            </div>

                            <div class="form-group">
                                <label for="evaluation">Évaluation:</label>
                                <input type="text" class="form-control" id="evaluation" name="evaluation" value="${enseignant.evaluation}">
                            </div>

                            <div class="form-group">
                                <label for="niveauSouhaite">Niveau souhaité:</label>
                                <input type="text" class="form-control" id="niveauSouhaite" name="niveauSouhaite" value="${enseignant.niveauSouhaite}">
                            </div>

                            <div class="form-group">
                                <label for="autresInformations">Autres informations:</label>
                                <input type="text" class="form-control" id="autresInformations" name="autresInformations" value="${enseignant.autresInformations}">
                            </div>
                            <input type="hidden" name="idEns" value="${enseignant.idEnseignant}">


                            <div class="form-group">
                                <label for="disponibilite">Disponibilité:</label>
                                <select class="form-control" id="disponibilite" name="disponibilite">
                                    <c:forEach var="disponibiliteValue" items="${['0', '1', '2']}">
                                        <option value="${disponibiliteValue}"
                                                <c:if test="${enseignant.disponibilite == disponibiliteValue}">selected</c:if>
                                        >
                                            <c:choose>
                                                <c:when test="${disponibiliteValue == '0'}">Ne se prononce pas</c:when>
                                                <c:when test="${disponibiliteValue == '1'}">Disponible</c:when>
                                                <c:when test="${disponibiliteValue == '2'}">Non disponible</c:when>
                                            </c:choose>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary" name="action" value="sauvegardeDetail">Sauvegarder</button>
                        </form>
                    </c:when>
                    <c:when test="${utilisateur.role == 2}">
                    <form class="form" action="profil-servlet" method="post">
                        <h2 class="mt-4">Entreprise ratachée</h2>
                            <div class="form-group">
                                <label for="nomEntreprise">Nom:</label>
                                <input type="text" class="form-control" id="nomEntreprise" name="nomEntreprise" value="${entreprise.nom}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="emailEntreprise">Email:</label>
                                <input type="text" class="form-control" id="emailEntreprise" name="emailEntreprise" value="${entreprise.email}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="telephoneEntreprise">Téléphone:</label>
                                <input type="text" class="form-control" id="telephoneEntreprise" name="telephoneEntreprise" value="${entreprise.telephone}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="siteWebEntreprise">Site Web:</label>
                                <input type="text" class="form-control" id="siteWebEntreprise" name="siteWebEntreprise" value="${entreprise.siteWeb}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="adresseEntreprise">Adresse:</label>
                                <input type="text" class="form-control" id="adresseEntreprise" name="adresseEntreprise" value="${entreprise.adresse}" readonly>
                            </div>

                    </form>
                    </c:when>
                    <c:otherwise>
                        <p class="mt-4">Rôle non reconnu.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<!-- Include Bootstrap JS (you should provide the actual URL to Bootstrap JS) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
