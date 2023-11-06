<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link href="style/login.css" rel="stylesheet" id="login_form">
        <link href="style/style.css" rel="stylesheet" id="global_css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <title>BalanceTonProf - Connexion</title>
    </head>

    <body>
        <nav>
            <div class="wrapper">
                <div class="logo">
                    <a href="#">
                        <img class="logo-progAv" src="img/logo_progAv.png"></img>
                    </a>
                </div>
                <ul class="nav-links">
                    <li><a href="inscription.jsp" class="btn btn-primary">S'inscrire</a></li>
                </ul>
            </div>
        </nav>

        <div class="form-box">
            <div class="card">
                <div style="color:red">
                    ${messageErreur}
                </div>
                <form action="connexion-servlet" method="post">
                    <h2 class="form-title">Connexion</h2>
                    <div class="form-group">
                        <label for="pseudo">Nom d'utilisateur</label>
                        <input placeholder="Entrer le nom d'utilisateur" name="pseudo" id="pseudo" autofocus="">
                    </div>
                    <div class="form-group">
                        <label for="motDePasse">Mot de passe</label>
                        <input placeholder="Entrer le mot de passe" name="motDePasse" id="motDePasse" type="password">
                    </div>

                    <div class="register">
                        <a href="inscription.jsp" class="register-text text-muted">S'inscrire</a>
                    </div>


                    <input type="submit" name="action" value="login" class="btn btn-primary"/>
                </form>
            </div>
        </div>
    </body>
</html>
