package fr.efrei.balancetonprof.utils;

public final class Constantes {
    //Constante variable de contexte / session
    public static final String UTILISATEUR = "utilisateur";
    public static final String LIST_ADMIN = "listeAdministrateurs";
    public static final String LIST_ENS = "listeProfesseurs";
    public static final String LIST_REC = "listeRecruteurs";
    public static final String LIST_OFFRE = "listeOffres";
    public static final String LIST_OFFRE_CANDIDATER = "listOffresCandidater";
    public static final String ID_UTILISATEUR = "id";
    public static final String ENSEIGNANT = "enseignant";
    public static final String RECRUTEUR = "recruteur";
    public static final String ENTREPRISE = "entreprise";
    public static final String PSEUDO = "pseudo";
    public static final String MOT_DE_PASSE = "motDePasse";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String EMAIL = "email";
    public static final String TELEPHONE = "telephone";
    public static final String SITE = "site";
    public static final String EXPERIENCE = "experience";
    public static final String COMPETENCE = "competence";
    public static final String INTERET = "interet";
    public static final String EVALUATION = "evalution";
    public static final String NIVEAU_SOUHAITE = "niveauSouhaite";
    public static final String AUTRES_INFORMATIONS = "autresInformations";
    public static final String DISPONIBILITE = "disponibilite";
    public static final String CHAMP_LOGIN = "champLogin";
    public static final String CHAMP_MDP = "champMotDePasse";
    public static final String CHAMP_NOM = "champNom";
    public static final String CHAMP_PRENOM = "champPrenom";
    public static final String CHAMP_EMAIL = "champEmail";
    public static final String CHAMP_ROLE = "champRole";
    public static final String MSG_ERREUR = "messageErreur";

    public static final String ID_OFFRE = "offreId";

    //COE -> création offre emploi
    public static final String COE_INTITULE = "coe.intitule";
    public static final String COE_DESCRIPTION = "coe.description";

    //IO -> information offre
    public static final String IO_INTITULE = "io.intitule";
    public static final String IO_DESCRIPTION = "io.description";

    //Constante action jsp
    public static final String ACTION = "action";
    public static final String CANDIDATER = "candidater";
    public static final String SAUVEGARDE_UTILISATEUR = "sauvegardeUtilisateur";
    public static final String SAUVGARDE_DETAIL = "sauvegardeDetail";
    public static final String RETIRER_CANDIDATURE = "retirer_candidatire";
    public static final String AJOUT_ADMIN = "ajouter_admin";
    public static final String AJOUT_ENS= "ajouter_prof";
    public static final String AJOUT_REC= "ajouter_recruteur";
    public static final String SUP_ENS= "supprimer_utilisateur";
    public static final String AJOUT_OFFRE = "ajouter_offre";
    public static final String SUP_OFFRE = "supprimer_offre";
    public static final String MOD_OFFRE = "modifier_offre";
    public static final String INSCRIPTION = "Inscription";

    //Constante path jsp & serlvet
    public static final String PROFIL_PATH = "profil.jsp";
    public static final String ADMIN_PATH = "admin.jsp";
    public static final String INDEX_PATH = "index.jsp";
    public static final String INSCRIPTION_PATH = "inscription.jsp";
    public static final String PROFIL_SERVLET = "/profil-servlet";
    public static final String INSCRIPTION_SERVLET =  "/inscription-servlet";

    // ERREUR & EXCEPTION
    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Infos de connexion non valides. Merci de les saisir à nouveau";
    public static final String MESSAGE_ERREUR_CANDIDATURE_EXISTANTE = "Vous avez deja candidater pour cette offre";

}
