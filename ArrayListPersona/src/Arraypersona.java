import java.util.*;
import java.io.*;

abstract class Persona implements Comparable<Persona> {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public abstract String toStringF();

    @Override
    public int compareTo(Persona otra) {
        return this.nombre.compareTo(otra.getNombre());
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }
}

class Alumno extends Persona {
    private int numMaterias;
    private String modulo;
    private boolean repetidor;

    public Alumno(String nombre, int edad, int numMaterias, String modulo) {
        super(nombre, edad);
        this.numMaterias = numMaterias;
        this.modulo = modulo;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }

    @Override
    public String toStringF() {
        return "Alumno " + getNombre() + " " + getEdad() + " " + modulo + " " + (repetidor ? "si" : "no") + " " + numMaterias;
    }

    @Override
    public String toString() {
        return super.toString() + ", Modulo: " + modulo + ", Repetidor: " + (repetidor ? "si" : "no") + ", NumMaterias: " + numMaterias;
    }
}

class Profesor extends Persona {
    private String asignatura;
    private boolean majo;

    public Profesor(String nombre, int edad, String asignatura) {
        super(nombre, edad);
        this.asignatura = asignatura;
    }

    public void setMajo(boolean majo) {
        this.majo = majo;
    }

    @Override
    public String toStringF() {
        return "Profe " + getNombre() + " " + getEdad() + " " + asignatura + " " + (majo ? "si" : "no");
    }

    @Override
    public String toString() {
        return super.toString() + ", Asignatura: " + asignatura + ", Majo: " + (majo ? "si" : "no");
    }
}

public class Main {
    private static char menu(Scanner sc) {
        char c;
        System.out.println("A. Añadir alumno");
        System.out.println("B. Añadir profesor");
        System.out.println("C. Mostrar personas");
        System.out.println("D. Ordenar personas");
        System.out.println("E. Eliminar personas");
        System.out.println("F. Vaciar personas");
        System.out.println("G. Guardar personas a fichero");
        System.out.println("H. Leer personas de fichero");
        System.out.println("Z. Salir");
        c = sc.next().toUpperCase().charAt(0);
        return c;
    }

    public static void GuardarAFichero(ArrayList<Persona> personas) {
        try {
            FileWriter myWriter = new FileWriter("personas.txt");
            for (int i = 0; i < personas.size(); i++) {
                myWriter.write(personas.get(i).toStringF() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void LeerDeFichero(ArrayList<Persona> personas) {
        try {
            File myObj = new File("personas.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] parts = line.split(" ");
                String tipo = parts[0];
                String nombre = parts[1];
                int edad = Integer.parseInt(parts[2]);

                if (tipo.equals("Profe")) {
                    String asignatura = parts[3];
                    String esMajo = parts[4];
                    Profesor p = new Profesor(nombre, edad, asignatura);
                    p.setMajo(esMajo.equals("si"));
                    personas.add(p);
                } else if (tipo.equals("Alumno")) {
                    String modulo = parts[3];
                    String repetidor = parts[4];
                    int numMaterias = Integer.parseInt(parts[5]);
                    Alumno a = new Alumno(nombre, edad, numMaterias, modulo);
                    a.setRepetidor(repetidor.equals("si"));
                    personas.add(a);
                } else {
                    System.err.println("Persona no reconocida");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Persona> personas = new ArrayList<Persona>();
        char opcion;

        do {
            opcion = menu(sc);
            switch (opcion) {
                case 'A':
                    AñadirAlumno(personas, sc);
                    break;
                case 'B':
                    AñadirProfesor(personas, sc);
                    break;
                case 'C':
                    MostrarPersonas(personas);
                    break;
                case 'D':
                    Collections.sort(personas);
                    System.out.println(personas);
                    break;
                case 'E':
                    EliminarPersonas(personas, sc);
                    break;
                case 'F':
                    personas.clear();
                    break;
                case 'G':
                    GuardarAFichero(personas);
                    break;
                case 'H':
                    LeerDeFichero(personas);
                    break;
                case 'Z':
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 'Z');
    }

    public static boolean EliminarPersonas(ArrayList<Persona> personas, Scanner sc) {
        int i;
        boolean res;
        do {
            System.out.println("Dime la posición");
            i = sc.nextInt();
        } while (i < 0 || i >= personas.size());
        return res = personas.remove(personas.get(i));
    }

    public static boolean AñadirAlumno(ArrayList<Persona> personas, Scanner sc) {
        String nombre, modulo;
        int edad, numMaterias;
        boolean nuevoAlumno;

        System.out.println("Dime el nombre: ");
        nombre = sc.next();
        System.out.println("Dime la edad: ");
        edad = sc.nextInt();
        System.out.println("Dime el número de materias: ");
        numMaterias = sc.nextInt();
        System.out.println("Dime el módulo: ");
        modulo = sc.next();
        return nuevoAlumno = personas.add(new Alumno(nombre, edad, numMaterias, modulo));
    }

    public static boolean AñadirProfesor(ArrayList<Persona> personas, Scanner sc) {
        String nombre, asignatura;
        int edad;
        boolean nuevoProfesor;

        System.out.println("Dime el nombre: ");
        nombre = sc.next();
        System.out.println("Dime la edad: ");
        edad = sc.nextInt();
        System.out.println("Dime la asignatura: ");
        asignatura = sc.next();
        return nuevoProfesor = personas.add(new Profesor(nombre, edad, asignatura));
    }

    public static void MostrarPersonas(ArrayList<Persona> personas) {
        for (Persona persona : personas) {
            System.out.println(persona);
        }
        System.out.println("Size: " + personas.size());
        System.out.println("isEmpty: " + personas.isEmpty());
    }
}