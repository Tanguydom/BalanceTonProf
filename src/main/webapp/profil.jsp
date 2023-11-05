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
<div class="container">
    <div class="row">
        <div class="col-md-4">
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
                                <label for="nom">Nom:</label>
                                <input type="text" class="form-control" id="nomEntreprise2" name="nom" value="${entreprise.nom}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="text" class="form-control" id="emailEntreprise" name="email" value="${entreprise.email}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="telephone">Téléphone:</label>
                                <input type="text" class="form-control" id="telephoneEntreprise" name="telephone" value="${entreprise.telephone}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="siteWeb">Site Web:</label>
                                <input type="text" class="form-control" id="siteWeb" name="siteWeb" value="${entreprise.siteWeb}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="adresse">Adresse:</label>
                                <input type="text" class="form-control" id="adresse" name="adresse" value="${entreprise.adresse}" readonly>
                            </div>
                    </form>
                    </c:when>
                    <c:otherwise>
                        <p class="mt-4">Rôle non reconnu.</p>
                    </c:otherwise>
                </c:choose>
            </div>
                <c:choose>
                    <c:when test="${utilisateur.role == 2}">
                        <div class="container">
                            <h2 class="mt-5">Créer une Offre d'Emploi</h2>
                            <form action="profil-servlet" method="POST">
                                <div class="form-group">
                                    <label for="coe.intitule">Intitulé de l'offre:</label>
                                    <input type="text" class="form-control" id="coe.intitule" name="coe.intitule" required>
                                </div>

                                <div class="form-group">
                                    <label for="coe.description">Description de l'offre:</label>
                                    <textarea class="form-control" id="coe.description" name="coe.description" required></textarea>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary" name="action" value="ajouter_offre">Créer l'offre</button>
                                </div>
                            </form>
                        </div>
                        <div class="container">
                            <h2 class="mt-5">Suivi des candidatures</h2>
                            <form action="profil-servlet" method="POST">
                                    <c:forEach var="candidat" items="${listeCandidatures}">
                                        <li class="list-group-item">
                                            <form action="profil-servlet" method="post">
                                                <strong>${candidat.intitule}</strong>
                                                <p>Entreprise: ${candidat.nomEntreprise}</p>
                                                <p>Nom du candidat: ${candidat.nomCandidat}</p>
                                                <p>Prénom du candidat: ${candidat.prenomCandidat}</p>

                                                <input type="hidden" name="offreId" value="${candidat.idOffre}">
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
                            <form action="profil-servlet" method="POST">
                                <c:forEach var="offre" items="${listeCandidatures}">
                                    <li class="list-group-item">
                                            <form action="profil-servlet" method="post">
                                                <strong>${offre.intitule}</strong>
                                                <p>Entreprise: ${offre.nomEntreprise}</p>
                                                <input type="hidden" name="offreId" value="${offre.idOffre}">
                                                <c:choose>
                                                    <c:when test="${offre.statut == 0}">
                                                        <label style="color: gray;">En attente</label>
                                                        <button type="submit" class="btn btn-danger" name="action" value="retirer_candidatire">Retirer ma candidature</button>
                                                    </c:when>
                                                    <c:when test="${offre.statut == 1}">
                                                        <!-- Statut "Candidature acceptée" en vert -->
                                                        <label style="color: green;">Candidature acceptée</label>
                                                    </c:when>
                                                    <c:when test="${offre.statut == 2}">
                                                        <!-- Statut "Refusée" en rouge -->
                                                        <label style="color: red;">Candidature refusée</label>
                                                    </c:when>
                                                </c:choose>
                                            </form>
                                    </li>
                                </c:forEach>
                            </form>
                        </div>
                    </c:when>
                </c:choose>
        </div>



        <div class="col-md-8">
            <div class="information-offre mt-4">
                <h2>Offres d'Emploi</h2>
                <ul class="list-group">
                    <c:forEach var="offre" items="${listeOffres}">
                        <li class="list-group-item">
                            <c:choose>
                                <c:when test="${utilisateur.role == 1}">
                                    <form action="profil-servlet" method="post">
                                        <strong>${offre.intitule}</strong>
                                        <p>Entreprise: ${offre.nomEntreprise}</p>
                                        <p>Description: ${offre.description}</p>
                                        <p>Nombres de candidats: ${offre.nbCandidat}</p>
                                        <p>Email: ${offre.emailRecruteur}</p>
                                        <p>Telephone: ${offre.telephoneRecruteur}</p>
                                        <p>Site Web: ${offre.siteWebEntreprise}</p>
                                        <p>Adresse: ${offre.adresseEntreprise}</p>
                                        <input type="hidden" name="offreId" value="${offre.idOffre}">
                                        <button type="submit" class="btn btn-success" name="action" value="candidater">Candidater</button>
                                    </form>
                                </c:when>
                                <c:when test="${utilisateur.role == 2}">
                                    <!-- Champs modifiables pour le rôle 2 -->
                                    <form action="profil-servlet" method="post">
                                        <label for="io.intitule">Initule:</label>
                                        <input type="text" class="form-control" id="io.intitule" name="io.intitule" value="${offre.intitule}">
                                        <label for="nomEntreprise">Nom de l'entreprise:</label>
                                        <input type="text" class="form-control" id="nomEntreprise" name="nomEntreprise" value="${offre.nomEntreprise}" readonly>
                                        <label for="io.description">Description:</label>
                                        <input type="text" class="form-control" id="io.description" name="io.description" value="${offre.description}">
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
                                        <input type="hidden" name="offreId" value="${offre.idOffre}">
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
</div>
<!-- Include Bootstrap JS (you should provide the actual URL to Bootstrap JS) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
