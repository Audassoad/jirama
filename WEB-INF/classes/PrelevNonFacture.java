package jirama;

import java.sql.Date;

public class PrelevNonFacture{
    int idPrelev;
    Date datePrelev;
    String moisPrelev;
    String anneePrelev;
    int client;

    public PrelevNonFacture(){}

    public int getIdPrelev() {
        return this.idPrelev;
    }

    public void setIdPrelev(int idPrelev) {
        this.idPrelev = idPrelev;
    }

    public Date getDatePrelev() {
        return this.datePrelev;
    }

    public void setDatePrelev(Date datePrelev) {
        this.datePrelev = datePrelev;
    }

    public String getMoisPrelev() {
        return this.moisPrelev;
    }

    public void setMoisPrelev(String moisPrelev) {
        this.moisPrelev = moisPrelev;
    }

    public String getAnneePrelev() {
        return this.anneePrelev;
    }

    public void setAnneePrelev(String anneePrelev) {
        this.anneePrelev = anneePrelev;
    }

    public int getClient() {
        return this.client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public PrelevNonFacture(int idPrelev, Date datePrelev, String moisPrelev, String anneePrelev, int client) {
        this.idPrelev = idPrelev;
        this.datePrelev = datePrelev;
        this.moisPrelev = moisPrelev;
        this.anneePrelev = anneePrelev;
        this.client = client;
    }

}