package fr.efrei.balancetonprof.model;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
@Stateless
public class UtilisateurSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();

    public List<UtilisateurEntity> getTousLesUtilisateurs(){
        Query q = em.createQuery("select e from UtilisateurEntity e");
        return  q.getResultList();
    }

    public UtilisateurEntity getUtilisateurParId(int idUtilisateur){
        Query q = em.createQuery("select e from UtilisateurEntity e where e.idUtilisateur = :idUtilisateur");
        q.setParameter("idUtilisateur", idUtilisateur);
        return (UtilisateurEntity) q.getSingleResult();
    }

    public Boolean utilisateurExist(String pseudo, String mdp) {
        Query query = em.createQuery("SELECT COUNT(e) FROM UtilisateurEntity e WHERE e.pseudo = :pseudo AND e.motDePasse = :mdp");
        query.setParameter("pseudo", pseudo);
        query.setParameter("mdp", mdp);

        long count = (long) query.getSingleResult();

        return count > 0;
    }
}