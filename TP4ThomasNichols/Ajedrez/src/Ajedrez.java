public class Ajedrez {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();

        // Colocación de piezas blancas usando representaciones en texto
        tablero.colocarElemento("Torre blanco", 0, 0);
        tablero.colocarElemento("Caballo blanco", 0, 1);
        tablero.colocarElemento("Alfil blanco", 0, 2);
        tablero.colocarElemento("Reina blanco", 0, 3);
        tablero.colocarElemento("Rey blanco", 0, 4);
        tablero.colocarElemento("Alfil blanco", 0, 5);
        tablero.colocarElemento("Caballo blanco", 0, 6);
        tablero.colocarElemento("Torre blanco", 0, 7);
        for (int i = 0; i < 8; i++) {
            tablero.colocarElemento("Peón blanco", 1, i);
        }

        // Colocación de piezas negras usando representaciones en texto
        tablero.colocarElemento("Torre negro", 7, 0);
        tablero.colocarElemento("Caballo negro", 7, 1);
        tablero.colocarElemento("Alfil negro", 7, 2);
        tablero.colocarElemento("Reina negro", 7, 3);
        tablero.colocarElemento("Rey negro", 7, 4);
        tablero.colocarElemento("Alfil negro", 7, 5);
        tablero.colocarElemento("Caballo negro", 7, 6);
        tablero.colocarElemento("Torre negro", 7, 7);
        for (int i = 0; i < 8; i++) {
            tablero.colocarElemento("Peón negro", 6, i);
        }

        // Imprimir todas las piezas blancas
        System.out.println("\nPiezas blancas en el tablero:");
        tablero.imprimirElementosPorTipo("blanco");

        // Imprimir todas las piezas negras
        System.out.println("\nPiezas negras en el tablero:");
        tablero.imprimirElementosPorTipo("negro");

        // Imprimir un tablero de ajedrez vacío con colores marrón y blanco
        System.out.println("\nTablero de ajedrez vacío:");
        tablero.imprimirTableroVacio();  // Aquí es donde se invoca el método
    }
}