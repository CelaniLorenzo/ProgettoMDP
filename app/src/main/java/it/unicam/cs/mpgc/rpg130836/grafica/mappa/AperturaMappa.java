package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.grafica.gioco.TextAreaOutput;
import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AperturaMappa {

    public DatiAperturaMappa creaDatiAperturaMappa(
            Stage stage,
            CreatoreElementiBaseMappa creatoreElementiBaseMappa,
            ControllerMappa controllerMappa,
            GestoreNemiciMappa gestoreNemiciMappa,
            String percorsoMappa,
            Runnable azioneTornaMenu,
            Runnable azioneMappaIndietro,
            Runnable azioneMappaAvanti
    ) {
        ElementiBaseMappa elementiBaseMappa =
                creatoreElementiBaseMappa.crea(
                        stage,
                        percorsoMappa,
                        () -> {
                            controllerMappa.fermaMovimentoNemici(gestoreNemiciMappa);
                            azioneTornaMenu.run();
                        }
                );

        PulsantiNavigazioneMappa pulsantiNavigazioneMappa =
                controllerMappa.creaPulsantiNavigazioneMappa(
                        stage,
                        azioneMappaIndietro,
                        azioneMappaAvanti
                );

        return new DatiAperturaMappa(
                elementiBaseMappa.getMappaPane(),
                elementiBaseMappa.getSfondoMappa(),
                elementiBaseMappa.getIstruzioni(),
                elementiBaseMappa.getTornaGioco(),
                pulsantiNavigazioneMappa.getMappaIndietro(),
                pulsantiNavigazioneMappa.getMappaAvanti()
        );
    }
    public void aggiungiElementiBaseAllaMappa(
            GestoreElementiMappa gestoreElementiMappa,
            DatiAperturaMappa datiAperturaMappa,
            TextArea logMappa,
            Button combattiMappaButton,
            Button attaccoSquadraMappaButton,
            Button potenziaMappaButton
    ) {
        gestoreElementiMappa.aggiungiElementiBaseAllaMappa(
                datiAperturaMappa.getMappaPane(),
                datiAperturaMappa.getSfondoMappa(),
                datiAperturaMappa.getIstruzioni(),
                datiAperturaMappa.getTornaGioco(),
                datiAperturaMappa.getMappaIndietro(),
                datiAperturaMappa.getMappaAvanti(),
                logMappa,
                combattiMappaButton,
                attaccoSquadraMappaButton,
                potenziaMappaButton
        );
    }
    public DatiPreparazioneCombattimentoMappa preparaCombattimentoMappa(
            GestoreInterfacciaMappa gestoreInterfacciaMappa,
            GestorePulsantiMappa gestorePulsantiMappa,
            Dado dado,
            Runnable azioneCombatti,
            Runnable azioneAttaccoSquadra,
            Runnable azionePotenzia
    ) {
        TextArea logMappa = gestoreInterfacciaMappa.creaLogMappa();

        GestorePotenziamenti gestorePotenziamentiMappa =
                new GestorePotenziamenti(new TextAreaOutput(logMappa));

        GestoreCombattimentoMappa gestoreCombattimentoMappa =
                new GestoreCombattimentoMappa(dado);

        Button combattiMappaButton =
                gestorePulsantiMappa.creaPulsanteCombatti(azioneCombatti);

        Button attaccoSquadraMappaButton =
                gestorePulsantiMappa.creaPulsanteAttaccoSquadraMappa(azioneAttaccoSquadra);

        Button potenziaMappaButton =
                gestorePulsantiMappa.creaPulsantePotenziaMappa(azionePotenzia);

        return new DatiPreparazioneCombattimentoMappa(
                logMappa,
                gestorePotenziamentiMappa,
                gestoreCombattimentoMappa,
                combattiMappaButton,
                attaccoSquadraMappaButton,
                potenziaMappaButton
        );
    }
    public void aggiungiPersonaggiAllaMappa(
            ControllerMappa controllerMappa,
            Pane mappaPane,
            Runnable azioneAggiungiEroi,
            Runnable azioneAggiungiNemici
    ) {
        controllerMappa.aggiungiPersonaggiAllaMappa(
                mappaPane,
                azioneAggiungiEroi,
                azioneAggiungiNemici
        );
    }
    public DatiSelezioneEroeApplicazione selezionaEroeDaMappa(
            ControllerMappa controllerMappa,
            Eroe eroe,
            javafx.scene.image.ImageView eroeView,
            GestoreMovimentoEroe gestoreMovimentoEroe,
            java.util.List<javafx.scene.image.ImageView> eroiMappa,
            javafx.scene.control.TextArea logMappa
    ) {
        DatiSelezioneEroeMappa dati =
                controllerMappa.selezionaEroeDaMappa(
                        eroe,
                        eroeView,
                        gestoreMovimentoEroe,
                        eroiMappa,
                        logMappa
                );

        return new DatiSelezioneEroeApplicazione(
                dati.getEroe(),
                dati.getEroeView(),
                dati.getNome(),
                dati.getVita(),
                dati.getVitaMassima(),
                dati.getDanno()
        );
    }



}