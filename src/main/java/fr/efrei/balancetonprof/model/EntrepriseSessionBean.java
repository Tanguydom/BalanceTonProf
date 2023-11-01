package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EntrepriseSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public List<EntrepriseEntity> getToutesLesEntreprises(){
        Query q = em.createQuery("select e from EntrepriseEntity e");
        return  q.getResultList();
    }

    public EntrepriseEntity getEntrepriseById(int id){
        return em.find(EntrepriseEntity.class, id);
    }

    public void insererEntreprise(EntrepriseEntity entrepriseEntity) {
        em.getTransaction().begin();
        em.persist(entrepriseEntity);
        em.getTransaction().commit();
    }
    public void supprimerEntreprise(int id) {
        em.getTransaction().begin();
        EntrepriseEntity entrepriseEntity = em.find(EntrepriseEntity.class, id);
        em.remove(entrepriseEntity);
        em.getTransaction().commit();
    }
}
