package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Candidature", schema = "balancetonprofbd", catalog = "")
public class CandidatureEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_candidature", nullable = false)
    private int idCandidature;
    @Basic
    @Column(name = "id_enseignant", nullable = true)
    private Integer idEnseignant;
    @Basic
    @Column(name = "id_offre", nullable = true)
    private Integer idOffre;
    @Basic
    @Column(name = "statut", nullable = true, length = 255)
    private int statut;

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Integer getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Integer idOffre) {
        this.idOffre = idOffre;
    }
}
