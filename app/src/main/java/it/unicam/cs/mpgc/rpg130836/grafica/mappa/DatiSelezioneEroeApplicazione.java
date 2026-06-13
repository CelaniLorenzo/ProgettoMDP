package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;

public class DatiSelezioneEroeApplicazione {

    private final Eroe eroe;
    private final javafx.scene.image.ImageView eroeView;
    private final String nome;
    private final int vita;
    private final int vitaMassima;
    private final int danno;

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

    public Eroe getEroe() {
        return eroe;
    }

    public javafx.scene.image.ImageView getEroeView() {
        return eroeView;
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

    public int getDanno() {
        return danno;
    }
}