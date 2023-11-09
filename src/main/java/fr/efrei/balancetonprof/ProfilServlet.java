package fr.efrei.balancetonprof;

import fr.efrei.balancetonprof.model.*;
import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import java.io.IOException;

//@WebServlet(name = "profilServlet", value = "/profil-servlet")

public class ProfilServlet extends HttpServlet {
    @EJB
    private UtilisateurSessionBean utilisateurSessionBean;
    @EJB
    private RecruteurSessionBean recruteurSessionBean;
    @EJB
    private CandidatureSessionBean candidatureSessionBean;
    @EJB
    private EntrepriseSessionBean entrepriseSessionBean;
    @EJB
    private EnseignantSessionBean enseignantSessionBean;
    @EJB
    private RecrutementSessionBean recrutementSessionBean;
    @EJB
    private OffreSessionBean offreSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute(Constantes.UTILISATEUR);

        int userId = utilisateur.getId_utilisateur();
        request.setAttribute(Constantes.UTILISATEUR, utilisateur);

        List<EntrepriseEntity> entrepriseEntityList = entrepriseSessionBean.getListEnterprise();
        request.setAttribute(Constantes.LIST_ENTREPRISE, entrepriseEntityList);

        String path = Constantes.PROFIL_PATH;

        String action = request.getParameter(Constantes.ACTION);
        switch (action){
            case Constantes.SAUVEGARDE_UTILISATEUR :
                sauvegardeUtilisateur(request);
                session.setAttribute(Constantes.UTILISATEUR, utilisateur);
                break;
            default:break;
        }
        List<OffreEmploiEntity> listeOffresEntity;

