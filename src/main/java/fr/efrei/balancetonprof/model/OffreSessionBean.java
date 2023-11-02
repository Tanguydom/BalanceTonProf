package fr.efrei.balancetonprof.model;

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
    public List<OffreEmploiEntity> getToutesLesOffres(){
        Query q = em.createQuery("select e from OffreEmploiEntity e");
        return  q.getResultList();
    }

    public List<OffreEmploiEntity> getToutesLesOffresNonCandidater(int id) {
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e " +
                "LEFT JOIN CandidatureEntity c ON e.idOffre = c.idOffre AND c.idProf = :id " +
                "WHERE c.idOffre IS NULL");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public List<OffreEmploiEntity> getToutesLesOffresCandidater(int id) {
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e, CandidatureEntity c WHERE e.idOffre = c.idOffre AND c.idProf = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }


    public List<OffreEmploiEntity> getToutesLesOffresPourUnRecruteur(int id) {
        //a modifier temporaire
        Query q = em.createQuery("SELECT e FROM OffreEmploiEntity e, RecrutementEntity r WHERE e.idOffre = r.idOffre AND r.idRecruteur = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public void modifieOffre(int idOffre, String initule, String description) {
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE OffreEmploiEntity e SET e.intitule = :initule , e.description = :description WHERE e.idOffre = :idOffre");
        query.setParameter("initule", initule);
        query.setParameter("description", description);
        query.setParameter("idOffre", idOffre);
        em.flush();
        em.clear();

        query.executeUpdate();
        em.getTransaction().commit();
    }

    public void insererOffre(OffreEmploiEntity nouvelleOffre) {
        em.getTransaction().begin();
        em.persist(nouvelleOffre);
        em.getTransaction().commit();
    }
    public void supprimerOffre(int id) {
        em.getTransaction().begin();
        OffreEmploiEntity offre = em.find(OffreEmploiEntity.class, id);
        em.remove(offre);
        em.getTransaction().commit();
    }

}
