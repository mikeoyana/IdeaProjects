import java.util.*;

public class Parser {

    private static Map<String, List<String>> estacionesPorLinea = new HashMap<>();
    private static List<List<String>> conexiones = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar datos de líneas y estaciones");
            System.out.println("2. Agregar conexión entre estaciones");
            System.out.println("3. Verificar existencia de estación");
            System.out.println("4. Listar todas las estaciones de una línea");
            System.out.println("5. Mostrar todas las líneas y estaciones");
            System.out.println("6. Eliminar una estación");
            System.out.println("7. Renombrar una estación");
            System.out.println("8. Listar todas las conexiones de una estación");
            System.out.println("9. Verificar conexión entre dos estaciones");
            System.out.println("10. Salir");

            String opcion = sc.nextLine();
            try {
                switch (opcion) {
                    case "1":
                        ingresarDatos(sc);
                        break;
                    case "2":
                        agregarConexion(sc);
                        break;
                    case "3":
                        verificarEstacion(sc);
                        break;
                    case "4":
                        listarEstaciones(sc);
                        break;
                    case "5":
                        mostrarDatos();
                        break;
                    case "6":
                        eliminarEstacion(sc);
                        break;
                    case "7":
                        renombrarEstacion(sc);
                        break;
                    case "8":
                        listarConexionesEstacion(sc);
                        break;
                    case "9":
                        verificarConexion(sc);
                        break;
                    case "10":
                        System.out.println("Saliendo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void ingresarDatos(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la línea:");
        String nombreLinea = sc.nextLine();
        if (estacionesPorLinea.containsKey(nombreLinea)) {
            throw new Exception("La línea ya existe.");
        }

        System.out.println("Ingrese los nombres de las estaciones de la línea separados por comas:");
        String[] estaciones = sc.nextLine().split(",");
        estacionesPorLinea.put(nombreLinea, new ArrayList<>(Arrays.asList(estaciones)));
        System.out.println("Datos ingresados correctamente.");
    }

    private static void agregarConexion(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la primera estación:");
        String estacion1 = sc.nextLine();
        System.out.println("Ingrese el nombre de la segunda estación:");
        String estacion2 = sc.nextLine();

        if (!existeEstacion(estacion1) || !existeEstacion(estacion2)) {
            throw new Exception("Una o ambas estaciones no existen.");
        }

        List<String> conexion = Arrays.asList(estacion1, estacion2);
        conexiones.add(conexion);
        System.out.println("Conexión agregada correctamente.");
    }

    private static void verificarEstacion(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la estación a verificar:");
        String estacion = sc.nextLine();

        if (existeEstacion(estacion)) {
            System.out.println("La estación " + estacion + " existe.");
        } else {
            System.out.println("La estación " + estacion + " no existe.");
        }
    }

    private static boolean existeEstacion(String estacion) {
        for (List<String> estaciones : estacionesPorLinea.values()) {
            if (estaciones.contains(estacion)) {
                return true;
            }
        }
        return false;
    }

    private static void listarEstaciones(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la línea:");
        String nombreLinea = sc.nextLine();

        List<String> estaciones = estacionesPorLinea.get(nombreLinea);
        if (estaciones == null) {
            throw new Exception("La línea no existe.");
        }

        System.out.println("Estaciones de la línea " + nombreLinea + ":");
        for (String estacion : estaciones) {
            System.out.println(estacion);
        }
    }

    private static void mostrarDatos() throws Exception {
        if (estacionesPorLinea.isEmpty()) {
            throw new Exception("No hay datos disponibles.");
        }

        System.out.println("Líneas y estaciones:");
        for (Map.Entry<String, List<String>> entry : estacionesPorLinea.entrySet()) {
            System.out.println("Línea " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Conexiones:");
        for (List<String> conexion : conexiones) {
            System.out.println(conexion.get(0) + " <--> " + conexion.get(1));
        }
    }

    private static void eliminarEstacion(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la línea:");
        String nombreLinea = sc.nextLine();
        System.out.println("Ingrese el nombre de la estación a eliminar:");
        String estacion = sc.nextLine();

        List<String> estaciones = estacionesPorLinea.get(nombreLinea);
        if (estaciones == null || !estaciones.remove(estacion)) {
            throw new Exception("Línea o estación no existe.");
        }

        // Eliminar conexiones de la estación
        conexiones.removeIf(conexion -> conexion.contains(estacion));
        System.out.println("Estación eliminada correctamente.");
    }

    private static void renombrarEstacion(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la línea:");
        String nombreLinea = sc.nextLine();
        System.out.println("Ingrese el nombre actual de la estación:");
        String nombreActual = sc.nextLine();
        System.out.println("Ingrese el nuevo nombre de la estación:");
        String nuevoNombre = sc.nextLine();

        List<String> estaciones = estacionesPorLinea.get(nombreLinea);
        if (estaciones == null || !estaciones.contains(nombreActual)) {
            throw new Exception("Línea o estación no existe.");
        }

        // Renombrar la estación
        estaciones.set(estaciones.indexOf(nombreActual), nuevoNombre);
        for (List<String> conexion : conexiones) {
            if (conexion.contains(nombreActual)) {
                int index = conexion.indexOf(nombreActual);
                conexion.set(index, nuevoNombre);
            }
        }
        System.out.println("Estación renombrada correctamente.");
    }

    private static void listarConexionesEstacion(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la estación:");
        String estacion = sc.nextLine();

        if (!existeEstacion(estacion)) {
            throw new Exception("La estación no existe.");
        }

        System.out.println("Conexiones de la estación " + estacion + ":");
        for (List<String> conexion : conexiones) {
            if (conexion.contains(estacion)) {
                System.out.println(conexion.get(0) + " <--> " + conexion.get(1));
            }
        }
    }

    private static void verificarConexion(Scanner sc) throws Exception {
        System.out.println("Ingrese el nombre de la primera estación:");
        String estacion1 = sc.nextLine();
        System.out.println("Ingrese el nombre de la segunda estación:");
        String estacion2 = sc.nextLine();

        if (!existeEstacion(estacion1) || !existeEstacion(estacion2)) {
            throw new Exception("Una o ambas estaciones no existen.");
        }

        boolean conexionExiste = false;
        for (List<String> conexion : conexiones) {
            if (conexion.contains(estacion1) && conexion.contains(estacion2)) {
                conexionExiste = true;
                break;
            }
        }

        if (conexionExiste) {
            System.out.println("Existe una conexión directa entre " + estacion1 + " y " + estacion2);
        } else {
            System.out.println("No existe una conexión directa entre " + estacion1 + " y " + estacion2);
        }
    }
}