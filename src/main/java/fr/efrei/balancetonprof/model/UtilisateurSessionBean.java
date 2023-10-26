package fr.efrei.balancetonprof.model;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;
@Stateless
public class UtilisateurSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();

    public List<UtilisateurEntity> getTousLesUtilisateurs(){
        Query q = em.createQuery("select e from UtilisateurEntity e");
        return  q.getResultList();
    }

    public List<UtilisateurEntity> getTousLesProfesseurs(){ // role = 1
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 1");
        return  q.getResultList();
    }
    public List<UtilisateurEntity> getTousLesRecruteurs(){ // role = 2
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 2");
        return  q.getResultList();
    }
    public List<UtilisateurEntity> getTousLesAdministrateurs(int id){ // role = 0
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 0 and e.idUtilisateur != :id");
        q.setParameter("id", id); // Liez le paramètre 'id' ici
        return q.getResultList();
    }
    public UtilisateurEntity getUtilisateurById(int id) {
        return em.find(UtilisateurEntity.class, id);
    }

    public int utilisateurExist(String pseudo, String mdp) {
        try{
            TypedQuery<Integer> query = em.createQuery("SELECT e.idUtilisateur  FROM UtilisateurEntity e WHERE e.pseudo = :pseudo AND e.motDePasse = :mdp", Integer.class);
            query.setParameter("pseudo", pseudo);
            query.setParameter("mdp", mdp);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return -1;
        }
    }
    public void insererUtilisateur(UtilisateurEntity nouvelUtilisateur) {
        em.getTransaction().begin();
        em.persist(nouvelUtilisateur);
        em.getTransaction().commit();
    }

    public void updateUtilisateur(UtilisateurEntity utilisateur) {
        em.getTransaction().begin();
        em.merge(utilisateur);
        em.getTransaction().commit();
    }
    public void supprimerUtilisateur(int id) {
        em.getTransaction().begin();
        UtilisateurEntity utilisateur = em.find(UtilisateurEntity.class, id);
        em.remove(utilisateur);
        em.getTransaction().commit();
    }

}