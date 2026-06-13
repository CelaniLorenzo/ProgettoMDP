package it.unicam.cs.mpgc.rpg130836.grafica.Avvio;

public interface NavigazioneApplicazione extends
        AvvioPartita,
        CaricamentoPartita,
        AccessoSchermataGioco {

    /*
     * Mostra il menu principale dell'applicazione.
     */
    void mostraMenu();
}