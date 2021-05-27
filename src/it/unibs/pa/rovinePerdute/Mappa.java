package it.unibs.pa.rovinePerdute;

import java.util.*;
import java.util.ArrayList;


public  class Mappa {
    int matricePercorsi[][];
    ArrayList<Citta> listaCitta;

    public Mappa(ArrayList<Citta> listaCitta) {
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
        int altezza;
        for (int i=0; i < Sorgente.listaCitta.size(); i++){
            int indice =0;
            for (int j=0; j < Sorgente.listaCitta.size(); j++){
                if (Sorgente.matricePercorsi[i][j] == 1) {
                     altezza = (Math.abs(Sorgente.listaCitta.get(i).getAltitudine()
                            - Sorgente.listaCitta.get(j).getAltitudine()));
                    this.matricePercorsi[i][j]= altezza;
                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(altezza);
                    indice++;
                }
                else if (i == j){
                    this.matricePercorsi[i][j] = 0;
                }
                else{
                    this.matricePercorsi[i][j] = -1;
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
                    this.matricePercorsi[i][j]= distanza;

                    this.listaCitta.get(i).getCittaCollegate().get(indice).setPeso(distanza);
                    indice++;
                }
                else if (i == j){
                    this.matricePercorsi[i][j] = 0;
                }
                else{
                    this.matricePercorsi[i][j]= -1;
                }
            }
        }
    }

    public void percorsoCorto ( Citta prov){
        prov.setDist(0);
        PriorityQueue<Citta> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(prov);
        prov.setVisited(true);

        while (!priorityQueue.isEmpty()){
            Citta nodoAttuale =  priorityQueue.poll();

            for ( Archi arco  : nodoAttuale.getCittaCollegate()){
                Citta c = arco.getCittaArrivo();
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
// manca la citta di prvenienza il ciclo non va

    public List<Citta> getShortest ( Citta cittaTarget){
        List<Citta> percorso = new ArrayList<>();
        for(Citta nodoPredecessore = cittaTarget; nodoPredecessore!= null; nodoPredecessore = nodoPredecessore.getCittaProvenienza()){
            percorso.add(nodoPredecessore);
        }
        Collections.reverse(percorso);
        return percorso;
    }



   /* public void creaAlbero(int idNodoPartenza, int idNodoArrivo) {
        ArrayList <Nodo> nodiDaVisitare = new ArrayList<Nodo>();
        ArrayList <Nodo> nodiVisitati = new ArrayList<Nodo>();
        Nodo nodo = new Nodo();

        *//**Inizializza la lista dei nodi da visitare*//*
        //Imposta il nodo di partenza
        nodiDaVisitare.get(0).setIdNodo(idNodoPartenza);
        nodiDaVisitare.get(0).setDistanza(0);
        nodiDaVisitare.get(0).setPartenza(true);

        nodo.setIdNodoProvenienza(-1);
        nodo.setDistanza(0);
        nodo.setPartenza(false);
        for (int i=0; i < this.listaCitta.size(); i++){
            nodo.setIdNodo(this.listaCitta.get(i).getId());
            nodiDaVisitare.add(nodo);
        }

        int idNodoAttuale = idNodoPartenza;
        int nodoCollegato, indiceNodo, distanza;

        //Se viene trovato il percorso per il nodo di arrivo il ciclo viene fermato
        while (idNodoAttuale != idNodoArrivo){
            //Controlla quanti nodi sono collegati a quello attualmente in analisi
            for (int i=0; i < this.listaCitta.get(idNodoAttuale).getLinkTo().size(); i++){
                nodoCollegato = this.listaCitta.get(idNodoAttuale).getLinkTo().get(i);

                //Verifica che il nodo debba essere ancora controllato, altrimenti prosegue
                if (nodiDaVisitare.contains(nodoCollegato)){
                    indiceNodo = trovaIndiceNodo(nodoCollegato, nodiDaVisitare);

                    //Se la distanza non e' ancora stata impostata viene messa quella indicata nella tabella
                    if (nodiDaVisitare.get(indiceNodo).getDistanza() == 0){
                        //imposta la distanza del nodo dall'origine
                        distanza = this.matricePercorsi[idNodoAttuale][nodoCollegato];
                        nodiDaVisitare.get(indiceNodo).setDistanza(distanza);
                    }
                    else *//*if ()*//*{

                    }
                }
            }
        }
    }

    //Restituisce l'indice del nodo cercato tra quelli della lista
    private static int trovaIndiceNodo(int idNodoCercato, ArrayList <Nodo> nodiDaVisitare){
        for (int i=0; i < nodiDaVisitare.size(); i++){
            if (idNodoCercato == nodiDaVisitare.get(i).getIdNodo())
                return i;
        }
        //Evento che non dovrebbe mai verificarsi
        return 0;
    }*/
}