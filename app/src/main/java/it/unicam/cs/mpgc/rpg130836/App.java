package it.unicam.cs.mpgc.rpg130836;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

import java.io.IOException;
import java.util.List;

public class App extends Application {
    private TextArea logArea;
    private Button attaccoSquadraButton;
    private Button potenziaButton;
    private boolean potenziamentoDisponibile = false;
    private Eroe eroeDaPotenziare;
    private int numeroIncontro = 0;
    private Label nomeGiocatoreLabel;
    private Label vitaGiocatoreLabel;
    private Label nomeNemicoLabel;
    private Label vitaNemicoLabel;
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

    private List<Eroe> eroiDisponibili;
    private Eroe eroeAttuale;
    private List<Nemico> nemiciDisponibili;
    private Nemico nemicoAttuale;
    private boolean attaccoSquadraUsato = false;


    @Override
    public void start(Stage stage) {

        Button nuovaPartita = new Button("Nuova Partita");
        Button caricaPartita = new Button("Carica Partita");
        Button esci = new Button("Esci");

        String stileBottoneMenu = """
        -fx-font-size: 22px;
        -fx-font-weight: bold;
        -fx-text-fill: black;
        -fx-background-color: transparent;
        -fx-border-color: transparent;
        -fx-padding: 8 20 8 20;
        -fx-focus-color: transparent;
        -fx-faint-focus-color: transparent;
        """;

        String stileBottoneMenuHover = """
        -fx-font-size: 22px;
        -fx-font-weight: bold;
        -fx-text-fill: #005bbb;
        -fx-background-color: transparent;
        -fx-border-color: transparent;
        -fx-padding: 8 20 8 20;
        -fx-focus-color: transparent;
        -fx-faint-focus-color: transparent;
        """;

        nuovaPartita.setStyle(stileBottoneMenu);
        caricaPartita.setStyle(stileBottoneMenu);
        esci.setStyle(stileBottoneMenu);

        nuovaPartita.setPrefWidth(220);
        caricaPartita.setPrefWidth(220);
        esci.setPrefWidth(220);

        nuovaPartita.setOnMouseEntered(e -> nuovaPartita.setStyle(stileBottoneMenuHover));
        nuovaPartita.setOnMouseExited(e -> nuovaPartita.setStyle(stileBottoneMenu));

        caricaPartita.setOnMouseEntered(e -> caricaPartita.setStyle(stileBottoneMenuHover));
        caricaPartita.setOnMouseExited(e -> caricaPartita.setStyle(stileBottoneMenu));

        esci.setOnMouseEntered(e -> esci.setStyle(stileBottoneMenuHover));
        esci.setOnMouseExited(e -> esci.setStyle(stileBottoneMenu));

        nuovaPartita.setOnAction(e -> {
            resettaPartita();
            mostraSchermataGioco(stage);
        });
        caricaPartita.setOnAction(e -> {
            mostraSchermataGioco(stage);
            caricaPartitaDaFile();
        });


        esci.setOnAction(e -> stage.close());

        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateY(-90);
        menuBox.setTranslateX(-30);

        menuBox.getChildren().addAll(
                nuovaPartita,
                caricaPartita,
                esci
        );

        Image sfondo = new Image(
                Objects.requireNonNull(getClass().getResource("/images/sfondo_menu.png")).toExternalForm()
        );

        ImageView sfondoView = new ImageView(sfondo);
        sfondoView.setPreserveRatio(true);
        sfondoView.setSmooth(true);

        StackPane root = new StackPane();
        root.getChildren().addAll(sfondoView, menuBox);

        Scene scene = new Scene(root, 620, 800);
        sfondoView.fitHeightProperty().bind(scene.heightProperty());

        stage.setTitle("Iron Man e i suoi fantastici amici");
        stage.setScene(scene);
        stage.show();
    }
    private void mostraSchermataGioco(Stage stage) {

        Label titolo = new Label("PARTITA IN CORSO");
        nomeGiocatoreLabel = new Label("Giocatore: " + nomeGiocatoreAttuale);
        vitaGiocatoreLabel = new Label("Vita: -");
        nomeNemicoLabel = new Label("Nemico: nessuno");
        vitaNemicoLabel = new Label("Vita: -");

        VBox boxGiocatore = new VBox(5);
        boxGiocatore.setAlignment(Pos.CENTER);
        boxGiocatore.getChildren().addAll(nomeGiocatoreLabel, vitaGiocatoreLabel);

        VBox boxNemico = new VBox(5);
        boxNemico.setAlignment(Pos.CENTER);
        boxNemico.getChildren().addAll(nomeNemicoLabel, vitaNemicoLabel);

        HBox pannelloInfo = new HBox(100);
        pannelloInfo.setAlignment(Pos.CENTER);
        pannelloInfo.getChildren().addAll(boxGiocatore, boxNemico);

        Label scegliEroeLabel = new Label("Scegli eroe:");

        eroiDisponibili = creatorePersonaggi.creaEroi();
        nemiciDisponibili = creatorePersonaggi.creaNemici();

        Button eroe1 = new Button(eroiDisponibili.get(0).getNome());
        Button eroe2 = new Button(eroiDisponibili.get(1).getNome());
        Button eroe3 = new Button(eroiDisponibili.get(2).getNome());

        HBox sceltaEroi = new HBox(10);
        sceltaEroi.setAlignment(Pos.CENTER);
        sceltaEroi.getChildren().addAll(eroe1, eroe2, eroe3);

        eroe1.setOnAction(e -> selezionaEroe(eroiDisponibili.get(0)));

        eroe2.setOnAction(e -> selezionaEroe(eroiDisponibili.get(1)));

        eroe3.setOnAction(e -> selezionaEroe(eroiDisponibili.get(2)));


        Button prossimoIncontro = new Button("Prossimo incontro");
        prossimoIncontro.setOnAction(e -> {
            if (!eroeSelezionato) {
                logArea.appendText("Devi prima scegliere un eroe.\n");
                return;
            }
            if (vitaNemicoAttuale > 0) {
                logArea.appendText("Devi prima sconfiggere il nemico attuale.\n");
                return;
            }
            if (numeroIncontro >= nemiciDisponibili.size()) {
                logArea.appendText("Non ci sono altri nemici.\n");
                return;
            }

            numeroIncontro++;

            nemicoAttuale = nemiciDisponibili.get(numeroIncontro - 1);
            attaccoSquadraUsato = false;
            attaccoSquadraButton.setDisable(false);
            potenziamentoDisponibile = false;
            eroeDaPotenziare = null;
            potenziaButton.setDisable(true);

            nomeNemicoAttuale = nemicoAttuale.getNome();
            vitaNemicoAttuale = nemicoAttuale.getVita();
            vitaNemicoMassima = nemicoAttuale.getVitaMassima();

            nomeNemicoLabel.setText("Nemico: " + nomeNemicoAttuale);
            vitaNemicoLabel.setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);

            logArea.appendText("Incontro " + numeroIncontro + ": compare " + nomeNemicoAttuale + "!\n");
        });

