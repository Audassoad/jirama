package jirama;

import java.sql.Date;

public class Facture{
    int idFacture;
    int idClient;
    Date dateFacture;
    double montant;
    String categorie;

   
    public Facture(){}

    public Facture(int idFacture, int idClient, Date dateFacture, double montant, String categorie) {
        this.idFacture = idFacture;
        this.idClient = idClient;
        this.dateFacture = dateFacture;
        this.montant = montant;
        this.categorie = categorie;
    }

    public int getIdFacture() {
        return this.idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdClient() {
        return this.idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDateFacture() {
        return this.dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
