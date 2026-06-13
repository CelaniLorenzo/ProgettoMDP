package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import javafx.scene.image.ImageView;

public class DatiSelezioneEroeMappa {

    private final Eroe eroe;
    private final ImageView eroeView;
    private final String nome;
    private final int vita;
    private final int vitaMassima;
    private final int danno;

    public DatiSelezioneEroeMappa(Eroe eroe, ImageView eroeView) {
        this.eroe = eroe;
        this.eroeView = eroeView;
        this.nome = eroe.getNome();
        this.vita = eroe.getVita();
        this.vitaMassima = eroe.getVitaMassima();
        this.danno = eroe.calcolaAttacco();
    }

    public Eroe getEroe() { return eroe; }
    public ImageView getEroeView() { return eroeView; }
    public String getNome() { return nome; }
    public int getVita() { return vita; }
    public int getVitaMassima() { return vitaMassima; }
    public int getDanno() { return danno; }
}