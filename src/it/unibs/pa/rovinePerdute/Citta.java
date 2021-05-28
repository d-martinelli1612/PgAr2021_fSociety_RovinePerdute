package it.unibs.pa.rovinePerdute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Citta implements Comparable<Citta>{

    private int coordinataX ;
    private int coordinataY;
    private int altitudine;
    private String nome;
    private int id;
    private List<Archi> cittaCollegate;
    private Citta cittaProvenienza;
    private int dist;
    private boolean visited;

    public Citta(int coordinataX, int coordinataY, int altitudine, String nome, int id, List<Archi> cittaCollegate) {
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
        this.altitudine = altitudine;
        this.nome = nome;
        this.id = id;
        this.cittaCollegate = cittaCollegate;
    }

    public Citta() {
        this.dist =  Integer.MAX_VALUE;
        this.cittaCollegate =  new ArrayList<>();
    }

    public int getCoordinataX() {
        return coordinataX;
    }

    public void setCoordinataX(int coordinataX) {
        this.coordinataX = coordinataX;
    }

    public int getCoordinataY() {
        return coordinataY;
    }

    public void setCoordinataY(int coordinataY) {
        this.coordinataY = coordinataY;
    }

    public int getAltitudine() {
        return altitudine;
    }

    public void setAltitudine(int altitudine) {
        this.altitudine = altitudine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Archi> getCittaCollegate() {
        return cittaCollegate;
    }

    public void setCittaCollegate(ArrayList<Archi> cittaCollegate) {
        this.cittaCollegate = cittaCollegate;
    }

    public Citta getCittaProvenienza() {
        return cittaProvenienza;
    }

    public void setCittaProvenienza(Citta cittaProvenienza) {
        this.cittaProvenienza = cittaProvenienza;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int compareTo(Citta cit){
        return Integer.compare(this.dist, cit.getDist());
    }

    public static HashMap<Integer, Citta> clonaLista (HashMap<Integer, Citta> listaSorgente){
        HashMap<Integer, Citta> nuovaLista = new HashMap<>();
        Citta cittaCopia = null;

        for (int i=0; i < listaSorgente.size(); i++){
            cittaCopia = new Citta(listaSorgente.get(i).coordinataX, listaSorgente.get(i).coordinataY,
                    listaSorgente.get(i).altitudine, listaSorgente.get(i).nome, listaSorgente.get(i).id,
                    listaSorgente.get(i).cittaCollegate);
            nuovaLista.put(i, cittaCopia);
        }

        return nuovaLista;
    }
}
