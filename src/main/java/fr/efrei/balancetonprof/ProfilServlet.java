package fr.efrei.balancetonprof;

import fr.efrei.balancetonprof.model.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

//@WebServlet(name = "profilServlet", value = "/profil-servlet")

public class ProfilServlet extends HttpServlet {
    @EJB
    private UtilisateurSessionBean utilisateurSessionBean;
    @EJB
    private EnseignantSessionBean enseignantSessionBean;
    @EJB
    private RecruteurSessionBean recruteurSessionBean;
    @EJB
    private OffreSessionBean offreSessionBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        int userId = utilisateur.getId_utilisateur();
        request.setAttribute("utilisateur", utilisateur);

        String path = "" ;

        String action = request.getParameter("action");

        if(utilisateur.getRole() != 0 && action.equals("sauvegarde")){
            sauvegardeUtilisateur(request, utilisateur, userId);
            session.setAttribute("utilisateur", utilisateur);
        }

        path = "profil.jsp";

        switch (utilisateur.getRole()){
            case 0 :
                List<UtilisateurEntity> listeAdministrateurs = utilisateurSessionBean.getTousLesAdministrateurs(utilisateur.getId_utilisateur());
                request.setAttribute("listeAdministrateurs", listeAdministrateurs);

                if (action.equals("ajouter_admin")) {
                    //formulaire ajout addAdmin
                    try {
                        createUtilisateur(request, 0);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else if (action.equals("ajouter_prof")) {
                    //formulaire ajout addProf
                    try {
                        createUtilisateur(request, 1);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else if (action.equals("ajouter_recruteur")) {
                    //formulaire ajout addRecruteur
                    try {
                        createUtilisateur(request, 2);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else if (action.equals("supprimer_utilisateur")) {
                    //suppression d'un utilisateur
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        deleteUtilisateur(id);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else if (action.equals("ajouter_offre")) {
                    //formulaire ajout addOffre
                    try {
                        createOffre(request);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else if (action.equals("supprimer_offre")) {
                    //suppression d'une offre
                    try {
                        deleteOffre();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else if (action.equals("modifier_offre")) {
                    try {
                        updateUtilisateur(request);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
                // ajouter les modifications

                List<UtilisateurEntity> listeProfesseurs = utilisateurSessionBean.getTousLesProfesseurs();
                request.setAttribute("listeProfesseurs", listeProfesseurs);

                List<UtilisateurEntity> listeRecruteurs = utilisateurSessionBean.getTousLesRecruteurs();
                request.setAttribute("listeRecruteurs", listeRecruteurs);

                List<OffreEmploiEntity> listeOffres = offreSessionBean.getToutesLesOffres();
                request.setAttribute("listeOffres", listeOffres);

                path = "admin.jsp";

                break ;
            case 1 :
                EnseignantEntity enseignant = enseignantSessionBean.findEnseignantByIdUtilisateur(userId);
                request.setAttribute("enseignant", enseignant);
                if("sauvegardeDetail".equals(action)){
                    sauvegardeEnseignant(request, userId);
                }break;
            case 2 :
                RecruteurEntity recruteur = recruteurSessionBean.findRecruteurByIdUtilisateur(userId);
                request.setAttribute("recruteur", recruteur);
                if("sauvegardeDetail".equals(action)){
                    //save recruteur
                }break;
            default:break;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void createUtilisateur(HttpServletRequest request, int role){
        String pseudo = request.getParameter("pseudo");
        String motDePasse = request.getParameter("motDePasse");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");

        //creation de l'admin
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setPseudo(pseudo);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setRole(role);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setTelephone(telephone);
        utilisateur.setSiteWeb(null);

        //ajout de l'admin en bdd
        utilisateurSessionBean.insererUtilisateur(utilisateur);
    }
    public void deleteUtilisateur(int id){
        try {
            utilisateurSessionBean.supprimerUtilisateur(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void updateUtilisateur(HttpServletRequest request){
        try {
            System.out.println("######################");
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void createOffre(HttpServletRequest request){
    }
    public void deleteOffre(){
    }

    public void sauvegardeUtilisateur(HttpServletRequest request, Utilisateur utilisateur, int userId) {
        UtilisateurEntity newUtilisateurEntity = new UtilisateurEntity(
                userId,
                request.getParameter("pseudo"),
                request.getParameter("motDePasse"),
                request.getParameter("nom"),
                request.getParameter("prenom"),
                utilisateur.getRole(),
                request.getParameter("email"),
                request.getParameter("telephone"),
                request.getParameter("site")
        );
        utilisateurSessionBean.updateUtilisateur(newUtilisateurEntity);
        utilisateur.setLoginSaisi(newUtilisateurEntity.getPseudo());
        utilisateur.setMotDePasseSaisi(newUtilisateurEntity.getMotDePasse());
        utilisateur.setNom(newUtilisateurEntity.getNom());
        utilisateur.setPrenom(newUtilisateurEntity.getPrenom());
        utilisateur.setTelephone(newUtilisateurEntity.getTelephone());
        utilisateur.setEmail(newUtilisateurEntity.getEmail());
        utilisateur.setSite(newUtilisateurEntity.getSiteWeb());
    }

    public void sauvegardeEnseignant(HttpServletRequest request, int userId){
        String experience = request.getParameter("experience");
        String competence = request.getParameter("competence");
        String interet = request.getParameter("interet");
        String evaluation = request.getParameter("evaluation");
        String niveauSouhaite = request.getParameter("niveauSouhaite");
        String autresInformations = request.getParameter("autresInformations");
        String disponibilite = request.getParameter("disponibilite");

        EnseignantEntity newEnseignantEntity = new EnseignantEntity(
                userId,
                experience,
                competence,
                interet,
                evaluation,
                niveauSouhaite,
                autresInformations,
                disponibilite
        );
        EnseignantEntity entity = (EnseignantEntity) request.getAttribute("enseignant");
        if(entity != null){
            enseignantSessionBean.updateEnseignant(newEnseignantEntity);
            request.setAttribute("enseignant", newEnseignantEntity);
        }
    }
}

