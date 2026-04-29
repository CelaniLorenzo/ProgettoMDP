package it.unicam.cs.mpgc.rpg130836;

public abstract class Personaggio implements Combattente {

    protected String nome;
    protected int vita;
    protected int vitaMassima;
    protected int attaccoBase;
    protected int difesaBase;

    protected Personaggio(String nome, int vitaMassima, int attaccoBase, int difesaBase) {
        validaParametri(nome, vitaMassima, attaccoBase, difesaBase);

        this.nome = nome;
        this.vitaMassima = vitaMassima;
        this.vita = vitaMassima;
        this.attaccoBase = attaccoBase;
        this.difesaBase = difesaBase;
    }

    private void validaParametri(String nome, int vitaMassima, int attaccoBase, int difesaBase) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del personaggio non può essere vuoto.");
        }

        if (vitaMassima <= 0) {
            throw new IllegalArgumentException("La vita massima deve essere positiva.");
        }

        if (attaccoBase < 0 || difesaBase < 0) {
            throw new IllegalArgumentException("Attacco e difesa non possono essere negativi.");
        }
    }

    @Override
    public int attacca(Combattente avversario) {
        if (avversario == null) {
            throw new IllegalArgumentException("L'avversario non può essere null.");
        }

        if (!isVivo() || !avversario.isVivo()) {
            return 0;
        }

        int danno = Math.max(1, calcolaAttacco() - avversario.calcolaDifesa());
        avversario.riceviDanno(danno);

        return danno;
    }

    @Override
    public void riceviDanno(int danno) {
        if (danno < 0) {
            throw new IllegalArgumentException("Il danno non può essere negativo.");
        }

        vita = Math.max(0, vita - danno);
    }

    @Override
    public int calcolaAttacco() {
        return attaccoBase;
    }

    @Override
    public int calcolaDifesa() {
        return difesaBase;
    }

    protected void aumentaAttaccoBase(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("L'incremento non può essere negativo.");
        }

        attaccoBase += incremento;
    }

    protected void aumentaDifesaBase(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("L'incremento non può essere negativo.");
        }

        difesaBase += incremento;
    }

    protected void aumentaVitaMassima(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("L'incremento non può essere negativo.");
        }

        vitaMassima += incremento;
        vita += incremento;
    }

    protected void cura(int puntiVita) {
        if (puntiVita < 0) {
            throw new IllegalArgumentException("La cura non può essere negativa.");
        }

        vita = Math.min(vitaMassima, vita + puntiVita);
    }

    @Override
    public boolean isVivo() {
        return vita > 0;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVita() {
        return vita;
    }

    @Override
    public int getVitaMassima() {
        return vitaMassima;
    }

    public int getAttaccoBase() {
        return attaccoBase;
    }

    public int getDifesaBase() {
        return difesaBase;
    }
}