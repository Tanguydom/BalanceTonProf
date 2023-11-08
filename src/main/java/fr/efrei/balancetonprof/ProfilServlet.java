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

        List<EntrepriseEntity> entrepriseEntityList = entrepriseSessionBean.getListEntreprise();
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

                    case Constantes.SAUVEGARDE_ADMIN : sauvegarAdminParId(request); break;
                    case Constantes.SAUVEGARDE_RECRUTEUR : sauvegarRecruteurParId(request); break;
                    case Constantes.SAUVEGARDE_PROF : sauvegarEnseignantParId(request); break;

                    case Constantes.SUPPRIMER_ADMIN : supprimerAdmin(request); break;
                    case Constantes.SUPPRIMER_PROF : supprimerEnseignant(request); break;
                    case Constantes.SUPPRIMER_RECRUTEUR : supprimerRecruteur(request); break;
                    case Constantes.NAV_PROFIL_PROF: path = Constantes.ADMIN_GESTION_PROF_PATH; break;
                    case Constantes.NAV_PROFIL_REC: path = Constantes.ADMIN_GESTION_REC_PATH; break;
                    default:break;
                }
                List<UtilisateurEntity> listeAdministrateurs = utilisateurSessionBean.getTousLesAdministrateurs(utilisateur.getId_utilisateur());
                List<UtilisateurEntity> listeRecruteurs = utilisateurSessionBean.getTousLesRecruteurs();
                listeOffresEntity = offreSessionBean.chercherToutesLesOffres();
                List<UtilisateurEntity> listeProfesseurs = utilisateurSessionBean.getTousLesProfesseurs();
                request.setAttribute(Constantes.LIST_ENS, listeProfesseurs);

                request.setAttribute(Constantes.LIST_REC, listeRecruteurs);
                request.setAttribute(Constantes.LIST_ADMIN, listeAdministrateurs);
                request.setAttribute(Constantes.LIST_OFFRE, listeOffresEntity);
                break ;
            case 1 : //cas enseignant
                switch (action){
                    case Constantes.SAUVEGARDE_DETAIL : sauvegardeDetails(request); break;
                    default:break;
                }
                EnseignantEntity enseignant = enseignantSessionBean.chercheEnseignantParIdUtilisateur(userId);
                request.setAttribute(Constantes.ENSEIGNANT, enseignant);
                break;

            case 2 : //cas recruteur
                RecruteurEntity recruteur = recruteurSessionBean.chercheRecruteurParIdUtilisateur(userId);
                Integer idEntreprise;
                if(action.equals(Constantes.CHOISIR_ENTREPRISE)){
                    idEntreprise = Integer.valueOf(request.getParameter(Constantes.ID_ENTREPRISE));
                    recruteur.setIdEntreprise(idEntreprise);
                    recruteurSessionBean.changementRecruteur(recruteur);
                }else{
                    idEntreprise = recruteur.getIdEntreprise();
                }
                EntrepriseEntity entrepriseEntity;
                if(idEntreprise != null){
                    entrepriseEntity = entrepriseSessionBean.chercheEntrepriseParId(recruteur.getIdEntreprise());
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

        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setPseudo(pseudo);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setRole(role);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setEmail(email);
        utilisateur.setTelephone(telephone);
        utilisateur.setSiteWeb(null);
        utilisateurSessionBean.insertionUtilisateur(utilisateur);

        switch (role) {
            case 0:
                break; //case 0
            case 1:
                EnseignantEntity enseignant = new EnseignantEntity();
                enseignant.setIdUtilisateur(utilisateur.getIdUtilisateur());
                enseignantSessionBean.insertionEnseignant(enseignant);
                break; //case 1
            case 2:
                RecruteurEntity recruteur = new RecruteurEntity();
                recruteur.setIdUtilisateur(utilisateur.getIdUtilisateur());
                recruteurSessionBean.insertionRecruteur(recruteur);
                break; //case 2
        }
    }
    public void sauvegardeUtilisateur(HttpServletRequest request) {
        Utilisateur utilisateur = (Utilisateur) request.getAttribute(Constantes.UTILISATEUR);
        UtilisateurEntity utilisateurEntity = utilisateurSessionBean.chercheUtilisateurById(utilisateur.getId_utilisateur()) ;
        utilisateurEntity.setEmail(request.getParameter(Constantes.EMAIL));
        utilisateurEntity.setNom(request.getParameter(Constantes.NOM));
        utilisateurEntity.setPrenom(request.getParameter(Constantes.PRENOM));
        utilisateurEntity.setMotDePasse(request.getParameter(Constantes.MOT_DE_PASSE));
        utilisateurEntity.setPseudo(request.getParameter(Constantes.PSEUDO));
        utilisateurEntity.setSiteWeb(request.getParameter(Constantes.SITE));
        utilisateurEntity.setTelephone(request.getParameter(Constantes.TELEPHONE));

        utilisateurSessionBean.modificationUtilisateur(utilisateurEntity);
        utilisateur.setLoginSaisi(utilisateurEntity.getPseudo());
        utilisateur.setMotDePasseSaisi(utilisateurEntity.getMotDePasse());
        utilisateur.setNom(utilisateurEntity.getNom());
        utilisateur.setPrenom(utilisateurEntity.getPrenom());
        utilisateur.setTelephone(utilisateurEntity.getTelephone());
        utilisateur.setEmail(utilisateurEntity.getEmail());
        utilisateur.setSite(utilisateurEntity.getSiteWeb());
    }
    public void sauvegarAdminParId(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter(Constantes.ADMINFORMID));
        UtilisateurEntity admin = utilisateurSessionBean.chercheUtilisateurById(id);
        admin.setEmail(request.getParameter(Constantes.ADMINFORMEMAIL));
        admin.setNom(request.getParameter(Constantes.ADMINFORMNOM));
        admin.setPrenom(request.getParameter(Constantes.ADMINFORMPRENOM));
        admin.setMotDePasse(request.getParameter(Constantes.ADMINFORMMDP));
        admin.setPseudo(request.getParameter(Constantes.ADMINFORMPSEUDO));
        admin.setSiteWeb(request.getParameter(Constantes.ADMINFORMSITE));
        admin.setTelephone(request.getParameter(Constantes.ADMINFORMTEL));

        utilisateurSessionBean.modificationUtilisateur(admin);
    }
    public void sauvegarEnseignantParId(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter(Constantes.PROFFORMId));
        UtilisateurEntity enseignant = utilisateurSessionBean.chercheUtilisateurById(id);
        enseignant.setEmail(request.getParameter(Constantes.PROFFORMEMAIL));
        enseignant.setNom(request.getParameter(Constantes.PROFFORMNOM));
        enseignant.setPrenom(request.getParameter(Constantes.PROFFORMPRENOM));
        enseignant.setMotDePasse(request.getParameter(Constantes.PROFFORMMDP));
        enseignant.setPseudo(request.getParameter(Constantes.PROFFORMPSEUDO));
        enseignant.setSiteWeb(request.getParameter(Constantes.PROFFORMSITE));
        enseignant.setTelephone(request.getParameter(Constantes.PROFFORMTEL));

        utilisateurSessionBean.modificationUtilisateur(enseignant);
    }
    public void sauvegarRecruteurParId(HttpServletRequest request)
    {
        int id = Integer.parseInt(request.getParameter(Constantes.RECRUTEURFORMID));
        UtilisateurEntity recruteur = utilisateurSessionBean.chercheUtilisateurById(id);
        recruteur.setEmail(request.getParameter(Constantes.RECRUTEURFORMEMAIL));
        recruteur.setNom(request.getParameter(Constantes.RECRUTEURFORMNOM));
        recruteur.setPrenom(request.getParameter(Constantes.RECRUTEURFORMPRENOM));
        recruteur.setMotDePasse(request.getParameter(Constantes.RECRUTEURFORMMDP));
        recruteur.setPseudo(request.getParameter(Constantes.RECRUTEURFORMPSEUDO));
        recruteur.setSiteWeb(request.getParameter(Constantes.RECRUTEURFORMSITE));
        recruteur.setTelephone(request.getParameter(Constantes.RECRUTEURFORMTEL));
        utilisateurSessionBean.modificationUtilisateur(recruteur);
    }
    public void supprimerAdmin(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter(Constantes.ADMINFORMID));
        utilisateurSessionBean.suppressionUtilisateur(id);
    }
    public void supprimerEnseignant(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter(Constantes.PROFFORMId));
        utilisateurSessionBean.suppresionCandidature(id);
        utilisateurSessionBean.suppresionProfesseur(id);
        utilisateurSessionBean.suppressionUtilisateur(id);
    }
    public void supprimerRecruteur(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter(Constantes.RECRUTEURFORMID));
        RecruteurEntity recruteur = recruteurSessionBean.chercheRecruteurParIdUtilisateur(id);
        utilisateurSessionBean.mettreProfNullOnRecruteur(recruteur.getIdRecruteur());
        utilisateurSessionBean.suppresionRecruteur(id);
        utilisateurSessionBean.suppressionUtilisateur(id);
    }
    public void sauvegardeDetails(HttpServletRequest request){
        String experience = request.getParameter(Constantes.EXPERIENCE);
        String competence = request.getParameter(Constantes.COMPETENCE);
        String interet = request.getParameter(Constantes.INTERET);
        String evaluation = request.getParameter(Constantes.EVALUATION);
        String niveauSouhaite = request.getParameter(Constantes.NIVEAU_SOUHAITE);
        String autresInformations = request.getParameter(Constantes.AUTRES_INFORMATIONS);
        int disponibilite = Integer.parseInt(request.getParameter(Constantes.DISPONIBILITE));
        int idEns = Integer.parseInt(request.getParameter(Constantes.ID_ENS));

        EnseignantEntity entity = enseignantSessionBean.chercheEnseignantParId(idEns);
        entity.setExperience(experience);
        entity.setCompetence(competence);
        entity.setDisponibilite(disponibilite);
        entity.setEvaluation(evaluation);
        entity.setAutresInformations(autresInformations);
        entity.setInteret(interet);
        entity.setNiveauSouhaite(niveauSouhaite);

        enseignantSessionBean.changementEnseignant(entity);
    }
}

