package fr.efrei.balancetonprof.model;
import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class UtilisateurSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public UtilisateurEntity chercheUtilisateurById(int id) {
        return em.find(UtilisateurEntity.class, id);
    }
    public int verificationExistanceUtilisateur(String pseudo, String motDePasse) {
        try{
            TypedQuery<Integer> query = em.createQuery("SELECT e.idUtilisateur  FROM UtilisateurEntity e WHERE e.pseudo = :pseudo AND e.motDePasse = :motDePasse", Integer.class);
            query.setParameter(Constantes.PSEUDO, pseudo);
            query.setParameter(Constantes.MOT_DE_PASSE, motDePasse);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return -1;
        }
    }
    public void insertionUtilisateur(UtilisateurEntity nouvelUtilisateur) {
        em.getTransaction().begin();
        em.persist(nouvelUtilisateur);
        em.getTransaction().commit();
    }
    public void modificationUtilisateur(UtilisateurEntity utilisateur) {
        em.getTransaction().begin();
        em.merge(utilisateur);
        em.getTransaction().commit();
    }
    public void suppressionUtilisateur(int id) {
        em.getTransaction().begin();
        UtilisateurEntity utilisateur = em.find(UtilisateurEntity.class, id);
        em.remove(utilisateur);
        em.getTransaction().commit();
    }
    public void suppresionProfesseur(int idEns){
        em.getTransaction().begin();
        Query q = em.createQuery("DELETE FROM EnseignantEntity e WHERE e.idUtilisateur = :idEns");
        q.setParameter(Constantes.ID_ENS, idEns);
        q.executeUpdate();
        em.getTransaction().commit();
    }
    public List<UtilisateurEntity> getTousLesAdministrateurs(int id){ // role = 0
        Query q = em.createQuery("select e from UtilisateurEntity e where e.role = 0 and e.idUtilisateur != :id");
        q.setParameter("id", id); // Liez le paramètre 'id' ici
        return q.getResultList();
    }
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

    public void suppresionCandidature(int idEns){
        Query q = em.createQuery("DELETE FROM CandidatureEntity e WHERE e.idProf = :idEns");
        em.getTransaction().begin();
        q.setParameter(Constantes.ID_ENS, idEns);
        q.executeUpdate();
        em.getTransaction().commit();
    }
    public void mettreProfNullOnRecruteur(int idRec){
        Query q = em.createQuery("UPDATE RecrutementEntity r SET r.idRecruteur = NULL WHERE r.idRecruteur = :idRecruteur");
        em.getTransaction().begin();
        q.setParameter(Constantes.ID_RECRUCTEUR, idRec);
        q.executeUpdate();
        em.getTransaction().commit();
    }
    public void suppresionRecruteur(int idRec){
        Query q = em.createQuery("DELETE FROM RecruteurEntity e WHERE e.idUtilisateur = :idRecruteur");
        em.getTransaction().begin();
        q.setParameter(Constantes.ID_RECRUCTEUR, idRec);
        q.executeUpdate();
        em.getTransaction().commit();
    }
}