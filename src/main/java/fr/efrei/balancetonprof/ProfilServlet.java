package fr.efrei.balancetonprof;

import fr.efrei.balancetonprof.model.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet(name = "profilServlet", value = "/profil-servlet")

public class ProfilServlet extends HttpServlet {
    @EJB
    private UtilisateurSessionBean utilisateurSessionBean;
    @EJB
    private EnseignantSessionBean enseignantSessionBean;
    @EJB
    private RecruteurSessionBean recruteurSessionBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérez l'utilisateur actuellement connecté (vous devez implémenter la gestion de l'authentification)
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        int userId = utilisateur.getId_utilisateur();
        request.setAttribute("utilisateur", utilisateur);


        // Récupérez les données de l'utilisateur en utilisant votre session bean en fonction du rôle
        if (utilisateur.getRole() == 1) {
            // Récupérez les données de l'enseignant à l'aide de votre session bean
            EnseignantEntity enseignant = enseignantSessionBean.getEnseignantById(userId);
            request.setAttribute("enseignant", enseignant);
        } else if (utilisateur.getRole() == 2) {
            // Récupérez les données du recruteur à l'aide de votre session bean
            RecruteurEntity recruteur = recruteurSessionBean.getRecruteurById(userId);
            request.setAttribute("recruteur", recruteur);
        }

        // Transférez le contrôle à la page de profil
        request.getRequestDispatcher("profil.jsp").forward(request, response);
    }
}

