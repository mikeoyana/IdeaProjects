public class Profesor extends Persona{
    private String asignatura;
    private boolean esmajo;
    private static int nProfesores = 0;

    public Profesor(String nombre, int edad, String asignatura) {
        super(nombre, edad);
        this.asignatura = asignatura;
        this.esmajo = false;
        this.nProfesores ++;

    }
    public void setMajo(boolean esmajo) {
        this.esmajo = esmajo;
    }

   /* @Override
    public String toString() {
        return nombre+" {" +
                "asignatura='" + asignatura + '\'' +
                ", esmajo=" + esmajo +
                ", edad=" + edad +
                '}';
    }*/

    @Override
    public String toString(){
            return getNombre() + " imparte " + asignatura + " y " + (esmajo? "" : " no ") + "es majo";
            //return super.toString() + "imparte " + asignatura + " y" +(esmajo? "" : " no ") + " es majo";
        }

    @Override
    public String toStringF(){
        return "Profe "+this.getNombre()+" "+ this.getEdad()+" "+ this.asignatura+" "+(esmajo? " si " : " no ");
    }

    public static int getnProfesores(){
        return nProfesores;
    }
    public boolean getMajo(){
        return this.esmajo;
    }

    public void setAsignatura(String esmajo){
        this.asignatura = esmajo;
    }
}

