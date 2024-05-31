import java.util.Scanner;

class ValidacionDNIException extends Exception {
    public ValidacionDNIException(String message) {
        super(message);
    }
}

public class DNI {

    public static void validaDNI(String dni) throws ValidacionDNIException { //Comprobamos que tiene una Longitud de 9 caracteres
        if (dni.length() != 9) {
            throw new ValidacionDNIException("Longitud no válida");
        }

        //Comprobamos que el último carácter es una letra if (iCharacter.isLetter(dni.charAt())) {

        if (!Character.isLetter(dni.charAt(8))) {
            throw new ValidacionDNIException("El último caracter no es una letra");
        }
        //Comprobamos que el resto de caracteres son digitos
        for (int i=0;i < 8;i++){
            if (!Character.isDigit(dni.charAt(i))) {
                new ValidacionDNIException("La numeración contiene dígitos no válidos");
            }
        }
    }

    public static void main(String[] args) throws ValidacionDNIException {

        Scanner teclado = new Scanner(System.in);
        String dni;
        System.out.print("Introduce el DNI: ");
        dni = teclado.nextLine();
        teclado.close();
        validaDNI(dni);
        System.out.println("DNI Correcto");
    }
}