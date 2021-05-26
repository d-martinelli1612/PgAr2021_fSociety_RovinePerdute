package it.unibs.pa.rovinePerdute;

public class Nodo {
    private int idNodo;
    private int idNodoProvenienza;
    private int distanza;
    private boolean partenza;

    public Nodo(int idNodo, int from, int costo) {
        this.idNodo = idNodo;
        this.idNodoProvenienza = from;
        this.distanza = costo;
    }

    public Nodo() { }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public int getIdNodoProvenienza() {
        return idNodoProvenienza;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setIdNodoProvenienza(int idNodoProvenienza) {
        this.idNodoProvenienza = idNodoProvenienza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public boolean isPartenza() {
        return partenza;
    }

    public void setPartenza(boolean partenza) {
        this.partenza = partenza;
    }
}
