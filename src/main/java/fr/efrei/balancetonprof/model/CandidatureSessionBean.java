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

        Long nombreCandidatures = (long) q.getSingleResult();
        return nombreCandidatures.intValue();
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
