<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<div class="modal" id="ajouterAdminModal">
    <div class="modal-content">
        <h2>Ajouter un Recruteur</h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo" required><br>
            <input type="password" name="motDePasse" placeholder="Mot de passe" required><br>
            <input type="text" name="nom" placeholder="Nom" required><br>
            <input type="text" name="prenom" placeholder="Prenom" required><br>
            <input type="text" name="email" placeholder="Email" required><br>
            <input type="text" name="telephone" placeholder="Telephone" required><br>
            <input type="submit" name="action" value="ajouter_recruteur" class="btn btn-primary"/>
        </form>
    </div>
</div>

<script>
    function fermerFenetreAvecSucces() {
        window.close();
    }
</script>

</body>
</html>
