package it.unibs.pa.rovinePerdute;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

    public void creaAlbero(int idNodoPartenza, int idNodoArrivo) {
        //Nodi la cui distanza dal punto di partenza può sempre essere modificata
        ArrayList <Nodo> nodiDaVisitare = new ArrayList<Nodo>();
        //Nodi la cui distanza è considerata definitiva
        ArrayList <Nodo> nodiVisitati = new ArrayList<Nodo>();
        Nodo nodo = new Nodo();

        /**Inizializza la lista dei nodi da visitare*/
        //Imposta il nodo di partenza
        nodiDaVisitare.get(0).setIdNodo(idNodoPartenza);
        nodiDaVisitare.get(0).setDistanza(0);
        nodiDaVisitare.get(0).setPartenza(true);

        //Inizialmente queste impostazioni sono valide per tutti i nodi eccetto quello di partenza
        nodo.setIdNodoProvenienza(Integer.MAX_VALUE);
        nodo.setDistanza(0);
        nodo.setPartenza(false);
        for (int i=1; i < this.listaCitta.size(); i++){
            nodo.setIdNodo(this.listaCitta.get(i).getId());
            nodiDaVisitare.add(nodo);
        }

        int idNodoAttuale = idNodoPartenza;
        int nodoCollegato, indiceNodo, distanza;

        //Se idNodoAttuale corrisponde a idNodoArrivo significa che e' stato trovato il percorso piu' breve
        while (idNodoAttuale != idNodoArrivo){

            //Controlla quanti nodi sono collegati a quello attualmente in analisi
            for (int i=0; i < this.listaCitta.get(idNodoAttuale).getLinkTo().size(); i++){
                nodoCollegato = this.listaCitta.get(idNodoAttuale).getLinkTo().get(i);

                //Verifica che il nodo debba essere ancora controllato, altrimenti prosegue
                if (nodiDaVisitare.contains(nodoCollegato)){

                    indiceNodo = trovaIndiceNodo(nodoCollegato, nodiDaVisitare);


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
        return -1;
    }
}