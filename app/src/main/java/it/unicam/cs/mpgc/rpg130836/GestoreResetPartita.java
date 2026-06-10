package it.unicam.cs.mpgc.rpg130836;

import java.util.List;

public class GestoreResetPartita {

    private final GestorePersonaggi gestorePersonaggi;
    private final GestoreMovimentoEroe gestoreMovimentoEroe;

    public GestoreResetPartita(GestorePersonaggi gestorePersonaggi,
                               GestoreMovimentoEroe gestoreMovimentoEroe) {
        this.gestorePersonaggi = gestorePersonaggi;
        this.gestoreMovimentoEroe = gestoreMovimentoEroe;
    }

    public List<Eroe> creaEroiReset() {
        return gestorePersonaggi.creaEroi();
    }

    public List<Nemico> creaNemiciReset() {
        return gestorePersonaggi.creaNemiciMescolati();
    }

    public void resettaMovimentoEroe() {
        gestoreMovimentoEroe.impostaPosizione(380, 280);
    }
}