package it.unibs.pa.rovinePerdute;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public  class Mappa {
    int matricePercorsi[][];
    HashMap<Integer, Citta> listaCitta;

    private static int idNodoAttuale = 0;

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
        //Nodi la cui nuovaDistanza dal punto di partenza può sempre essere modificata
        ArrayList <Nodo> nodiDaVisitare = new ArrayList<Nodo>();
        //Nodi la cui nuovaDistanza è considerata definitiva
        ArrayList <Nodo> nodiVisitati = new ArrayList<Nodo>();
        Nodo nodo = new Nodo();

        /**Inizializza la lista dei nodi da visitare*/
        //Imposta il nodo di partenza
        nodiDaVisitare.get(0).setIdNodo(idNodoPartenza);
        nodiDaVisitare.get(0).setDistanza(0);
        nodiDaVisitare.get(0).setPartenza(true);

        //Inizialmente queste impostazioni sono valide per tutti i nodi eccetto quello di partenza
        //nodo.setIdNodoProvenienza(0);
        nodo.setDistanza(Integer.MAX_VALUE);
        nodo.setPartenza(false);
        for (int i=1; i < this.listaCitta.size(); i++){
            nodo.setIdNodo(this.listaCitta.get(i).getId());
            nodiDaVisitare.add(nodo);
        }

        int idNodoCollegato, idNodoProvenienza, indiceNodo, nuovaDistanza, distanzaAttualeCollegato;

        //Se idNodoArrivo e' all'indice zero della lista significa che e' stato trovato il percorso piu' breve
        while (trovaIndiceNodo(idNodoArrivo, nodiDaVisitare) != 0){

            //Controlla quanti nodi sono collegati a quello attualmente in analisi
            for (int i=0; i < this.listaCitta.get(idNodoAttuale).getLinkTo().size(); i++){
                idNodoCollegato = this.listaCitta.get(idNodoAttuale).getLinkTo().get(i);

                //Verifica che il nodo debba essere ancora controllato, altrimenti prosegue
                if (nodiDaVisitare.contains(idNodoCollegato)){
                    //Individua il nodo all'interno della lista
                    indiceNodo = trovaIndiceNodo(idNodoCollegato, nodiDaVisitare);

                    //Ricava dalla matrice la nuovaDistanza tra il nodo attuale e il suo nodo collegato
                    distanzaAttualeCollegato = this.matricePercorsi[nodiDaVisitare.get(idNodoAttuale).getIdNodo()][idNodoCollegato];
                    //Calcola la nuovaDistanza del nodo dal punto di partenza,
                    //sommando quella del nodo attuale e quella tra l'attuale e il suo nodo collegato
                    nuovaDistanza = nodiDaVisitare.get(idNodoAttuale).getDistanza() + distanzaAttualeCollegato;

                    //Confronta la nuovaDistanza con quella attuale
                    if (nuovaDistanza < nodiDaVisitare.get(indiceNodo).getDistanza()){
                        //Imposta la nuovaDistanza come la distanza del nodo dall'origine
                        nodiDaVisitare.get(indiceNodo).setDistanza(nuovaDistanza);

                        //Imposta il nodo attuale come quello di provenienza
                        idNodoProvenienza = nodiDaVisitare.get(idNodoAttuale).getIdNodo();
                        nodiDaVisitare.get(indiceNodo).setIdNodoProvenienza(idNodoProvenienza);

                        //Ordina la lista per distanza dall'origine crescente
                        nodiDaVisitare = ordinaLista(indiceNodo, nodiDaVisitare);
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
        return -1;
    }

    private static ArrayList<Nodo> ordinaLista(int indiceNodo, ArrayList <Nodo> nodiDaVisitare){
        Nodo nodo = new Nodo();

        while (nodiDaVisitare.get(indiceNodo).getDistanza() < nodiDaVisitare.get(indiceNodo - 1).getDistanza()){
            nodo = nodiDaVisitare.get(indiceNodo);
            //nodiDaVisitare.
        }

        return nodiDaVisitare;
    }
}