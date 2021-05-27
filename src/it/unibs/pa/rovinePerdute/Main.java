package it.unibs.pa.rovinePerdute;



import javax.xml.stream.XMLStreamException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String args[]) throws XMLStreamException {

        ArrayList<Citta >listaCitta = InputXML.leggiInputCitta();
        Mappa mappaSorgente = new Mappa(listaCitta);
        Mappa mappaAltitudine = new Mappa(listaCitta);
        Mappa mappaCartesiana = new Mappa(listaCitta);
        for ( int i = 0 ; i < mappaCartesiana.listaCitta.size(); i++){
            if( mappaSorgente.listaCitta.get(i).
        }


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

        mappaCartesiana.percorsoCorto(listaCitta.get(0));

        for ( int i =0 ; i < listaCitta.get(1).getCittaCollegate().size(); i++){
            System.out.println(listaCitta.get(1).getCittaCollegate().get(i).getCittaArrivo());
        }

        /*ArrayList<Citta> percorso = new ArrayList<Citta>(mappaCartesiana.getShortest(listaCitta.get(listaCitta.size())));*//*
        System.out.print("Percorso piu corto: ");

        System.out.print( mappaCartesiana.getShortest(listaCitta.get(listaCitta.size())));
        System.out.println(mappaCartesiana.listaCitta.size());

        for ( int j = 0 ; j < mappaCartesiana.listaCitta.size(); j++){

            System.out.println(mappaCartesiana.listaCitta.get(j).getNome());

        }*/


        System.out.println(mappaCartesiana.getShortest(listaCitta.get(listaCitta.size()-1)));
    }
}