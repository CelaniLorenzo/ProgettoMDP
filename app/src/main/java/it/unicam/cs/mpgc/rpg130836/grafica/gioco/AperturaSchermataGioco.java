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

    public Label creaEtichettaSceltaEroe(SchermataGioco schermataGioco) {
        return schermataGioco.creaEtichettaSceltaEroe();
    }

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
    public DatiPersonaggiGioco creaPersonaggi(
            ControllerGioco controllerGioco
    ) {
        return new DatiPersonaggiGioco(
                controllerGioco.creaEroi(),
                controllerGioco.creaNemiciMescolati()
        );
    }
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