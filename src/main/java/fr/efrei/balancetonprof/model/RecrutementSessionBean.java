package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class RecrutementSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public Integer getIdRecruteurByIdOffre(int idOffre) {
        Query q = em.createQuery("SELECT c.idRecruteur FROM RecrutementEntity c WHERE c.idOffre = :idOffre");
        q.setParameter(Constantes.ID_OFFRE, idOffre);
        Integer idRecruteur;
        try {
            idRecruteur = (Integer) q.getSingleResult();
        } catch (Exception e) {
            idRecruteur = null;
        }
        return idRecruteur;
    }
    public RecrutementEntity getRecruitmentByIdOffre(int idOffre) {
        return em.createQuery("SELECT c FROM RecrutementEntity c WHERE c.idOffre = :idOffre", RecrutementEntity.class)
                .setParameter(Constantes.ID_OFFRE, idOffre)
                .getSingleResult();
    }
    public void insertRecruitment(RecrutementEntity recrutementEntity) {
        em.getTransaction().begin();
        em.persist(recrutementEntity);
        em.getTransaction().commit();
    }
    public void updateRecruitment(RecrutementEntity recrutementEntity) {
        em.getTransaction().begin();
        em.merge(recrutementEntity);
        em.getTransaction().commit();
    }
    public void deleteRecruitment(int idOffre) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM RecrutementEntity r WHERE  r.idOffre = :idOffre");
        query.setParameter(Constantes.ID_OFFRE, idOffre);
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
