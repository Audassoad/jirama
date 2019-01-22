package jirama;

import java.sql.Date;

public class PrelevAnnule{
    int idPrelevAnnule; 
    int numPrelev;
    Date dateAnnulation;


    public PrelevAnnule(){}

    public PrelevAnnule(int idPrelevAnnule, int numPrelev, Date dateAnnulation){
        setIdNumPrelevAnnule(idPrelevAnnule);
        setNumPrelev(numPrelev);
        setDateAnnulation(dateAnnulation);
    }

    public int getIdNumPrelevAnnule(){
        return this.idPrelevAnnule;
    }

    public void setIdNumPrelevAnnule(int idPrelevAnnule){
        this.idPrelevAnnule = idPrelevAnnule;
    }

    public int getNumPrelev(){
        return this.numPrelev;
    }

    public void setNumPrelev(int numPrelev){
        this.numPrelev = numPrelev;
    }

    public Date getDateAnnulation(){
        return this.dateAnnulation;
    }

    public void setDateAnnulation(Date dateAnnulation){
        this.dateAnnulation = dateAnnulation;
    }
}