        Button attacca = new Button("Combatti turno");
            attacca.setOnAction(e -> {
                if (!eroeSelezionato || eroeAttuale == null) {
                    logArea.appendText("Devi prima scegliere un eroe.\n");
                    return;
                }

                if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
                    logArea.appendText("Non c'è nessun nemico da attaccare.\n");
                    return;
                }

                if (!eroeAttuale.isVivo()) {
                    logArea.appendText("Sei stato sconfitto. Non puoi attaccare.\n");
                    return;
                }

                combattimento.eseguiTurnoSingolo(eroeAttuale, nemicoAttuale);

                vitaGiocatoreAttuale = eroeAttuale.getVita();
                vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

                vitaNemicoAttuale = nemicoAttuale.getVita();
                vitaNemicoMassima = nemicoAttuale.getVitaMassima();

                vitaGiocatoreLabel.setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);
                vitaNemicoLabel.setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);

                if (!nemicoAttuale.isVivo()) {
                    gestisciNemicoSconfitto();
                }

                if (!eroeAttuale.isVivo()) {
                    logArea.appendText("Sei stato sconfitto!\n");
                }
            });
        attaccoSquadraButton = new Button("Attacco di squadra");
        attaccoSquadraButton.setOnAction(e -> eseguiAttaccoSquadra());
        potenziaButton = new Button("Potenzia vincitore");
        potenziaButton.setDisable(true);
        potenziaButton.setOnAction(e -> potenziaVincitore());
        Button salva = new Button("Salva partita");
        salva.setOnAction(e -> salvaPartita());
        Button tornaMenu = new Button("Torna al menu");
        tornaMenu.setOnAction(e -> start(stage));

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefHeight(250);
        logArea.setText("Partita iniziata.\n");

        combattimento = new Combattimento(dado, new TextAreaOutput(logArea));

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                titolo,
                scegliEroeLabel,
                sceltaEroi,
                pannelloInfo,
                logArea,
                prossimoIncontro,
                attacca,
                attaccoSquadraButton,
                potenziaButton,
                salva,
                tornaMenu
        );

        Scene scene = new Scene(root, 800, 500);

        stage.setScene(scene);
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
            salvataggioJson.salva(statoGioco, FILE_SALVATAGGIO);
            logArea.appendText("Partita salvata in " + FILE_SALVATAGGIO + ".\n");

        } catch (IOException ex) {
            logArea.appendText("Errore durante il salvataggio della partita.\n");
            ex.printStackTrace();
        }
    }
    private void caricaPartitaDaFile() {
        try {
            StatoGioco statoGioco = salvataggioJson.carica(FILE_SALVATAGGIO);

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

            eroeAttuale = trovaEroePerNome(nomeGiocatoreAttuale);
            nemicoAttuale = trovaNemicoPerNome(nomeNemicoAttuale);

            eroeSelezionato = eroeAttuale != null;

            applicaVitaCaricata(eroeAttuale, vitaGiocatoreAttuale);
            applicaVitaCaricata(nemicoAttuale, vitaNemicoAttuale);

            aggiornaSchermataDopoCaricamento();

            if (attaccoSquadraButton != null) {
                attaccoSquadraButton.setDisable(attaccoSquadraUsato);
            }

            logArea.appendText("Partita caricata da " + FILE_SALVATAGGIO + ".\n");

        } catch (IOException ex) {
            logArea.appendText("Nessuna partita salvata trovata o errore durante il caricamento.\n");
            ex.printStackTrace();
        }
    }
    private void aggiornaSchermataDopoCaricamento() {
        nomeGiocatoreLabel.setText("Giocatore: " + nomeGiocatoreAttuale);
        vitaGiocatoreLabel.setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);

        if (vitaNemicoAttuale > 0) {
            nomeNemicoLabel.setText("Nemico: " + nomeNemicoAttuale);
            vitaNemicoLabel.setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);
        } else {
            nomeNemicoLabel.setText("Nemico: nessuno");
            vitaNemicoLabel.setText("Vita: -");
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

    }
    private void selezionaEroe(Eroe eroe) {
        eroeAttuale = eroe;

        nomeGiocatoreAttuale = eroeAttuale.getNome();
        vitaGiocatoreAttuale = eroeAttuale.getVita();
        vitaGiocatoreMassima = eroeAttuale.getVitaMassima();
        dannoGiocatore = eroeAttuale.calcolaAttacco();
        eroeSelezionato = true;

        nomeGiocatoreLabel.setText("Giocatore: " + nomeGiocatoreAttuale);
        vitaGiocatoreLabel.setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);

        logArea.appendText("Hai scelto " + nomeGiocatoreAttuale + ".\n");
    }
    private Eroe trovaEroePerNome(String nome) {
        for (Eroe eroe : eroiDisponibili) {
            if (eroe.getNome().equals(nome)) {
                return eroe;
            }
        }

        return null;
    }

    private Nemico trovaNemicoPerNome(String nome) {
        for (Nemico nemico : nemiciDisponibili) {
            if (nemico.getNome().equals(nome)) {
                return nemico;
            }
        }

        return null;
    }
    private void applicaVitaCaricata(Combattente combattente, int vitaCaricata) {
        if (combattente == null) {
            return;
        }

        int dannoDaApplicare = combattente.getVita() - vitaCaricata;

        if (dannoDaApplicare > 0) {
            combattente.riceviDanno(dannoDaApplicare);
        }
    }
    private void eseguiAttaccoSquadra() {
        if (!eroeSelezionato || eroeAttuale == null) {
            logArea.appendText("Devi prima scegliere un eroe.\n");
            return;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            logArea.appendText("Non c'è nessun nemico da attaccare.\n");
            return;
        }

        if (attaccoSquadraUsato) {
            logArea.appendText("Hai già usato l'attacco di squadra in questo incontro.\n");
            return;
        }

        attaccoSquadraUsato = true;
        attaccoSquadraButton.setDisable(true);

        logArea.appendText("ATTACCO DI SQUADRA!\n");

        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo() && nemicoAttuale.isVivo()) {
                int danno = eroe.attacca(nemicoAttuale);

                logArea.appendText(
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

        vitaGiocatoreLabel.setText("Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima);
        vitaNemicoLabel.setText("Vita: " + vitaNemicoAttuale + " / " + vitaNemicoMassima);

        if (!nemicoAttuale.isVivo()) {
            gestisciNemicoSconfitto();
        }
    }

    private void gestisciNemicoSconfitto() {
        if (nemicoAttuale == null || nemicoAttuale.isVivo()) {
            return;
        }

        logArea.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
        nomeNemicoLabel.setText("Nemico: sconfitto");

        if (!potenziamentoDisponibile) {
            potenziamentoDisponibile = true;
            eroeDaPotenziare = eroeAttuale;

            if (potenziaButton != null) {
                potenziaButton.setDisable(false);
            }

            logArea.appendText(
                    eroeDaPotenziare.getNome()
                            + " ha ottenuto un potenziamento! Premi 'Potenzia vincitore'.\n"
            );
        }
    }

    private void potenziaVincitore() {
        if (!potenziamentoDisponibile) {
            logArea.appendText("Non hai potenziamenti disponibili.\n");
            return;
        }

        if (eroeDaPotenziare == null) {
            logArea.appendText("Non c'è nessun vincitore da potenziare.\n");
            return;
        }

        eroeDaPotenziare.potenzia();

        logArea.appendText(eroeDaPotenziare.getNome() + " è stato potenziato!\n");
        logArea.appendText(eroeDaPotenziare.descrizionePotenziamento() + "\n");

        if (eroeDaPotenziare == eroeAttuale) {
            dannoGiocatore = eroeAttuale.calcolaAttacco();
            vitaGiocatoreAttuale = eroeAttuale.getVita();
            vitaGiocatoreMassima = eroeAttuale.getVitaMassima();

            vitaGiocatoreLabel.setText(
                    "Vita: " + vitaGiocatoreAttuale + " / " + vitaGiocatoreMassima
            );
        }

        potenziamentoDisponibile = false;
        eroeDaPotenziare = null;
        potenziaButton.setDisable(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}