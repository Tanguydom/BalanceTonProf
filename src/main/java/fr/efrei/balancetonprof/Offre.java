package fr.efrei.balancetonprof;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import java.util.List;

public class Offre {
    private int idOffre;
    private Integer idRecruteur;
    private String intitule;
    private Integer idEntreprise;
    private String description;
    private int nbCandidat ;
    private String nomEntreprise;
    private String emailRecruteur;
    private String telephoneRecruteur;
    private String siteWebEntreprise;
    private String adresseEntreprise;

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

    public Integer getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
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

    public String getSiteWebEntreprise() {
        return siteWebEntreprise;
    }

    public void setSiteWebEntreprise(String siteWebEntreprise) {
        this.siteWebEntreprise = siteWebEntreprise;
    }

    public String getAdresseEntreprise() {
        return adresseEntreprise;
    }

    public void setAdresseEntreprise(String adresseEntreprise) {
        this.adresseEntreprise = adresseEntreprise;
    }
}
