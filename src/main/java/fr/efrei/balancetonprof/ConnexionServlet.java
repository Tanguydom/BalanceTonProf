package fr.efrei.balancetonprof;

import java.io.*;

import fr.efrei.balancetonprof.model.UtilisateurEntity;
import fr.efrei.balancetonprof.model.UtilisateurSessionBean;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

//@WebServlet(name = "connexionServlet", value = "/connexion-servlet")
public class ConnexionServlet extends HttpServlet {

    @EJB
    private UtilisateurSessionBean UtilisateurSessionBean;
    Utilisateur unUtilisateur;
    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        placerUtilisateurDansContexte(request);

        aiguillerVersLaProchainePage(request, response);
    }
    public boolean verifierInfosConnexion(Utilisateur unUtilisateur){
        int id = UtilisateurSessionBean.verificationExistanceUtilisateur(unUtilisateur.getLoginSaisi(), unUtilisateur.getMotDePasseSaisi());
        boolean check = id != -1;
        if(check){
            UtilisateurEntity utilisateurEntity = UtilisateurSessionBean.chercheUtilisateurById(id);
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

        unUtilisateur.setLoginSaisi(request.getParameter(Constantes.PSEUDO));
        unUtilisateur.setMotDePasseSaisi(request.getParameter(Constantes.MOT_DE_PASSE));

        request.setAttribute(Constantes.UTILISATEUR, unUtilisateur);
    }

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (verifierInfosConnexion(unUtilisateur)){

            HttpSession session = request.getSession();
            session.setAttribute(Constantes.UTILISATEUR, unUtilisateur);

            request.getRequestDispatcher(Constantes.PROFIL_SERVLET).forward(request, response);
        }else{
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_CREDENTIALS_KO);
            request.getRequestDispatcher(Constantes.INDEX_PATH).forward(request, response);
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
        String action = request.getParameter(Constantes.ACTION);

        if (Constantes.INSCRIPTION.equals(action)) {
            request.getRequestDispatcher(Constantes.INSCRIPTION_SERVLET).forward(request, response);
        } else {
            processRequest(request, response);
        }
    }

}