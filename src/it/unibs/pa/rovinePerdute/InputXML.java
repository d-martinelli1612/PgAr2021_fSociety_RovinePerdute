package it.unibs.pa.rovinePerdute;

import it.unibs.pa.rovinePerdute.Citta;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

//package it.unibs.Pa.CodiceFiscale;
public class InputXML {

    /**
     * crazione Persona
     *
     * @throws XMLStreamException
     * @ArrayList
     */
    public static HashMap<Integer, Citta> leggiInputCitta() throws XMLStreamException {
        HashMap<Integer, Citta> elenco_citta = new HashMap<>();

        Citta cit = null;
        Citta cittaCollegata = null;
        Archi arco = null;
        int id, x, y, h, linkTo;
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String FileXML = "PgAr_Map_10000.xml";

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(FileXML, new FileInputStream(FileXML));
        } catch (Exception e) {
            System.out.println("Errore nell'inzializzazione del reader");
            System.out.println(e.getMessage());
        }

        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if (xmlr.getLocalName().equals("city")) {
                        cit = new Citta();

                        //Legge gli attributi della citta'
                        for (int i=0; i<xmlr.getAttributeCount(); i++){
                            //Legge il nome della citta'
                            if (xmlr.getAttributeLocalName(i).equals("name")) {
                                cit.setNome(xmlr.getAttributeValue(i));
                            }
                            //Legge la posizione sull'asse X
                            else if (xmlr.getAttributeLocalName(i).equals("x")) {
                                //Conversione della stringa in un intero
                                x = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setCoordinataX(x);
                            }
                            //Legge la posizione sull'asse Y
                            else if (xmlr.getAttributeLocalName(i).equals("y")) {
                                //Conversione della stringa in un intero
                                y = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setCoordinataY(y);
                            }
                            //Legge l'altitudine
                            else if (xmlr.getAttributeLocalName(i).equals("h")) {
                                //Conversione della stringa in un intero
                                h = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setAltitudine(h);
                            }
                            //Legge l'ID della citta'
                            else if (xmlr.getAttributeLocalName(i).equals("id")) {
                                //Conversione della stringa in un intero
                                id = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setId(id);
                            }
                        }
                    }
                    //Legge le destinazioni che si possono raggiungere da una citta'
                    else if (xmlr.getLocalName().equals("link")) {
                        for (int i=0; i<xmlr.getAttributeCount(); i++){
                            if (xmlr.getAttributeLocalName(i).equals("to")){
                                cittaCollegata = new Citta();
                                arco = new Archi();

                                //Conversione della stringa in un intero
                                linkTo = Integer.parseInt(xmlr.getAttributeValue(i));

                                //Imposta linkTo come iD della citta' di arrivo dell'arco
                                cittaCollegata.setId(linkTo);

                                //Imposta per l'arco la citta' di partenza
                                arco.setCittaPartenza(cit);

                                //Imposta per l'arco la citta' di arrivo
                                arco.setCittaArrivo(cittaCollegata);
                                cit.getCittaCollegate().add(arco);
                            }
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (xmlr.getLocalName().equals("city")) {
                        //Aggiunge la citta' all'elenco delle citta' esistente
                        elenco_citta.put(cit.getId(), cit);
                    }
                    break;

                default:
                    break;
            }
            xmlr.next();
        }
        return elenco_citta;
    }

}