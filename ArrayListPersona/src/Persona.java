public abstract class Persona  implements Comparable<Persona>{
    int edad;
    String nombre;
    public abstract String toString();
    public abstract String toStringF();

    public Persona(String n, int e) {
        this.nombre = n;
        this.edad = e;
    }
    public void cumpleAños(){
        this.edad++;
        System.out.println("Fiesta de "+this.nombre);
    }

    @Override
    public int compareTo(Persona o) {
        //return this.getNombre().compareTo(o.getNombre());
        return this.getEdad()-(o.getEdad());
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String n){
        this.nombre=n;
    }

    public int getEdad(){
        return this.edad;
    }
}
