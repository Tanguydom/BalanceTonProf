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
    @Column(name = "id_prof", nullable = true)
    private Integer idProf;
    @Basic
    @Column(name = "id_offre", nullable = true)
    private Integer idOffre;
    @Basic
    @Column(name = "cv", nullable = true, length = 255)
    private String cv;
    @Basic
    @Column(name = "lettre_motivation", nullable = true, length = 255)
    private String lettreMotivation;

    public int getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public Integer getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Integer idOffre) {
        this.idOffre = idOffre;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidatureEntity that = (CandidatureEntity) o;

        if (idCandidature != that.idCandidature) return false;
        if (idProf != null ? !idProf.equals(that.idProf) : that.idProf != null) return false;
        if (idOffre != null ? !idOffre.equals(that.idOffre) : that.idOffre != null) return false;
        if (cv != null ? !cv.equals(that.cv) : that.cv != null) return false;
        if (lettreMotivation != null ? !lettreMotivation.equals(that.lettreMotivation) : that.lettreMotivation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCandidature;
        result = 31 * result + (idProf != null ? idProf.hashCode() : 0);
        result = 31 * result + (idOffre != null ? idOffre.hashCode() : 0);
        result = 31 * result + (cv != null ? cv.hashCode() : 0);
        result = 31 * result + (lettreMotivation != null ? lettreMotivation.hashCode() : 0);
        return result;
    }
}
