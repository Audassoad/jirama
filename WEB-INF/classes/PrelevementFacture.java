package jirama;

public class PrelevementFacture {
    int idFacture;
    int idClient;

    public PrelevementFacture() {
    }

    public PrelevementFacture(int idFacture, int idClient) {
        this.idFacture = idFacture;
        this.idClient = idClient;
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
    
}