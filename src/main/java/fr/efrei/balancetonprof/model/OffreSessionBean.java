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
    public List<OffreEmploiEntity> getOffers(){
        Query q = em.createQuery("select e from OffreEmploiEntity e");
        return  q.getResultList();
    }
    public List<OffreEmploiEntity> getOffersWithNoApplicant(int idEns) {
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e " +
                "LEFT JOIN CandidatureEntity c ON e.idOffre = c.idOffre AND c.idEnseignant = :idEnseignant " +
                "WHERE c.idOffre IS NULL");
        q.setParameter(Constantes.ID_ENSEIGNANT, idEns);
        return q.getResultList();
    }
    public Integer getSchoolByIdOffre(int idOffre){
        Query q = em.createQuery("SELECT o.idEcole FROM OffreEmploiEntity o WHERE o.idOffre = :idOffre");
        q.setParameter(Constantes.ID_OFFRE, idOffre);
        Integer idEcole ;
        try {
            idEcole = (Integer) q.getSingleResult();
        } catch (Exception e) {
            idEcole = null ;
        }
        return idEcole;
    }
    public List<OffreEmploiEntity> getOffersByIdRecruteur(int idRec) {
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e, RecrutementEntity r WHERE e.idOffre = r.idOffre AND r.idRecruteur = :idRecruteur");
        q.setParameter(Constantes.ID_RECRUCTEUR, idRec);
        return q.getResultList();
    }
    public void updateOffer(OffreEmploiEntity offreEmploiEntity) {
        em.getTransaction().begin();
        em.merge(offreEmploiEntity);
        em.getTransaction().commit();
    }
    public void insertOffer(OffreEmploiEntity nouvelleOffre) {
        em.getTransaction().begin();
        em.persist(nouvelleOffre);
        em.getTransaction().commit();
    }
    public void deleteOffer(int id) {
        em.getTransaction().begin();
        OffreEmploiEntity offre = em.find(OffreEmploiEntity.class, id);
        em.remove(offre);
        em.getTransaction().commit();
    }
    public OffreEmploiEntity getOfferById(int id){
        OffreEmploiEntity offre = em.find(OffreEmploiEntity.class, id);
        return offre;
    }
}
