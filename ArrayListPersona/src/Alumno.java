public class Alumno extends Persona {
    private String modulo;
    private int numMaterias;
    private boolean repetidor;
    private static int nAlumnos = 0;

    public Alumno(String n, int e, int nm, String m) {
        super(n, e); // construye una persona
        this.modulo = m;
        this.numMaterias = nm;
        this.repetidor = false;
        nAlumnos++;
    }

    @Override
    public String toString() {
        return this.getNombre() + " tiene " + this.getEdad() + " y estudia " + this.modulo+ " y "+ (this.repetidor?"":" no ")+ " "+this.numMaterias+" materias";
    }
    @Override
    public String toStringF() {
        return "Alumno "+this.getNombre() + " " + getEdad()+" "+this.modulo+" "+ (this.repetidor?"":" no ")+ " "+this.numMaterias;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }


    public static int getnAlumnos() {
        return nAlumnos;
    }

    public String getModulo() {
        return this.modulo;
    }

    public void setModulo(String m) {
        this.modulo = m;
    }

    public void setnMaterias(int nm) {
        this.numMaterias = nm;
    }

    public boolean getRepetidor() {
        return this.repetidor;
    }



}