import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        String[] V={};
        ArrayList<Partido> liga = new ArrayList<Partido>();
        Map<String,Integer>golesAFavor= new HashMap<String,Integer>();
        File f = new File("partidos2013-2014.txt");
        try {
            Scanner myReader = new Scanner(f).useDelimiter(",|\n");
            myReader.nextLine(); //no coge la linea
            while (myReader.hasNext()) {
                int njornada = myReader.nextInt();
                String fecha = myReader.next();
                String eqlocal = myReader.next();
                int gl = myReader.nextInt();
                int gv = myReader.nextInt();
                String eqvisitante = myReader.next();
                liga.add(new Partido(njornada, fecha, eqlocal, gl, gv, eqvisitante));


            }
           /* for (Partido partido:liga) {
                System.out.println(partido);
            }*/
            //partido con mas goles
            System.out.println(partidoMasGoles(liga));
            //partido con mas diferencia de goles
            System.out.println(partidoConMasDiferencia(liga));
            //Mas goles en un Empate
            System.out.println(mayorEmpate(liga));
            //mas goles local
            System.out.println(maxLocal(liga));
            //mas goles visitante
            System.out.println(maxVisitante(liga));
            //for(jornada=1;joe<=38;jor++){ mostrar quiniela(liga,jornada)}
            //mostrar numero de 1,2,x
            System.out.println(quiniela(liga));
        } catch (FileNotFoundException e) {
            System.out.println("FICHERO NO ENCONTRADO " + e);
        }
    }


    public static String maxVisitante(ArrayList<Partido> liga){
        int pos=0;
        int mayorVisi=(liga.get(0).getGv());

        for (int i = 1; i < liga.size(); i++) {
            int Actual=(liga.get(i).getGv());
            if (mayorVisi<Actual){
                mayorVisi=Actual;
                pos=i;
            }
        }
        return mayorVisi+" goles locales en el  "+ liga.get(pos).toString();
    }
    public static String maxLocal(ArrayList<Partido> liga){
        int pos=0;
        int mayorLocal=(liga.get(0).getGl());

        for (int i = 1; i < liga.size(); i++) {
            int Actual=(liga.get(i).getGl());
            if (mayorLocal<Actual){
                mayorLocal=Actual;
                pos=i;
            }
        }
        return mayorLocal+" goles locales en el  "+ liga.get(pos).toString();
    }

    public static String mayorEmpate(ArrayList<Partido> liga){
        int pos=0;
        int maxEmpate=0;

        for (int i = 0; i < liga.size(); i++) {
            if((liga.get(i).getGl()) == (liga.get(i).getGv())) {
                int eqA = (liga.get(i).getGl()) + (liga.get(i).getGv());
                if (maxEmpate < eqA) {
                    maxEmpate = eqA;
                    pos = i;
                }
            }
        }
        return maxEmpate+" goles en el "+ liga.get(pos).toString();
    }
    public static String partidoConMasDiferencia(ArrayList<Partido> liga){
        int pos=0;
        int mayorDiferencia=(liga.get(0).getGl())-(liga.get(0).getGv());
        for (int i = 1; i < liga.size(); i++) {
            int mayorActual=(liga.get(i).getGl())-(liga.get(i).getGv());

            if (mayorDiferencia <mayorActual) {
                mayorDiferencia=mayorActual;
                pos=i;
            }
        }
        return mayorDiferencia+" goles de diferencia en el "+ liga.get(pos).toString();
    }

    public static String quiniela(ArrayList<Partido> liga){
        String res="";
        int unos=0;
        int doses=0;
        int x=0;

        for (int i = 0; i < liga.size(); i++) {

            int equipoLocal=liga.get(i).getGl();
            int equipoVisitante=liga.get(i).getGv();

            if (equipoLocal>equipoVisitante) {
                res= "ganó el local "+liga.get(i).toString();
                unos++;
            } else if (equipoLocal<equipoVisitante) {
                res="ganó el local "+liga.get(i).toString();
                doses++;
            }else {
                res="empate entre "+liga.get(i).toString();
                x++;
            }
            //System.out.println(res);


        }
        return unos+" victorias locales, "+doses+" victorias visitantes y "+x+"  empates";
    }

    public static String partidoMasGoles(ArrayList<Partido> liga){
        int pos=0;
        int masGoles=(liga.get(0).getGl())+(liga.get(0).getGv());
        for (int i = 1; i < liga.size(); i++) {

            if (masGoles >(liga.get(i).getGl())+(liga.get(i).getGv())) {
                masGoles=masGoles;
            } else if (masGoles <(liga.get(i).getGl())+(liga.get(i).getGv())) {
                masGoles=(liga.get(i).getGl())+(liga.get(i).getGv());
                pos=i;
            }else {
                masGoles=masGoles;
                pos=i;
            }
        }
        return masGoles+" goles en el "+ liga.get(pos);
    }
}

/*
//Map<Partido,String>temporada23_24=new HashMap<Partido,String>();
        //ArrayList<Map<Partido,String>> ligas=new ArrayList<Map<Partido,String>>();


/*vector de ficheros
String []= nf {  }
"p13-14.csv",..."p23-24.csv"

Diccionario
Map<String,Integer>puntos
Map<String,Integer>golesAFavor
Map<String,Integer>golesEnContra
 */