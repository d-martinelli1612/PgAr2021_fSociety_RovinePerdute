package it.unibs.pa.rovinePerdute;

public class Nodo {
    private int idProvenienza;
    private int costo;

    public Nodo(int from, int costo) {
        this.idProvenienza = from;
        this.costo = costo;
    }

    public int getIdProvenienza() {
        return idProvenienza;
    }

    public int getCosto() {
        return costo;
    }

    public void setIdProvenienza(int idProvenienza) {
        this.idProvenienza = idProvenienza;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
