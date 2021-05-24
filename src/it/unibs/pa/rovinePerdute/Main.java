package it.unibs.pa.rovinePerdute;



import it.unibs.pa.rovinePerdute.Citta;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.HashMap;




public class Main {
    public static void main(String args[]) throws XMLStreamException {

        for(Citta cit : InputXml2.leggiInputCitta()){

            System.out.println(cit.toString());

        }


    }
}