package jirama;

import java.sql.Date;

public class Prelevement{
    int numPrelev;
    Date datePrelev;
    String mois;
    String annee;
    double valeur;
    String numCompteur;

    public Prelevement() {
    }

    public Prelevement(int numPrelev, Date datePrelev, String mois, String annee, double valeur, String numCompteur) {
        this.numPrelev = numPrelev;
        this.datePrelev = datePrelev;
        this.mois = mois;
        this.annee = annee;
        this.valeur = valeur;
        this.numCompteur = numCompteur;
    }

    public int getNumPrelev() {
        return this.numPrelev;
    }

    public void setNumPrelev(int numPrelev) {
        this.numPrelev = numPrelev;
    }

    public Date getDatePrelev() {
        return this.datePrelev;
    }

    public void setDatePrelev(Date datePrelev) {
        this.datePrelev = datePrelev;
    }

    public String getMois() {
        return this.mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return this.annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public double getValeur() {
        return this.valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String getNumCompteur() {
        return this.numCompteur;
    }

    public void setNumCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
    }

}