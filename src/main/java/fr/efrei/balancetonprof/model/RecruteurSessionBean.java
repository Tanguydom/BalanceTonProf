package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

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

    public Integer checkIfExist(int idRec, int idEntreprise){
        Query q = em.createQuery("SELECT COUNT(c.idRecruteur) FROM RecruteurEntity c WHERE c.idEntreprise = :idEntreprise AND c.idUtilisateur = :idRec");
        q.setParameter(Constantes.ID_REC, idRec);
        q.setParameter(Constantes.ID_ENTREPRISE, idEntreprise);
        Long checkIfExist ;
        try {
            checkIfExist = (Long) q.getSingleResult();
        } catch (Exception e) {
            checkIfExist = null;
        }
        return checkIfExist.intValue();
    }
    public void changementRecruteur(RecruteurEntity recruteur) {
        em.getTransaction().begin();
        em.merge(recruteur);
        em.getTransaction().commit();
    }
}

