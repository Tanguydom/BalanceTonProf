package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class OffreSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public List<OffreEmploiEntity> chercheOffresNonCandidater(int idEns) {
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e " +
                "LEFT JOIN CandidatureEntity c ON e.idOffre = c.idOffre AND c.idProf = :idEns " +
                "WHERE c.idOffre IS NULL");
        q.setParameter(Constantes.ID_ENS, idEns);
        return q.getResultList();
    }
    public Integer chercheEntrepriseIdParOffreId(int idOffre){
        Query q = em.createQuery("SELECT o.idEntreprise FROM OffreEmploiEntity o WHERE o.idOffre = :idOffre");
        q.setParameter(Constantes.ID_OFFRE, idOffre);
        Integer idEntreprise ;
        try {
            idEntreprise = (Integer) q.getSingleResult();
        } catch (Exception e) {
            idEntreprise = null ;
        }
        return idEntreprise;
    }
    public List<OffreEmploiEntity> chercheOffresPourUnRecruteur(int idRec) {
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e, RecrutementEntity r WHERE e.idOffre = r.idOffre AND r.idRecruteur = :idRec");
        q.setParameter(Constantes.ID_REC, idRec);
        return q.getResultList();
    }
    public void modificationOffre(OffreEmploiEntity offreEmploiEntity) {
        em.getTransaction().begin();
        em.merge(offreEmploiEntity);
        em.getTransaction().commit();
    }
    public void insertionOffre(OffreEmploiEntity nouvelleOffre) {
        em.getTransaction().begin();
        em.persist(nouvelleOffre);
        em.getTransaction().commit();
    }
    public void suppressionOffre(int id) {
        em.getTransaction().begin();
        OffreEmploiEntity offre = em.find(OffreEmploiEntity.class, id);
        em.remove(offre);
        em.getTransaction().commit();
    }
    public OffreEmploiEntity chercheOffreParId(int id){
        OffreEmploiEntity offre = em.find(OffreEmploiEntity.class, id);
        return offre;
    }
    public List<OffreEmploiEntity> chercherToutesLesOffres(){
        Query q = em.createQuery("select e from OffreEmploiEntity e");
        return  q.getResultList();
    }
}
