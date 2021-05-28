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

        //Crea le liste delle citta dei percorsi
        List<Citta> percorsoTonathiu = mappaCartesiana.getShortest(listaCittaCartesiana.get(listaCittaCartesiana.size()-1));
        List<Citta> percorsoMetztli = mappaAltitudine.getShortest(listaCittaAltitudine.get(listaCittaAltitudine.size()-1));

        Output.stampaXML(percorsoTonathiu, percorsoMetztli);
    }
}