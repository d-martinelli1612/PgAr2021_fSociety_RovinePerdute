package it.unibs.pa.rovinePerdute;

import java.util.*;
import java.util.ArrayList;


public  class Mappa {
    int matricePercorsi[][];
    HashMap<Integer, Citta> listaCitta;

    public Mappa(HashMap<Integer, Citta> listaCitta) {
        this.listaCitta = listaCitta;
        this.matricePercorsi = new int[listaCitta.size()][listaCitta.size()];
    }


    public void matriceDistanze() {
        //seleziona righe matrice ciascuna corrispondente a una localita'
        for (int i=0; i < this.listaCitta.size(); i++){
            //Controlla quante destinazioni sono raggiungibili partendo dalla localita' selezionata
            for (int j = 0; j < this.listaCitta.get(i).getCittaCollegate().size(); j++){
                //Imposta il percorso come "esistente" all'interno della matricePercorsi
                this.matricePercorsi[i][this.listaCitta.get(i).getCittaCollegate().get(j).getCittaArrivo().getId()] = 1;
            }
        }
    }


    //Crea la mappa per la squadra che considera solo l'altitudine
    public void percorsoAltitudine(Mappa Sorgente){
        int altezza=0;
        for (int i=0; i < Sorgente.listaCitta.size(); i++){
            int indice =0;
            for (int j=0; j < Sorgente.listaCitta.size(); j++){
                if (Sorgente.matricePercorsi[i][j] == 1) {
                     altezza = (Math.abs(Sorgente.listaCitta.get(i).getAltitudine()
                            - Sorgente.listaCitta.get(j).getAltitudine()));
                    //this.matricePercorsi[i][j]= altezza;
                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(altezza);
                    indice++;
                }
            }
        }
    }


    //Crea la mappa per la squadra che considera solo le distanze cartesiane
    public void percorsoPlanare(Mappa sorgente){
        int distanza =0, indiceX, indiceY;


        for (int i=0; i < sorgente.listaCitta.size(); i++){
            indiceX = sorgente.listaCitta.get(i).getCoordinataX();
            indiceY = sorgente.listaCitta.get(i).getCoordinataY();
            int indice = 0;
            for (int j=0; j < sorgente.listaCitta.size(); j++){
                if (sorgente.matricePercorsi[i][j] == 1) {
                    distanza = (int) Math.sqrt(Math.pow((indiceX - listaCitta.get(j).getCoordinataX()), 2)
                            + Math.pow((indiceY - listaCitta.get(j).getCoordinataY()), 2));
                    //this.matricePercorsi[i][j]= distanza;

                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(distanza);
                    indice++;
                }
            }
        }
    }

    public void percorsoCorto (Citta prov){

        prov.setDist(0);
        PriorityQueue<Citta> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(prov);
        prov.setVisited(true);
        int idCercato;

        while (!priorityQueue.isEmpty()){
            Citta nodoAttuale =  priorityQueue.poll();
            for ( Archi arco  : nodoAttuale.getCittaCollegate()){
                idCercato = arco.getCittaArrivo().getId();
                Citta c = trovaCitta(idCercato, this.listaCitta);
                if ( !c.isVisited()){
                    int newDistance = nodoAttuale.getDist() + arco.getPeso();
                    if ( newDistance < c.getDist()) {
                        priorityQueue.remove(c);
                        c.setDist(newDistance);
                        c.setCittaProvenienza(nodoAttuale);
                        priorityQueue.add(c);
                    }

                }
            }
                nodoAttuale.setVisited(true);
        }

    }

    public List<Citta> getShortest ( Citta cittaTarget){
        List<Citta> percorso = new ArrayList<>();
        for(Citta nodoPredecessore = cittaTarget; nodoPredecessore!= null; nodoPredecessore = nodoPredecessore.getCittaProvenienza()){
            percorso.add(nodoPredecessore);
        }
        Collections.reverse(percorso);
        return percorso;
    }

    private static Citta trovaCitta(int idCitta, HashMap<Integer, Citta> listaCitta){
        //Restituisce la citta' presente a quel determinato indice della listaCitta
        return listaCitta.get(idCitta);
    }

}