        switch (utilisateur.getRole()){
            case 0 : //cas admin
                path = Constantes.ADMIN_PATH;
                switch (action){
                    case Constantes.AJOUT_ADMIN : createUtilisateur(request, 0); break;
                    case Constantes.AJOUT_ENS : createUtilisateur(request, 1); break;
                    case Constantes.AJOUT_REC : createUtilisateur(request, 2); break;
                    case Constantes.SAUVEGARDER_UTILISATEURS: sauvegarderUtilisateurs(request); break;
                    case Constantes.SUPPRIMER_PROF: supprimerEnseignant(request); break;
                    case Constantes.SUPPRIMER_ADMIN: supprimerAdmin(request);break;
                    case Constantes.SUPPRIMER_RECRUTEUR: supprimerRecruteur(request);break;
                    case Constantes.NAV_PROFIL_PROF: path = Constantes.ADMIN_GESTION_PROF_PATH; break;
                    case Constantes.NAV_PROFIL_REC: path = Constantes.ADMIN_GESTION_REC_PATH; break;
                    default:break;
                }
                List<UtilisateurEntity> listeAdministrateurs = utilisateurSessionBean.getAdmins(utilisateur.getId_utilisateur());
                List<UtilisateurEntity> listeRecruteurs = utilisateurSessionBean.getRecruiter();
                listeOffresEntity = offreSessionBean.getOffers();
                List<UtilisateurEntity> listeProfesseurs = utilisateurSessionBean.getProfessors();
                request.setAttribute(Constantes.LIST_ENS, listeProfesseurs);

                request.setAttribute(Constantes.LIST_REC, listeRecruteurs);
                request.setAttribute(Constantes.LIST_ADMIN, listeAdministrateurs);
                request.setAttribute(Constantes.LIST_OFFRE, listeOffresEntity);
                break ;
            case 1 : //cas enseignant
                switch (action){
                    case Constantes.SAUVEGARDE_DETAIL : sauvegarderDetailsEnseignant(request); break;
                    default:break;
                }
                EnseignantEntity enseignant = enseignantSessionBean.getProfessorByIdUtilisateur(userId);
                request.setAttribute(Constantes.ENSEIGNANT, enseignant);
                break;

            case 2 : //cas recruteur
                RecruteurEntity recruteur = recruteurSessionBean.getRecruiterByIdUtilisateur(userId);
                Integer idEntreprise;
                if(action.equals(Constantes.CHOISIR_ENTREPRISE)){
                    idEntreprise = Integer.valueOf(request.getParameter(Constantes.ID_ENTREPRISE));
                    recruteur.setIdEntreprise(idEntreprise);
                    recruteurSessionBean.updateRecruiter(recruteur);
                }else{
                    idEntreprise = recruteur.getIdEntreprise();
                }
                EntrepriseEntity entrepriseEntity;
                if(idEntreprise != null){
                    entrepriseEntity = entrepriseSessionBean.getEnterpriseById(recruteur.getIdEntreprise());
                    request.setAttribute(Constantes.ENTREPRISE, entrepriseEntity);
                }
                request.setAttribute(Constantes.RECRUTEUR, recruteur);
                break;
            default:break;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    public void createUtilisateur(HttpServletRequest request, int role){
        String pseudo = request.getParameter(Constantes.PSEUDO);
        String motDePasse = request.getParameter(Constantes.MOT_DE_PASSE);
        String nom = request.getParameter(Constantes.NOM);
        String prenom = request.getParameter(Constantes.PRENOM);
        String email = request.getParameter(Constantes.EMAIL);
        String telephone = request.getParameter(Constantes.TELEPHONE);
        String site = request.getParameter(Constantes.SITE);

        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setPseudo(pseudo);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setRole(role);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setTelephone(telephone);
        utilisateur.setSiteWeb(site);
        utilisateurSessionBean.insertUser(utilisateur);
        if(role == 1){
            try {
                EnseignantEntity enseignant = new EnseignantEntity();
                enseignant.setIdUtilisateur(utilisateur.getIdUtilisateur());
                enseignantSessionBean.insertProfessor(enseignant);
                request.setAttribute(Constantes.MSG_ERREUR, null);
            }catch(Exception e){
                request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_INSERTUSER_KO);
            }
        }else if(role == 2){
            try {
                RecruteurEntity recruteur = new RecruteurEntity();
                recruteur.setIdUtilisateur(utilisateur.getIdUtilisateur());
                recruteurSessionBean.insertRecruiter(recruteur);
                request.setAttribute(Constantes.MSG_ERREUR, null);
            }catch(Exception e){
                request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_INSERTUSER_KO);
            }
        }
    }
    public void sauvegardeUtilisateur(HttpServletRequest request) {
        Utilisateur utilisateur = (Utilisateur) request.getAttribute(Constantes.UTILISATEUR);
        UtilisateurEntity utilisateurEntity = utilisateurSessionBean.getUserById(utilisateur.getId_utilisateur()) ;
        utilisateurEntity.setEmail(request.getParameter(Constantes.EMAIL));
        utilisateurEntity.setNom(request.getParameter(Constantes.NOM));
        utilisateurEntity.setPrenom(request.getParameter(Constantes.PRENOM));
        utilisateurEntity.setMotDePasse(request.getParameter(Constantes.MOT_DE_PASSE));
        utilisateurEntity.setPseudo(request.getParameter(Constantes.PSEUDO));
        utilisateurEntity.setSiteWeb(request.getParameter(Constantes.SITE));
        utilisateurEntity.setTelephone(request.getParameter(Constantes.TELEPHONE));

        try{
            utilisateurSessionBean.updateUser(utilisateurEntity);
            utilisateur.setLoginSaisi(utilisateurEntity.getPseudo());
            utilisateur.setMotDePasseSaisi(utilisateurEntity.getMotDePasse());
            utilisateur.setNom(utilisateurEntity.getNom());
            utilisateur.setPrenom(utilisateurEntity.getPrenom());
            utilisateur.setTelephone(utilisateurEntity.getTelephone());
            utilisateur.setEmail(utilisateurEntity.getEmail());
            utilisateur.setSite(utilisateurEntity.getSiteWeb());
            request.setAttribute(Constantes.MSG_ERREUR, null);
        }catch(Exception e){
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_UPDATEUSER_KO);
        }

    }
    public void sauvegarderUtilisateurs(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
        UtilisateurEntity utilisateur = utilisateurSessionBean.getUserById(id);
        utilisateur.setEmail(request.getParameter(Constantes.EMAIL));
        utilisateur.setNom(request.getParameter(Constantes.NOM));
        utilisateur.setPrenom(request.getParameter(Constantes.PRENOM));
        utilisateur.setMotDePasse(request.getParameter(Constantes.MOT_DE_PASSE));
        utilisateur.setPseudo(request.getParameter(Constantes.PSEUDO));
        utilisateur.setSiteWeb(request.getParameter(Constantes.SITE));
        utilisateur.setTelephone(request.getParameter(Constantes.TELEPHONE));

        try{
            utilisateurSessionBean.updateUser(utilisateur);
            request.setAttribute(Constantes.MSG_ERREUR, null);
        }catch(Exception e){
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_UPDATEUSER_KO);
        }
    }
    public void supprimerAdmin(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            utilisateurSessionBean.deleteUser(id);
            request.setAttribute(Constantes.MSG_ERREUR, null);
        }catch(Exception e){
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_DELETEUSER_KO);
        }
    }
    public void supprimerEnseignant(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            candidatureSessionBean.deleteApplication(id);
            enseignantSessionBean.deleteProfessorByIdUtilisateur(id);
            utilisateurSessionBean.deleteUser(id);
            request.setAttribute(Constantes.MSG_ERREUR, null);
        }catch(Exception e){
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_DELETEUSER_KO);
        }
    }
    public void supprimerRecruteur(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            RecruteurEntity recruteur = recruteurSessionBean.getRecruiterByIdUtilisateur(id);
            utilisateurSessionBean.deleteRecruiterFromRecruitment(recruteur.getIdRecruteur());
            recruteurSessionBean.deleteRecruiteurByIdRecruteur(id);
            utilisateurSessionBean.deleteUser(id);
            request.setAttribute(Constantes.MSG_ERREUR, null);
        }catch(Exception e){
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_DELETEUSER_KO);
        }
    }
    public void sauvegarderDetailsEnseignant(HttpServletRequest request){
        String experience = request.getParameter(Constantes.EXPERIENCE);
        String competence = request.getParameter(Constantes.COMPETENCE);
        String interet = request.getParameter(Constantes.INTERET);
        String evaluation = request.getParameter(Constantes.EVALUATION);
        String niveauSouhaite = request.getParameter(Constantes.NIVEAU_SOUHAITE);
        String autresInformations = request.getParameter(Constantes.AUTRES_INFORMATIONS);
        int disponibilite = Integer.parseInt(request.getParameter(Constantes.DISPONIBILITE));
        int idEns = Integer.parseInt(request.getParameter(Constantes.ID_ENSEIGNANT));

        EnseignantEntity entity = enseignantSessionBean.getProfessorById(idEns);
        entity.setExperience(experience);
        entity.setCompetence(competence);
        entity.setDisponibilite(disponibilite);
        entity.setEvaluation(evaluation);
        entity.setAutresInformations(autresInformations);
        entity.setInteret(interet);
        entity.setNiveauSouhaite(niveauSouhaite);

        try{
            enseignantSessionBean.updateProfessor(entity);
            request.setAttribute(Constantes.MSG_ERREUR, null);
        }catch(Exception e){
            request.setAttribute(Constantes.MSG_ERREUR, Constantes.MESSAGE_ERREUR_UPDATEUSER_KO);
        }
    }
}

