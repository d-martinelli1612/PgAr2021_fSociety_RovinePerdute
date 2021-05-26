package it.unibs.pa.rovinePerdute;

public class Archi {
    private int peso;
    private Citta cittaPartenza;
    private Citta targaCitta;
    private String name;

    public Archi(int peso, Citta cittaPartenza, Citta targaCitta, String name) {
        this.peso = peso;
        this.cittaPartenza = cittaPartenza;
        this.targaCitta = targaCitta;
        this.name = name;
    }

    public Archi(int peso) {
        /*this.peso = peso;*/ // questa e la distanza quindi non funzionera mai , dobbiamo metterci il collegamento
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Citta getCittaPartenza() {
        return cittaPartenza;
    }

    public void setCittaPartenza(Citta cittaPartenza) {
        this.cittaPartenza = cittaPartenza;
    }

    public Citta getTargaCitta() {
        return targaCitta;
    }

    public void setTargaCitta(Citta targaCitta) {
        this.targaCitta = targaCitta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
