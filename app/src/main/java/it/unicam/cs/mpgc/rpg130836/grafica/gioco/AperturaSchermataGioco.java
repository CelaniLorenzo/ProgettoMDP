package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AperturaSchermataGioco {

    /*
     * Crea l'etichetta che invita il giocatore
     * a scegliere l'eroe da utilizzare.
     */
    public Label creaEtichettaSceltaEroe(SchermataGioco schermataGioco) {
        return schermataGioco.creaEtichettaSceltaEroe();
    }

    /*
     * Crea il contenitore con i pulsanti o elementi grafici
     * per scegliere uno tra gli eroi disponibili.
     */
    public HBox creaSceltaEroi(
            GestoreSchermataGioco gestoreSchermataGioco,
            List<Eroe> eroiDisponibili,
            Runnable selezionaEroe1,
            Runnable selezionaEroe2,
            Runnable selezionaEroe3
    ) {
        return gestoreSchermataGioco.creaSceltaEroi(
                eroiDisponibili.get(0),
                eroiDisponibili.get(1),
                eroiDisponibili.get(2),
                selezionaEroe1,
                selezionaEroe2,
                selezionaEroe3
        );
    }

    /*
     * Crea gli oggetti necessari alla gestione del combattimento,
     * cioè il gestore del combattimento, il gestore dei potenziamenti
     * e il combattimento vero e proprio.
     */
    public DatiCombattimentoGioco creaDatiCombattimentoGioco(
            SchermataGioco schermataGioco,
            Button attaccoSquadraButton,
            Button potenziaButton,
            Dado dado
    ) {
        GestoreCombattimentoGioco gestoreCombattimentoGioco =
                new GestoreCombattimentoGioco(
                        schermataGioco,
                        attaccoSquadraButton,
                        potenziaButton
                );

        GestorePotenziamenti gestorePotenziamentiGioco =
                new GestorePotenziamenti(
                        new TextAreaOutput(schermataGioco.getLogArea())
                );

        Combattimento combattimento =
                new Combattimento(
                        dado,
                        new TextAreaOutput(schermataGioco.getLogArea())
                );

        return new DatiCombattimentoGioco(
                gestoreCombattimentoGioco,
                gestorePotenziamentiGioco,
                combattimento
        );
    }

    /*
     * Crea i personaggi della partita,
     * cioè gli eroi disponibili e i nemici mescolati.
     */
    public DatiPersonaggiGioco creaPersonaggi(
            ControllerGioco controllerGioco
    ) {
        return new DatiPersonaggiGioco(
                controllerGioco.creaEroi(),
                controllerGioco.creaNemiciMescolati()
        );
    }

    /*
     * Crea i pulsanti principali del combattimento,
     * cioè il pulsante per l'attacco di squadra
     * e quello per il potenziamento.
     */
    public PulsantiCombattimentoGioco creaPulsantiCombattimento(
            GestoreSchermataGioco gestoreSchermataGioco,
            Runnable azioneAttaccoSquadra,
            Runnable azionePotenzia
    ) {
        return new PulsantiCombattimentoGioco(
                gestoreSchermataGioco.creaAttaccoSquadra(azioneAttaccoSquadra),
                gestoreSchermataGioco.creaPotenzia(azionePotenzia)
        );
    }

    /*
     * Collega le azioni ai pulsanti della schermata di gioco,
     * come attaccare, salvare, aprire la mappa,
     * passare al prossimo incontro o tornare al menu.
     */
    public void configuraAzioniPulsanti(
            Stage stage,
            GestoreSchermataGioco gestoreSchermataGioco,
            Runnable azioneProssimoIncontro,
            Runnable azioneAttacca,
            Runnable azioneSalva,
            Runnable azioneApriMappa,
            Runnable azioneTornaMenu
    ) {
        gestoreSchermataGioco.configuraPulsantiSchermataGioco(
                stage,
                azioneProssimoIncontro,
                azioneAttacca,
                azioneSalva,
                azioneApriMappa,
                azioneTornaMenu
        );
    }

    /*
     * Configura il layout della schermata di gioco,
     * inserendo la scelta degli eroi e i pulsanti speciali
     * del combattimento.
     */
    public void configuraLayout(
            GestoreSchermataGioco gestoreSchermataGioco,
            Label scegliEroeLabel,
            HBox sceltaEroi,
            Button attaccoSquadraButton,
            Button potenziaButton
    ) {
        gestoreSchermataGioco.configuraLayoutGioco(
                scegliEroeLabel,
                sceltaEroi,
                attaccoSquadraButton,
                potenziaButton
        );
    }
}