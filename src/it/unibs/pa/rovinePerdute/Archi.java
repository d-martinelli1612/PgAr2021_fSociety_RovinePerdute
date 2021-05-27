package it.unibs.pa.rovinePerdute;

public class Archi {
    private int peso;
    private Citta cittaPartenza;
    private Citta cittaArrivo;
    private int codici;


    public Archi(int peso, Citta cittaPartenza, Citta targaCitta) {
        this.peso = peso;
        this.cittaPartenza = cittaPartenza;
        this.cittaArrivo = targaCitta;
    }

    public Archi(Citta cittaPartenza, int codici) {
        this.cittaPartenza = cittaPartenza;
        this.codici = codici;
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

    public Citta getCittaArrivo() {
        return cittaArrivo;
    }

    public void setCittaArrivo(Citta cittaArrivo) {
        this.cittaArrivo = cittaArrivo;
    }

}



