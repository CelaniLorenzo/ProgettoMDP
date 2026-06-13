package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.GestorePersonaggi;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import it.unicam.cs.mpgc.rpg130836.model.stato.DatiResetPartita;
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
    public DatiResetPartita resettaPartita() {
        resettaMovimentoEroe();

        return new DatiResetPartita(
                creaEroiReset(),
                creaNemiciReset()
        );
    }
}