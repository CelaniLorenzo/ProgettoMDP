package it.unicam.cs.mpgc.rpg130836;

public class Personaggio {
    protected String nome;
    protected int vita;
    protected int attacco;
    protected int difesa;

    public Personaggio(String nome, int vita, int attacco, int difesa) {
        this.nome = nome;
        this.vita = vita;
        this.attacco = attacco;
        this.difesa = difesa;
    }

    public int lanciaDado() {
        return (int)(Math.random() * 6) + 1;
    }

    public void attacca(Personaggio nemico) {
        int dado = lanciaDado();

        int danno = attacco + dado - nemico.difesa;
        if (danno < 0) danno = 0;

        if (dado == 6) {
            danno *= 2;
            System.out.println("💥 COLPO CRITICO!");
        }

        nemico.vita -= danno;

        System.out.println(nome + " tira " + dado + " → danno: " + danno);
    }

    public void usaAbilita(Personaggio nemico) {
        System.out.println(nome + " non ha abilità speciale.");
    }

    public void difendi() {
        difesa += 5;
        System.out.println(nome + " aumenta la difesa!");
    }

    public boolean isVivo() {
        return vita > 0;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public String getNome() {
        return nome;
    }
}

