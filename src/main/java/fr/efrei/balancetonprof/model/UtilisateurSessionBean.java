package fr.efrei.balancetonprof.model;
import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class UtilisateurSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public UtilisateurEntity getUserById(int id) {
        return em.find(UtilisateurEntity.class, id);
    }
    public int checkIfExist(String pseudo, String motDePasse) {
        try{
            TypedQuery<Integer> query = em.createQuery("SELECT e.idUtilisateur  FROM UtilisateurEntity e WHERE e.pseudo = :pseudo AND e.motDePasse = :motDePasse", Integer.class);
            query.setParameter(Constantes.PSEUDO, pseudo);
            query.setParameter(Constantes.MOT_DE_PASSE, motDePasse);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return -1;
        }
    }
    public void insertUser(UtilisateurEntity nouvelUtilisateur) {
        em.getTransaction().begin();
        em.persist(nouvelUtilisateur);
        em.getTransaction().commit();
    }
    public void updateUser(UtilisateurEntity utilisateur) {
        em.getTransaction().begin();
        em.merge(utilisateur);
        em.getTransaction().commit();
    }
    public void deleteUser(int id) {
        em.getTransaction().begin();
        UtilisateurEntity utilisateur = em.find(UtilisateurEntity.class, id);
        em.remove(utilisateur);
        em.getTransaction().commit();
    }
    public List<UtilisateurEntity> getAdmins(int id){
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 0 and e.idUtilisateur != :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    public List<UtilisateurEntity> getProfessors(){
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 1");
        return  q.getResultList();
    }
    public List<UtilisateurEntity> getRecruiter(){
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 2");
        return  q.getResultList();
    }
    public void deleteRecruiterFromRecruitment(int idRec){
        Query q = em.createQuery("UPDATE RecrutementEntity r SET r.idRecruteur = NULL WHERE r.idRecruteur = :idRecruteur");
        em.getTransaction().begin();
        q.setParameter(Constantes.ID_RECRUCTEUR, idRec);
        q.executeUpdate();
        em.getTransaction().commit();
    }
}