package jirama;

import java.sql.Date;

public class FactureAvoir {
    String idFactureAvoir;
    int idFacture;
    double montantAvoir;
    Date dateFactureAvoir;

    public FactureAvoir() {
    }

    public FactureAvoir(String idFactureAvoir, int idFacture, double montantAvoir, Date dateFactureAvoir) {
        this.idFactureAvoir = idFactureAvoir;
        this.idFacture = idFacture;
        this.montantAvoir = montantAvoir;
        this.dateFactureAvoir = dateFactureAvoir;
    }

    public String getIdFactureAvoir() {
        return this.idFactureAvoir;
    }

    public void setIdFactureAvoir(String idFactureAvoir) {
        this.idFactureAvoir = idFactureAvoir;
    }

    public int getIdFacture() {
        return this.idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public double getMontantAvoir() {
        return this.montantAvoir;
    }

    public void setMontantAvoir(double montantAvoir) {
        this.montantAvoir = montantAvoir;
    }

    public Date getDateFactureAvoir() {
        return this.dateFactureAvoir;
    }

    public void setDateFactureAvoir(Date dateFactureAvoir) {
        this.dateFactureAvoir = dateFactureAvoir;
    }

}