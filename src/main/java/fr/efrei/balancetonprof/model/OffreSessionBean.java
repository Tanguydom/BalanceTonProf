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
