package it.unibs.pa.rovinePerdute;

public class Archi {
    private int peso;
    private Citta cittaPartenza;
    private Citta cittaArrivo;
    private String name;

    public Archi(int peso, Citta cittaPartenza, Citta targaCitta, String name) {
        this.peso = peso;
        this.cittaPartenza = cittaPartenza;
        this.cittaArrivo = targaCitta;
        this.name = name;
    }

    public Archi(int idCittaArrivo) {
        Citta citta = new Citta();
        citta.setId(idCittaArrivo);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
