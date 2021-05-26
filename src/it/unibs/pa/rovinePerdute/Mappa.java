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

    public void creaAlbero(){

        /**Questa parte del metodo corrisponde ai punti 1 e 2 delle slide: lezione_7.1 pagina 37*/
        HashSet<Integer> idCittaDaVisitare = new HashSet();
        HashMap<Integer, Nodo> listaNodi = new HashMap<>();
        Nodo nodo = new Nodo();

        for (int i=0; i < this.listaCitta.size(); i++) {
            idCittaDaVisitare.add(i);
            nodo.setIdNodo(i);

            //zero corrisponde all'ID del campo base
            nodo.setIdNodoProvenienza(0);

            //-1 corrisponde a distanza infinita o più correttamente non ancora indicata
            nodo.setDistanza(-1);

            //l'indice del nodo nell'HashMap e' anche il suo ID
            listaNodi.put(nodo.getIdNodo(), nodo);
        }


        //Nodo di partenza
        int idNodoAttuale = 0;
        int nodo_meno_distante = 0;
        int nodoCollegato, distanza, minor_distanza;
        while (!idCittaDaVisitare.isEmpty()) {

            /**Questa parte del metodo svolge il punto 3 delle slide: lezione_7.1 pagina 37*/
            minor_distanza = 0;
            //Seleziona il luogo/nodo a minore distanza tra quelli collegati all'origine
            for (int i=0; i < this.listaCitta.get(idNodoAttuale).getLinkTo().size(); i++){
                //Seleziona un luogo/nodo collegato a quello di origine
                nodoCollegato = this.listaCitta.get(idNodoAttuale).getLinkTo().get(i);

                //Ottiene la distanza tra i due luoghi/nodi dalla matrice
                distanza = this.matricePercorsi[idNodoAttuale][nodoCollegato];

                if (distanza < listaNodi.get(nodoCollegato).getDistanza() || listaNodi.get(nodoCollegato).getDistanza() == -1){
                    listaNodi.get(nodoCollegato).setDistanza(distanza);
                    listaNodi.get(nodoCollegato).setIdNodoProvenienza(idNodoAttuale);
                }

                if (distanza < minor_distanza || minor_distanza == 0){
                    minor_distanza = distanza;
                    nodo_meno_distante = nodoCollegato;
                }
            }

            idCittaDaVisitare.remove(idNodoAttuale);

            idNodoAttuale = nodo_meno_distante;
        }

        //STAMPA listaNodi
        for (int i=0; i < listaNodi.size(); i++)
            System.out.println("Nodo: " + listaNodi.get(i).getIdNodo()
                + "; Provenienza: " + listaNodi.get(i).getIdNodoProvenienza()
                + "; Distanza: " + listaNodi.get(i).getDistanza() + ";");

    }
}