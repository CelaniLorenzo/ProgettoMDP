package it.unicam.cs.mpgc.rpg130836.persistenza;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Combattente;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import it.unicam.cs.mpgc.rpg130836.model.stato.ArchivioStatoGioco;
import it.unicam.cs.mpgc.rpg130836.model.stato.StatoGioco;
import java.io.IOException;
import java.util.List;

public class GestoreSalvataggioPartita {

    private final ArchivioStatoGioco archivioStatoGioco;
    private final String fileSalvataggio;

    public GestoreSalvataggioPartita(
            ArchivioStatoGioco archivioStatoGioco,
            String fileSalvataggio
    ) {
        this.archivioStatoGioco = archivioStatoGioco;
        this.fileSalvataggio = fileSalvataggio;
    }

    public void salva(StatoGioco statoGioco) throws IOException {
        archivioStatoGioco.salva(statoGioco, fileSalvataggio);
    }

    public StatoGioco carica() throws IOException {
        return archivioStatoGioco.carica(fileSalvataggio);
    }

    public Eroe trovaEroePerNome(String nome, List<Eroe> eroi) {
        for (Eroe eroe : eroi) {
            if (eroe.getNome().equals(nome)) {
                return eroe;
            }
        }
        return null;
    }

    public Nemico trovaNemicoPerNome(String nome, List<Nemico> nemici) {
        for (Nemico nemico : nemici) {
            if (nemico.getNome().equals(nome)) {
                return nemico;
            }
        }
        return null;
    }

    public void applicaVitaCaricata(Combattente combattente, int vitaCaricata) {
        if (combattente == null) {
            return;
        }

        int dannoDaApplicare = combattente.getVita() - vitaCaricata;

        if (dannoDaApplicare > 0) {
            combattente.riceviDanno(dannoDaApplicare);
        }
    }
}