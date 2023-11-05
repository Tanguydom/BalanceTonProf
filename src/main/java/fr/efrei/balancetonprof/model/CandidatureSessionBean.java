package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
@Stateless
public class CandidatureSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public List<CandidatureEntity> getToutesLesCandidatures(){
        Query q = em.createQuery("select e from CandidatureEntity e");
        return  q.getResultList();
    }

    public int getNombreCandidaturesPourOffre(int idOffre) {
        Query q = em.createQuery("SELECT COUNT(c.idProf) FROM CandidatureEntity c WHERE c.idOffre = :idOffre");
        q.setParameter("idOffre", idOffre);

        Long nombreCandidatures = (long) q.getSingleResult();
        return nombreCandidatures.intValue();
    }
    public void insererCandidature(CandidatureEntity candidatureEntity) {
        em.getTransaction().begin();
        em.persist(candidatureEntity);
        em.getTransaction().commit();
    }
    public Integer findCandidatureByIdOffreIdEns(int idOffre, int idEns) {
        Query q = em.createQuery("SELECT COUNT(c.idCandidature) FROM CandidatureEntity c WHERE c.idOffre = :idOffre AND c.idProf = :idEns");
        q.setParameter("idOffre", idOffre);
        q.setParameter("idEns", idEns);

        try {
            Integer nombreCandidatures = (Integer) q.getSingleResult();
            return nombreCandidatures;
        } catch (Exception e) {
            return null; // Aucune entreprise trouvée pour l'IDOffre donné.
        }
    }

    public void changeStatut(int idCandidature, int status){
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE CandidatureEntity c SET c.statut = :status WHERE c.idCandidature = :idCandidature");
        q.setParameter("idCandidature", idCandidature);
        q.setParameter("status", status);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public List<CandidatureEntity> getCandidatures(int id) {
        Query q = em.createQuery("SELECT c FROM OffreEmploiEntity e, RecrutementEntity r, CandidatureEntity c " +
                "WHERE e.idOffre = r.idOffre AND r.idRecruteur = :id AND c.idOffre = e.idOffre AND c.statut = 0");
        q.setParameter("id", id);
        return q.getResultList();
    }
    public List<CandidatureEntity> getCandidaturesByEns(int id) {
        Query q = em.createQuery("SELECT c FROM OffreEmploiEntity e, RecrutementEntity r, CandidatureEntity c " +
                "WHERE e.idOffre = r.idOffre AND c.idProf = :id AND c.idOffre = e.idOffre");
        q.setParameter("id", id);
        return q.getResultList();
    }


    public void supprimerCandidature(int idOffre, int idEns){
        em.getTransaction().begin();
        Query q = em.createQuery("DELETE FROM CandidatureEntity c WHERE c.idOffre = :idOffre AND c.idProf = :idEns");
        q.setParameter("idOffre", idOffre);
        q.setParameter("idEns", idEns);
        q.executeUpdate();
        em.getTransaction().commit();

    }
    public void supprimerCandidature(int id) {
        em.getTransaction().begin();
        CandidatureEntity candidature = em.find(CandidatureEntity.class, id);
        em.remove(candidature);
        em.getTransaction().commit();
    }
}
