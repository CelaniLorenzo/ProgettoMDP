package it.unicam.cs.mpgc.rpg130836;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class App extends Application {

    private final GestoreCollisioniMappa gestoreCollisioniMappa =
            new GestoreCollisioniMappa();
    private GestorePotenziamenti gestorePotenziamentiMappa;
    private Button attaccoSquadraButton;
    private Button potenziaButton;
    private boolean potenziamentoDisponibile = false;
    private Eroe eroeDaPotenziare;
    private int numeroIncontro = 0;
    private String nomeNemicoAttuale = "";
    private int vitaNemicoAttuale = 0;
    private int vitaNemicoMassima = 0;
    private Dado dado = new Dado();
    private Combattimento combattimento;
    private boolean giocatoreAttaccaPerPrimo = true;
    private int vitaGiocatoreAttuale = 100;
    private int vitaGiocatoreMassima = 100;
    private String nomeGiocatoreAttuale = "Nessun eroe";
    private int dannoGiocatore = 0;
    private boolean eroeSelezionato = false;
    private final CreatorePersonaggi creatorePersonaggi = new CreatorePersonaggi();
    private final SalvataggioJson salvataggioJson = new SalvataggioJson();
    private static final String FILE_SALVATAGGIO = "partita.json";
    private final RisorseImmagini risorseImmagini = new RisorseImmagini();
    private final GestoreNemiciMappa gestoreNemiciMappa = new GestoreNemiciMappa();
    private final GestoreAnimazioni gestoreAnimazioni = new GestoreAnimazioni();
    private final GestoreMovimentoEroe gestoreMovimentoEroe =
            new GestoreMovimentoEroe();
    private final SchermataMenu schermataMenu = new SchermataMenu();
    private final SchermataMappa schermataMappa = new SchermataMappa();
    private final SchermataGioco schermataGioco = new SchermataGioco();
    private final GestoreInterfacciaMappa gestoreInterfacciaMappa =
            new GestoreInterfacciaMappa();
    private final GestoreSchermataGioco gestoreSchermataGioco =
            new GestoreSchermataGioco(schermataGioco);
    private GestorePotenziamenti gestorePotenziamentiGioco;
    private GestoreCombattimentoGioco gestoreCombattimentoGioco;
    private GestoreIncontriMappa gestoreIncontriMappa;
    private List<Eroe> eroiDisponibili;
    private Eroe eroeAttuale;
    private List<Nemico> nemiciDisponibili;
    private Nemico nemicoAttuale;
    private boolean attaccoSquadraUsato = false;
    private ImageView eroeMappa;
    private GestoreMovimentoNemiciMappa gestoreMovimentoNemiciMappa;
    private final DatiCombattimentoMappa datiCombattimentoMappa =
            new DatiCombattimentoMappa();
    private List<ImageView> eroiMappa = new ArrayList<>();
    private List<Eroe> eroiAssociatiMappa = new ArrayList<>();
    private static final double VELOCITA_EROE = 10;
    private List<ImageView> nemiciMappa = new ArrayList<>();
    private List<Label> nomiNemiciMappa = new ArrayList<>();
    private List<Nemico> nemiciAssociatiMappa = new ArrayList<>();
    private Pane mappaPaneAttuale;
    private final Random random = new Random();
    private TextArea logMappa;
    private TextArea logMappe;
    private boolean potenziamentoDiSquadra = false;
    private final GestorePulsantiMappa gestorePulsantiMappa =
            new GestorePulsantiMappa();
    private final GestoreEroiMappa gestoreEroiMappa =
            new GestoreEroiMappa(risorseImmagini, schermataMappa);
    private final GestoreNemiciVisualiMappa gestoreNemiciVisualiMappa =
            new GestoreNemiciVisualiMappa(risorseImmagini, schermataMappa);
    private boolean combattimentoMappaAttivo = false;
    private GestoreCombattimentoMappa gestoreCombattimentoMappa;
    private final GestoreSalvataggioPartita gestoreSalvataggioPartita =
            new GestoreSalvataggioPartita(salvataggioJson, FILE_SALVATAGGIO);
    private int indiceMappaAttuale = 0;
    private final GestorePersonaggi gestorePersonaggi =
            new GestorePersonaggi(creatorePersonaggi);
    private final GestoreResetPartita gestoreResetPartita =
            new GestoreResetPartita(gestorePersonaggi, gestoreMovimentoEroe);
    private final String[] mappe = {
            "/images/mappa.png",
            "/images/mappa_2.png",
            "/images/mappa_3.png"
    };

    private Button combattiMappaButton;
    private Button attaccoSquadraMappaButton;
    private Button potenziaMappaButton;

    @Override
    public void start(Stage stage) {

        schermataMenu.mostra(
                stage,
                () -> {
                    resettaPartita();
                    mostraMappaGioco(stage);
                },
                () -> {
                    mostraSchermataGioco(stage);
                    caricaPartitaDaFile();
                },
                stage::close
        );
    }

    private void mostraSchermataGioco(Stage stage) {
        schermataGioco.mostra(stage, nomeGiocatoreAttuale);
        Label scegliEroeLabel = schermataGioco.creaEtichettaSceltaEroe();
        inizializzaPersonaggi();
        HBox sceltaEroi = creaSceltaEroi();

        creaPulsantiCombattimentoGioco();
        gestoreCombattimentoGioco = new GestoreCombattimentoGioco(
                schermataGioco,
                attaccoSquadraButton,
                potenziaButton
        );
        configuraPulsantiSchermataGioco(stage);
        gestorePotenziamentiGioco = new GestorePotenziamenti(new TextAreaOutput(schermataGioco.getLogArea()));
        combattimento = new Combattimento(dado, new TextAreaOutput(schermataGioco.getLogArea()));

        configuraLayoutGioco(
                scegliEroeLabel,
                sceltaEroi
        );
    }
    private void configuraLayoutGioco(
            Label scegliEroeLabel,
            HBox sceltaEroi
    ) {
        VBox root = schermataGioco.getRoot();

        root.getChildren().add(1, scegliEroeLabel);
        root.getChildren().add(2, sceltaEroi);

        root.getChildren().add(
                root.getChildren().indexOf(schermataGioco.getApriMappaButton()),
                attaccoSquadraButton
        );

        root.getChildren().add(
                root.getChildren().indexOf(schermataGioco.getApriMappaButton()),
                potenziaButton
        );
    }
    private void creaPulsantiCombattimentoGioco() {
        attaccoSquadraButton =
                gestoreSchermataGioco.creaAttaccoSquadraButton(this::eseguiAttaccoSquadra);

        potenziaButton =
                gestoreSchermataGioco.creaPotenziaButton(this::potenziaVincitore);
    }

    private void inizializzaPersonaggi() {
        eroiDisponibili = gestorePersonaggi.creaEroi();
        nemiciDisponibili = gestorePersonaggi.creaNemiciMescolati();
    }

    private void configuraPulsantiSchermataGioco(Stage stage) {
        schermataGioco.getProssimoIncontroButton()
                .setOnAction(e -> gestisciProssimoIncontro());

        schermataGioco.getAttaccaButton()
                .setOnAction(e -> gestisciAttacco());

        schermataGioco.getSalvaButton()
                .setOnAction(e -> salvaPartita());

        schermataGioco.getApriMappaButton()
                .setOnAction(e -> gestisciAperturaMappa(stage));

        schermataGioco.getTornaMenuButton()
                .setOnAction(e -> start(stage));
    }

    private HBox creaSceltaEroi() {
        return gestoreSchermataGioco.creaSceltaEroi(
                eroiDisponibili.get(0),
                eroiDisponibili.get(1),
                eroiDisponibili.get(2),
                () -> selezionaEroe(eroiDisponibili.get(0)),
                () -> selezionaEroe(eroiDisponibili.get(1)),
                () -> selezionaEroe(eroiDisponibili.get(2))
        );
    }
    private void gestisciProssimoIncontro() {
        gestoreSchermataGioco.gestisciProssimoIncontro(
                eroeSelezionato,
                vitaNemicoAttuale,
                numeroIncontro,
                nemiciDisponibili.size(),
                this::avviaNuovoIncontro
        );
    }
    private void avviaNuovoIncontro() {
        numeroIncontro++;

        nemicoAttuale = gestoreCombattimentoGioco.preparaNuovoIncontro(
                numeroIncontro,
                nemiciDisponibili
        );

        attaccoSquadraUsato = false;
        potenziamentoDisponibile = false;
        eroeDaPotenziare = null;

        nomeNemicoAttuale = nemicoAttuale.getNome();
        vitaNemicoAttuale = nemicoAttuale.getVita();
        vitaNemicoMassima = nemicoAttuale.getVitaMassima();
    }

    private void gestisciAttacco() {
        if (!gestoreCombattimentoGioco.attaccoValido(
                eroeAttuale,
                eroeSelezionato,
                nemicoAttuale
        )) {
            return;
        }

        combattimento.eseguiTurnoSingolo(eroeAttuale, nemicoAttuale);

        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

        vitaNemicoAttuale = nemicoAttuale.getVita();
        vitaNemicoMassima = nemicoAttuale.getVitaMassima();

        gestoreCombattimentoGioco.aggiornaViteDopoAttacco(
                eroeAttuale,
                nemicoAttuale
        );

        if (!nemicoAttuale.isVivo()) {
            gestisciNemicoSconfitto();
        }

        if (!eroeAttuale.isVivo()) {
            gestoreCombattimentoGioco.scriviMessaggioSconfittaEroe();
        }
    }
    private void gestisciAperturaMappa(Stage stage) {
        if (vitaNemicoAttuale > 0 && nemicoAttuale != null && nemicoAttuale.isVivo()) {
            schermataGioco.getLogArea()
                    .appendText("Devi prima sconfiggere il nemico attuale.\n");
            return;
        }

        mostraMappaGioco(stage);
    }

    private void salvaPartita() {
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

        try {
            gestoreSalvataggioPartita.salva(statoGioco);
            schermataGioco.getLogArea().appendText("Partita salvata in " + FILE_SALVATAGGIO + ".\n");

        } catch (IOException ex) {
            schermataGioco.getLogArea().appendText("Errore durante il salvataggio della partita.\n");
            ex.printStackTrace();
        }
    }
    private void caricaPartitaDaFile() {
        try {
            StatoGioco statoGioco = gestoreSalvataggioPartita.carica();

            numeroIncontro = statoGioco.getNumeroIncontro();
            eroeSelezionato = statoGioco.isEroeSelezionato();
            attaccoSquadraUsato = statoGioco.isAttaccoSquadraUsato();

            nomeGiocatoreAttuale = statoGioco.getNomeGiocatoreAttuale();
            vitaGiocatoreAttuale = statoGioco.getVitaGiocatoreAttuale();
            vitaGiocatoreMassima = statoGioco.getVitaGiocatoreMassima();
            dannoGiocatore = statoGioco.getDannoGiocatore();

            nomeNemicoAttuale = statoGioco.getNomeNemicoAttuale();
            vitaNemicoAttuale = statoGioco.getVitaNemicoAttuale();
            vitaNemicoMassima = statoGioco.getVitaNemicoMassima();

            eroeAttuale = gestoreSalvataggioPartita.trovaEroePerNome(nomeGiocatoreAttuale, eroiDisponibili);
            nemicoAttuale = gestoreSalvataggioPartita.trovaNemicoPerNome(nomeNemicoAttuale, nemiciDisponibili);

            eroeSelezionato = eroeAttuale != null;

            gestoreSalvataggioPartita.applicaVitaCaricata(eroeAttuale, vitaGiocatoreAttuale);
            gestoreSalvataggioPartita.applicaVitaCaricata(nemicoAttuale, vitaNemicoAttuale);
            gestoreSalvataggioPartita.aggiornaSchermataDopoCaricamento(
                    schermataGioco,
                    nomeGiocatoreAttuale,
                    vitaGiocatoreAttuale,
                    vitaGiocatoreMassima,
                    nomeNemicoAttuale,
                    vitaNemicoAttuale,
                    vitaNemicoMassima
            );

            if (attaccoSquadraButton != null) {
                attaccoSquadraButton.setDisable(attaccoSquadraUsato);
            }

            schermataGioco.getLogArea().appendText("Partita caricata da " + FILE_SALVATAGGIO + ".\n");

        } catch (IOException ex) {
            schermataGioco.getLogArea().appendText("Nessuna partita salvata trovata o errore durante il caricamento.\n");
            ex.printStackTrace();
        }
    }
    private void resettaPartita() {
        numeroIncontro = 0;

        nomeNemicoAttuale = "";
        vitaNemicoAttuale = 0;
        vitaNemicoMassima = 0;

        vitaGiocatoreAttuale = 100;
        vitaGiocatoreMassima = 100;
        nomeGiocatoreAttuale = "Nessun eroe";
        dannoGiocatore = 0;
        eroeSelezionato = false;

        giocatoreAttaccaPerPrimo = true;
        attaccoSquadraUsato = false;
        potenziamentoDisponibile = false;
        eroeDaPotenziare = null;
        eroeAttuale = null;
        nemicoAttuale = null;
        eroiDisponibili = gestoreResetPartita.creaEroiReset();
        nemiciDisponibili = gestoreResetPartita.creaNemiciReset();

        eroeMappa = null;
        gestoreResetPartita.resettaMovimentoEroe();
    }
    private void selezionaEroe(Eroe eroe) {
        eroeAttuale = eroe;

        nomeGiocatoreAttuale = eroeAttuale.getNome();
        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();
        dannoGiocatore = eroeAttuale.calcolaAttacco();
        eroeSelezionato = true;
        schermataGioco.getNomeGiocatoreLabel()
                .setText("Giocatore: " + nomeGiocatoreAttuale);

        schermataGioco.getVitaGiocatoreLabel()
                .setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);

        schermataGioco.getLogArea()
                .appendText("Hai scelto " + nomeGiocatoreAttuale + ".\n");
    }

    private void eseguiAttaccoSquadra() {
        if (!eroeSelezionato || eroeAttuale == null) {
            schermataGioco.getLogArea().appendText("Devi prima scegliere un eroe.\n");
            return;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            schermataGioco.getLogArea().appendText("Non c'è nessun nemico da attaccare.\n");
            return;
        }

        if (attaccoSquadraUsato) {
            schermataGioco.getLogArea().appendText("Hai già usato l'attacco di squadra in questo incontro.\n");
            return;
        }

        attaccoSquadraUsato = true;
        gestoreCombattimentoGioco.disabilitaAttaccoSquadra();

        schermataGioco.getLogArea().appendText("ATTACCO DI SQUADRA!\n");

        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo() && nemicoAttuale.isVivo()) {
                int danno = eroe.attacca(nemicoAttuale);

                schermataGioco.getLogArea().appendText(
                        eroe.getNome() + " attacca " +
                                nemicoAttuale.getNome() +
                                " causando " + danno + " danni.\n"
                );
            }
        }

        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

        vitaNemicoAttuale = nemicoAttuale.getVita();
        vitaNemicoMassima = nemicoAttuale.getVitaMassima();

        schermataGioco.getVitaGiocatoreLabel().setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);
        schermataGioco.getVitaNemicoLabel().setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);

        if (!nemicoAttuale.isVivo()) {
            gestisciNemicoSconfitto();
        }
    }

    private void gestisciNemicoSconfitto() {
        if (gestoreCombattimentoGioco.gestisciNemicoSconfittoConPotenziamento(
                nemicoAttuale,
                eroeAttuale,
                potenziamentoDisponibile
        )) {
            potenziamentoDisponibile = true;
            eroeDaPotenziare = eroeAttuale;
        }
    }
    private void potenziaVincitore() {
        if (!gestoreCombattimentoGioco.puoPotenziare(
                potenziamentoDisponibile,
                eroeDaPotenziare
        )) {
            return;
        }
        gestorePotenziamentiGioco.potenziaVincitore(eroeDaPotenziare);

        gestoreCombattimentoGioco.aggiornaEroeDopoPotenziamento(
                eroeAttuale,
                eroeDaPotenziare,
                () -> {
                    dannoGiocatore = eroeAttuale.calcolaAttacco();
                    vitaGiocatoreAttuale = eroeAttuale.getVita();
                    vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

                    gestoreCombattimentoGioco.aggiornaInterfacciaEroe(
                            vitaGiocatoreAttuale,
                            vitaGiocatoreMassima
                    );
                }
        );
        potenziamentoDisponibile = false;
        eroeDaPotenziare = null;
        gestoreCombattimentoGioco.terminaPotenziamento();
    }
    private void mostraMappaGioco(Stage stage) {
        Pane mappaPane = schermataMappa.creaPaneMappa(stage);
        mappaPaneAttuale = mappaPane;

        ImageView sfondoMappa =
                schermataMappa.creaSfondo(mappe[indiceMappaAttuale]);


        Label istruzioni = schermataMappa.creaIstruzioni();

        Button tornaGioco =
                schermataMappa.creaPulsanteTornaMenu(() -> {
                    fermaMovimentoNemici();
                    start(stage);
                });

        Button mappaIndietro = creaPulsanteMappaIndietro(stage);
        Button mappaAvanti = creaPulsanteMappaAvanti(stage);

        creaPulsantiCombattimentoMappa();
        inizializzaLogMappa();
        gestoreCombattimentoMappa = new GestoreCombattimentoMappa(dado);

        mappaPane.getChildren().addAll(
                sfondoMappa,
                istruzioni,
                tornaGioco,
                mappaIndietro,
                mappaAvanti,
                logMappa,
                combattiMappaButton,
                attaccoSquadraMappaButton,
                potenziaMappaButton
        );

        aggiungiEroiAllaMappa(mappaPane);
        aggiungiNemiciAllaMappa(mappaPane, stage);
        gestoreIncontriMappa = new GestoreIncontriMappa();
        gestoreMovimentoNemiciMappa =
                new GestoreMovimentoNemiciMappa(nemiciMappa, nemiciAssociatiMappa);
        avviaMovimentoNemici(stage);
        Scene scene = new Scene(mappaPane, 800, 600);

        scene.setOnKeyPressed(e -> {

            if (eroeMappa == null) {
                if (logMappa != null) {
                    logMappa.appendText("Devi prima selezionare un eroe sulla mappa.\n");
                }
                return;
            }

            gestoreMovimentoEroe.muovi(e.getCode().toString());

            eroeMappa.setLayoutX(
                    gestoreMovimentoEroe.getEroeX()
            );

            eroeMappa.setLayoutY(
                    gestoreMovimentoEroe.getEroeY()
            );

            controllaCollisioneNemici(stage);
        });

        stage.setScene(scene);
        Platform.runLater(() -> mappaPane.requestFocus());
    }
    private void inizializzaLogMappa() {
        logMappa = gestoreInterfacciaMappa.creaLogMappa();
        gestorePotenziamentiMappa =
                new GestorePotenziamenti(new TextAreaOutput(logMappa));
    }
    private void creaPulsantiCombattimentoMappa() {
        combattiMappaButton =
                gestorePulsantiMappa.creaPulsanteCombatti(
                        this::combattiTurnoSuMappa
                );

        attaccoSquadraMappaButton =
                gestorePulsantiMappa.creaPulsanteAttaccoSquadraMappa(
                        this::eseguiAttaccoSquadraSuMappa
                );
        potenziaMappaButton =
                gestorePulsantiMappa.creaPulsantePotenziaMappa(
                        this::potenziaVincitoreSuMappa
                );
    }
    private Button creaPulsanteMappaAvanti(Stage stage) {
        return gestorePulsantiMappa.creaPulsanteMappaAvanti(() -> {
            fermaMovimentoNemici();
            indiceMappaAttuale++;

            if (indiceMappaAttuale >= mappe.length) {
                indiceMappaAttuale = 0;
            }

            mostraMappaGioco(stage);
        });
    }
    private Button creaPulsanteMappaIndietro(Stage stage) {
        return gestorePulsantiMappa.creaPulsanteMappaIndietro(() -> {
            fermaMovimentoNemici();
            indiceMappaAttuale--;

            if (indiceMappaAttuale < 0) {
                indiceMappaAttuale = mappe.length - 1;
            }

            mostraMappaGioco(stage);
        });
    }

    private void aggiungiEroiAllaMappa(Pane mappaPane) {
        if (eroiDisponibili == null) {
            eroiDisponibili = creatorePersonaggi.creaEroi();
        }

        gestoreEroiMappa.aggiungiEroiAllaMappa(
                mappaPane,
                eroiDisponibili,
                eroiMappa,
                eroiAssociatiMappa,
                this::selezionaEroeDaMappa
        );
    }
    private void selezionaEroeDaMappa(Eroe eroe, ImageView eroeView) {
        eroeAttuale = eroe;
        eroeMappa = eroeView;

        nomeGiocatoreAttuale = eroeAttuale.getNome();
        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();
        dannoGiocatore = eroeAttuale.calcolaAttacco();
        eroeSelezionato = true;

        gestoreMovimentoEroe.impostaPosizione(
                eroeView.getLayoutX(),
                eroeView.getLayoutY()
        );

        for (ImageView view : eroiMappa) {
            view.setStyle("");
        }

        eroeView.setStyle("-fx-effect: dropshadow(gaussian, yellow, 18, 0.8, 0, 0);");

        if (logMappa != null) {
            logMappa.appendText("Hai selezionato " + eroeAttuale.getNome() + ".\n");
        }
    }

    private void aggiungiNemiciAllaMappa(Pane mappaPane, Stage stage) {
        if (nemiciDisponibili == null) {
            nemiciDisponibili = creatorePersonaggi.creaNemici();
        }

        gestoreNemiciVisualiMappa.aggiungiNemiciAllaMappa(
                mappaPane,
                nemiciDisponibili,
                nemiciMappa,
                nomiNemiciMappa,
                nemiciAssociatiMappa,
                nemico -> avviaIncontroDaMappa(nemico, stage)
        );
    }
    private void avviaIncontroDaMappa(Nemico nemico, Stage stage) {
        if (!gestoreIncontriMappa.incontroValido(nemico)) {
            return;
        }
        datiCombattimentoMappa.setCombattimentoAttivo(true);
        combattimentoMappaAttivo = true;

        datiCombattimentoMappa.setNemicoAttuale(nemico);
        nemicoAttuale = nemico;

        nomeNemicoAttuale = gestoreIncontriMappa.prendiNomeNemico(nemicoAttuale);
        vitaNemicoAttuale = gestoreIncontriMappa.prendiVitaNemico(nemicoAttuale);
        vitaNemicoMassima = gestoreIncontriMappa.prendiVitaMassimaNemico(nemicoAttuale);

        if (eroeAttuale != null) {
            datiCombattimentoMappa.setEroeAttuale(eroeAttuale);
            nomeGiocatoreAttuale = eroeAttuale.getNome();
            vitaGiocatoreAttuale = eroeAttuale.getVita();
            vitaGiocatoreMassima = eroeAttuale.getVitaMassima();
            dannoGiocatore = eroeAttuale.calcolaAttacco();
            datiCombattimentoMappa.setEroeSelezionato(true);
            eroeSelezionato = true;
        }

        datiCombattimentoMappa.setAttaccoSquadraUsato(false);
        attaccoSquadraUsato = false;
        datiCombattimentoMappa.setPotenziamentoDisponibile(false);
        potenziamentoDisponibile = false;

        datiCombattimentoMappa.setEroeDaPotenziare(null);
        eroeDaPotenziare = null;

        datiCombattimentoMappa.setPotenziamentoDiSquadra(false);
        potenziamentoDiSquadra = false;

        if (logMappa != null) {
            logMappa.appendText("Hai incontrato " + nomeNemicoAttuale + " sulla mappa!\n");
            logMappa.appendText("Eroe: " + nomeGiocatoreAttuale + " | Vita: "
                    + vitaGiocatoreAttuale + "/" + vitaGiocatoreMassima + "\n");
            logMappa.appendText("Nemico: " + nomeNemicoAttuale + " | Vita: "
                    + vitaNemicoAttuale + "/" + vitaNemicoMassima + "\n");
        }
        if (combattiMappaButton != null) {
            combattiMappaButton.setDisable(false);
        }

        if (combattiMappaButton != null) {
            combattiMappaButton.setDisable(false);
        }

        if (attaccoSquadraMappaButton != null) {
            attaccoSquadraMappaButton.setDisable(false);
        }

        if (potenziaMappaButton != null) {
            potenziaMappaButton.setDisable(true);
        }
    }

    private void controllaCollisioneNemici(Stage stage) {
        Nemico nemicoToccato =
                gestoreCollisioniMappa.trovaNemicoInCollisione(
                        eroeMappa,
                        nemiciMappa,
                        nemiciAssociatiMappa
                );

        if (nemicoToccato != null) {
            avviaIncontroDaMappa(nemicoToccato, stage);
        }
    }
    private void combattiTurnoSuMappa() {
        if (eroeAttuale == null || !eroeSelezionato) {
            logMappa.appendText("Devi prima scegliere un eroe.\n");
            return;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            logMappa.appendText("Devi prima toccare un nemico sulla mappa.\n");
            return;
        }

        if (!eroeAttuale.isVivo()) {
            logMappa.appendText(eroeAttuale.getNome() + " è sconfitto e non può combattere.\n");
            return;
        }

        lanciaPotereVersoNemico();
        Combattimento combattimentoMappa = new Combattimento(dado, new TextAreaOutput(logMappa));
        combattimentoMappa.eseguiTurnoSingolo(eroeAttuale, nemicoAttuale);

        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

        vitaNemicoAttuale = nemicoAttuale.getVita();
        vitaNemicoMassima = nemicoAttuale.getVitaMassima();

        logMappa.appendText(
                eroeAttuale.getNome() + " vita: "
                        + vitaGiocatoreAttuale + "/" + vitaGiocatoreMassima + "\n"
        );

        logMappa.appendText(
                nemicoAttuale.getNome() + " vita: "
                        + vitaNemicoAttuale + "/" + vitaNemicoMassima + "\n"
        );
        combattimentoMappaAttivo = false;
        datiCombattimentoMappa.setCombattimentoAttivo(false);

        if (mappaPaneAttuale != null) {
            Platform.runLater(() -> mappaPaneAttuale.requestFocus());
        }

        if (!nemicoAttuale.isVivo()) {
            gestisciNemicoSconfittoSuMappa();
        }
    }
    private void avviaMovimentoNemici(Stage stage) {
        gestoreNemiciMappa.avviaMovimentoNemici(() -> {
            gestoreMovimentoNemiciMappa.muoviNemici();
            controllaCollisioneNemici(stage);
        });
    }

    private void fermaMovimentoNemici() {
        gestoreNemiciMappa.fermaMovimentoNemici();
    }

    private void eseguiAttaccoSquadraSuMappa() {
        if (attaccoSquadraUsato) {
            logMappa.appendText("Hai già usato l'attacco di squadra contro questo nemico.\n");
            return;
        }
        if (logMappa == null) {
            return;
        }

        if (eroeAttuale == null || !eroeSelezionato) {
            logMappa.appendText("Devi prima selezionare un eroe sulla mappa.\n");
            return;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            logMappa.appendText("Devi prima selezionare un nemico vivo.\n");
            return;
        }

        attaccoSquadraUsato = true;
        datiCombattimentoMappa.setAttaccoSquadraUsato(true);

        if (attaccoSquadraMappaButton != null) {
            attaccoSquadraMappaButton.setDisable(true);
        }
        if (mappaPaneAttuale != null) {
            Platform.runLater(() -> mappaPaneAttuale.requestFocus());
        }

        logMappa.appendText("ATTACCO DI SQUADRA!\n");

        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo() && nemicoAttuale.isVivo()) {
                lanciaPotereEroeVersoNemico(eroe);
                int danno = eroe.attacca(nemicoAttuale);

                logMappa.appendText(
                        eroe.getNome()
                                + " attacca "
                                + nemicoAttuale.getNome()
                                + " causando "
                                + danno
                                + " danni.\n"
                );
            }
        }

        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

        vitaNemicoAttuale = nemicoAttuale.getVita();
        vitaNemicoMassima = nemicoAttuale.getVitaMassima();

        logMappa.appendText(
                eroeAttuale.getNome()
                        + " vita: "
                        + vitaGiocatoreAttuale
                        + "/"
                        + vitaGiocatoreMassima
                        + "\n"
        );

        logMappa.appendText(
                nemicoAttuale.getNome()
                        + " vita: "
                        + vitaNemicoAttuale
                        + "/"
                        + vitaNemicoMassima
                        + "\n"
        );

        if (!nemicoAttuale.isVivo()) {
            datiCombattimentoMappa.setPotenziamentoDiSquadra(true);
            potenziamentoDiSquadra = true;
            gestisciNemicoSconfittoSuMappa();
        }
    }
    private void gestisciNemicoSconfittoSuMappa() {
        if (nemicoAttuale == null || nemicoAttuale.isVivo()) {
            return;
        }

        if (logMappa != null) {
            logMappa.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
        }
        rimuoviNemicoSconfittoDallaMappa();

        potenziamentoDisponibile = true;
        datiCombattimentoMappa.setPotenziamentoDisponibile(true);

        if (potenziamentoDiSquadra) {
            potenziamentoDisponibile = true;
            eroeDaPotenziare = null;
            datiCombattimentoMappa.setEroeDaPotenziare(null);

            if (potenziaMappaButton != null) {
                potenziaMappaButton.setDisable(false);
            }
            logMappa.appendText(
                    "Attacco di squadra riuscito! Premi Potenzia vincitore per potenziare tutti gli eroi.\n"
            );
        }
        else {
            eroeDaPotenziare = eroeAttuale;
            datiCombattimentoMappa.setEroeDaPotenziare(eroeAttuale);

            if (potenziaMappaButton != null) {
                potenziaMappaButton.setDisable(false);
            }
        }

        if (!potenziamentoDiSquadra && logMappa != null && eroeDaPotenziare != null) {
            logMappa.appendText(
                    eroeDaPotenziare.getNome()
                            + " può essere potenziato! Premi Potenzia vincitore.\n"
            );
        }
        nemicoAttuale = null;
        nomeNemicoAttuale = "";
        vitaNemicoAttuale = 0;
        vitaNemicoMassima = 0;

        if (combattiMappaButton != null) {
            combattiMappaButton.setDisable(true);
        }

        if (attaccoSquadraMappaButton != null) {
            attaccoSquadraMappaButton.setDisable(true);
        }
        combattimentoMappaAttivo = false;
        datiCombattimentoMappa.setCombattimentoAttivo(false);

        if (mappaPaneAttuale != null) {
            avviaMovimentoNemici(
                    (Stage) mappaPaneAttuale.getScene().getWindow()
            );

            Platform.runLater(() -> mappaPaneAttuale.requestFocus());
        }
    }
    private void potenziaVincitoreSuMappa() {
        if (logMappa == null) {
            return;
        }

        if (!gestoreCombattimentoMappa.puoPotenziareMappa(
                potenziamentoDisponibile,
                logMappa
        )) {
            return;
        }

        if (potenziamentoDiSquadra) {
            gestoreCombattimentoMappa.potenziaSquadra(
                    eroiDisponibili,
                    gestorePotenziamentiMappa
            );
        } else {
            if (!gestoreCombattimentoMappa.puoPotenziareEroe(
                    eroeDaPotenziare,
                    logMappa
            )) {
                return;
            }

            gestorePotenziamentiMappa.potenziaVincitore(eroeDaPotenziare);
        }

        potenziamentoDisponibile = false;
        datiCombattimentoMappa.setPotenziamentoDisponibile(false);

        datiCombattimentoMappa.setPotenziamentoDiSquadra(false);
        potenziamentoDiSquadra = false;

        datiCombattimentoMappa.setEroeDaPotenziare(null);
        eroeDaPotenziare = null;

        if (potenziaMappaButton != null) {
            potenziaMappaButton.setDisable(true);
        }

        combattimentoMappaAttivo = false;
        datiCombattimentoMappa.setCombattimentoAttivo(false);

        if (mappaPaneAttuale != null) {
            Platform.runLater(() -> mappaPaneAttuale.requestFocus());
        }
    }
    private void rimuoviNemicoSconfittoDallaMappa() {
        if (mappaPaneAttuale == null || nemicoAttuale == null) {
            return;
        }

        int indice = nemiciAssociatiMappa.indexOf(nemicoAttuale);

        if (indice == -1) {
            return;
        }

        ImageView nemicoView = nemiciMappa.get(indice);
        Label nomeNemico = nomiNemiciMappa.get(indice);

        mappaPaneAttuale.getChildren().removeAll(nemicoView, nomeNemico);

        nemiciMappa.remove(indice);
        nomiNemiciMappa.remove(indice);
        nemiciAssociatiMappa.remove(indice);
    }
    private void lanciaPotereVersoNemico() {
        if (mappaPaneAttuale == null || eroeMappa == null || nemicoAttuale == null) {
            return;
        }

        int indiceNemico = nemiciAssociatiMappa.indexOf(nemicoAttuale);

        if (indiceNemico == -1) {
            return;
        }

        ImageView nemicoView = nemiciMappa.get(indiceNemico);

        Image immaginePotere = new Image(
                Objects.requireNonNull(
                        getClass().getResource(risorseImmagini.percorsoPotere(eroeAttuale))
                ).toExternalForm()
        );

        ImageView potereView = new ImageView(immaginePotere);
        potereView.setFitWidth(45);
        potereView.setFitHeight(45);
        potereView.setPreserveRatio(true);

        potereView.setLayoutX(eroeMappa.getLayoutX() + 30);
        potereView.setLayoutY(eroeMappa.getLayoutY() + 20);

        mappaPaneAttuale.getChildren().add(potereView);
        potereView.toFront();

        TranslateTransition animazione = new TranslateTransition(Duration.millis(500), potereView);
        animazione.setToX(nemicoView.getLayoutX() - eroeMappa.getLayoutX());
        animazione.setToY(nemicoView.getLayoutY() - eroeMappa.getLayoutY());

        animazione.setOnFinished(e -> mappaPaneAttuale.getChildren().remove(potereView));
        animazione.play();
    }
    private void lanciaPotereEroeVersoNemico(Eroe eroe) {
        if (mappaPaneAttuale == null || nemicoAttuale == null) {
            return;
        }

        int indiceEroe = eroiAssociatiMappa.indexOf(eroe);
        int indiceNemico = nemiciAssociatiMappa.indexOf(nemicoAttuale);

        if (indiceEroe == -1 || indiceNemico == -1) {
            return;
        }

        ImageView eroeView = eroiMappa.get(indiceEroe);
        ImageView nemicoView = nemiciMappa.get(indiceNemico);

        gestoreAnimazioni.lanciaPotere(
                mappaPaneAttuale,
                eroeView,
                nemicoView,
                risorseImmagini.percorsoPotere(eroe)
        );

    }

    public static void main(String[] args) {
        launch(args);
    }
}
