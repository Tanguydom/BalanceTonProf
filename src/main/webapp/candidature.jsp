<%--
  Created by IntelliJ IDEA.
  User: bozel
  Date: 06/11/2023
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/admin.css">

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
<div class="container card" style="margin:10px auto">
    <div class="row" style="width: 100%">
        <div >

            <c:choose>
                <c:when test="${utilisateur.role == 2}">
                    <div class="container">
                        <h2 class="mt-5">Suivi des candidatures</h2>
                        <div style="color:red">
                                ${messageErreur}
                        </div>
                        <form action="candidature-servlet" method="POST">
                            <c:forEach var="candidat" items="${listeCandidatures}">
                                <li class="list-group-item">
                                    <form action="candidature-servlet" method="post">
                                        <strong>${candidat.intitule}</strong>
                                        <p>Ecole: ${candidat.nomEcole}</p>
                                        <p>Nom du candidat: ${candidat.nomCandidat}</p>
                                        <p>Prénom du candidat: ${candidat.prenomCandidat}</p>

                                        <input type="hidden" name="idOffre" value="${candidat.idOffre}">
                                        <input type="hidden" name="idCandidature" value="${candidat.idCandidature}">
                                        <button type="button" class="btn btn-primary" name="action" value="voir_candidat" id="voir_candidat" onclick="afficherDetailsCandidat('${candidat.nomCandidat}','${candidat.prenomCandidat}','${candidat.experience}','${candidat.competence}','${candidat.evaluation}','${candidat.interet}','${candidat.niveauSouhaite}','${candidat.autresInformations}','${candidat.disponibilite}')">Afficher le détail du candidat</button>
                                        <button type="submit" class="btn btn-danger" name="action" value="refuser_candidature">Refuser la candidature</button>
                                        <button type="submit" class="btn btn-success" name="action" value="accepter_candidature">Accepter la candidature</button>
                                    </form>
                                </li>
                            </c:forEach>
                        </form>
                    </div>
                    <div id="modalPopup" class="modal">
                        <div class="modal-content">
                            <span class="close" onclick="fermerPopup()">&times;</span>
                            <div id="popupContent"></div>
                        </div>
                    </div>
                    <style>
                        /* Styles pour la fenêtre modale */
                        .modal {
                            display: none;
                            position: fixed;
                            z-index: 1;
                            left: 0;
                            top: 0;
                            width: 100%;
                            height: 100%;
                            background-color: rgba(0, 0, 0, 0.5); /* Fond semi-transparent */
                        }

                        .modal-content {
                            position: absolute;
                            left: 50%;
                            top: 50%;
                            transform: translate(-50%, -50%); /* Centrez horizontalement et verticalement */
                            background-color: white;
                            padding: 20px;
                            border: 1px solid #ccc;
                            box-shadow: 0px 0px 10px #888;
                            max-width: 80%; /* Largeur maximale de la fenêtre modale */
                            max-height: 80%; /* Hauteur maximale de la fenêtre modale */
                            overflow: auto; /* Ajoutez une barre de défilement si le contenu dépasse la taille maximale */
                        }

                        .close {
                            position: absolute;
                            top: 10px;
                            right: 10px;
                            font-size: 20px;
                            cursor: pointer;
                        }
                    </style>
                    <script>
                        function getDisponibiliteText(disponibiliteValue) {
                            switch (disponibiliteValue) {
                                case '0':
                                    return 'Ne se prononce pas';
                                case '1':
                                    return 'Disponible';
                                case '2':
                                    return 'Non disponible';
                                default:
                                    return 'Valeur inconnue';
                            }
                        }
                        function afficherDetailsCandidat(nom, prenom , xp, comp, eva, interet, niveau, infos, dispo) {
                            // Créez une fenêtre modale (popup) avec les détails du candidat
                            var modal = document.getElementById("modalPopup");
                            var content = document.getElementById("popupContent");

                            var disponibiliteText = getDisponibiliteText(dispo);

                            // Remplissez le contenu de la popup avec les détails du candidat
                            var popupContent = `
                                    <p>Nom :  ` + nom +  `</p>
                                    <p>Prénom : ` + prenom +  `</p>
                                    <p>Experience : ` + xp +  `</p>
                                    <p>Competence : ` + comp +  `</p>
                                    <p>Evaluation : ` + eva +  `</p>
                                    <p>Interet : ` + interet +  `</p>
                                    <p>Niveau Souhaité : ` + niveau +  `</p>
                                    <p>Autres Informations : ` + infos +  `</p>
                                    <p>Disponibilité : ` + disponibiliteText +  `</p>
                                    `;
                            content.innerHTML = popupContent;

                            // Affichez la fenêtre modale
                            modal.style.display = "block";
                        }

                        function fermerPopup() {
                            // Fermez la fenêtre modale en masquant son affichage
                            var modal = document.getElementById("modalPopup");
                            modal.style.display = "none";
                        }
                    </script>

                </c:when>
                <c:when test="${utilisateur.role == 1}">
                    <div class="container">
                        <h2 class="mt-5">Mes candidatures</h2>
                        <div style="color:red">
                                ${messageErreur}
                        </div>
                        <c:forEach var="candidat" items="${listeCandidatures}">
                            <li class="list-group-item">
                                <form action="candidature-servlet" method="post">
                                    <strong>${candidat.intitule}</strong>
                                    <p>Ecole: ${candidat.nomEcole}</p>
                                    <input type="hidden" name="idOffre" value="${candidat.idOffre}">
                                    <c:choose>
                                        <c:when test="${candidat.statut == 0}">
                                            <label style="color: gray;">En attente</label>
                                            <input type="hidden" name="idCandidature" value="${candidat.idCandidature}">
                                            <button type="submit" class="btn btn-danger" name="action" value="retirer_candidature">Retirer ma candidature</button>
                                        </c:when>
                                        <c:when test="${candidat.statut == 1}">
                                            <!-- Statut "Candidature acceptée" en vert -->
                                            <label style="color: green;">Candidature acceptée</label>
                                        </c:when>
                                        <c:when test="${candidat.statut == 2}">
                                            <!-- Statut "Refusée" en rouge -->
                                            <label style="color: red;">Candidature refusée</label>
                                        </c:when>
                                    </c:choose>
                                </form>
                            </li>
                        </c:forEach>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
<!-- Include Bootstrap JS (you should provide the actual URL to Bootstrap JS) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
