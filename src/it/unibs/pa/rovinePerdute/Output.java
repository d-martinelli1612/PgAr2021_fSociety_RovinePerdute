package it.unibs.pa.rovinePerdute;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Output {
    public static void stampaXML(List<Citta> percorsoTonathiu, List<Citta> percorsoMetztli){
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        String consumo, numeroCitta, id, nome;

        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Routes.xml"), "utf-8");
            ((XMLStreamWriter) xmlw).writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }

        try { // blocco try per raccogliere eccezioni

            //Apertura output
            xmlw.writeStartElement("routes");

            //Apre elemento primo percorso
            xmlw.writeStartElement("route");

            //Stampa Team e attributi
            xmlw.writeAttribute("team", "Tonathiu");
            consumo = String.valueOf(percorsoTonathiu.get(percorsoTonathiu.size() - 1).getDist());
            xmlw.writeAttribute("cost", consumo);
            numeroCitta = String.valueOf(percorsoTonathiu.size());
            xmlw.writeAttribute("cities", numeroCitta);

            for (int i=0; i < percorsoTonathiu.size(); i++){
                xmlw.writeStartElement("city");
                id = String.valueOf(percorsoTonathiu.get(i).getId());
                xmlw.writeAttribute("id", id);
                nome = percorsoTonathiu.get(i).getNome();
                xmlw.writeAttribute("name", nome);
                xmlw.writeEndElement();
            }

            xmlw.writeEndElement();
            xmlw.writeEndElement();
        }
        catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println("Errore nella scrittura");
            e.printStackTrace();

        }
    }
}
