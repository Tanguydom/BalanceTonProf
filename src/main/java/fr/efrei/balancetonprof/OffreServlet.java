package fr.efrei.balancetonprof;

import fr.efrei.balancetonprof.model.*;
import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//@WebServlet(name = "offreServlet", value = "/offre-servlet")

public class OffreServlet extends HttpServlet {

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
    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute(Constantes.UTILISATEUR);

        int userId = utilisateur.getId_utilisateur();
        request.setAttribute(Constantes.UTILISATEUR, utilisateur);

        String path = Constantes.OFFRE_PATH;

        String action = request.getParameter(Constantes.ACTION);
        List<OffreEmploiEntity> listeOffresEntity;
        List<Offre> listOffres;

        switch (utilisateur.getRole()){
            case 0 :
                switch (action){
                    case Constantes.AJOUT_OFFRE: creationOffre(request);break;
                    case Constantes.SUP_OFFRE: suppressionOffre(request);break;
                    case Constantes.MOD_OFFRE: modificationOffre(request); break;
                    case Constantes.ATTRIBUER_RECRUTEUR : assigneRecruteur(request); break;
                    default:break;
                }
                listeOffresEntity = offreSessionBean.getOffers();
                listOffres = conversion(listeOffresEntity);
                List<UtilisateurEntity> recruteurList = utilisateurSessionBean.getRecruiter();
                List<EntrepriseEntity> entrepriseEntityList = entrepriseSessionBean.getListEnterprise();

                request.setAttribute(Constantes.LIST_REC, recruteurList);
                request.setAttribute(Constantes.LIST_ENTREPRISE, entrepriseEntityList);
                request.setAttribute(Constantes.LIST_OFFRE, listOffres);
                break ;
            case 1 :
                switch (action){
                    case Constantes.CANDIDATER: candidater(request, userId);break;
                    default:break;
                }
                EnseignantEntity enseignant = enseignantSessionBean.getProfessorByIdUtilisateur(userId);
                listeOffresEntity = offreSessionBean.getOffersWithNoApplicant(userId);
                listOffres = conversion(listeOffresEntity);

                request.setAttribute(Constantes.LIST_OFFRE, listOffres);
                request.setAttribute(Constantes.ENSEIGNANT, enseignant);
                break;

            case 2 :
                RecruteurEntity recruteur = recruteurSessionBean.getRecruiterByIdUtilisateur(userId);
                EntrepriseEntity entrepriseEntity = entrepriseSessionBean.getEnterpriseById(recruteur.getIdEntreprise());

                switch (action){
                    case Constantes.AJOUT_OFFRE: creationOffre(request, entrepriseEntity.getIdEntreprise(), recruteur.getIdUtilisateur());break;
                    case Constantes.SUP_OFFRE: suppressionOffre(request);break;
                    case Constantes.MOD_OFFRE: modificationOffre(request); break;
                    default:break;
                }

                listeOffresEntity = offreSessionBean.getOffersByIdRecruteur(recruteur.getIdUtilisateur());
                listOffres = conversion(listeOffresEntity);
                request.setAttribute(Constantes.RECRUTEUR, recruteur);
                request.setAttribute(Constantes.ENTREPRISE, entrepriseEntity);
                request.setAttribute(Constantes.LIST_OFFRE, listOffres);
                break;
            default:break;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    public void assigneRecruteur(HttpServletRequest request){
        Integer idRecruteur = Integer.valueOf(request.getParameter(Constantes.COMBOREC2));
        Integer idEntreprise = Integer.valueOf(request.getParameter(Constantes.ID_ENTREPRISE));
        Integer idOffre = Integer.valueOf(request.getParameter(Constantes.ID_OFFRE));

        if(idEntreprise != null && idRecruteur !=null
                && recruteurSessionBean.checkIfExist(idRecruteur, idEntreprise) != null
                && recruteurSessionBean.checkIfExist(idRecruteur, idEntreprise) > 0 ){
            RecrutementEntity recrutementEntity = recrutementSessionBean.getRecruitmentByIdOffre(idOffre);
            recrutementEntity.setIdRecruteur(idRecruteur);
            recrutementSessionBean.updateRecruitment(recrutementEntity);
        }

    }
    public void creationOffre(HttpServletRequest request, int idEntreprise, int idUtilisateur){
        OffreEmploiEntity offreEmploiEntity = new OffreEmploiEntity();

        String intitule = request.getParameter(Constantes.COE_INTITULE);
        String description = request.getParameter(Constantes.COE_DESCRIPTION);

        offreEmploiEntity.setIntitule(intitule);
        offreEmploiEntity.setDescription(description);
        offreEmploiEntity.setIdEntreprise(idEntreprise);
        offreSessionBean.insertOffer(offreEmploiEntity);

        RecrutementEntity recrutementEntity = new RecrutementEntity();
        recrutementEntity.setIdOffre(offreEmploiEntity.getIdOffre());
        recrutementEntity.setIdRecruteur(idUtilisateur);
        recrutementSessionBean.insertRecruitment(recrutementEntity);
    }

    public void creationOffre(HttpServletRequest request){
        OffreEmploiEntity offreEmploiEntity = new OffreEmploiEntity();

        String intitule = request.getParameter(Constantes.COE_INTITULE);
        String description = request.getParameter(Constantes.COE_DESCRIPTION);

        offreEmploiEntity.setIntitule(intitule);
        offreEmploiEntity.setDescription(description);

        Integer idEntreprise = Integer.valueOf(request.getParameter(Constantes.COMBOENTR));
        Integer idRecruteur = Integer.valueOf(request.getParameter(Constantes.COMBOREC));

        if(idEntreprise != null && idRecruteur !=null
                && recruteurSessionBean.checkIfExist(idRecruteur, idEntreprise) != null
                && recruteurSessionBean.checkIfExist(idRecruteur, idEntreprise) > 0 ){

            offreEmploiEntity.setIdEntreprise(idEntreprise);
            offreSessionBean.insertOffer(offreEmploiEntity);

            RecrutementEntity recrutementEntity = new RecrutementEntity();
            recrutementEntity.setIdOffre(offreEmploiEntity.getIdOffre());
            recrutementEntity.setIdRecruteur(idRecruteur);
            recrutementSessionBean.insertRecruitment(recrutementEntity);
        }else{
           //entreprise ou recruteur  id null ou entreprise non lié au recruteur
        }

    }
    public void suppressionOffre(HttpServletRequest request){
        int idOffre = Integer.parseInt(request.getParameter(Constantes.ID_OFFRE));
        recrutementSessionBean.deleteRecruitment(idOffre);
        offreSessionBean.deleteOffer(idOffre);
    }
    public void modificationOffre(HttpServletRequest request) {
        int idOffre = Integer.parseInt(request.getParameter(Constantes.ID_OFFRE));
        OffreEmploiEntity offreEmploiEntity = offreSessionBean.getOfferById(idOffre);
        offreEmploiEntity.setDescription(request.getParameter(Constantes.DESCRIPTION));
        offreEmploiEntity.setIntitule(request.getParameter(Constantes.INTITULE));
        offreSessionBean.updateOffer(offreEmploiEntity);
    }
    public void candidater(HttpServletRequest request, int userId){
        int idOffre = Integer.parseInt(request.getParameter(Constantes.ID_OFFRE));
        Integer res = candidatureSessionBean.getApplicationByIdOffreIdEns(idOffre,userId);
        if(res == null || res <= 0){
            CandidatureEntity candidature = new CandidatureEntity();
            candidature.setIdProf(userId);
            candidature.setIdOffre(idOffre);
            candidature.setStatut(0);
            candidatureSessionBean.insertApplication(candidature);
        }
    }
    public List<Offre> conversion(List<OffreEmploiEntity> entityList){
        List<Offre> offreList = new ArrayList<>();
        for(OffreEmploiEntity entity : entityList){
            Offre offre = new Offre();

            offre.setIdOffre(entity.getIdOffre());
            offre.setIntitule(entity.getIntitule());
            offre.setDescription(entity.getDescription());
            offre.setIdEntreprise(entity.getIdEntreprise());

            EntrepriseEntity entrepriseEntity  = entrepriseSessionBean.getEnterpriseById(offre.getIdEntreprise());
            offre.setNomEntreprise(entrepriseEntity.getNom());
            offre.setSiteWebEntreprise(entrepriseEntity.getSiteWeb());
            offre.setAdresseEntreprise(entrepriseEntity.getAdresse());

            Integer nbCandidat = candidatureSessionBean.countApplicationsByIdOffre(offre.getIdOffre());
            offre.setNbCandidat(nbCandidat);

            Integer idRecruteur = recrutementSessionBean.getIdRecruteurByIdOffre(offre.getIdOffre());
            offre.setIdRecruteur(idRecruteur);
            if(idRecruteur != null) {
                UtilisateurEntity utilisateurEntity = utilisateurSessionBean.getUserById(idRecruteur);
                offre.setEmailRecruteur(utilisateurEntity.getEmail());
                offre.setTelephoneRecruteur(utilisateurEntity.getTelephone());
            }else{
                offre.setEmailRecruteur(null);
                offre.setTelephoneRecruteur(null);
            }
            offreList.add(offre);
        }
        return offreList;
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
