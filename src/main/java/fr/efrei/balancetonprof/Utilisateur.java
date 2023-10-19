package fr.efrei.balancetonprof;

public class Utilisateur {
    private int id_utilisateur;

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    private String loginSaisi;
    private String motDePasseSaisi;

    private String nom ;

    private String prenom;

    private String email;

    private String telephone;

    private String site;

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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    private int role;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLoginSaisi() {
        return loginSaisi;
    }

    public void setLoginSaisi(String loginSaisi) {
        this.loginSaisi = loginSaisi;
    }

    public String getMotDePasseSaisi() {
        return motDePasseSaisi;
    }

    public void setMotDePasseSaisi(String motDePasseSaisi) {
        this.motDePasseSaisi = motDePasseSaisi;
    }
}
