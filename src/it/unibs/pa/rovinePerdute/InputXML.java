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
    public static ArrayList<Citta> leggiInputCitta() throws XMLStreamException {
        ArrayList<Citta> elenco_citta = new ArrayList<Citta>();

        Citta cit = null;
        int id, x, y, h, linkTo;
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        String FileXML = "PgAr_Map_5.xml";

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
                        /*Legge gli attributi della citta'*/
                        for (int i=0; i<xmlr.getAttributeCount(); i++){
                            if (xmlr.getAttributeLocalName(i).equals("name")) {
                                cit.setNome(xmlr.getAttributeValue(i));
                            }
                            else if (xmlr.getAttributeLocalName(i).equals("x")) {
                                //Conversione della stringa in un intero
                                x = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setCoordinataX(x);
                            }
                            else if (xmlr.getAttributeLocalName(i).equals("y")) {
                                //Conversione della stringa in un intero
                                y = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setCoordinataY(y);
                            }
                            else if (xmlr.getAttributeLocalName(i).equals("h")) {
                                //Conversione della stringa in un intero
                                h = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setAltitudine(h);
                            }
                            else if (xmlr.getAttributeLocalName(i).equals("id")) {
                                //Conversione della stringa in un intero
                                id = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.setId(id);
                            }
                        }
                    }
                    /*Legge le destinazioni che si possono raggiungere da una citta'*/
                    else if (xmlr.getLocalName().equals("link")) {
                        for (int i=0; i<xmlr.getAttributeCount(); i++){
                            if (xmlr.getAttributeLocalName(i).equals("to")){
                                linkTo = Integer.parseInt(xmlr.getAttributeValue(i));
                                cit.getLinkTo().add(linkTo);
                            }
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (xmlr.getLocalName().equals("city")) {
                        elenco_citta.add(cit);
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
//}
