package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Entretien", schema = "balancetonprofbd", catalog = "")
public class EntretienEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_entretien", nullable = false)
    private int idEntretien;
    @Basic
    @Column(name = "salle", nullable = true, length = 255)
    private String salle;
    @Basic
    @Column(name = "id_recruteur", nullable = true)
    private Integer idRecruteur;
    @Basic
    @Column(name = "id_prof", nullable = true)
    private Integer idProf;
    @Basic
    @Column(name = "date", nullable = true)
    private Date date;

    public int getIdEntretien() {
        return idEntretien;
    }

    public void setIdEntretien(int idEntretien) {
        this.idEntretien = idEntretien;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Integer getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(Integer idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntretienEntity that = (EntretienEntity) o;

        if (idEntretien != that.idEntretien) return false;
        if (salle != null ? !salle.equals(that.salle) : that.salle != null) return false;
        if (idRecruteur != null ? !idRecruteur.equals(that.idRecruteur) : that.idRecruteur != null) return false;
        if (idProf != null ? !idProf.equals(that.idProf) : that.idProf != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEntretien;
        result = 31 * result + (salle != null ? salle.hashCode() : 0);
        result = 31 * result + (idRecruteur != null ? idRecruteur.hashCode() : 0);
        result = 31 * result + (idProf != null ? idProf.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
