package it.unibs.pa.rovinePerdute;



import javax.xml.stream.XMLStreamException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String args[]) throws XMLStreamException {

        HashMap<Integer, Citta> listaCittaCartesiana = InputXML.leggiInputCitta();
        HashMap<Integer, Citta> listaCittaAltitudine = InputXML.leggiInputCitta();
        //HashMap<Integer, Citta> listaCittaAltitudine = new HashMap<>();
        Mappa mappaSorgente = new Mappa(listaCittaCartesiana);
        Mappa mappaAltitudine = new Mappa(listaCittaAltitudine);
        Mappa mappaCartesiana = new Mappa(listaCittaCartesiana);

        //Crea la mappaSorgente, riportante i percorsi esistenti
        mappaSorgente.matriceDistanze();

        //Trova percorso altitudine
        mappaAltitudine.percorsoAltitudine(mappaSorgente);

        //STAMPA
        /*System.out.println("Mappa altitudine");
        for (int i=0; i<mappaAltitudine.listaCitta.size(); i++){
            for (int j=0; j<mappaAltitudine.listaCitta.size(); j++){
                System.out.print(String.format("|%5d|", mappaAltitudine.matricePercorsi[i][j]));
            }
            System.out.println();
        }*/

        mappaAltitudine.percorsoCorto(mappaAltitudine.listaCitta.get(0));
        List<Citta> cit = mappaAltitudine.getShortest(listaCittaAltitudine.get(listaCittaAltitudine.size()-1));
        System.out.println("\n\nPercorso altezza:");
        for(int i=0; i < cit.size(); i++){
            System.out.println(cit.get(i).getNome() + " " + i);
        }


        //Trova percorso distanza planare
        mappaCartesiana.percorsoPlanare(mappaSorgente);

        /*System.out.println("\nMappa cartesiana");
        for (int i=0; i<mappaCartesiana.listaCitta.size(); i++){
            for (int j=0; j<mappaCartesiana.listaCitta.size(); j++){
                System.out.print(String.format("|%5d|", mappaCartesiana.matricePercorsi[i][j]));
            }
            System.out.println();
        }*/

        mappaCartesiana.percorsoCorto(mappaCartesiana.listaCitta.get(0));
        List<Citta> citta = mappaCartesiana.getShortest(listaCittaCartesiana.get(listaCittaCartesiana.size()-1));
        System.out.println("\n\nPercorso cartesiano:");
         int i =0;
        for ( Citta cittaCartesiana : citta){

            System.out.println(cittaCartesiana.getNome() + " " + i);
            i++;
        }

    }
}