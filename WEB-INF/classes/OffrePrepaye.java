package jirama;

public class OffrePrepaye{
    String idOffre;
    double offreConsom;
    double majoration;
    int duree;
    String typeOffre;
    double prix;

    public OffrePrepaye(){}

    public OffrePrepaye(String idOffre, double offreConsom, double majoration, int duree, String typeOffre, double prix) {
        this.idOffre = idOffre;
        this.offreConsom = offreConsom;
        this.majoration = majoration;
        this.duree = duree;
        this.typeOffre = typeOffre;
        this.prix = prix;
    }

    public String getIdOffre() {
        return this.idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public double getOffreConsom() {
        return this.offreConsom;
    }

    public void setOffreConsom(double offreConsom) {
        this.offreConsom = offreConsom;
    }

    public double getMajoration() {
        return this.majoration;
    }

    public void setMajoration(double majoration) {
        this.majoration = majoration;
    }

    public int getDuree() {
        return this.duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getTypeOffre() {
        return this.typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public double getPrix(){
        return this.prix;
    }

    public void setPrix(double prix){
        this.prix = prix;
    }
}