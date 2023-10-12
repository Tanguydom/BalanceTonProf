package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Entreprise", schema = "balancetonprofbd", catalog = "")
public class EntrepriseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_entreprise", nullable = false)
    private int idEntreprise;
    @Basic
    @Column(name = "nom", nullable = true, length = 255)
    private String nom;
    @Basic
    @Column(name = "raison_sociale", nullable = true, length = 255)
    private String raisonSociale;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "telephone", nullable = true, length = 255)
    private String telephone;
    @Basic
    @Column(name = "site_web", nullable = true, length = 255)
    private String siteWeb;
    @Basic
    @Column(name = "adresse", nullable = true, length = 255)
    private String adresse;

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntrepriseEntity that = (EntrepriseEntity) o;

        if (idEntreprise != that.idEntreprise) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (raisonSociale != null ? !raisonSociale.equals(that.raisonSociale) : that.raisonSociale != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (siteWeb != null ? !siteWeb.equals(that.siteWeb) : that.siteWeb != null) return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEntreprise;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (raisonSociale != null ? raisonSociale.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (siteWeb != null ? siteWeb.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        return result;
    }
}
