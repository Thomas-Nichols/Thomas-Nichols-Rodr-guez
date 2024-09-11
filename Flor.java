package src;
public class Flor {
    private String actitud;
    private String apariencia;

    //Getters
    public String getActitud () {
        return actitud;
    }
    public String getApariencia () {
        return apariencia;
    }

    //Setters
    public void setActitud (String newActitud) {
        this.actitud = newActitud;
    }
    public void setApariencia (String newApariencia) {
        this.apariencia = newApariencia;
    }


    
    public Flor () {}

    public Flor (String act, String apar) {
        setActitud(act);
        setApariencia(apar);
    }
    
}
