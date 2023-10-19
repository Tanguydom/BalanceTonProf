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
      <title>Title</title>
  </head>
  <body>
  <form action="inscription-servlet" method="post">
    <div class="form-group">
      <input class="form-control" placeholder="Login" name="champLogin" autofocus="" required>
    </div>
    <div class="form-group">
      <input class="form-control" placeholder="Mot de passe" name="champMotDePasse" type="password" required>
    </div>
    <!-- Ajoutez ici d'autres champs pour le nom, le prénom, l'email, etc. -->
    <div class="form-group">
      <input class="form-control" placeholder="Nom" name="champNom" required>
    </div>
    <div class="form-group">
      <input class="form-control" placeholder="Prénom" name="champPrenom" required>
    </div>
    <div class="form-group">
      <input class="form-control" placeholder="Email" name="champEmail" type="email" required>
    </div>
    <label for="champRole">Rôle :</label>
    <select class="form-control" name="champRole" id="champRole" required>
      <option value="">Sélectionnez un rôle</option>
      <option value="1">Professeur</option>
      <option value="2">Recruteur</option>
    </select>
    <input type="submit" name="action" value="Inscription" class="btn btn-primary"/>
  </form>
  <a href="index.jsp" class="btn btn-secondary">Retour</a>

  </body>
  </html>
