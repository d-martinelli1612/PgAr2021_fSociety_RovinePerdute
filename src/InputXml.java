import it.unibs.pa.rovinePerdute.Citta;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.HashMap;

//package it.unibs.Pa.CodiceFiscale;
//public class InputXml {



    //public class InputXml {

/*
        /**
         * crazione Persona
         *
         * @throws XMLStreamException
         * @ArrayList
         */
   /*     public static HashMap<Integer, Citta> leggiInputhjhjkjkCitta() throws XMLStreamException {*/
           /* HashMap<Integer, Citta> elenco_citta = new HashMap<Integer, Citta>();
            Citta cit = null;
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
                            //Imposta automaticamente l'ID della citta'
                            cit = new Citta(elenco_citta.size());
                        } else if (xmlr.getLocalName().equals("name")) {
                            xmlr.next();
                            cit.setNome(xmlr.getText());
                        } else if (xmlr.getLocalName().equals("x")) {
                            xmlr.next();
                            cit.setCoordinataX(xmlr.getText());
                        } else if (xmlr.getLocalName().equals("y")) {
                            xmlr.next();
                            cit.setCoordinataY(xmlr.getText());
                        } else if (xmlr.getLocalName().equals("h")) {
                            xmlr.next();
                            cit.setAltitudine(xmlr.getText());
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (xmlr.getLocalName().equals("city")) {
                            elenco_citta.put(cit.getId(), cit);
                        }
                        break;

                    default:
                        break;
                }
                xmlr.next();
            }
*//*
            return elenco_citta;
        }

    }    */
//}*/