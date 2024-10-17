public class Pieza {
    private String nombre;
    private String color;  // "blanco" o "negro"
    private String movimiento;  

    // Constructor vacio
    public Pieza() {
    }

    // Constructor con parametros
    public Pieza(String nombre, String color, String movimiento) {
        this.nombre = nombre;
        this.color = color;
        this.movimiento = movimiento;  
    }

    // Metodos get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMovimiento() {
        return movimiento;  
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    // Imprimimos los atributos en pantalla
    @Override
    public String toString() {
        return "Pieza{" +
                "nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", movimiento='" + movimiento + '\'' +
                '}';
    }
}
