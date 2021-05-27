package it.unibs.pa.rovinePerdute;



import javax.xml.stream.XMLStreamException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String args[]) throws XMLStreamException {

        HashMap<Integer, Citta> listaCitta = InputXML.leggiInputCitta();
        Mappa mappaSorgente = new Mappa(listaCitta);
        Mappa mappaAltitudine = new Mappa(listaCitta);
        Mappa mappaCartesiana = new Mappa(listaCitta);

        //Stampa lista citta
        /*for (int i=0; i<listaCitta.size(); i++){
            listaCitta.get(i).stampaCitta();
        }*/

        //Crea la mappaSorgente, riportante i percorsi esistenti
        mappaSorgente.matriceDistanze();

        //Trova percorso altitudine
        mappaAltitudine.percorsoAltitudine(mappaSorgente);

        //Trova percorso distanza planare
        mappaCartesiana.percorsoPlanare(mappaSorgente);

        //STAMPA
        for (int i=0; i<mappaAltitudine.listaCitta.size(); i++){
            for (int j=0; j<mappaAltitudine.listaCitta.size(); j++){
                System.out.print(String.format("|%5d|", mappaAltitudine.matricePercorsi[i][j]));
            }
            System.out.println();
        }

        System.out.println();

        for (int i=0; i<mappaCartesiana.listaCitta.size(); i++){
            for (int j=0; j<mappaCartesiana.listaCitta.size(); j++){
                System.out.print(String.format("|%5d|", mappaCartesiana.matricePercorsi[i][j]));
            }
            System.out.println();
        }




        mappaCartesiana.percorsoCorto(mappaCartesiana.listaCitta.get(0));
        List<Citta> citta = mappaCartesiana.getShortest(listaCitta.get(listaCitta.size()-1));
        System.out.println("Percorso: ");
        System.out.println();
        System.out.println();
        int i =0;
        for( Citta cit : citta){
            System.out.println(cit.getNome() + i);
            i++;
        }

         mappaAltitudine.percorsoCorto(mappaAltitudine.listaCitta.get(0));
        System.out.println("\n\n");
        List<Citta> cit = mappaAltitudine.getShortest(listaCitta.get(listaCitta.size()-1));
        System.out.println("Percorso: altezza");
          i=0;
        for( Citta citta1 : citta){
            System.out.println(citta1.getNome() + " " +i);
            i++;
        }
    }
}





/*ArrayList<Citta> percorso = new ArrayList<Citta>(mappaCartesiana.getShortest(listaCitta.get(listaCitta.size())));*//*
        System.out.print("Percorso piu corto: ");

        System.out.print( mappaCartesiana.getShortest(listaCitta.get(listaCitta.size())));
        System.out.println(mappaCartesiana.listaCitta.size());

        for ( int j = 0 ; j < mappaCartesiana.listaCitta.size(); j++){

            System.out.println(mappaCartesiana.listaCitta.get(j).getNome());

        }*/