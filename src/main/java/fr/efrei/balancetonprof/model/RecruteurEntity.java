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
    @Column(name = "id_ecole", nullable = true)
    private Integer idEcole;
    @Basic
    @Column(name = "id_utilisateur", nullable = true)
    private Integer idUtilisateur;
    public RecruteurEntity() {

    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public Integer getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(Integer idEcole) {
        this.idEcole = idEcole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecruteurEntity that = (RecruteurEntity) o;

        if (idRecruteur != that.idRecruteur) return false;
        if (idEcole != null ? !idEcole.equals(that.idEcole) : that.idEcole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecruteur;
        result = 31 * result + (idEcole != null ? idEcole.hashCode() : 0);
        return result;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
