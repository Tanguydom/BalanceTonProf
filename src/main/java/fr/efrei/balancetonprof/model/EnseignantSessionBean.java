package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class EnseignantSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public EnseignantEntity getEnseignantById(int id) {
        return em.find(EnseignantEntity.class, id);
    }

    public void updateEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE EnseignantEntity e SET e.experience = :experience, e.competence = :competence, e.interet = :interet, e.evaluation = :evaluation, e.niveauSouhaite = :niveauSouhaite, e.autresInformations = :autresInformations, e.disponibilite = :disponibilite WHERE e.idUtilisateur = :id")
                .setParameter("experience", enseignant.getExperience())
                .setParameter("competence", enseignant.getCompetence())
                .setParameter("interet", enseignant.getInteret())
                .setParameter("evaluation", enseignant.getEvaluation())
                .setParameter("niveauSouhaite", enseignant.getNiveauSouhaite())
                .setParameter("autresInformations", enseignant.getAutresInformations())
                .setParameter("disponibilite", enseignant.getDisponibilite())
                .setParameter("id", enseignant.getIdUtilisateur());

        query.executeUpdate();

        em.getTransaction().commit();
    }

    public void insererEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.persist(enseignant);
        em.getTransaction().commit();
    }

    public EnseignantEntity findEnseignantByIdUtilisateur(int id_utilisateur) {
        return em.createQuery("SELECT e FROM EnseignantEntity e WHERE e.idUtilisateur = :id_utilisateur", EnseignantEntity.class)
                .setParameter("id_utilisateur", id_utilisateur)
                .getSingleResult();
    }

}

