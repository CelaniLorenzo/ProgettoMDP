package it.unicam.cs.mpgc.rpg130836.model.personaggi;

import it.unicam.cs.mpgc.rpg130836.model.abilita.Potenziabile;

public class DatiPersonaggio {

    private String categoria;
    private String tipo;
    private String nome;
    private int vita;
    private int vitaMassima;
    private int attaccoBase;
    private int difesaBase;
    private int attaccoTotale;
    private int difesaTotale;
    private boolean vivo;
    private String potenziamento;

    public DatiPersonaggio() {
    }

    private DatiPersonaggio(Personaggio personaggio) {
        this.categoria = determinaCategoria(personaggio);
        this.tipo = personaggio.getClass().getSimpleName();
        this.nome = personaggio.getNome();
        this.vita = personaggio.getVita();
        this.vitaMassima = personaggio.getVitaMassima();
        this.attaccoBase = personaggio.getAttaccoBase();
        this.difesaBase = personaggio.getDifesaBase();
        this.attaccoTotale = personaggio.calcolaAttacco();
        this.difesaTotale = personaggio.calcolaDifesa();
        this.vivo = personaggio.isVivo();
        this.potenziamento = determinaPotenziamento(personaggio);
    }

    public static DatiPersonaggio daPersonaggio(Personaggio personaggio) {
        if (personaggio == null) {
            throw new IllegalArgumentException("Il personaggio non può essere null.");
        }

        return new DatiPersonaggio(personaggio);
    }

    private String determinaCategoria(Personaggio personaggio) {
        if (personaggio instanceof Eroe) {
            return "EROE";
        }

        if (personaggio instanceof Nemico) {
            return "NEMICO";
        }

        return "PERSONAGGIO";
    }

    private String determinaPotenziamento(Personaggio personaggio) {
        if (personaggio instanceof Potenziabile) {
            Potenziabile potenziabile = (Potenziabile) personaggio;
            return potenziabile.descrizionePotenziamento();
        }

        return "Nessun potenziamento";
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTipo() {
        return tipo;
    }

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

    public int getAttaccoTotale() {
        return attaccoTotale;
    }

    public int getDifesaTotale() {
        return difesaTotale;
    }

    public boolean isVivo() {
        return vivo;
    }

    public String getPotenziamento() {
        return potenziamento;
    }
}