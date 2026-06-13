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

    /*
     * Costruisce un'istanza di DatiPersonaggio.
     */
    public DatiPersonaggio() {
    }

    /*
     * Costruisce un'istanza di DatiPersonaggio.
     */
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

    /*
     * Crea i dati a partire dal personaggio indicato.
     */
    public static DatiPersonaggio daPersonaggio(Personaggio personaggio) {
        if (personaggio == null) {
            throw new IllegalArgumentException("Il personaggio non può essere null.");
        }

        return new DatiPersonaggio(personaggio);
    }

    /*
     * Determina la categoria del personaggio.
     */
    private String determinaCategoria(Personaggio personaggio) {
        if (personaggio instanceof Eroe) {
            return "EROE";
        }

        if (personaggio instanceof Nemico) {
            return "NEMICO";
        }

        return "PERSONAGGIO";
    }

    /*
     * Determina il potenziamento del personaggio.
     */
    private String determinaPotenziamento(Personaggio personaggio) {
        if (personaggio instanceof Potenziabile) {
            Potenziabile potenziabile = (Potenziabile) personaggio;
            return potenziabile.descrizionePotenziamento();
        }

        return "Nessun potenziamento";
    }

    /*
     * Restituisce la categoria.
     */
    public String getCategoria() {
        return categoria;
    }

    /*
     * Restituisce il tipo.
     */
    public String getTipo() {
        return tipo;
    }

    /*
     * Restituisce il nome.
     */
    public String getNome() {
        return nome;
    }

    /*
     * Restituisce la vita.
     */
    public int getVita() {
        return vita;
    }

    /*
     * Restituisce la vita massima.
     */
    public int getVitaMassima() {
        return vitaMassima;
    }

    /*
     * Restituisce l'attacco base.
     */
    public int getAttaccoBase() {
        return attaccoBase;
    }

    /*
     * Restituisce la difesa base.
     */
    public int getDifesaBase() {
        return difesaBase;
    }

    /*
     * Restituisce l'attacco totale.
     */
    public int getAttaccoTotale() {
        return attaccoTotale;
    }

    /*
     * Restituisce la difesa totale.
     */
    public int getDifesaTotale() {
        return difesaTotale;
    }

    /*
     * Controlla se il personaggio è ancora vivo.
     */
    public boolean isVivo() {
        return vivo;
    }

    /*
     * Restituisce il potenziamento.
     */
    public String getPotenziamento() {
        return potenziamento;
    }
}