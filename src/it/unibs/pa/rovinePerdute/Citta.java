package it.unibs.pa.rovinePerdute;

public class Citta {

    private String coordinataX;
    private String coordinataY;
    private String altitudine;
    private String nome;
    private int id;

    public Citta(String coordinataX, String coordinataY, String altitudine, String nome, int id) {
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
        this.altitudine = altitudine;
        this.nome = nome;
        this.id = id;
    }

    public Citta(int id) {
        this.id = id;
    }

    public String getCoordinataX() {
        return coordinataX;
    }

    public void setCoordinataX(String coordinataX) {
        this.coordinataX = coordinataX;
    }

    public String getCoordinataY() {
        return coordinataY;
    }

    public void setCoordinataY(String coordinataY) {
        this.coordinataY = coordinataY;
    }

    public String getAltitudine() {
        return altitudine;
    }

    public void setAltitudine(String altitudine) {
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
/*
    private double coordinataX;
    private double coordinataY;
    private double altitudine;
    private String nome;
    private int id;

    public Citta(int id) {
        this.id = id;
    }

    public double getCoordinataX() {
        return coordinataX;
    }

    public void setCoordinataX(double coordinataX) {
        this.coordinataX = coordinataX;
    }

    public double getCoordinataY() {
        return coordinataY;
    }

    public void setCoordinataY(double coordinataY) {
        this.coordinataY = coordinataY;
    }

    public double getAltitudine() {
        return altitudine;
    }

    public void setAltitudine(double altitudine) {
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
    }*/
}
