package jirama;

public class Client{
    int idClient;
    String nom;
    String prenom;
    String adresse;

    public Client() {}

    public Client(int idClient, String nom, String prenom, String adresse) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public int getIdClient() {
        return this.idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}