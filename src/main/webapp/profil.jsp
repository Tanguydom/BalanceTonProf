<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
</head>
<body>
    <h2>Profil de l'Utilisateur</h2>
    <p>Pseudo: ${utilisateur.loginSaisi}</p>
    <p>Nom: ${utilisateur.nom}</p>
    <p>Prénom: ${utilisateur.prenom}</p>
    <p>Mot de passe: ${utilisateur.motDePasseSaisi}</p>
    <p>Email: ${utilisateur.email}</p>
    <p>Téléphone: ${utilisateur.telephone}</p>
    <p>Site web: ${utilisateur.site}</p>


<c:choose>
    <c:when test="${utilisateur.role == 1}">
        <!-- Affichage du profil de l'enseignant -->
        <h2>Profil d'Enseignant</h2>
        <p>Expérience: ${enseignant.experience}</p>
        <p>Compétence: ${enseignant.competence}</p>
        <p>Intéret: ${enseignant.interet}</p>
        <p>Evaluation: ${enseignant.evaluation}</p>:
        <p>Niveau souhaité: ${enseignant.niveauSouhaite}</p>
        <p>Autres informations: ${enseignant.autresInformations}</p>
        <p>Disponibilité: ${enseignant.disponibilite}</p>

    </c:when>
    <c:when test="${utilisateur.role == 2}">
        <!-- Affichage du profil du recruteur -->
        <h2>Profil de Recruteur</h2>
        <!-- Ajoutez d'autres informations spécifiques au recruteur ici -->
    </c:when>
    <c:when test="${utilisateur.role == 0}">
        <!-- Affichage du profil du admin -->
        <h2>Profil de l'admin</h2>
        <!-- Ajoutez d'autres informations spécifiques a l'admin ici -->
    </c:when>
    <c:otherwise>
        <p>Rôle non reconnu.</p>
    </c:otherwise>
</c:choose>
    <input type="submit" value="Éditer">
    <form action="/votre-servlet" method="post" id="saveForm" class="editable">
        <!-- Bouton "Enregistrer" pour envoyer une requête HTTP pour sauvegarder les modifications -->
        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
