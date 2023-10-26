<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<div class="modal" id="ajouterAdminModal">
    <div class="modal-content">
        <h2>Ajouter une offre</h2>
        <form action="profil-servlet" method="post">
            <input type="text" name="pseudo" placeholder="Pseudo" required><br>
            <input type="submit" name="action" value="ajouter_offre" class="btn btn-primary"/>
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