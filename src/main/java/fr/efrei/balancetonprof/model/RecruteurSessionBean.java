package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RecruteurSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public RecruteurEntity getRecruteurById(int id) {
        return em.find(RecruteurEntity.class, id);
    }

    public void updateRecruteur(RecruteurEntity recruteur) {
        em.merge(recruteur);
    }

    public void insererRecruteur(RecruteurEntity recruteur) {
        em.persist(recruteur);
    }

}

