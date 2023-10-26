  <%--
    Created by IntelliJ IDEA.
    User: bozel
    Date: 19/10/2023
    Time: 08:12
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
    <title>BalanceTonProf - Inscription</title>
    <link href="style/register.css" rel="stylesheet" id="login_form">
    <link href="style/style.css" rel="stylesheet" id="global_css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  </head>
  <body>
    <nav>
      <div class="wrapper">
        <div class="logo">
          <a href="index.jsp">
            <img class="logo-progAv" src="img/logo_progAv.png"></img>
          </a>
        </div>
        <ul class="nav-links">
          <li><a href="index.jsp" class="btn btn-primary">Se connecter</a></li>
        </ul>
      </div>
    </nav>

    <div class="form-box">
      <div class="card">
        <form action="inscription-servlet" method="post">
          <h2 class="form-title">Inscription</h2>
          <div class="grid-2">
            <div class="form-group full">
              <label for="champNom">Nom</label>
              <input class="form-control" placeholder="Nom" name="champNom" id="champNom" required>
            </div>

            <div class="form-group full">
              <label for="champPrenom">Prénom</label>
              <input class="form-control" placeholder="Prénom" name="champPrenom" id="champPrenom" required>
            </div>
          </div>
          <div class="form-group full">
            <label for="champLogin">Nom d'utilisateur</label>
            <input class="form-control" placeholder="Nom d'utilisateur" name="champLogin" autofocus="" id="champLogin" required>
          </div>

          <div class="form-group full">
            <label for="champMotDePasse">Mot de passe</label>
            <input class="form-control" placeholder="Mot de passe" name="champMotDePasse" type="password" id="champMotDePasse" required>
          </div>

          <div class="form-group full">
            <label for="champEmail">Email</label>
            <input class="form-control" placeholder="Email" name="champEmail" type="email" id="champEmail" required>
          </div>

          <div class="form-group full">
            <label for="champRole">Rôle :</label>
            <select class="form-control" name="champRole" id="champRole" required>
              <option value="">Sélectionnez un rôle</option>
              <option value="1">Professeur</option>
              <option value="2">Recruteur</option>
            </select>
          </div>

          <input type="submit" name="action" value="Inscription" class="btn btn-primary"/>
        </form>

        <p class="text-muted">Vous avez déjà un compte ? &nbsp; <a href="index.jsp" class="">Se connecter</a></p>

      </div>
    </div>
  </body>
  </html>
