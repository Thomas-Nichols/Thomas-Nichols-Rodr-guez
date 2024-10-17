class Tablero {
    private String[][] casillas;

    // Colores ANSI para la impresión
    private static final String RESET = "\u001B[0m";
    private static final String MARRON = "\u001B[43m";  // Color de fondo marrón
    private static final String BLANCO = "\u001B[47m";  // Color de fondo blanco
    private static final String NEGRO_TEXTO = "\u001B[30m";  // Texto en negro
    private static final String BLANCO_TEXTO = "\u001B[37m";  // Texto en blanco

    // Constructor vacío
    public Tablero() {
        this.casillas = new String[8][8];  // Tablero de 8x8
        // Inicializamos todas las casillas como vacías
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = "vacío"; // Puede ser cualquier identificador
            }
        }
    }

    // Método para colocar algo en una posición específica
    public void colocarElemento(String elemento, int fila, int columna) {
        casillas[fila][columna] = elemento;
    }

    // Método para imprimir todas las casillas con un color específico
    public void imprimirElementosPorTipo(String color) {
        System.out.println("Elementos del color " + color + ":");
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j] != null && casillas[i][j].contains(color)) {
                    // Imprimir los detalles del elemento
                    System.out.println("Elemento: " + casillas[i][j] +
                                       " | Posición: (" + i + ", " + j + ")");
                }
            }
        }
    }

    // Método para imprimir un tablero vacío con colores alternados (marrón y blanco)
    public void imprimirTableroVacio() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Usamos colores alternados para simular las casillas de ajedrez
                if ((i + j) % 2 == 0) {
                    System.out.print(BLANCO + "   " + RESET);
                } else {
                    System.out.print(MARRON + "   " + RESET);
                }
            }
            System.out.println();  // Salto de línea después de cada fila
        }
    }
}