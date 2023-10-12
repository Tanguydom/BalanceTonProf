<%--
  Created by IntelliJ IDEA.
  User: nitsu
  Date: 11/09/2023
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ALTN72 - Liste des employés </title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
   <!--  <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>  -->
</head>
<body>

<div class="container" >
    <div class="row col-md-6 col-md-offset-0 custyle">
        <form method="post" action="hello-servlet">
            <table class="table table-striped custab">
                <thead>
                <h1>Liste des utilisateurs</h1>
                <tr class="text-center">
                    <th>NOM</th>
                    <th>PRENOM</th>
                    <th>TEL PORTABLE</th>
                    <th>EMAIL</th>
                </tr>
                </thead>
                </tr>

                <c:forEach items="${tousLesUtilisateurs}" var="utilisateur">
                    <tr>
                        <td>${utilisateur.nom}</td>
                        <td>${utilisateur.prenom}</td>
                        <td>${utilisateur.telephone}</td>
                        <td>${utilisateur.email}</td>

                    </tr>
                </c:forEach>

            </table>
            <input type="submit" name="action" value="Supprimer" class="btn btn-primary"/>
            <input type="submit" name="action" value="Details" class="btn btn-primary"/>
        </form>
    </div>
</div>











</body>
</html>
