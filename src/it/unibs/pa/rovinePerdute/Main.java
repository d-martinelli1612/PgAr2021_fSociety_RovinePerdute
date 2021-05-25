package it.unibs.pa.rovinePerdute;



import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;


public class Main {
    public static void main(String args[]) throws XMLStreamException {

        ArrayList<Citta> listaCitta = InputXML.leggiInputCitta();

        for (int i=0; i<listaCitta.size(); i++){
            listaCitta.get(i).stampaCitta();
        }
    }
}