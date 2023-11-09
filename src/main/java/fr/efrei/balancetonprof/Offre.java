package fr.efrei.balancetonprof;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.util.List;

public class Offre {
    private int idOffre;
    private Integer idRecruteur;
    private String intitule;
    private Integer idEcole;
    private String description;
    private int nbCandidat ;
    private String nomEcole;
    private String emailRecruteur;
    private String telephoneRecruteur;
    private String siteWebEcole;
    private String adresseEcole;

    public Offre(){
    }

    public int getNbCandidat() {
        return nbCandidat;
    }

    public void setNbCandidat(int nbCandidat) {
        this.nbCandidat = nbCandidat;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public Integer getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(Integer idRecruteur) {
        this.idRecruteur = idRecruteur;
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
    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public String getEmailRecruteur() {
        return emailRecruteur;
    }

    public void setEmailRecruteur(String emailRecruteur) {
        this.emailRecruteur = emailRecruteur;
    }

    public String getTelephoneRecruteur() {
        return telephoneRecruteur;
    }

    public void setTelephoneRecruteur(String telephoneRecruteur) {
        this.telephoneRecruteur = telephoneRecruteur;
    }

    public String getSiteWebEcole() {
        return siteWebEcole;
    }

    public void setSiteWebEcole(String siteWebEcole) {
        this.siteWebEcole = siteWebEcole;
    }

    public String getAdresseEcole() {
        return adresseEcole;
    }

    public void setAdresseEcole(String adresseEcole) {
        this.adresseEcole = adresseEcole;
    }
}
