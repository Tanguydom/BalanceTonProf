<!DOCTYPE html>
<html>
<head>
    <title>Détails de l'employé</title>
</head>
<body>
<h1>Détails de l'employé</h1>
    <c:if test="${not empty employee}">
        <p>Nom : ${employee.nom}</p>
        <p>Prénom : ${employee.prenom}</p>
        <p>Email : ${employee.email}</p>
        <!-- Ajoutez d'autres détails de l'employé ici -->
    </c:if>
<a href="/liste-employes">Retour à la liste des employés</a>
</body>
</html>
