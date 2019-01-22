package except;

public class DateException extends Exception{
    String erreur;

    public DateException(){}

    public DateException(String erreur){
        this.erreur = erreur;
    }

    public String getErreur(){
        return this.erreur;
    }

    public void setErreur(String erreur){
        this.erreur = erreur;
    }

}