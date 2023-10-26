<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
</head>
<body>
<form action="profil-servlet" method="post">
    <h2>Profil de l'Utilisateur</h2>
    <p>Pseudo: <input type="text" name="pseudo" value="${utilisateur.loginSaisi}"/></p>
    <p>Nom: <input type="text" name="nom" value="${utilisateur.nom}" }/></p>
    <p>Prénom: <input type="text" name="prenom" value="${utilisateur.prenom}" }/></p>
    <p>Mot de passe: <input type="text" name="motDePasse" value="${utilisateur.motDePasseSaisi}" }/></p>
    <p>Email: <input type="text" name="email" value="${utilisateur.email}" }/></p>
    <p>Téléphone: <input type="text" name="telephone" value="${utilisateur.telephone}" }/></p>
    <p>Site web: <input type="text" name="site" value="${utilisateur.site}" }/></p>
    <input type="submit" name="action" value="sauvegarde" id="sauvegarde">
</form>


    <c:choose>
    <c:when test="${utilisateur.role == 1}">
        <!-- Affichage du profil de l'enseignant -->
        <form action="profil-servlet" method="post">
            <h2>Profil d'Enseignant</h2>

            <label for="experience">Expérience:</label>
            <input type="text" id="experience" name="experience" value="${enseignant.experience}">

            <label for="competence">Compétence:</label>
            <input type="text" id="competence" name="competence" value="${enseignant.competence}">

            <label for="interet">Intérêt:</label>
            <input type="text" id="interet" name="interet" value="${enseignant.interet}">

            <label for="evaluation">Évaluation:</label>
            <input type="text" id="evaluation" name="evaluation" value="${enseignant.evaluation}">

            <label for="niveauSouhaite">Niveau souhaité:</label>
            <input type="text" id="niveauSouhaite" name="niveauSouhaite" value="${enseignant.niveauSouhaite}">

            <label for="autresInformations">Autres informations:</label>
            <input type="text" id="autresInformations" name="autresInformations" value="${enseignant.autresInformations}">

            <label for="disponibilite">Disponibilité:</label>
            <select id="disponibilite" name="disponibilite">
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



            <input type="submit" name="action" value="sauvegardeDetail" id="sauvegardeDetail">
        </form>


    </c:when>
    <c:when test="${utilisateur.role == 2}">
        <!-- Affichage du profil du recruteur -->
        <h2>Profil de Recruteur</h2>
        <!-- Ajoutez d'autres informations spécifiques au recruteur ici -->
    </c:when>
    <c:otherwise>
        <p>Rôle non reconnu.</p>
    </c:otherwise>
</c:choose>


</body>
</html>
