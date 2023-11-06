<%--
  Created by IntelliJ IDEA.
  User: bozel
  Date: 06/11/2023
  Time: 09:37
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
        <div class="col-md-4">
            <c:choose>
                <c:when test="${utilisateur.role == 2}">
                        <div class="container">
                            <h2 class="mt-5">Créer une Offre d'Emploi</h2>
                            <form action="offre-servlet" method="POST">
                                <div class="form-group">
                                    <label for="coe_intitule">Intitulé de l'offre:</label>
                                    <input type="text" class="form-control" id="coe_intitule" name="coe_intitule" required>
                                </div>

                                <div class="form-group">
                                    <label for="coe_description">Description de l'offre:</label>
                                    <textarea class="form-control" id="coe_description" name="coe_description" required></textarea>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary" name="action" value="ajouter_offre">Créer l'offre</button>
                                </div>
                            </form>
                        </div>
                </c:when>
            </c:choose>
        </div>
    </div>



    <div class="col-md-8">
        <div class="information-offre mt-4">
            <h2>Offres d'Emploi</h2>
            <ul class="list-group">
                <c:forEach var="offre" items="${listeOffres}">
                    <li class="list-group-item">
                        <c:choose>
                            <c:when test="${utilisateur.role == 1}">
                                <form action="offre-servlet" method="post">
                                    <strong>${offre.intitule}</strong>
                                    <p>Entreprise: ${offre.nomEntreprise}</p>
                                    <p>Description: ${offre.description}</p>
                                    <p>Nombres de candidats: ${offre.nbCandidat}</p>
                                    <p>Email: ${offre.emailRecruteur}</p>
                                    <p>Telephone: ${offre.telephoneRecruteur}</p>
                                    <p>Site Web: ${offre.siteWebEntreprise}</p>
                                    <p>Adresse: ${offre.adresseEntreprise}</p>
                                    <input type="hidden" name="idOffre" value="${offre.idOffre}">
                                    <button type="submit" class="btn btn-success" name="action" value="candidater">Candidater</button>
                                </form>
                            </c:when>
                            <c:when test="${utilisateur.role == 2}">
                                <!-- Champs modifiables pour le rôle 2 -->
                                <form action="offre-servlet" method="post">
                                    <label for="intitule">Initule:</label>
                                    <input type="text" class="form-control" id="intitule" name="intitule" value="${offre.intitule}">
                                    <label for="nomEntreprise">Nom de l'entreprise:</label>
                                    <input type="text" class="form-control" id="nomEntreprise" name="nomEntreprise" value="${offre.nomEntreprise}" readonly>
                                    <label for="description">Description:</label>
                                    <input type="text" class="form-control" id="description" name="description" value="${offre.description}">
                                    <label for="nbCandidat">Nombre de candidat:</label>
                                    <input type="text" class="form-control" id="nbCandidat" name="nbCandidat" value="${offre.nbCandidat}" readonly>
                                    <label for="emailRecruteur">Email du recruteur:</label>
                                    <input type="text" class="form-control" id="emailRecruteur" name="emailRecruteur" value="${offre.emailRecruteur}"readonly>
                                    <label for="telephoneRecruteur">Telephone du recruteur:</label>
                                    <input type="text" class="form-control" id="telephoneRecruteur" name="telephoneRecruteur" value="${offre.telephoneRecruteur}"readonly>
                                    <label for="siteWebEntreprise">Site web de l'entreprise:</label>
                                    <input type="text" class="form-control" id="siteWebEntreprise" name="siteWebEntreprise" value="${offre.siteWebEntreprise}"readonly>
                                    <label for="adresseEntreprise">Adresse de l'entreprise:</label>
                                    <input type="text" class="form-control" id="adresseEntreprise" name="adresseEntreprise" value="${offre.adresseEntreprise}"readonly>
                                    <input type="hidden" name="idOffre" value="${offre.idOffre}">
                                    <button type="submit" class="btn btn-danger" name="action" value="supprimer_offre">Supprimer</button>
                                    <button type="submit" class="btn btn-warning" name="action" value="modifier_offre">Modifier</button>
                                </form>
                            </c:when>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
