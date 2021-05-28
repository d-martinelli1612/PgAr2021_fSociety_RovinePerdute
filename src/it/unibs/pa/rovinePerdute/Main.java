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
        //HashMap<Integer, Citta> listaCittaAltitudine = Citta.clonaLista(listaCittaCartesiana);
        Mappa mappaSorgente = new Mappa(listaCittaCartesiana);
        Mappa mappaAltitudine = new Mappa(listaCittaAltitudine);
        Mappa mappaCartesiana = new Mappa(listaCittaCartesiana);

        //Crea la mappaSorgente, riportante i percorsi esistenti
        mappaSorgente.matriceDistanze();

        //Trova percorso altitudine
        mappaAltitudine.percorsoAltitudine(mappaSorgente);
        mappaAltitudine.percorsoCorto(mappaAltitudine.listaCitta.get(0));

        //Trova percorso distanza planare
        mappaCartesiana.percorsoPlanare(mappaSorgente);
        mappaCartesiana.percorsoCorto(mappaCartesiana.listaCitta.get(0));

        List<Citta> percorsoTonathiu = mappaCartesiana.getShortest(listaCittaCartesiana.get(listaCittaCartesiana.size()-1));
        List<Citta> percorsoMetztli = mappaAltitudine.getShortest(listaCittaAltitudine.get(listaCittaAltitudine.size()-1));

        Output.stampaXML(percorsoTonathiu, percorsoMetztli);
        //Rimuovere alla fine dei test
        /*List<Citta> cit = mappaAltitudine.getShortest(listaCittaAltitudine.get(listaCittaAltitudine.size()-1));
        System.out.println("\n\nPercorso altezza:");
        for(int i=0; i < cit.size(); i++){
            System.out.println(cit.get(i).getNome() + " " + i);
        }

        //Rimuovere alla fine dei test
        List<Citta> citta = mappaCartesiana.getShortest(listaCittaCartesiana.get(listaCittaCartesiana.size()-1));
        System.out.println("\n\nPercorso cartesiano:");
         int i =0;
        for ( Citta cittaCartesiana : citta){

            System.out.println(cittaCartesiana.getNome() + " " + i);
            i++;
        }*/

    }
}