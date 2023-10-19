package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@Stateless
public class EnseignantSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public EnseignantEntity getEnseignantById(int id) {
        return em.find(EnseignantEntity.class, id);
    }

    public void updateEnseignant(EnseignantEntity enseignant) {
        em.merge(enseignant);
    }

    public void insererEnseignant(EnseignantEntity enseignant) {
        em.persist(enseignant);
    }

}

