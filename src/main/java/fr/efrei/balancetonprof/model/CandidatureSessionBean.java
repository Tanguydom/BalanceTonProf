package fr.efrei.balancetonprof.model;

import fr.efrei.balancetonprof.utils.Constantes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
@Stateless
public class CandidatureSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public Integer nombreCandidaturesParIdOffre(int idOffre) {
        Query q = em.createQuery("SELECT COUNT(c.idProf) FROM CandidatureEntity c WHERE c.idOffre = :idOffre");
        q.setParameter(Constantes.ID_OFFRE, idOffre);
        Integer nbCandidat = 0;
        try {
            nbCandidat = (Integer) q.getSingleResult();
        } catch (Exception e) {
        }
        return nbCandidat;
    }
    public void insertionCandidature(CandidatureEntity candidatureEntity) {
        em.getTransaction().begin();
        em.persist(candidatureEntity);
        em.getTransaction().commit();
    }
    public Integer chercheCandidatureParIdOffre_IdEns(int idOffre, int idEns) {
        Query q = em.createQuery("SELECT COUNT(c.idCandidature) FROM CandidatureEntity c WHERE c.idOffre = :idOffre AND c.idProf = :idEns");
        q.setParameter(Constantes.ID_OFFRE, idOffre);
        q.setParameter(Constantes.ID_ENS, idEns);
        Integer idCandidature ;
        try {
            idCandidature = (Integer) q.getSingleResult();
        } catch (Exception e) {
            idCandidature = null;
        }
        return idCandidature;
    }
    public void changementStatut(int idCandidature, int statut){
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE CandidatureEntity c SET c.statut = :statut WHERE c.idCandidature = :idCandidature");
        q.setParameter(Constantes.ID_CANDIDATURE, idCandidature);
        q.setParameter(Constantes.STATUT, statut);
        q.executeUpdate();
        em.getTransaction().commit();
    }
    public List<CandidatureEntity> chercheCandidaturesParRecruteurId(int idRec) {
        Query q = em.createQuery("SELECT c FROM OffreEmploiEntity e, RecrutementEntity r, CandidatureEntity c " +
                "WHERE e.idOffre = r.idOffre AND r.idRecruteur = :idRec AND c.idOffre = e.idOffre AND c.statut = 0");
        q.setParameter(Constantes.ID_REC, idRec);
        return q.getResultList();
    }
    public List<CandidatureEntity> chercheCandidaturesParEnseignantId(int idEns) {
        Query q = em.createQuery("SELECT c FROM OffreEmploiEntity e, RecrutementEntity r, CandidatureEntity c " +
                "WHERE e.idOffre = r.idOffre AND c.idProf = :idEns AND c.idOffre = e.idOffre");
        q.setParameter(Constantes.ID_ENS, idEns);
        return q.getResultList();
    }
    public void suppressionCandidature(int idCandidature) {
        em.getTransaction().begin();
        CandidatureEntity candidature = em.find(CandidatureEntity.class, idCandidature);
        em.remove(candidature);
        em.getTransaction().commit();
    }
}
