package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class EnseignantSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public void changementEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.merge(enseignant);
        em.getTransaction().commit();
    }
    public void insertionEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.persist(enseignant);
        em.getTransaction().commit();
    }

    public EnseignantEntity chercheEnseignantParId(int idEns){
        return em.find(EnseignantEntity.class, idEns);
    }
    public EnseignantEntity chercheEnseignantParIdUtilisateur(int idUti) {
        return em.createQuery("SELECT e FROM EnseignantEntity e WHERE e.idUtilisateur = :idUti", EnseignantEntity.class)
                .setParameter(Constantes.ID_UTI, idUti)
                .getSingleResult();
    }

}

