package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Recruteur", schema = "balancetonprofbd", catalog = "")
public class RecruteurEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_recruteur", nullable = false)
    private int idRecruteur;
    @Basic
    @Column(name = "id_entreprise", nullable = true)
    private Integer idEntreprise;
    @Basic
    @Column(name = "id_utilisateur", nullable = true)
    private Integer idUtilisateur;

    public RecruteurEntity(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public RecruteurEntity() {

    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public Integer getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecruteurEntity that = (RecruteurEntity) o;

        if (idRecruteur != that.idRecruteur) return false;
        if (idEntreprise != null ? !idEntreprise.equals(that.idEntreprise) : that.idEntreprise != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecruteur;
        result = 31 * result + (idEntreprise != null ? idEntreprise.hashCode() : 0);
        return result;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
