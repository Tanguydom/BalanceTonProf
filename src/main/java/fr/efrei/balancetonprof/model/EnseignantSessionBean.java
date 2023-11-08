package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class EnseignantSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public void updateProfessor(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.merge(enseignant);
        em.getTransaction().commit();
    }
    public void deleteProfessorByIdUtilisateur(int idEns){
        em.getTransaction().begin();
        Query q = em.createQuery("DELETE FROM EnseignantEntity e WHERE e.idUtilisateur = :idEnseignant");
        q.setParameter(Constantes.ID_ENSEIGNANT, idEns);
        q.executeUpdate();
        em.getTransaction().commit();
    }
    public void insertProfessor(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.persist(enseignant);
        em.getTransaction().commit();
    }
    public EnseignantEntity getProfessorById(int idEns){
        return em.find(EnseignantEntity.class, idEns);
    }
    public EnseignantEntity getProfessorByIdUtilisateur(int idUti) {
        return em.createQuery("SELECT e FROM EnseignantEntity e WHERE e.idUtilisateur = :idUtilisateur", EnseignantEntity.class)
                .setParameter(Constantes.ID_UTILISATEUR, idUti)
                .getSingleResult();
    }

}

