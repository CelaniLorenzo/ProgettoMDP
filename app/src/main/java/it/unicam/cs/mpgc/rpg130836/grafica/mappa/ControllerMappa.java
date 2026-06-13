package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.function.Supplier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerMappa {

    private final GestorePulsantiMappa gestorePulsantiMappa;

    public ControllerMappa(
            GestorePulsantiMappa gestorePulsantiMappa
    ) {
        this.gestorePulsantiMappa = gestorePulsantiMappa;
    }

    public PulsantiNavigazioneMappa creaPulsantiNavigazioneMappa(
            Stage stage,
            Runnable azioneIndietro,
            Runnable azioneAvanti
    ) {
        return new PulsantiNavigazioneMappa(
                gestorePulsantiMappa.creaPulsanteMappaIndietro(
                        azioneIndietro
                ),
                gestorePulsantiMappa.creaPulsanteMappaAvanti(
                        azioneAvanti
                )
        );
    }
    public void aggiungiPersonaggiAllaMappa(
            Pane mappaPane,
            Runnable aggiungiEroi,
            Runnable aggiungiNemici
    ) {
        aggiungiEroi.run();
        aggiungiNemici.run();
    }
    public void mostraSceneMappa(
            Stage stage,
            Pane mappaPane,
            Scene scene
    ) {
        stage.setScene(scene);
        Platform.runLater(() -> mappaPane.requestFocus());
    }
    public GestoreIncontriMappa creaGestoreIncontriMappa(
            GestoreInizializzazioneMappa gestoreInizializzazioneMappa
    ) {
        return gestoreInizializzazioneMappa.creaGestoreIncontriMappa();
    }

    public GestoreMovimentoNemiciMappa creaGestoreMovimentoNemiciMappa(
            GestoreInizializzazioneMappa gestoreInizializzazioneMappa,
            java.util.List<javafx.scene.image.ImageView> nemiciMappa,
            java.util.List<Nemico> nemiciAssociatiMappa
    ) {
        return gestoreInizializzazioneMappa.creaGestoreMovimentoNemiciMappa(
                nemiciMappa,
                nemiciAssociatiMappa
        );
    }
    public Scene creaSceneMappa(
            Pane mappaPane,
            Supplier<ImageView> eroeMappaSupplier,
            Supplier<TextArea> logMappaSupplier,
            GestoreMovimentoEroe gestoreMovimentoEroe,
            Runnable controlloCollisione
    ) {
        Scene scene = new Scene(mappaPane, 800, 600);
        Set<KeyCode> tastiPremuti = new HashSet<>();

        AnimationTimer movimentoEroe = new AnimationTimer() {

            private long ultimoFrame;

            @Override
            public void handle(long now) {
                if (tastiPremuti.isEmpty()) {
                    ultimoFrame = 0;
                    stop();
                    return;
                }

                ImageView eroeMappa = eroeMappaSupplier.get();

                if (eroeMappa == null) {
                    return;
                }

                double deltaSecondi = calcolaDeltaSecondi(now);

                gestoreMovimentoEroe.muovi(tastiPremuti, deltaSecondi);

                eroeMappa.setLayoutX(
                        gestoreMovimentoEroe.getEroeX()
                );

                eroeMappa.setLayoutY(
                        gestoreMovimentoEroe.getEroeY()
                );

                controlloCollisione.run();
            }

            private double calcolaDeltaSecondi(long now) {
                if (ultimoFrame == 0) {
                    ultimoFrame = now;
                    return 1.0 / 60.0;
                }

                double deltaSecondi =
                        (now - ultimoFrame) / 1_000_000_000.0;

                ultimoFrame = now;
                return Math.min(deltaSecondi, 0.033);
            }
        };

        scene.setOnKeyPressed(e -> {
            if (eroeMappaSupplier.get() == null) {
                TextArea logMappa = logMappaSupplier.get();

                if (logMappa != null) {
                    logMappa.appendText(
                            "Devi prima selezionare un eroe sulla mappa.\n"
                    );
                }

                return;
            }

            tastiPremuti.add(e.getCode());
            movimentoEroe.start();
        });

        scene.setOnKeyReleased(e -> {
            tastiPremuti.remove(e.getCode());
        });

        return scene;
    }
    public void gestisciCollisioneConNemico(
            Nemico nemicoToccato,
            Runnable avvioIncontro
    ) {
        if (nemicoToccato != null) {
            avvioIncontro.run();
        }
    }
    public void controllaCollisioneNemici(
            GestoreCollisioniMappa gestoreCollisioniMappa,
            ImageView eroeMappa,
            List<ImageView> nemiciMappa,
            List<Nemico> nemiciAssociatiMappa,
            java.util.function.Consumer<Nemico> gestioneCollisione
    ) {
        Nemico nemicoToccato =
                gestoreCollisioniMappa.trovaNemicoInCollisione(
                        eroeMappa,
                        nemiciMappa,
                        nemiciAssociatiMappa
                );

        gestioneCollisione.accept(nemicoToccato);
    }
    public void fermaMovimentoNemici(GestoreNemiciMappa gestoreNemiciMappa) {
        gestoreNemiciMappa.fermaMovimentoNemici();
    }
    public void avviaMovimentoNemici(
            GestoreNemiciMappa gestoreNemiciMappa,
            GestoreMovimentoNemiciMappa gestoreMovimentoNemiciMappa,
            Runnable controlloCollisione
    ) {
        gestoreNemiciMappa.avviaMovimentoNemici(deltaSecondi -> {
            gestoreMovimentoNemiciMappa.muoviNemici(deltaSecondi);
            controlloCollisione.run();
        });
    }
    public void aggiungiEroiAllaMappa(
            Pane mappaPane,
            GestoreEroiMappa gestoreEroiMappa,
            List<Eroe> eroiDisponibili,
            List<ImageView> eroiMappa,
            List<Eroe> eroiAssociatiMappa,
            SelezioneEroeMappa selezioneEroe
    ) {
        gestoreEroiMappa.aggiungiEroiAllaMappa(
                mappaPane,
                eroiDisponibili,
                eroiMappa,
                eroiAssociatiMappa,
                selezioneEroe
        );
    }
    public void aggiungiNemiciAllaMappa(
            Pane mappaPane,
            GestoreNemiciVisualiMappa gestoreNemiciVisualiMappa,
            List<Nemico> nemiciDisponibili,
            List<ImageView> nemiciMappa,
            List<Label> nomiNemiciMappa,
            List<Nemico> nemiciAssociatiMappa,
            SelezioneNemicoMappa avvioIncontro
    ) {
        gestoreNemiciVisualiMappa.aggiungiNemiciAllaMappa(
                mappaPane,
                nemiciDisponibili,
                nemiciMappa,
                nomiNemiciMappa,
                nemiciAssociatiMappa,
                avvioIncontro
        );
    }
    public DatiSelezioneEroeMappa selezionaEroeDaMappa(
            Eroe eroe,
            ImageView eroeView,
            GestoreMovimentoEroe gestoreMovimentoEroe,
            List<ImageView> eroiMappa,
            TextArea logMappa
    ) {
        gestoreMovimentoEroe.impostaPosizione(
                eroeView.getLayoutX(),
                eroeView.getLayoutY()
        );

        for (ImageView view : eroiMappa) {
            view.setStyle("");
        }

        eroeView.setStyle("-fx-effect: dropshadow(gaussian, yellow, 18, 0.8, 0, 0);");

        if (logMappa != null) {
            logMappa.appendText("Hai selezionato " + eroe.getNome() + ".\n");
        }

        return new DatiSelezioneEroeMappa(eroe, eroeView);
    }
}
