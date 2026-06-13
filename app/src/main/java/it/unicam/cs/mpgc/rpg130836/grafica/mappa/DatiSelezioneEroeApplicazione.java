package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;

public class DatiSelezioneEroeApplicazione {

    private final Eroe eroe;
    private final javafx.scene.image.ImageView eroeView;
    private final String nome;
    private final int vita;
    private final int vitaMassima;
    private final int danno;

    /*
     * Costruisce un'istanza di DatiSelezioneEroeApplicazione.
     */
    public DatiSelezioneEroeApplicazione(
            Eroe eroe,
            javafx.scene.image.ImageView eroeView,
            String nome,
            int vita,
            int vitaMassima,
            int danno
    ) {
        this.eroe = eroe;
        this.eroeView = eroeView;
        this.nome = nome;
        this.vita = vita;
        this.vitaMassima = vitaMassima;
        this.danno = danno;
    }

    /*
     * Restituisce l'eroe.
     */
    public Eroe getEroe() {
        return eroe;
    }

    public javafx.scene.image.ImageView getEroeView() {
        return eroeView;
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
     * Restituisce il danno.
     */
    public int getDanno() {
        return danno;
    }
}