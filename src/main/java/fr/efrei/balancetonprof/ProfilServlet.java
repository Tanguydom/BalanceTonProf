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

        String path = Constantes.PROFIL_PATH; ;

        String action = request.getParameter(Constantes.ACTION);

        if(action.equals(Constantes.SAUVEGARDE_UTILISATEUR)){
            sauvegardeUtilisateur(request);
            session.setAttribute(Constantes.UTILISATEUR, utilisateur);
        }
        List<OffreEmploiEntity> listeOffresEntity;

        switch (utilisateur.getRole()){
            case 0 : //cas admin
                switch (action){
                    case Constantes.AJOUT_ADMIN : createUtilisateur(request, 0); break;
                    case Constantes.AJOUT_ENS : createUtilisateur(request, 1); break;
                    case Constantes.AJOUT_REC : createUtilisateur(request, 2); break;
                    case Constantes.SAUVEGARDE_UTILISATEURS : sauvegarUtilisateurParId(request); break;
                    case Constantes.SUP_UTIL :
                        deleteUtilisateur(request);
                        break;
                    //case Constantes.AJOUT_OFFRE : createOffre(request, 0, 0); break;
                    //case Constantes.SUP_OFFRE : deleteOffre(null, 0); break;
                    //case Constantes.MOD_OFFRE : updateOffre(request); break;
                    default:break;
                }
                List<UtilisateurEntity> listeAdministrateurs = utilisateurSessionBean.getTousLesAdministrateurs(utilisateur.getId_utilisateur());

                List<UtilisateurEntity> listeProfesseurs = utilisateurSessionBean.getTousLesProfesseurs();
                request.setAttribute(Constantes.LIST_ENS, listeProfesseurs);

                List<UtilisateurEntity> listeRecruteurs = utilisateurSessionBean.getTousLesRecruteurs();
                listeOffresEntity = offreSessionBean.chercherToutesLesOffres();

                request.setAttribute(Constantes.LIST_REC, listeRecruteurs);
                request.setAttribute(Constantes.LIST_ADMIN, listeAdministrateurs);
                request.setAttribute(Constantes.LIST_OFFRE, listeOffresEntity);

                path = Constantes.ADMIN_PATH;
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
        utilisateurSessionBean.insertionUtilisateur(utilisateur);
    }
    public void deleteUtilisateur(HttpServletRequest request){
        try {
            int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
            utilisateurSessionBean.suppressionUtilisateur(id);
        } catch (Exception e) {
            System.out.println(e.toString());
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
    public void sauvegarUtilisateurParId(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
        UtilisateurEntity utilisateurEntity = utilisateurSessionBean.chercheUtilisateurById(id);
        utilisateurEntity.setEmail(request.getParameter(Constantes.EMAIL));
        utilisateurEntity.setNom(request.getParameter(Constantes.NOM));
        utilisateurEntity.setPrenom(request.getParameter(Constantes.PRENOM));
        utilisateurEntity.setMotDePasse(request.getParameter(Constantes.MOT_DE_PASSE));
        utilisateurEntity.setPseudo(request.getParameter(Constantes.PSEUDO));
        utilisateurEntity.setSiteWeb(request.getParameter(Constantes.SITE));
        utilisateurEntity.setTelephone(request.getParameter(Constantes.TELEPHONE));
        utilisateurSessionBean.modificationUtilisateur(utilisateurEntity);
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

