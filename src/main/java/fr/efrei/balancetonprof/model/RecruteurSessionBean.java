package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Stateless
public class RecruteurSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public void insertionRecruteur(RecruteurEntity recruteur) {
        em.getTransaction().begin();
        em.persist(recruteur);
        em.getTransaction().commit();
    }
    public RecruteurEntity chercheRecruteurParIdUtilisateur(int idUti) {
        return em.createQuery("SELECT e FROM RecruteurEntity e WHERE e.idUtilisateur = :idUti", RecruteurEntity.class)
                .setParameter(Constantes.ID_UTI, idUti)
                .getSingleResult();
    }
}

