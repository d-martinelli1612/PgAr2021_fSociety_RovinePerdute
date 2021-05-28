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
                //Verifica se esiste un collegamento tra due citta
                if (Sorgente.matricePercorsi[i][j] == 1) {
                    //Calcola la distanza tra le due citta tra due citta
                    altezza = (Math.abs(Sorgente.listaCitta.get(i).getAltitudine()
                            - Sorgente.listaCitta.get(j).getAltitudine()));
                    //Imposta la distanza
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
                //Verifica se esiste un collegamento tra due citta
                if (sorgente.matricePercorsi[i][j] == 1) {
                    //Calcola la distanza tra le due citta tra due citta
                    distanza = (int) Math.sqrt(Math.pow((indiceX - listaCitta.get(j).getCoordinataX()), 2)
                            + Math.pow((indiceY - listaCitta.get(j).getCoordinataY()), 2));
                    //Imposta la distanza
                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(distanza);
                    indice++;
                }
            }
        }
    }

    /**Questo metodo trova il percorso migliore per raggiungere le Rovine Perdute*/
    public void percorsoCorto (Citta prov){

        prov.setDist(0);
        PriorityQueue<Citta> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(prov);
        prov.setVisited(true);
        int idCercato;

        //Se la lista di elementi da controllare e' vuota il metodo si interrompe
        while (!priorityQueue.isEmpty()){
            //Seleziona la citta da analizzare
            Citta nodoAttuale = priorityQueue.poll();

            //Controlla quanti collegamenti possiede
            for ( Archi arco  : nodoAttuale.getCittaCollegate()){

                //Individua la citta collegata e ne recupera le informazioni
                idCercato = arco.getCittaArrivo().getId();
                Citta c = trovaCitta(idCercato, this.listaCitta);

                //Se la citta e' gia' stata controllata viene ignorata
                if ( !c.isVisited()){
                    //Calcola la lunghezza del percorso attraverso il nodoAttuale
                    int newDistance = nodoAttuale.getDist() + arco.getPeso();
                    //Se il percorso attraverso il nodoAttuale risulta essere migliore viene salvato
                    if ( newDistance < c.getDist()) {
                        //Rimuove la citta per sostituirla
                        priorityQueue.remove(c);

                        //Aggiorna gli attributi
                        c.setDist(newDistance);
                        c.setCittaProvenienza(nodoAttuale);

                        //Salva nuovamente la citta nella lista
                        priorityQueue.add(c);
                    }

                }
            }
                //Segna che la citta e' stata controllata
                nodoAttuale.setVisited(true);
        }

    }

    public List<Citta> getShortest ( Citta cittaTarget){
        List<Citta> percorso = new ArrayList<>();
        //Le tappe del percorso vengono salvate in un'apposita lista
        for(Citta nodoPredecessore = cittaTarget; nodoPredecessore!= null; nodoPredecessore = nodoPredecessore.getCittaProvenienza()){
            percorso.add(nodoPredecessore);
        }
        //La lista viene invertita in modo che la partenza sia a indice 0
        Collections.reverse(percorso);
        //Restituisce il percorso
        return percorso;
    }

    private static Citta trovaCitta(int idCitta, HashMap<Integer, Citta> listaCitta){
        //Restituisce la citta' presente a quel determinato indice della listaCitta
        return listaCitta.get(idCitta);
    }

}