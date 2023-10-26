package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Enseignant", schema = "balancetonprofbd", catalog = "")
public class EnseignantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_enseignant", nullable = false)
    private int idEnseignant;
    @Basic
    @Column(name = "experience", nullable = true, length = 255)
    private String experience;
    @Basic
    @Column(name = "competence", nullable = true, length = 255)
    private String competence;
    @Basic
    @Column(name = "interet", nullable = true, length = 255)
    private String interet;
    @Basic
    @Column(name = "evaluation", nullable = true, length = 255)
    private String evaluation;
    @Basic
    @Column(name = "niveau_souhaite", nullable = true, length = 255)
    private String niveauSouhaite;
    @Basic
    @Column(name = "autres_informations", nullable = true, length = 255)
    private String autresInformations;
    @Basic
    @Column(name = "disponibilite", nullable = true)
    private Integer disponibilite;
    @Basic
    @Column(name = "id_utilisateur", nullable = true)
    private Integer idUtilisateur;

    public EnseignantEntity(int userId, String experience, String competence, String interet, String evaluation, String niveauSouhaite, String autresInformations, String disponibilite) {
        this.idUtilisateur = userId;
        this.experience = experience;
        this.competence = competence;
        this.interet = interet;
        this.evaluation = evaluation;
        this.niveauSouhaite = niveauSouhaite;
        this.autresInformations = autresInformations;
        this.disponibilite = Integer.valueOf(disponibilite);
    }
    public EnseignantEntity(){

    }

    public EnseignantEntity(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getInteret() {
        return interet;
    }

    public void setInteret(String interet) {
        this.interet = interet;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getNiveauSouhaite() {
        return niveauSouhaite;
    }

    public void setNiveauSouhaite(String niveauSouhaite) {
        this.niveauSouhaite = niveauSouhaite;
    }

    public String getAutresInformations() {
        return autresInformations;
    }

    public void setAutresInformations(String autresInformations) {
        this.autresInformations = autresInformations;
    }

    public Integer getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Integer disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnseignantEntity that = (EnseignantEntity) o;

        if (idEnseignant != that.idEnseignant) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;
        if (competence != null ? !competence.equals(that.competence) : that.competence != null) return false;
        if (interet != null ? !interet.equals(that.interet) : that.interet != null) return false;
        if (evaluation != null ? !evaluation.equals(that.evaluation) : that.evaluation != null) return false;
        if (niveauSouhaite != null ? !niveauSouhaite.equals(that.niveauSouhaite) : that.niveauSouhaite != null)
            return false;
        if (autresInformations != null ? !autresInformations.equals(that.autresInformations) : that.autresInformations != null)
            return false;
        if (disponibilite != null ? !disponibilite.equals(that.disponibilite) : that.disponibilite != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEnseignant;
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (competence != null ? competence.hashCode() : 0);
        result = 31 * result + (interet != null ? interet.hashCode() : 0);
        result = 31 * result + (evaluation != null ? evaluation.hashCode() : 0);
        result = 31 * result + (niveauSouhaite != null ? niveauSouhaite.hashCode() : 0);
        result = 31 * result + (autresInformations != null ? autresInformations.hashCode() : 0);
        result = 31 * result + (disponibilite != null ? disponibilite.hashCode() : 0);
        return result;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
