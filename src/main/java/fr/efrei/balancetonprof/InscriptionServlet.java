package fr.efrei.balancetonprof;

import fr.efrei.balancetonprof.model.UtilisateurEntity;
import fr.efrei.balancetonprof.model.UtilisateurSessionBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(name = "inscriptionServlet", value = "/inscription-servlet")
public class InscriptionServlet extends HttpServlet {

    // Injectez votre session bean pour gérer les utilisateurs
    @EJB
    private UtilisateurSessionBean UtilisateurSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("inscription.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérez les données du formulaire d'inscription
        String login = request.getParameter("champLogin");
        String motDePasse = request.getParameter("champMotDePasse");
        String nom = request.getParameter("champNom");
        String prenom = request.getParameter("champPrenom");
        String email = request.getParameter("champEmail");
        String role = request.getParameter("champRole");
        int roleId = Integer.parseInt(role);

        UtilisateurEntity nouvelUtilisateur = new UtilisateurEntity();
        nouvelUtilisateur.setPseudo(login);
        nouvelUtilisateur.setMotDePasse(motDePasse);
        nouvelUtilisateur.setNom(nom);
        nouvelUtilisateur.setPrenom(prenom);
        nouvelUtilisateur.setEmail(email);
        nouvelUtilisateur.setRole(roleId);

        UtilisateurSessionBean.insererUtilisateur(nouvelUtilisateur);

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
