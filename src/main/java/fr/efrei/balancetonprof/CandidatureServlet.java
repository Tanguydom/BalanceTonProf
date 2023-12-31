package fr.efrei.balancetonprof;

//@WebServlet(name = "candidatureServlet", value = "/candidature-servlet")

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

public class CandidatureServlet extends HttpServlet {
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

        String path = Constantes.CANDIDATURE_PATH;
        String action = request.getParameter(Constantes.ACTION);
        List<CandidatureEntity> candidatureEntityList;
        List<Candidature> listCandidature;

        switch (utilisateur.getRole()){
            case 0 :break ;
            case 1 :
                EnseignantEntity enseignant = enseignantSessionBean.getProfessorByIdUtilisateur(userId);

                switch (action){
                    case Constantes.RETIRER_CANDIDATURE: retirerCandidature(request, userId) ;break;
                    default:break;
                }
                candidatureEntityList = candidatureSessionBean.getApplicationsByIdEnseignant(userId);
                listCandidature = conversion(candidatureEntityList);

                request.setAttribute(Constantes.LIST_CANDIDATURE, listCandidature);
                request.setAttribute(Constantes.ENSEIGNANT, enseignant);
                break;

            case 2 :
                RecruteurEntity recruteur = recruteurSessionBean.getRecruiterByIdUtilisateur(userId);
                EcoleEntity ecoleEntity = ecoleSessionBean.getSchoolById(recruteur.getIdEcole());

                switch (action){
                    case Constantes.ACCEPTER_CANDIDATURE: accepterCandidature(request);break;
                    case Constantes.REFUSER_CANDIDATURE: refuserCandidature(request);break;
                    default:break;
                }
                candidatureEntityList = candidatureSessionBean.getApplicationsByIdRecruteur(userId);
                listCandidature = conversion(candidatureEntityList);

                request.setAttribute(Constantes.LIST_CANDIDATURE, listCandidature);
                request.setAttribute(Constantes.RECRUTEUR, recruteur);
                request.setAttribute(Constantes.ECOLE, ecoleEntity);
                break;
            default:break;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    public void refuserCandidature(HttpServletRequest request){
        try{
            int idCandidature = Integer.parseInt(request.getParameter(Constantes.ID_CANDIDATURE));
            candidatureSessionBean.changeStatut(idCandidature, 2);
        }catch (Exception e){
        }
    }
    public void accepterCandidature(HttpServletRequest request){
        try{
            int idCandidature = Integer.parseInt(request.getParameter(Constantes.ID_CANDIDATURE));
            candidatureSessionBean.changeStatut(idCandidature, 1);
        }catch (Exception e){
        }
    }
    public void retirerCandidature(HttpServletRequest request, int userId){
        try{
            int idCandidature = Integer.parseInt(request.getParameter(Constantes.ID_CANDIDATURE));
            candidatureSessionBean.deleteApplication(idCandidature);
        }catch (Exception e){
        }
    }
    public List<Candidature> conversion(List<CandidatureEntity> candidatureEntityList){
        List<Candidature> listCandidature = new ArrayList<>();
        for(CandidatureEntity candidatureEntity : candidatureEntityList){
            Candidature candidature = new Candidature();
            candidature.setIdCandidature(candidatureEntity.getIdCandidature());
            candidature.setIdOffre(candidatureEntity.getIdOffre());

            OffreEmploiEntity offre = offreSessionBean.getOfferById(candidature.getIdOffre());
            candidature.setIntitule(offre.getIntitule());

            Integer idEcole = offreSessionBean.getSchoolByIdOffre(offre.getIdOffre());
            EcoleEntity ecoleEntity = ecoleSessionBean.getSchoolById(idEcole);

            UtilisateurEntity utilisateur = utilisateurSessionBean.getUserById(candidatureEntity.getIdEnseignant());
            EnseignantEntity enseignant = enseignantSessionBean.getProfessorByIdUtilisateur(candidatureEntity.getIdEnseignant());

            candidature.setNomCandidat(utilisateur.getNom());
            candidature.setPrenomCandidat(utilisateur.getPrenom());
            candidature.setCompetence(enseignant.getCompetence());
            candidature.setEvaluation(enseignant.getEvaluation());
            candidature.setDisponibilite(enseignant.getDisponibilite());
            candidature.setExperience(enseignant.getExperience());
            candidature.setNiveauSouhaite(enseignant.getNiveauSouhaite());
            candidature.setAutresInformations(enseignant.getAutresInformations());
            candidature.setInteret(enseignant.getInteret());
            candidature.setIdEcole(ecoleEntity.getIdEcole());
            candidature.setNomEcole(ecoleEntity.getNom());
            candidature.setStatut(candidatureEntity.getStatut());
            listCandidature.add(candidature);
        }

        return listCandidature;
    }

}
