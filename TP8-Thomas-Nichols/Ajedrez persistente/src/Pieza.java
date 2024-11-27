public class Pieza {
    private String color;
    private String tipo;

    
    public Pieza(String color, String tipo) {
        this.color = color;
        this.tipo = tipo;
    }

    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    public void imprimir() {
        System.out.println("Pieza: " + tipo + " Color: " + color);
    }
}
