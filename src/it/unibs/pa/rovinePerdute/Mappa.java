package it.unibs.pa.rovinePerdute;

import java.util.ArrayList;

public  class Mappa {
    int matricePercorsi[][];
    ArrayList<Citta> listaCitta;

    public Mappa(ArrayList listaCitta) {
        this.listaCitta = listaCitta;
        this.matricePercorsi = new int[listaCitta.size()][listaCitta.size()];
    }


    public void matriceDistanze() {
        //seleziona righe matrice ciascuna corrispondente a una localita'
        for (int i=0; i < this.listaCitta.size(); i++){
            //Controlla quante destinazioni sono raggiungibili partendo dalla localita' selezionata
            for (int j=0; j < this.listaCitta.get(i).getLinkTo().size(); j++){
                //Imposta il percorso come "esistente" all'interno della matricePercorsi
                this.matricePercorsi[i][this.listaCitta.get(i).getLinkTo().get(j)] = 1;
            }
        }
    }


    //Crea la mappa per la squadra che considera solo l'altitudine
    public void percorsoAltitudine(Mappa Sorgente){
        for (int i=0; i < Sorgente.listaCitta.size(); i++){
            for (int j=0; j < Sorgente.listaCitta.size(); j++){
                if (Sorgente.matricePercorsi[i][j] == 1) {
                    this.matricePercorsi[i][j] = (Math.abs(Sorgente.listaCitta.get(i).getAltitudine()
                            - Sorgente.listaCitta.get(j).getAltitudine()));
                }else{
                    this.matricePercorsi[i][j]= -1;
                }
            }
        }
    }


    //Crea la mappa per la squadra che considera solo le distanze cartesiane
    public void percorsoPlanare(Mappa Sorgente){
        int distanza, indiceX, indiceY;

        for (int i=0; i < Sorgente.listaCitta.size(); i++){
            indiceX = Sorgente.listaCitta.get(i).getCoordinataX();
            indiceY = Sorgente.listaCitta.get(i).getCoordinataY();
            for (int j=0; j < Sorgente.listaCitta.size(); j++){
                if (Sorgente.matricePercorsi[i][j] == 1) {
                    distanza = (int) Math.sqrt(Math.pow((indiceX - listaCitta.get(j).getCoordinataX()), 2)
                            + Math.pow((indiceY - listaCitta.get(j).getCoordinataY()), 2));
                    this.matricePercorsi[i][j]= distanza;

                }else{
                    this.matricePercorsi[i][j]= -1;
                }
            }
        }
    }
}