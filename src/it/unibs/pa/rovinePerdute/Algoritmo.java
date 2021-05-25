package it.unibs.pa.rovinePerdute;

import java.util.ArrayList;

public  class  Algoritmo {
    int matricePerscorsi[][];
    ArrayList<Citta> listaCitta;

    public Algoritmo(ArrayList listaCitta) {
        this.listaCitta = listaCitta;
        this.matricePerscorsi = new int[listaCitta.size()][listaCitta.size()];
    }

    public void matriceDistanze() {


        int distanza;

        //aggiungere che se il cicli

        //seleziona righe matrice ciascuna corrispondente a una localita'
        for (int i=0; i < listaCitta.size(); i++){
            //Controlla quante destinazioni sono raggiungibili partendo dalla localita' selezionata
            for (int j=0; j < listaCitta.get(i).getLinkTo().size(); j++){
                //Imposta il percorso come "esistente" all'interno della matricePercorsi
                matricePerscorsi[i][listaCitta.get(i).getLinkTo().get(j)] = 1;
            }
        }

        // STAMPA
        for (int k = 0; k < listaCitta.size(); k++){

                for (int n = 0; n < listaCitta.size(); n++) {
                    System.out.print(String.format(" %d\t ", matricePerscorsi[k][n]));

                }
            System.out.println();
        }
    }


    // matrice per trovare l'altitudine
    public void trovaAltitudine ( ){
          int matriceAltitudine [] [] = new int [ listaCitta.size()][listaCitta.size()];
        //seleziona righe matrice ciascuna corrispondente a una localita'
        for (int i=0; i < listaCitta.size(); i++){
            //Controlla quante destinazioni sono raggiungibili partendo dalla localita' selezionata
            for (int j=0; j < listaCitta.size(); j++){

                if (matricePerscorsi[i][j] == 1) {
                    matriceAltitudine[i][j] = (Math.abs(listaCitta.get(i).getAltitudine()- listaCitta.get(j).getAltitudine()));
                }else{
                    matriceAltitudine[ i][ j ]= -1;
                }

            }
        }

        for (int k = 0; k < listaCitta.size(); k++){

            for (int n = 0; n < listaCitta.size(); n++) {
                System.out.print(String.format(" %d \t ", matriceAltitudine[k][n]));

            }
            System.out.println();
        }

    }


    public void trovaDistanza ( ){
        int distanza;
        int matriceDistanza[] [] = new int [ listaCitta.size()][listaCitta.size()];

        for (int i=0; i < listaCitta.size(); i++){
            for (int j=0; j < listaCitta.size(); j++){

                if (matricePerscorsi[i][j] == 1) {
                    distanza = (int) Math.sqrt(Math.pow((listaCitta.get(i).getCoordinataX() - listaCitta.get(j).getCoordinataX()), 2)
                            + Math.pow((listaCitta.get(i).getCoordinataY() - listaCitta.get(j).getCoordinataY()), 2));
                    matriceDistanza[i][j]= distanza;

                }else{
                    matriceDistanza[i][j]= -1;
                }

            }
        }
        for (int k = 0; k < listaCitta.size(); k++){

            for (int n = 0; n < listaCitta.size(); n++) {
                System.out.print(String.format(" %d \t ", matriceDistanza[k][n]));

            }
            System.out.println();
        }

    }



}




































// CREAZIONE SBAGLIATA DELLA MATRICE
/*for (int i = 0; i < citta.size(); i++){
            for ( int j = 0 ; j < citta.size(); j++)
                for (int h = 0; h < citta.get(j).getLinkTo().size(); h++) {

                    if (citta.get(j).getLinkTo().get(h) == citta.get(i).getId()) {
                        //distanza = radice di differenza di x di differenza di y
                        distanza = Math.sqrt(Math.pow((citta.get(i).getCoordinataX() - citta.get(j).getCoordinataX()), 2)
                                + Math.pow((citta.get(i).getCoordinataY() - citta.get(j).getCoordinataY()), 2));

                        matrice[i][j] = distanza;

                    } else {
                        matrice[i][j] = -1;
                    }
                }

        }*/