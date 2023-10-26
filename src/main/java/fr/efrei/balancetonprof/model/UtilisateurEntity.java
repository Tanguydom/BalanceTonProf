package fr.efrei.balancetonprof.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Utilisateur", schema = "balancetonprofbd", catalog = "")
public class UtilisateurEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utilisateur", nullable = false)
    private int idUtilisateur;
    @Basic
    @Column(name = "pseudo", nullable = true, length = 255)
    private String pseudo;
    @Basic
    @Column(name = "mot_de_passe", nullable = true, length = 255)
    private String motDePasse;
    @Basic
    @Column(name = "role", nullable = true)
    private Integer role;
    @Basic
    @Column(name = "nom", nullable = true, length = 255)
    private String nom;
    @Basic
    @Column(name = "prenom", nullable = true, length = 255)
    private String prenom;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "telephone", nullable = true, length = 255)
    private String telephone;
    @Basic
    @Column(name = "site_web", nullable = true, length = 255)
    private String siteWeb;

    public UtilisateurEntity(int userId, String pseudo, String motDePasse, String nom, String prenom, int role, String email, String telephone, String site) {
        this.idUtilisateur = userId;
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.role = role;
        this.siteWeb = site;
        this.telephone = telephone;
        this.pseudo = pseudo;

    }

    public UtilisateurEntity() {

    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UtilisateurEntity that = (UtilisateurEntity) o;

        if (idUtilisateur != that.idUtilisateur) return false;
        if (pseudo != null ? !pseudo.equals(that.pseudo) : that.pseudo != null) return false;
        if (motDePasse != null ? !motDePasse.equals(that.motDePasse) : that.motDePasse != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (siteWeb != null ? !siteWeb.equals(that.siteWeb) : that.siteWeb != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUtilisateur;
        result = 31 * result + (pseudo != null ? pseudo.hashCode() : 0);
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (siteWeb != null ? siteWeb.hashCode() : 0);
        return result;
    }
}
