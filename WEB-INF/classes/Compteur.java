package jirama;

public class Compteur{
    String numCompteur;
    String categorie;
    String tarif;
    int idClient;

    public Compteur() {
    }

    public Compteur(String numCompteur, String categorie, String tarif, int idClient) {
        this.numCompteur = numCompteur;
        this.categorie = categorie;
        this.tarif = tarif;
        this.idClient = idClient;
    }

    public String getNumCompteur() {
        return this.numCompteur;
    }

    public void setNumCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTarif() {
        return this.tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public int getIdClient() {
        return this.idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }



}