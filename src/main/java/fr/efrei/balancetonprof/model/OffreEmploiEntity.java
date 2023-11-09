package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "OffreEmploi", schema = "balancetonprofbd", catalog = "")
public class OffreEmploiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_offre", nullable = false)
    private int idOffre;
    @Basic
    @Column(name = "intitule", nullable = true, length = 255)
    private String intitule;
    @Basic
    @Column(name = "id_ecole", nullable = true)
    private Integer idEcole;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    public OffreEmploiEntity() {
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Integer getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(Integer idEcole) {
        this.idEcole = idEcole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OffreEmploiEntity that = (OffreEmploiEntity) o;

        if (idOffre != that.idOffre) return false;
        if (intitule != null ? !intitule.equals(that.intitule) : that.intitule != null) return false;
        if (idEcole != null ? !idEcole.equals(that.idEcole) : that.idEcole != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOffre;
        result = 31 * result + (intitule != null ? intitule.hashCode() : 0);
        result = 31 * result + (idEcole != null ? idEcole.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
