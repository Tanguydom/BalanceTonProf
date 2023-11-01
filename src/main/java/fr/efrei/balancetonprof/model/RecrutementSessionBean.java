package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class RecrutementSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public List<RecruteurEntity> getTousLesRecrutements(){
        Query q = em.createQuery("select e from RecruteurEntity e");
        return  q.getResultList();
    }

    public Integer getIdRecruteurPourOffre(int idOffre) {
        Query q = em.createQuery("SELECT c.idRecruteur FROM RecrutementEntity c WHERE c.idOffre = :idOffre");
        q.setParameter("idOffre", idOffre);

        try {
            Integer idProf = (Integer) q.getSingleResult();
            return idProf;
        } catch (Exception e) {
            return null; // Aucune candidature trouvée pour l'IDOffre donné.
        }
    }

    public void insererRecrutement(RecrutementEntity recrutementEntity) {
        em.getTransaction().begin();
        em.persist(recrutementEntity);
        em.getTransaction().commit();
        //insertion table recrutement
    }
    public void supprimerRecrutement(int idOffre, int idRecruteur) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM RecrutementEntity r WHERE r.idRecruteur = :idRecruteur AND r.idOffre = :idOffre");
        query.setParameter("idRecruteur", idRecruteur);
        query.setParameter("idOffre", idOffre);
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
