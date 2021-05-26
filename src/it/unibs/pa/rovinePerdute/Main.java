package it.unibs.pa.rovinePerdute;



import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;


public class Main {
    public static void main(String args[]) throws XMLStreamException {

        ArrayList<Citta> listaCitta = InputXML.leggiInputCitta();
        Mappa mappaSorgente = new Mappa(listaCitta);
        Mappa mappaAltitudine = new Mappa(listaCitta);
        Mappa mappaCartesiana = new Mappa(listaCitta);

        //Stampa lista citta
        /*for (int i=0; i<listaCitta.size(); i++){
            listaCitta.get(i).stampaCitta();
        }*/

        mappaSorgente.matriceDistanze();

        //Trova percorso altitudine
        mappaAltitudine.percorsoAltitudine(mappaSorgente);
        //STAMPA
        /*for (int i=0; i<mappaAltitudine.listaCitta.size(); i++){
            for (int j=0; j<mappaAltitudine.listaCitta.size(); j++){
                System.out.print(String.format("|%5d|", mappaAltitudine.matricePercorsi[i][j]));
            }
            System.out.println();
        }*/

        //System.out.println();

        //Trova percorso distanza planare
        mappaCartesiana.percorsoPlanare(mappaSorgente);
        //STAMPA
        /*for (int i=0; i<mappaCartesiana.listaCitta.size(); i++){
            for (int j=0; j<mappaCartesiana.listaCitta.size(); j++){
                System.out.print(String.format("|%5d|", mappaCartesiana.matricePercorsi[i][j]));
            }
            System.out.println();
        }*/
    }
}