package fr.efrei.balancetonprof.model;
import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

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
}