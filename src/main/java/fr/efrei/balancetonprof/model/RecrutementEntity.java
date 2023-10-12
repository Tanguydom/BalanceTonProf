package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Recrutement", schema = "balancetonprofbd", catalog = "")
public class RecrutementEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_recrutement", nullable = false)
    private int idRecrutement;
    @Basic
    @Column(name = "id_recruteur", nullable = true)
    private Integer idRecruteur;
    @Basic
    @Column(name = "id_offre", nullable = true)
    private Integer idOffre;

    public int getIdRecrutement() {
        return idRecrutement;
    }

    public void setIdRecrutement(int idRecrutement) {
        this.idRecrutement = idRecrutement;
    }

    public Integer getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(Integer idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public Integer getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Integer idOffre) {
        this.idOffre = idOffre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecrutementEntity that = (RecrutementEntity) o;

        if (idRecrutement != that.idRecrutement) return false;
        if (idRecruteur != null ? !idRecruteur.equals(that.idRecruteur) : that.idRecruteur != null) return false;
        if (idOffre != null ? !idOffre.equals(that.idOffre) : that.idOffre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecrutement;
        result = 31 * result + (idRecruteur != null ? idRecruteur.hashCode() : 0);
        result = 31 * result + (idOffre != null ? idOffre.hashCode() : 0);
        return result;
    }
}
