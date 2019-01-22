package except;

public class PrelevementException extends Exception{
    String erreur;

    public PrelevementException(){}

    public PrelevementException(String erreur){
        this.erreur = erreur;
    }

    public String getErreur(){
        return this.erreur;
    }

    public void setErreur(String erreur){
        this.erreur = erreur;
    }

}