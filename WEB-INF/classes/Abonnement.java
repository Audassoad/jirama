package jirama;

import java.sql.Date;

public class Abonnement{
    String idAbonnement;
    String numCompteur;
    String idOffre;
    double quantite;
    Date dateDebut;
    Date dateFin;

    public Abonnement(){}

    public Abonnement(String idAbonnement, String numCompteur, String idOffre, double quantite, Date dateDebut, Date dateFin) {
        this.idAbonnement = idAbonnement;
        this.numCompteur = numCompteur;
        this.idOffre = idOffre;
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getIdAbonnement() {
        return this.idAbonnement;
    }

    public void setIdAbonnement(String idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public String getNumCompteur() {
        return this.numCompteur;
    }

    public void setNumCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
    }

    public String getIdOffre() {
        return this.idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public double getQuantite() {
        return this.quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


}