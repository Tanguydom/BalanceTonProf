package fr.efrei.balancetonprof;

import java.io.*;

import fr.efrei.balancetonprof.model.UtilisateurEntity;
import fr.efrei.balancetonprof.model.UtilisateurSessionBean;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

//@WebServlet(name = "connexionServlet", value = "/connexion-servlet")
public class ConnexionServlet extends HttpServlet {

    @EJB
    private UtilisateurSessionBean UtilisateurSessionBean;
    Utilisateur unUtilisateur;
    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Infos de connexion non valides. Merci de les saisir à nouveau";

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        placerUtilisateurDansContexte(request);

        aiguillerVersLaProchainePage(request, response);
    }

    public boolean verifierInfosConnexion(Utilisateur unUtilisateur){
        int id = UtilisateurSessionBean.utilisateurExist(unUtilisateur.getLoginSaisi(), unUtilisateur.getMotDePasseSaisi());
        boolean check = id != -1;
        if(check){
            UtilisateurEntity utilisateurEntity = UtilisateurSessionBean.getUtilisateurById(id);
            unUtilisateur.setId_utilisateur(id);
            unUtilisateur.setNom(utilisateurEntity.getNom());
            unUtilisateur.setPrenom(utilisateurEntity.getPrenom());
            unUtilisateur.setRole(utilisateurEntity.getRole());
            unUtilisateur.setEmail(utilisateurEntity.getEmail());
            unUtilisateur.setSite(utilisateurEntity.getSiteWeb());
            unUtilisateur.setTelephone(utilisateurEntity.getTelephone());
        }
        return check;
    }


    public void placerUtilisateurDansContexte(HttpServletRequest request){
        unUtilisateur = new Utilisateur();

        unUtilisateur.setLoginSaisi(request.getParameter("champLogin"));
        unUtilisateur.setMotDePasseSaisi(request.getParameter("champMotDePasse"));

        request.setAttribute("utilisateur", unUtilisateur);
    }

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (verifierInfosConnexion(unUtilisateur)){

            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", unUtilisateur);

            request.getRequestDispatcher("/profil-servlet").forward(request, response);
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
        String action = request.getParameter("action");

        if ("Inscription".equals(action)) {
            request.getRequestDispatcher("/inscription-servlet").forward(request, response);
        } else {
            processRequest(request, response);
        }
    }

}