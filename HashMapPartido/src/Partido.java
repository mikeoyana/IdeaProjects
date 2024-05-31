public class Partido {
    private int njornada;
    private String fecha;
    private String eqlocal;
    private int gl;
    private int gv;
    private String eqvisitante;

    public Partido(int njornada, String fecha, String eqlocal, int gl, int gv, String eqvisitante) {
        this.njornada = njornada;
        this.fecha = fecha;
        this.eqlocal = eqlocal;
        this.gl = gl;
        this.gv = gv;
        this.eqvisitante = eqvisitante;
    }

    public String toStringQuiniela() {
       String res;
        if(this.gl>this.gv){
            res="1";
        } else if (this.gl<this.gv) {
            res="2";
        }else{
            res="X";
        }
        return res;
    }
    public String toString() {
        return  this.eqlocal+" "+this.gl+"-"+this.gv+" "+this.eqvisitante;
    }

    public int getNjornada() {
        return njornada;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEqlocal() {
        return eqlocal;
    }

    public int getGl() {
        return gl;
    }

    public int getGv() {
        return gv;
    }

    public String getEqvisitante() {
        return eqvisitante;
    }
}
