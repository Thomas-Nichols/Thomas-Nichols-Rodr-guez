import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import java.time.LocalDateTime;  
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mostrarBienvenida();  
            mostrarPiezasBD();    
            mostrarMenuConsola(); 
        });
    }

    public static void mostrarBienvenida() {
        JFrame bienvenidaFrame = new JFrame("Bienvenidos");
        bienvenidaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bienvenidaFrame.setSize(400, 200);
        bienvenidaFrame.setLocationRelativeTo(null); // Centrar ventana

        JLabel label = new JLabel("¡Bienvenidos a la Gestión de Piezas de Ajedrez!", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        label.setForeground(new Color(50, 50, 150));
        bienvenidaFrame.add(label);

        bienvenidaFrame.setVisible(true);

        
        Timer timer = new Timer(3000, e -> bienvenidaFrame.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    public static void mostrarPiezasBD() {
        String query = "SELECT IDPIEZA, TP.DESCRIPCIONDEPIEZA AS TIPOPIEZA, P.DESCRIPCION AS DESCRIPCION_PIEZA, " +
                       "C.DESCRIPCION AS COLOR, M.DESCRIPCION AS MATERIAL, P.POSICION, P.FECHA_CREACION " +  
                       "FROM PIEZA AS P " +
                       "INNER JOIN COLOR AS C ON C.IDCOLOR = P.IDCOLOR " +
                       "INNER JOIN TAMANIO AS T ON T.IDTAMANIO = P.IDTAMANIO " +
                       "INNER JOIN MATERIAL AS M ON M.IDMATERIAL = P.IDMATERIAL " +
                       "INNER JOIN TIPOPIEZA AS TP ON TP.IDTIPOPIEZA = P.IDTIPOPIEZA " +
                       "ORDER BY IDPIEZA DESC LIMIT 32";

        try (Connection con = new AccesoDatos().getConexion();
             Statement sentencia = con.createStatement();
             ResultSet rs = sentencia.executeQuery(query)) {

            Vector<String> columnNames = new Vector<>();
            columnNames.add("Pieza Nro");
            columnNames.add("Tipo de Pieza");
            columnNames.add("Descripción Pieza");
            columnNames.add("Color");
            columnNames.add("Material");
            columnNames.add("Posición");
            columnNames.add("Fecha de Creación");

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("IDPIEZA"));
                row.add(rs.getString("TIPOPIEZA"));
                row.add(rs.getString("DESCRIPCION_PIEZA"));
                row.add(rs.getString("COLOR"));
                row.add(rs.getString("MATERIAL"));
                row.add(rs.getString("POSICION"));
                row.add(rs.getString("FECHA_CREACION"));
                data.add(row);
            }

            JFrame frame = new JFrame("Listado de Piezas");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.setLocationRelativeTo(null); // Centrar la ventana

            JTable table = new JTable(data, columnNames);
            table.setFillsViewportHeight(true);
            table.setFont(new Font("SansSerif", Font.PLAIN, 14));
            table.setRowHeight(25);

            // Configurar colores y alinear celdas
            table.getTableHeader().setBackground(new Color(50, 50, 150));
            table.getTableHeader().setForeground(Color.WHITE);
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setVisible(true);

        } catch (SQLException e) {
            System.err.println("Error al cargar datos.");
            e.printStackTrace();
        }
    }

    public static void mostrarMenuConsola() {
        int opcion;
        try (Scanner entrada = new Scanner(System.in)) {
            do {
                System.out.println("Elija una opción:");
                System.out.println("1) Mostrar las piezas (actualizar tabla)");
                System.out.println("2) Insertar piezas");
                System.out.println("0) Finalizar");

                opcion = entrada.nextInt();

                switch (opcion) {
                    case 0:
                        System.out.println("Programa finalizado");
                        break;
                    case 1:
                        mostrarPiezasBD();  
                        break;
                    case 2:
                        instanciarPiezasTablero();
                        break;
                    default:
                        System.out.println("Opción inválida, por favor intente nuevamente.");
                        break;
                }
            } while (opcion != 0);
        }
    }

    public static void insertarPieza(Pieza pieza, int color, int tipoPieza) {
        String query = "INSERT INTO pieza (idtipopieza, Descripcion, idColor, idtamanio, idmaterial, fecha_creacion) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = new AccesoDatos().getConexion();
             PreparedStatement insercion = con.prepareStatement(query)) {

            insercion.setInt(1, tipoPieza);
            insercion.setString(2, pieza.getTipo());
            insercion.setInt(3, color);
            insercion.setInt(4, 2);  
            insercion.setInt(5, 2);  
            insercion.setTimestamp(6, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            insercion.execute();

        } catch (SQLException e) {
            System.err.println("Error al insertar datos.");
            e.printStackTrace();
        }
    }

    public static void instanciarPiezasTablero() {
        int tipoDePieza = 1;
        int color = 1;

        do {
            switch (tipoDePieza) {
                case 1: instanciarYGuardarPieza(new Dama(color == 1 ? "Blanco" : "Negro"), color, tipoDePieza); break;
                case 2: instanciarYGuardarPieza(new Rey(color == 1 ? "Blanco" : "Negro"), color, tipoDePieza); break;
                case 3: instanciarPiezasMultiples(Torre.class, color, tipoDePieza, 2); break;
                case 4: instanciarPiezasMultiples(Alfil.class, color, tipoDePieza, 2); break;
                case 5: instanciarPiezasMultiples(Caballo.class, color, tipoDePieza, 2); break;
                case 6: instanciarPiezasMultiples(Peon.class, color, tipoDePieza, 8); break;
            }
            tipoDePieza++;
            if (tipoDePieza > 6 && color == 1) {
                tipoDePieza = 1;
                color++;
            }
        } while (tipoDePieza != 7);
    }

    private static void instanciarYGuardarPieza(Pieza pieza, int color, int tipoDePieza) {
        insertarPieza(pieza, color, tipoDePieza);
    }

    private static <T extends Pieza> void instanciarPiezasMultiples(Class<T> tipoClase, int color, int tipoDePieza, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            try {
                Pieza pieza = tipoClase.getDeclaredConstructor(String.class)
                                       .newInstance(color == 1 ? "Blanco" : "Negro");
                insertarPieza(pieza, color, tipoDePieza);
            } catch (Exception e) {
                System.err.println("Error al instanciar pieza: " + tipoClase.getSimpleName());
                e.printStackTrace();
            }
        }
    }
}