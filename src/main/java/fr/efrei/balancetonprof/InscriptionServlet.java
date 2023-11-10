package fr.efrei.balancetonprof;

import fr.efrei.balancetonprof.model.*;
import fr.efrei.balancetonprof.utils.Constantes;
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

    @EJB
    private EnseignantSessionBean enseignantSessionBean;
    @EJB
    private RecruteurSessionBean recruteurSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(Constantes.INSCRIPTION_PATH).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupérez les données du formulaire d'inscription
        String login = request.getParameter(Constantes.PSEUDO);
        String motDePasse = request.getParameter(Constantes.MOT_DE_PASSE);
        String nom = request.getParameter(Constantes.NOM);
        String prenom = request.getParameter(Constantes.PRENOM);
        String email = request.getParameter(Constantes.EMAIL);
        String role = request.getParameter(Constantes.ROLE);
        int roleId = Integer.parseInt(role);

        UtilisateurEntity nouvelUtilisateur = new UtilisateurEntity();
        nouvelUtilisateur.setPseudo(login);
        nouvelUtilisateur.setMotDePasse(motDePasse);
        nouvelUtilisateur.setNom(nom);
        nouvelUtilisateur.setPrenom(prenom);
        nouvelUtilisateur.setEmail(email);
        nouvelUtilisateur.setRole(roleId);
        UtilisateurSessionBean.insertUser(nouvelUtilisateur);
        try{
            if(roleId == 1){
                EnseignantEntity enseignant = new EnseignantEntity();
                enseignant.setIdUtilisateur(nouvelUtilisateur.getIdUtilisateur());
                enseignantSessionBean.insertProfessor(enseignant);

            }else if(roleId == 2){
                RecruteurEntity recruteur = new RecruteurEntity();
                recruteur.setIdUtilisateur(nouvelUtilisateur.getIdUtilisateur());
                recruteurSessionBean.insertRecruiter(recruteur);
            }
        }catch (Exception e){
        }
        request.getRequestDispatcher(Constantes.INDEX_PATH).forward(request, response);
    }
}
