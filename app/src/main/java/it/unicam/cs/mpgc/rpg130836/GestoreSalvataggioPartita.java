package it.unicam.cs.mpgc.rpg130836;

import java.io.IOException;
import java.util.List;

public class GestoreSalvataggioPartita {

    private final SalvataggioJson salvataggioJson;
    private final String fileSalvataggio;

    public GestoreSalvataggioPartita(SalvataggioJson salvataggioJson, String fileSalvataggio) {
        this.salvataggioJson = salvataggioJson;
        this.fileSalvataggio = fileSalvataggio;
    }

    public void salva(StatoGioco statoGioco) throws IOException {
        salvataggioJson.salva(statoGioco, fileSalvataggio);
    }

    public StatoGioco carica() throws IOException {
        return salvataggioJson.carica(fileSalvataggio);
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
    public void aggiornaSchermataDopoCaricamento(
            SchermataGioco schermataGioco,
            String nomeGiocatoreAttuale,
            int vitaGiocatoreAttuale,
            int vitaGiocatoreMassima,
            String nomeNemicoAttuale,
            int vitaNemicoAttuale,
            int vitaNemicoMassima
    ) {
        schermataGioco.getNomeGiocatoreLabel()
                .setText("Giocatore: " + nomeGiocatoreAttuale);

        schermataGioco.getVitaGiocatoreLabel()
                .setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);

        if (vitaNemicoAttuale > 0) {
            schermataGioco.getNomeNemicoLabel()
                    .setText("Nemico: " + nomeNemicoAttuale);

            schermataGioco.getVitaNemicoLabel()
                    .setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);
        } else {
            schermataGioco.getNomeNemicoLabel()
                    .setText("Nemico: nessuno");

            schermataGioco.getVitaNemicoLabel()
                    .setText("Vita: -");
        }
    }
}