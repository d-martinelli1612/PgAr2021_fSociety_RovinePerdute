package it.unibs.pa.rovinePerdute;



import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;


public class Main {
    public static void main(String args[]) throws XMLStreamException {

        ArrayList<Citta> listaCitta = InputXML.leggiInputCitta();
        Algoritmo al = new Algoritmo(listaCitta);






        // Stampa lista citta
        for (int i=0; i<listaCitta.size(); i++){
            listaCitta.get(i).stampaCitta();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        al.matriceDistanze();
        System.out.println();
        // trova altitudine
        al.trovaAltitudine();
        // trova la distanza
        System.out.println();
        al.trovaDistanza();

    }
}