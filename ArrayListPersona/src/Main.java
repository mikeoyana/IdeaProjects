import java.util.*;
import java.io.*;

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
        System.out.println("H.  Leer personas de fichero");//ya
        System.out.println("Z. Salir");
        c = sc.next().toUpperCase().charAt(0);
        return c;
    }

    public static void GuardarAFichero(ArrayList<Persona>personas){
        try {
            FileWriter myWriter = new FileWriter("personas.txt");
            for (int i = 0; i < personas.size(); i++) {
                myWriter.write(personas.get(i).toStringF()+ "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void LeerDeFichero(ArrayList<Persona>personas){
        try {
            File myObj = new File("personas.txt");
            Scanner myReader = new Scanner(myObj)/*.useDelimiter(",|\n")*/;
            int nlinea=0;
            while (myReader.hasNext()) {
                nlinea++;
                String tipo = myReader.next();
                String nombre= myReader.next();
                int edad= myReader.nextInt();

                if(tipo.equals("Profe")){
                    String asignatura = myReader.next();
                    String esmajo = myReader.next();
                    Profesor p=new Profesor(nombre,edad,asignatura);
                    p.setMajo(esmajo.equals("si"));
                    personas.add(p);
                } else if (tipo.equals("Alumno")) {
                    String modulo = myReader.next();
                    String repetidor = myReader.next();
                    int numMaterias = myReader.nextInt();
                    Alumno a=new Alumno(nombre,edad,numMaterias,modulo);
                    a.setRepetidor(repetidor.equals("si"));
                    personas.add(a);

                }else {
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
       /* personas.add (new Profesor("Vicente",50,"Programación"));
        personas.add (new Alumno("Pepe",20,6,"DAW"));
        personas.add ( new Alumno("Pepa",28,7,"DAM"));
        personas.add ( new Alumno("Pepito",21,5,"ASIR"));*/

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
                default:
                    System.out.println("Opcion incorrecta");
            }

        } while (opcion != 'Z');
    }


    public static boolean EliminarPersonas(ArrayList<Persona>personas, Scanner sc){
        int i;
        boolean res;
        do {
            System.out.println("Dime la posicion");
             i= sc.nextInt();
        }while (i<0 || i>= personas.size());
        return res=personas.remove(personas.get(i));




    }
    public static boolean AñadirAlumno(ArrayList<Persona>personas, Scanner sc){
        String nombre, modulo;
        int edad, nummaterias;
        boolean nuevoAlumno;

        System.out.println("Dime el nombre: ");
        nombre=sc.next();
        System.out.println("Dime el edad: ");
        edad=sc.nextInt();
        System.out.println("Dime el materias: ");
        nummaterias=sc.nextInt();
        System.out.println("Dime el modulo: ");
        modulo=sc.next();
        return nuevoAlumno=personas.add(new Alumno(nombre,edad,nummaterias,modulo));

    }
    public static boolean AñadirProfesor(ArrayList<Persona>personas, Scanner sc){
        String nombre, asignatura;
        int edad;
        boolean nuevoProfesor;

        System.out.println("Dime el nombre: ");
        nombre=sc.next();
        System.out.println("Dime el edad: ");
        edad=sc.nextInt();
        System.out.println("Dime el modulo: ");
        asignatura=sc.next();
        return nuevoProfesor=personas.add(new Profesor(nombre,edad,asignatura));

    }
    public static void MostrarPersonas(ArrayList<Persona>personas){
       /* for (int i = 0; i < personas.size(); i++) {
            System.out.printlna(personas.get(i));
        }*/
        System.out.println(personas);
        System.out.println("size: "+personas.size());
        System.out.println("isEmpty: "+personas.isEmpty());

    }
}