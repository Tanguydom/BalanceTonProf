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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute(Constantes.UTILISATEUR);

        int userId = utilisateur.getId_utilisateur();
        request.setAttribute(Constantes.UTILISATEUR, utilisateur);

        String path = "" ;

        String action = request.getParameter(Constantes.ACTION);

        if(utilisateur.getRole() != 0 && action.equals(Constantes.SAUVEGARDE_UTILISATEUR)){
            sauvegardeUtilisateur(request, utilisateur, userId);
            session.setAttribute(Constantes.UTILISATEUR, utilisateur);
        }
        List<OffreEmploiEntity> listeOffresEntity;
        List<Offre> listOffres;

        path = Constantes.PROFIL_PATH;

        switch (utilisateur.getRole()){
            case 0 :
                List<UtilisateurEntity> listeAdministrateurs = utilisateurSessionBean.getTousLesAdministrateurs(utilisateur.getId_utilisateur());
                request.setAttribute(Constantes.LIST_ADMIN, listeAdministrateurs);
                switch (action){
                    case Constantes.AJOUT_ADMIN : createUtilisateur(request, 0); break;
                    case Constantes.AJOUT_ENS : createUtilisateur(request, 1); break;
                    case Constantes.AJOUT_REC : createUtilisateur(request, 2); break;
                    case Constantes.SUP_ENS :
                        int id = Integer.parseInt(request.getParameter(Constantes.ID_UTILISATEUR));
                        deleteUtilisateur(id);
                        break;
                    case Constantes.AJOUT_OFFRE : createOffre(request, 0, 0); break;
                    case Constantes.SUP_OFFRE : deleteOffre(null, 0); break;
                    case Constantes.MOD_OFFRE : updateUtilisateur(request); break;
                    default:break;
                }

                List<UtilisateurEntity> listeProfesseurs = utilisateurSessionBean.getTousLesProfesseurs();
                request.setAttribute(Constantes.LIST_ENS, listeProfesseurs);

                List<UtilisateurEntity> listeRecruteurs = utilisateurSessionBean.getTousLesRecruteurs();
                request.setAttribute(Constantes.LIST_REC, listeRecruteurs);

                listeOffresEntity = offreSessionBean.getToutesLesOffres();
                request.setAttribute(Constantes.LIST_OFFRE, listeOffresEntity);

                path = Constantes.ADMIN_PATH;

                break ;
            case 1 :
                EnseignantEntity enseignant = enseignantSessionBean.findEnseignantByIdUtilisateur(userId);
                listeOffresEntity = offreSessionBean.getToutesLesOffres();
                listOffres = offreEntityToOffre(listeOffresEntity);
                request.setAttribute(Constantes.LIST_OFFRE, listOffres);
                request.setAttribute(Constantes.ENSEIGNANT, enseignant);
                switch (action){
                    case Constantes.SAUVGARDE_DETAIL :sauvegardeEnseignant(request, userId); break;
                    default:break;
                }break;

            case 2 :
                RecruteurEntity recruteur = recruteurSessionBean.findRecruteurByIdUtilisateur(userId);
                EntrepriseEntity entrepriseEntity = entrepriseSessionBean.getEntrepriseById(recruteur.getIdEntreprise());
                // a modifier temporaire
                listeOffresEntity = offreSessionBean.getToutesLesOffresPourUnRecruteur(recruteur.getIdUtilisateur());
                listOffres = offreEntityToOffre(listeOffresEntity);
                request.setAttribute(Constantes.RECRUTEUR, recruteur);
                request.setAttribute(Constantes.ENTREPRISE, entrepriseEntity);
                request.setAttribute(Constantes.LIST_OFFRE, listOffres);
                switch (action){
                    case Constantes.SAUVGARDE_DETAIL:sauvegardeEnseignant(request, userId); break;
                    case Constantes.AJOUT_OFFRE: createOffre(request, entrepriseEntity.getIdEntreprise(), recruteur.getIdUtilisateur());break;
                    case Constantes.SUP_OFFRE: deleteOffre(request, recruteur.getIdUtilisateur());break;
                    case Constantes.MOD_OFFRE: modifieOffre(request); break;
                    default:break;
                }
                //besoin pour refresh
                listeOffresEntity = offreSessionBean.getToutesLesOffresPourUnRecruteur(recruteur.getIdUtilisateur());
                listOffres = offreEntityToOffre(listeOffresEntity);
                request.setAttribute(Constantes.RECRUTEUR, recruteur);
                request.setAttribute(Constantes.ENTREPRISE, entrepriseEntity);
                request.setAttribute(Constantes.LIST_OFFRE, listOffres);
                break;
            default:break;
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public List<Offre> offreEntityToOffre(List<OffreEmploiEntity> entityList){
        List<Offre> offreList = new ArrayList<>();
        for(OffreEmploiEntity entity : entityList){
            Offre offre = new Offre();

            offre.setIdOffre(entity.getIdOffre());
            offre.setIntitule(entity.getIntitule());
            offre.setDescription(entity.getDescription());
            offre.setIdEntreprise(entity.getIdEntreprise());

            EntrepriseEntity entrepriseEntity  = entrepriseSessionBean.getEntrepriseById(offre.getIdEntreprise());
            offre.setNomEntreprise(entrepriseEntity.getNom());
            offre.setSiteWebEntreprise(entrepriseEntity.getSiteWeb());
            offre.setAdresseEntreprise(entrepriseEntity.getAdresse());

            int nbCandidat = candidatureSessionBean.getNombreCandidaturesPourOffre(offre.getIdOffre());
            offre.setNbCandidat(nbCandidat);

            Integer idRecruteur = recrutementSessionBean.getIdRecruteurPourOffre(offre.getIdOffre());
            if(idRecruteur != null) {
              //  RecruteurEntity recruteurEntity = recruteurSessionBean.getRecruteurById(idRecruteur);
                UtilisateurEntity utilisateurEntity = utilisateurSessionBean.getUtilisateurById(idRecruteur);
                offre.setEmailRecruteur(utilisateurEntity.getEmail());
                offre.setTelephoneRecruteur(utilisateurEntity.getTelephone());
            }
            offreList.add(offre);
        }
        return offreList;
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
    }
    public void createOffre(HttpServletRequest request, int idEntreprise, int idUtilisateur){
        OffreEmploiEntity offreEmploiEntity = new OffreEmploiEntity();

        String intitule = request.getParameter(Constantes.COE_INTITULE);
        String description = request.getParameter(Constantes.COE_DESCRIPTION);

        offreEmploiEntity.setIntitule(intitule);
        offreEmploiEntity.setDescription(description);
        offreEmploiEntity.setIdEntreprise(idEntreprise);
        offreSessionBean.insererOffre(offreEmploiEntity);

        RecrutementEntity recrutementEntity = new RecrutementEntity();
        recrutementEntity.setIdOffre(offreEmploiEntity.getIdOffre());
        recrutementEntity.setIdRecruteur(idUtilisateur);
        recrutementSessionBean.insererRecrutement(recrutementEntity);
    }
    public void deleteOffre(HttpServletRequest request, int idRecruteur){
        int idOffre = Integer.parseInt(request.getParameter(Constantes.ID_OFFRE));
        recrutementSessionBean.supprimerRecrutement(idOffre, idRecruteur);
        offreSessionBean.supprimerOffre(idOffre);
    }
    public void modifieOffre(HttpServletRequest request) {
        int idOffre = Integer.parseInt(request.getParameter(Constantes.ID_OFFRE));
        String intitule = request.getParameter(Constantes.IO_INTITULE);
        String description = request.getParameter(Constantes.IO_DESCRIPTION);
        offreSessionBean.modifieOffre(idOffre,intitule,description);
    }

    public void sauvegardeUtilisateur(HttpServletRequest request, Utilisateur utilisateur, int userId) {
        UtilisateurEntity newUtilisateurEntity = new UtilisateurEntity(
                userId,
                request.getParameter(Constantes.PSEUDO),
                request.getParameter(Constantes.MOT_DE_PASSE),
                request.getParameter(Constantes.NOM),
                request.getParameter(Constantes.PRENOM),
                utilisateur.getRole(),
                request.getParameter(Constantes.EMAIL),
                request.getParameter(Constantes.TELEPHONE),
                request.getParameter(Constantes.SITE)
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
        String experience = request.getParameter(Constantes.EXPERIENCE);
        String competence = request.getParameter(Constantes.COMPETENCE);
        String interet = request.getParameter(Constantes.INTERET);
        String evaluation = request.getParameter(Constantes.EVALUATION);
        String niveauSouhaite = request.getParameter(Constantes.NIVEAU_SOUHAITE);
        String autresInformations = request.getParameter(Constantes.AUTRES_INFORMATIONS);
        String disponibilite = request.getParameter(Constantes.DISPONIBILITE);

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
        EnseignantEntity entity = (EnseignantEntity) request.getAttribute(Constantes.ENSEIGNANT);
        if(entity != null){
            enseignantSessionBean.updateEnseignant(newEnseignantEntity);
            request.setAttribute(Constantes.ENSEIGNANT, newEnseignantEntity);
        }
    }
}

