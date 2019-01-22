package jirama;

public class Tarif {
    int numTarif;
    String nom;
    String categorie;
    double quantite;
    double prixU;

    public Tarif() {
    }

    public Tarif(int numTarif, String nom, String categorie, double quantite, double prixU) {
        this.numTarif = numTarif;
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prixU = prixU;
    }

    public int getNumTarif() {
        return this.numTarif;
    }

    public void setNumTarif(int numTarif) {
        this.numTarif = numTarif;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getQuantite() {
        return this.quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrixU() {
        return this.prixU;
    }

    public void setPrixU(double prixU) {
        this.prixU = prixU;
    }

}