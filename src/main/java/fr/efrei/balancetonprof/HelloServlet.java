package fr.efrei.balancetonprof;

import java.io.*;
import java.util.List;

import fr.efrei.balancetonprof.model.UtilisateurEntity;
import fr.efrei.balancetonprof.model.UtilisateurSessionBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @EJB
    private UtilisateurSessionBean UtilisateurSessionBean;
    Utilisateur unUtilisateur;
    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Infos de connexion non valides. Merci de les saisir à nouveau";

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Les données saisies par l'utilisateur sont placées dans le contexte
        placerUtilisateurDansContexte(request);

        // Récupération de la liste de tous les employés via notre service getTousLesEmployes()
        List<UtilisateurEntity> tousLesUtilisateurs = UtilisateurSessionBean.getTousLesUtilisateurs();

        // Je mets la liste dans l'objet request afin qu'il soit accessible dans les autres couches, notamment la Vue
        request.setAttribute("tousLesUtilisateurs", tousLesUtilisateurs);

        aiguillerVersLaProchainePage(request, response);
    }

    // Une tâche <-> une méthode
    public boolean verifierInfosConnexion(Utilisateur unUtilisateur){
        return UtilisateurSessionBean.utilisateurExist(unUtilisateur.getLoginSaisi(), unUtilisateur.getMotDePasseSaisi());
    }


    public void placerUtilisateurDansContexte(HttpServletRequest request){
        unUtilisateur = new Utilisateur();

        unUtilisateur.setLoginSaisi(request.getParameter("champLogin"));
        unUtilisateur.setMotDePasseSaisi(request.getParameter("champMotDePasse"));

        request.setAttribute("utilisateur", unUtilisateur);
    }

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (verifierInfosConnexion(unUtilisateur )){
            request.getRequestDispatcher("bienvenue.jsp").forward(request, response);
        }else{
            request.setAttribute("messageErreur", MESSAGE_ERREUR_CREDENTIALS_KO);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void init() {
    }

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

}