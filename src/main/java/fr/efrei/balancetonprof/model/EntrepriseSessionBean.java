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
    public EntrepriseEntity chercheEntrepriseParId(int id){
        return em.find(EntrepriseEntity.class, id);
    }
    public List<EntrepriseEntity> getListEntreprise(){
        Query q = em.createQuery("select e from EntrepriseEntity e");
        return q.getResultList();
    }
}
