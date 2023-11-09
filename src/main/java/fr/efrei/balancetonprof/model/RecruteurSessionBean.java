package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class RecruteurSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public void insertRecruiter(RecruteurEntity recruteur) {
        em.getTransaction().begin();
        em.persist(recruteur);
        em.getTransaction().commit();
    }
    public RecruteurEntity getRecruiterByIdUtilisateur(int idUti) {
        return em.createQuery("SELECT e FROM RecruteurEntity e WHERE e.idUtilisateur = :idUtilisateur", RecruteurEntity.class)
                .setParameter(Constantes.ID_UTILISATEUR, idUti)
                .getSingleResult();
    }
    public Integer checkIfExist(int idRec, int idEcole){
        Query q = em.createQuery("SELECT COUNT(c.idRecruteur) FROM RecruteurEntity c WHERE c.idEcole = :idEcole AND c.idUtilisateur = :idRecruteur");
        q.setParameter(Constantes.ID_RECRUCTEUR, idRec);
        q.setParameter(Constantes.ID_ECOLE, idEcole);
        Long checkIfExist ;
        try {
            checkIfExist = (Long) q.getSingleResult();
        } catch (Exception e) {
            checkIfExist = null;
        }
        return checkIfExist.intValue();
    }
    public void updateRecruiter(RecruteurEntity recruteur) {
        em.getTransaction().begin();
        em.merge(recruteur);
        em.getTransaction().commit();
    }
    public void deleteRecruiteurByIdRecruteur(int idRec){
        Query q = em.createQuery("DELETE FROM RecruteurEntity e WHERE e.idUtilisateur = :idRecruteur");
        em.getTransaction().begin();
        q.setParameter(Constantes.ID_RECRUCTEUR, idRec);
        q.executeUpdate();
        em.getTransaction().commit();
    }
}

