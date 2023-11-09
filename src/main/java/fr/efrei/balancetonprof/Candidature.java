package fr.efrei.balancetonprof;

public class Candidature {

    private int idCandidature;
    private int idOffre;
    private int idEcole;
    private String nomEcole;
    private String intitule;
    private String nomCandidat;
    private String prenomCandidat;
    private String experience;
    private String competence;
    private String interet;
    private String evaluation;
    private String niveauSouhaite;
    private int statut;

    public int getStatut() {
        return statut;
    }

    public int getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    private String autresInformations;

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getInteret() {
        return interet;
    }

    public void setInteret(String interet) {
        this.interet = interet;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getNiveauSouhaite() {
        return niveauSouhaite;
    }

    public void setNiveauSouhaite(String niveauSouhaite) {
        this.niveauSouhaite = niveauSouhaite;
    }

    public String getAutresInformations() {
        return autresInformations;
    }

    public void setAutresInformations(String autresInformations) {
        this.autresInformations = autresInformations;
    }

    public Integer getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Integer disponibilite) {
        this.disponibilite = disponibilite;
    }

    private Integer disponibilite;

    public Candidature() {

    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(int idEntreprise) {
        this.idEcole = idEcole;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getPrenomCandidat() {
        return prenomCandidat;
    }

    public void setPrenomCandidat(String prenomCandidat) {
        this.prenomCandidat = prenomCandidat;
    }
}
