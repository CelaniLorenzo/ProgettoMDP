package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.GestorePersonaggi;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import it.unicam.cs.mpgc.rpg130836.model.stato.StatoPartitaCorrente;
import java.util.List;

public class ControllerGioco {

    private final GestorePersonaggi gestorePersonaggi;

    public ControllerGioco(GestorePersonaggi gestorePersonaggi) {
        this.gestorePersonaggi = gestorePersonaggi;
    }

    public List<Eroe> creaEroi() {
        return gestorePersonaggi.creaEroi();
    }

    public List<Nemico> creaNemiciMescolati() {
        return gestorePersonaggi.creaNemiciMescolati();
    }
    public boolean puoUsareAttaccoSquadra(
            StatoPartitaCorrente stato,
            SchermataGioco schermataGioco
    ) {
        if (!stato.isEroeSelezionato() || stato.getEroeAttuale() == null) {
            schermataGioco.getLogArea().appendText("Devi prima scegliere un eroe.\n");
            return false;
        }

        if (stato.getNemicoAttuale() == null || !stato.getNemicoAttuale().isVivo()) {
            schermataGioco.getLogArea().appendText("Non c'è nessun nemico da attaccare.\n");
            return false;
        }

        if (stato.isAttaccoSquadraUsato()) {
            schermataGioco.getLogArea().appendText("Hai già usato l'attacco di squadra in questo incontro.\n");
            return false;
        }

        return true;
    }
    public void eseguiAttacchiSquadra(
            List<Eroe> eroiDisponibili,
            StatoPartitaCorrente stato,
            SchermataGioco schermataGioco
    ) {
        schermataGioco.getLogArea().appendText("ATTACCO DI SQUADRA!\n");

        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo() && stato.getNemicoAttuale().isVivo()) {
                int danno = eroe.attacca(stato.getNemicoAttuale());

                schermataGioco.getLogArea().appendText(
                        eroe.getNome() + " attacca " +
                                stato.getNemicoAttuale().getNome() +
                                " causando " + danno + " danni.\n"
                );
            }
        }
    }
    public void aggiornaViteDopoAttaccoSquadra(
            StatoPartitaCorrente stato,
            SchermataGioco schermataGioco
    ) {
        int vitaGiocatoreAttuale = stato.getEroeAttuale().getVita();
        int vitaGiocatoreMassima = stato.getEroeAttuale().getVitaMassima();

        int vitaNemicoAttuale = stato.getNemicoAttuale().getVita();
        int vitaNemicoMassima = stato.getNemicoAttuale().getVitaMassima();

        schermataGioco.getVitaGiocatoreLabel()
                .setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);

        schermataGioco.getVitaNemicoLabel()
                .setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);
    }
    public boolean eseguiAttaccoSquadra(
            List<Eroe> eroiDisponibili,
            StatoPartitaCorrente stato,
            SchermataGioco schermataGioco,
            GestoreCombattimentoGioco gestoreCombattimentoGioco
    ) {
        if (!puoUsareAttaccoSquadra(stato, schermataGioco)) {
            return false;
        }

        stato.setAttaccoSquadraUsato(true);
        gestoreCombattimentoGioco.disabilitaAttaccoSquadra();

        eseguiAttacchiSquadra(
                eroiDisponibili,
                stato,
                schermataGioco
        );

        aggiornaViteDopoAttaccoSquadra(
                stato,
                schermataGioco
        );

        return !stato.getNemicoAttuale().isVivo();
    }
    public boolean gestisciAttacco(
            StatoPartitaCorrente stato,
            Combattimento combattimento,
            GestoreCombattimentoGioco gestoreCombattimentoGioco
    ) {
        if (!gestoreCombattimentoGioco.attaccoValido(
                stato.getEroeAttuale(),
                stato.isEroeSelezionato(),
                stato.getNemicoAttuale()
        )) {
            return false;
        }

        combattimento.eseguiTurnoSingolo(
                stato.getEroeAttuale(),
                stato.getNemicoAttuale()
        );

        gestoreCombattimentoGioco.aggiornaViteDopoAttacco(
                stato.getEroeAttuale(),
                stato.getNemicoAttuale()
        );

        if (!stato.getEroeAttuale().isVivo()) {
            gestoreCombattimentoGioco.scriviMessaggioSconfittaEroe();
        }

        return !stato.getNemicoAttuale().isVivo();
    }
    public void gestisciNemicoSconfitto(
            StatoPartitaCorrente stato,
            GestoreCombattimentoGioco gestoreCombattimentoGioco
    ) {
        if (gestoreCombattimentoGioco.gestisciNemicoSconfittoConPotenziamento(
                stato.getNemicoAttuale(),
                stato.getEroeAttuale(),
                stato.isPotenziamentoDisponibile()
        )) {
            stato.setPotenziamentoDisponibile(true);
            stato.setEroeDaPotenziare(stato.getEroeAttuale());
        }
    }
    public void potenziaVincitore(
            StatoPartitaCorrente stato,
            GestoreCombattimentoGioco gestoreCombattimentoGioco,
            GestorePotenziamenti gestorePotenziamentiGioco
    ) {
        if (!gestoreCombattimentoGioco.puoPotenziare(
                stato.isPotenziamentoDisponibile(),
                stato.getEroeDaPotenziare()
        )) {
            return;
        }

        gestorePotenziamentiGioco.potenziaVincitore(
                stato.getEroeDaPotenziare()
        );

        gestoreCombattimentoGioco.aggiornaEroeDopoPotenziamento(
                stato.getEroeAttuale(),
                stato.getEroeDaPotenziare(),
                () -> gestoreCombattimentoGioco.aggiornaInterfacciaEroe(
                        stato.getEroeAttuale().getVita(),
                        stato.getEroeAttuale().getVitaMassima()
                )
        );

        stato.setPotenziamentoDisponibile(false);
        stato.setEroeDaPotenziare(null);

        gestoreCombattimentoGioco.terminaPotenziamento();
    }
    public void aggiornaSchermataDopoSelezioneEroe(
            StatoPartitaCorrente stato,
            SchermataGioco schermataGioco
    ) {
        schermataGioco.getNomeGiocatoreLabel()
                .setText("Giocatore: " + stato.getNomeGiocatoreAttuale());

        schermataGioco.getVitaGiocatoreLabel()
                .setText(
                        "Vita: " +
                                stato.getVitaGiocatoreAttuale() +
                                " / " +
                                stato.getVitaGiocatoreMassima()
                );

        schermataGioco.getLogArea()
                .appendText("Hai scelto " + stato.getNomeGiocatoreAttuale() + ".\n");
    }

    public Nemico avviaNuovoIncontro(
            StatoPartitaCorrente stato,
            GestoreCombattimentoGioco gestoreCombattimentoGioco,
            List<Nemico> nemiciDisponibili
    ) {
        int nuovoNumeroIncontro = stato.getNumeroIncontro() + 1;
        stato.setNumeroIncontro(nuovoNumeroIncontro);

        Nemico nemicoAttuale =
                gestoreCombattimentoGioco.preparaNuovoIncontro(
                        nuovoNumeroIncontro,
                        nemiciDisponibili
                );

        stato.aggiornaNemicoAttuale(nemicoAttuale);
        stato.setAttaccoSquadraUsato(false);
        stato.setPotenziamentoDisponibile(false);
        stato.setEroeDaPotenziare(null);

        return nemicoAttuale;
    }

}