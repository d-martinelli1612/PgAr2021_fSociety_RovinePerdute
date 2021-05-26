package it.unibs.pa.rovinePerdute;

import java.util.ArrayList;

public class Citta implements Comparable<Citta>{

    private int coordinataX ;
    private int coordinataY;
    private int altitudine;
    private String nome;
    private int id;
    private ArrayList<Integer> linkTo ;

    // AGGIUNTI CON LALGORITMO
    private Citta cittaProvenienza;
    private int dist= /*sposta nel costuttore*/Integer.MAX_VALUE;
    private boolean visited ;




    public Citta(int coordinataX, int coordinataY, int altitudine, String nome, int id) {
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
        this.altitudine = altitudine;
        this.nome = nome;
        this.id = id;
        this.linkTo =  new ArrayList<>();

    }

    public Citta(String nome ) {
        this.nome = nome;
        this.linkTo =  new ArrayList<>();
    }

    public Citta() {
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

    public ArrayList<Integer> getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(ArrayList<Integer> linkTo) {
        this.linkTo = linkTo;
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

    // prova andreaa
    public void addLinkTo (Integer intero ){
        this.linkTo.add(intero);
    }

    public void stampaCitta() {
        System.out.println(this.getNome() +
                " { ID: " + this.getId() +
                "; coordinata x: " + this.getCoordinataX() +
                "; coordinata y: " + this.getCoordinataY() +
                "; altitudine: " + this.getAltitudine() + "; }");

        System.out.print("Destinazioni { ");
        for (int i=0; i<this.linkTo.size(); i++)
            System.out.print(this.linkTo.get(i) + ";");
        System.out.print("}\n");
    }


    public int compareTo(Citta cit){
        return Integer.compare(this.dist, cit.getDist());
    }
}
