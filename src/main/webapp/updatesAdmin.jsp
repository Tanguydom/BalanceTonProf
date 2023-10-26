<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<div class="modal" id="ajouterAdminModal">
    <div class="modal-content">
        <h2>modifier un administrateur </h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo"><br>
            <input type="password" name="motDePasse" placeholder="Mot de passe"><br>
            <input type="text" name="nom" placeholder="Nom"><br>
            <input type="text" name="prenom" placeholder="Prenom"><br>
            <input type="text" name="email" placeholder="Email"><br>
            <input type="text" name="telephone" placeholder="Telephone"><br>

            <input type="hidden" name="action" value="modifier_utilisateur" />
            <input type="submit" value="Modifier" />
        </form>
    </div>
</div>

</body>
</html>
