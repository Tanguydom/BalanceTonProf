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
    private EcoleSessionBean ecoleSessionBean;
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

        List<EcoleEntity> ecoleEntityList = ecoleSessionBean.getListSchool();
        request.setAttribute(Constantes.LIST_ECOLE, ecoleEntityList);

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
                    case Constantes.AJOUT_ENS : createUtilisateur(request, 1);
                        path =  Constantes.ADMIN_GESTION_PROF_PATH ; break;
                    case Constantes.AJOUT_REC : createUtilisateur(request, 2);
                        path =  Constantes.ADMIN_GESTION_REC_PATH ; break;
                    case Constantes.SAUVEGARDER_UTILISATEURS: sauvegarderUtilisateurs(request);
                    case Constantes.SUPPRIMER_PROF: supprimerEnseignant(request);
                        path =  Constantes.ADMIN_GESTION_PROF_PATH ; break;
                    case Constantes.SUPPRIMER_ADMIN: supprimerAdmin(request); break;
                    case Constantes.SUPPRIMER_RECRUTEUR: supprimerRecruteur(request);
                        path =  Constantes.ADMIN_GESTION_REC_PATH ; break;
                    case Constantes.NAV_PROFIL_PROF: path = Constantes.ADMIN_GESTION_PROF_PATH; break;
                    case Constantes.NAV_PROFIL_REC: path = Constantes.ADMIN_GESTION_REC_PATH; break;
                    case Constantes.AJOUT_ECOLE: creationEcole(request);
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
                Integer idEcole;
                if(action.equals(Constantes.CHOISIR_ECOLE)){
                    idEcole = Integer.valueOf(request.getParameter(Constantes.ID_ECOLE));
                    recruteur.setIdEcole(idEcole);
                    recruteurSessionBean.updateRecruiter(recruteur);
                }else{
                    idEcole = recruteur.getIdEcole();
                }
                EcoleEntity ecoleEntity;
                if(idEcole != null){
                    ecoleEntity = ecoleSessionBean.getSchoolById(recruteur.getIdEcole());
                    request.setAttribute(Constantes.ECOLE, ecoleEntity);
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
            }catch(Exception e){
            }
        }else if(role == 2){
            try {
                RecruteurEntity recruteur = new RecruteurEntity();
                recruteur.setIdUtilisateur(utilisateur.getIdUtilisateur());
                recruteurSessionBean.insertRecruiter(recruteur);
            }catch(Exception e){
            }
        }
    }
    public void creationEcole(HttpServletRequest request){
        EcoleEntity ecoleEntity = new EcoleEntity();
        ecoleEntity.setEmail(request.getParameter(Constantes.EMAIL_ECOLE));
        ecoleEntity.setNom(request.getParameter(Constantes.NOM_ECOLE));
        ecoleEntity.setTelephone(request.getParameter(Constantes.TELEPHONE_ECOLE));
        ecoleEntity.setSiteWeb(request.getParameter(Constantes.SITE_ECOLE));
        ecoleEntity.setRaisonSociale(request.getParameter(Constantes.RAISON_SOCIALE));
        ecoleEntity.setAdresse(request.getParameter(Constantes.ADRESSE_ECOLE));
        ecoleSessionBean.insertSchool(ecoleEntity);
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
        }catch(Exception e){
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
        }catch(Exception e){
        }
    }
    public void supprimerAdmin(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            utilisateurSessionBean.deleteUser(id);
        }catch(Exception e){
        }
    }
    public void supprimerEnseignant(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            Integer nbCandidature = candidatureSessionBean.countApplicationsByIdEns(id);
            if(nbCandidature != null && nbCandidature > 0)
            {
                utilisateurSessionBean.deleteRecruiterFromRecruitment(id);
            }
            candidatureSessionBean.deleteApplicationByIdEnseignant(id);
            enseignantSessionBean.deleteProfessorByIdUtilisateur(id);
            utilisateurSessionBean.deleteUser(id);
        }catch(Exception e){
        }
    }
    public void supprimerRecruteur(HttpServletRequest request){
        try{
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            RecruteurEntity recruteur = recruteurSessionBean.getRecruiterByIdUtilisateur(id);
            Integer nbRecrutement = recrutementSessionBean.countRecruitmentByIdRecruteur(recruteur.getIdRecruteur());
            if(nbRecrutement != null &&nbRecrutement > 0)
            {
                utilisateurSessionBean.deleteRecruiterFromRecruitment(recruteur.getIdRecruteur());
            }
            recruteurSessionBean.deleteRecruiteurByIdRecruteur(id);
            utilisateurSessionBean.deleteUser(id);
        }catch(Exception e){
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
        }catch(Exception e){
        }
    }
}

