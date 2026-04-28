package it.unicam.cs.mpgc.rpg130836;

public abstract class Personaggio implements Combattente {

    private final String nome;
    private int vita;
    private int vitaMassima;
    private final int attaccoBase;
    private final int difesaBase;

    public Personaggio(String nome, int vitaMassima, int attaccoBase, int difesaBase) {
        this.nome = nome;
        this.vitaMassima = vitaMassima;
        this.vita = vitaMassima;
        this.attaccoBase = attaccoBase;
        this.difesaBase = difesaBase;
    }
    @Override
    public String getNome() {
        return nome;
    }

    public int getVita() {
        return vita;
    }

    public int getVitaMassima() {
        return vitaMassima;
    }

    public int getAttaccoBase() {
        return attaccoBase;
    }

    public int getDifesaBase() {
        return difesaBase;
    }
    @Override
    public boolean isVivo() {
        return vita > 0;
    }
    @Override
    public int attacca(Personaggio bersaglio) {
        int danno = calcolaAttacco();
        return bersaglio.riceviDanno(danno);
    }
    @Override
    public int riceviDanno(int danno) {
        int dannoEffettivo = danno - calcolaDifesa();

        if (dannoEffettivo < 1) {
            dannoEffettivo = 1;
        }

        vita -= dannoEffettivo;

        if (vita < 0) {
            vita = 0;
        }

        return dannoEffettivo;
    }

    protected int calcolaAttacco() {
        return attaccoBase;
    }

    protected int calcolaDifesa() {
        return difesaBase;
    }

    public void cura(int quantita) {
        vita += quantita;

        if (vita > vitaMassima) {
            vita = vitaMassima;
        }
    }

    protected void aumentaVitaMassima(int quantita) {
        vitaMassima += quantita;
        vita += quantita;
    }

    public String stato() {
        return nome + " - Vita: " + vita + "/" + vitaMassima;
    }
}

