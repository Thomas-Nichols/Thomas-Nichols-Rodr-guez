package src;

public class Principito {
    private String actitud;
    private String personalidad;
    private Flor flor; // Flor que pertenece al principito

    // Getters
    public String getActitud() {
        return actitud;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public Flor getFlor() {
        return flor;
    }

    // Setters
    public void setActitud(String newActitud) {
        this.actitud = newActitud;
    }

    public void setPersonalidad(String newPersonalidad) {
        this.personalidad = newPersonalidad;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
    }

    // Constructor vacío
    public Principito() {
    }

    // Constructor con parámetros
    public Principito(String act, String perso, Flor flor) {
        setActitud(act);
        setPersonalidad(perso);
        setFlor(flor);
    }

    // Método para imprimir los atributos de la flor del principito
    public void imprimirFlorPrincipito() {
        System.out.println("La flor del principito tiene las siguientes características:");
        System.out.println("Actitud: " + flor.getActitud());
        System.out.println("Apariencia: " + flor.getApariencia());
    }
}
