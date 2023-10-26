<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
    <h2>Profil de l'Utilisateur</h2>
    <p>Nom: ${utilisateur.nom}</p>
    <p>Prénom: ${utilisateur.prenom}</p>
    <p>Email: ${utilisateur.email}</p>
    <p>Téléphone: ${utilisateur.telephone}</p>
    <p>Site web: ${utilisateur.site}</p>

    <h2>Administrateurs</h2>
    <button onclick="ouvrirPopupAjouterAdmin()">Ajouter Professeur</button>
    <script>
        var popupWindow; // Variable globale pour stocker la référence à la fenêtre contextuelle

        function ouvrirPopupAjouterAdmin() {
            var popupUrl = "addAdmin.jsp";
            var popupName = "AjouterAdminPopup";
            var popupWidth = 400;
            var popupHeight = 400;
            var left = (screen.width - popupWidth) / 2;
            var top = (screen.height - popupHeight) / 2;
            // Ouvrir la fenêtre contextuelle et stocker la référence dans la variable globale
            popupWindow = window.open(popupUrl, popupName, "width=" + popupWidth + ",height=" + popupHeight + ",top=" + top + ",left=" + left);
        }
    </script>
    <br>
    <c:if test="${empty listeAdministrateurs}">
        <p>Aucun administrateur trouvé.</p>
    </c:if>
    <c:forEach var="admin" items="${listeAdministrateurs}" varStatus="loop">
        <tr>
            <td>${admin.nom}</td>
            <td>${admin.prenom}</td>
            <td>${admin.email}</td>
            <td>${admin.telephone}</td>
            <td>${admin.siteWeb}</td>
            <td>
                <form action="profil-servlet" method="post">
                    <input type="hidden" name="id" value="${admin.idUtilisateur}" />
                    <input type="hidden" name="action" value="supprimer_utilisateur" />
                    <input type="submit" value="Supprimer" />
                </form>
            </td>
            <td>
                <button onclick="ouvrirPopupModifierAdmin()">Modifier admin</button>
                <script>
                    var popupWindow; // Variable globale pour stocker la référence à la fenêtre contextuelle
                    function ouvrirPopupModifierAdmin() {
                        var popupUrl = "updatesAdmin.jsp";
                        var popupName = "ModifierAdminPopup";
                        var popupWidth = 400;
                        var popupHeight = 400;
                        var left = (screen.width - popupWidth) / 2;
                        var top = (screen.height - popupHeight) / 2;
                        // Ouvrir la fenêtre contextuelle et stocker la référence dans la variable globale
                        popupWindow = window.open(popupUrl, popupName, "width=" + popupWidth + ",height=" + popupHeight + ",top=" + top + ",left=" + left);
                    }
                </script>
            </td>
        </tr>

        <br><br>
    </c:forEach>



    <h2>Professeur</h2>
    <button onclick="ouvrirPopupAjouterProf()">Ajouter Professeur</button>
    <script>
        var popupWindow; // Variable globale pour stocker la référence à la fenêtre contextuelle

        function ouvrirPopupAjouterProf() {
            var popupUrl = "addProf.jsp";
            var popupName = "AjouterProfPopup";
            var popupWidth = 400;
            var popupHeight = 400;
            var left = (screen.width - popupWidth) / 2;
            var top = (screen.height - popupHeight) / 2;
            // Ouvrir la fenêtre contextuelle et stocker la référence dans la variable globale
            popupWindow = window.open(popupUrl, popupName, "width=" + popupWidth + ",height=" + popupHeight + ",top=" + top + ",left=" + left);
        }
    </script>
    <br>
    <c:if test="${empty listeProfesseurs}">
        <p>Aucun professeur trouvé.</p>
    </c:if>
    <c:forEach var="prof" items="${listeProfesseurs}">
        <tr>
            <td>${prof.nom}</td>
            <td>${prof.prenom}</td>
            <td>${prof.email}</td>
            <td>${prof.telephone}</td>
            <td>${prof.siteWeb}</td>
            <td>
                <form action="profil-servlet" method="post">
                    <input type="hidden" name="id" value="${prof.idUtilisateur}" />
                    <input type="hidden" name="action" value="supprimer_utilisateur" />
                    <input type="submit" value="Supprimer" />
                </form>
            </td>
        </tr>
    </c:forEach>

    <h2>Recruteur</h2>
    <button onclick="ouvrirPopupAjouterRecrut()">Ajouter Recruteur</button>
    <script>
        var popupWindow; // Variable globale pour stocker la référence à la fenêtre contextuelle

        function ouvrirPopupAjouterRecrut() {
            var popupUrl = "addRecruteur.jsp";
            var popupName = "AjouterRecruteurPopup";
            var popupWidth = 400;
            var popupHeight = 400;
            var left = (screen.width - popupWidth) / 2;
            var top = (screen.height - popupHeight) / 2;
            // Ouvrir la fenêtre contextuelle et stocker la référence dans la variable globale
            popupWindow = window.open(popupUrl, popupName, "width=" + popupWidth + ",height=" + popupHeight + ",top=" + top + ",left=" + left);
        }
    </script>
    <br>
    <c:if test="${empty listeRecruteurs}">
        <p>Aucun professeur trouvé.</p>
    </c:if>
    <c:forEach var="recrut" items="${listeRecruteurs}">
        <tr>
            <td>${recrut.nom}</td>
            <td>${recrut.prenom}</td>
            <td>${recrut.email}</td>
            <td>${recrut.telephone}</td>
            <td>${recrut.siteWeb}</td>
            <td>
                <form action="profil-servlet" method="post">
                    <input type="hidden" name="id" value="${recrut.idUtilisateur}" />
                    <input type="hidden" name="action" value="supprimer_utilisateur" />
                    <input type="submit" value="Supprimer" />
                </form>
            </td>
        </tr>
    </c:forEach>

    <h2>Offre</h2>
    <button onclick="ouvrirPopupAjouterOffre()">Ajouter Offre</button>
    <script>
        var popupWindow; // Variable globale pour stocker la référence à la fenêtre contextuelle

        function ouvrirPopupAjouterOffre() {
            var popupUrl = "addOffre.jsp";
            var popupName = "AjouterAdminPopup";
            var popupWidth = 400;
            var popupHeight = 400;
            var left = (screen.width - popupWidth) / 2;
            var top = (screen.height - popupHeight) / 2;
            // Ouvrir la fenêtre contextuelle et stocker la référence dans la variable globale
            popupWindow = window.open(popupUrl, popupName, "width=" + popupWidth + ",height=" + popupHeight + ",top=" + top + ",left=" + left);
        }
    </script>
    <c:if test="${empty listeOffres}">
        <p>Aucune Offre trouvé.</p>
    </c:if>
    <c:forEach var="offre" items="${listeOffres}">
        <tr>
            <td>${offre.intitule}</td>
            <td>${offre.idEntreprise}</td>
            <td>${offre.description}</td>
        </tr>
    </c:forEach>

</body>
</html>
