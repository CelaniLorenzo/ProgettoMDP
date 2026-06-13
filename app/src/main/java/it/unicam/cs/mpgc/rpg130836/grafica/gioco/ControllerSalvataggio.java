package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import it.unicam.cs.mpgc.rpg130836.model.stato.StatoGioco;
import it.unicam.cs.mpgc.rpg130836.persistenza.GestoreSalvataggioPartita;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ControllerSalvataggio {

    private final GestoreSalvataggioPartita gestoreSalvataggioPartita;
    private final String fileSalvataggio;

    public ControllerSalvataggio(
            GestoreSalvataggioPartita gestoreSalvataggioPartita,
            String fileSalvataggio
    ) {
        this.gestoreSalvataggioPartita = gestoreSalvataggioPartita;
        this.fileSalvataggio = fileSalvataggio;
    }

    public void salva(
            StatoGioco statoGioco,
            TextArea logArea
    ) {
        try {
            gestoreSalvataggioPartita.salva(statoGioco);

            logArea.appendText(
                    "Partita salvata in " +
                            fileSalvataggio +
                            ".\n"
            );

        } catch (IOException ex) {
            logArea.appendText(
                    "Errore durante il salvataggio della partita.\n"
            );

            ex.printStackTrace();
        }
    }
    public void salvaPartita(
            int numeroIncontro,
            boolean eroeSelezionato,
            boolean attaccoSquadraUsato,
            String nomeGiocatoreAttuale,
            int vitaGiocatoreAttuale,
            int vitaGiocatoreMassima,
            int dannoGiocatore,
            String nomeNemicoAttuale,
            int vitaNemicoAttuale,
            int vitaNemicoMassima,
            TextArea logArea
    ) {
        StatoGioco statoGioco = StatoGioco.creaDaGui(
                numeroIncontro,
                eroeSelezionato,
                attaccoSquadraUsato,
                nomeGiocatoreAttuale,
                vitaGiocatoreAttuale,
                vitaGiocatoreMassima,
                dannoGiocatore,
                nomeNemicoAttuale,
                vitaNemicoAttuale,
                vitaNemicoMassima
        );

        salva(statoGioco, logArea);
    }
    public StatoGioco carica(TextArea logArea) {
        try {
            StatoGioco statoGioco = gestoreSalvataggioPartita.carica();

            logArea.appendText(
                    "Partita caricata da " + fileSalvataggio + ".\n"
            );

            return statoGioco;

        } catch (IOException ex) {
            logArea.appendText(
                    "Nessuna partita salvata trovata o errore durante il caricamento.\n"
            );

            ex.printStackTrace();
            return null;
        }
    }
    public void applicaStatoCaricato(
            StatoGioco statoGioco,
            SchermataGioco schermataGioco,
            List<Eroe> eroiDisponibili,
            List<Nemico> nemiciDisponibili,
            Button attaccoSquadraButton
    ) {
        Eroe eroeAttuale =
                gestoreSalvataggioPartita.trovaEroePerNome(
                        statoGioco.getNomeGiocatoreAttuale(),
                        eroiDisponibili
                );

        Nemico nemicoAttuale =
                gestoreSalvataggioPartita.trovaNemicoPerNome(
                        statoGioco.getNomeNemicoAttuale(),
                        nemiciDisponibili
                );

        gestoreSalvataggioPartita.applicaVitaCaricata(
                eroeAttuale,
                statoGioco.getVitaGiocatoreAttuale()
        );

        gestoreSalvataggioPartita.applicaVitaCaricata(
                nemicoAttuale,
                statoGioco.getVitaNemicoAttuale()
        );

        aggiornaSchermataDopoCaricamento(
                schermataGioco,
                statoGioco.getNomeGiocatoreAttuale(),
                statoGioco.getVitaGiocatoreAttuale(),
                statoGioco.getVitaGiocatoreMassima(),
                statoGioco.getNomeNemicoAttuale(),
                statoGioco.getVitaNemicoAttuale(),
                statoGioco.getVitaNemicoMassima()
        );

        if (attaccoSquadraButton != null) {
            attaccoSquadraButton.setDisable(
                    statoGioco.isAttaccoSquadraUsato()
            );
        }
    }

    private void aggiornaSchermataDopoCaricamento(
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
