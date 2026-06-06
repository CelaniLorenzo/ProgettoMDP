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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class App extends Application {
    private TextArea logArea;
    private Button attaccoSquadraButton;
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

    private List<Eroe> eroiDisponibili;
    private Eroe eroeAttuale;
    private List<Nemico> nemiciDisponibili;
    private Nemico nemicoAttuale;
    private boolean attaccoSquadraUsato = false;

    @Override
    public void start(Stage stage) {

        Label titolo = new Label("IRON HULK RPG");

        Button nuovaPartita = new Button("Nuova Partita");
        Button caricaPartita = new Button("Carica Partita");
        Button esci = new Button("Esci");

        nuovaPartita.setOnAction(e -> {
            resettaPartita();
            mostraSchermataGioco(stage);
        });
        caricaPartita.setOnAction(e -> {
            mostraSchermataGioco(stage);
            caricaPartitaDaFile();
        });


        esci.setOnAction(e -> stage.close());

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                titolo,
                nuovaPartita,
                caricaPartita,
                esci
        );

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Iron Hulk RPG");
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
                    logArea.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
                    nomeNemicoLabel.setText("Nemico: sconfitto");
                }

                if (!eroeAttuale.isVivo()) {
                    logArea.appendText("Sei stato sconfitto!\n");
                }
            });
        attaccoSquadraButton = new Button("Attacco di squadra");
        attaccoSquadraButton.setOnAction(e -> eseguiAttaccoSquadra());
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
                salva,
                tornaMenu
        );

        Scene scene = new Scene(root, 800, 500);

        stage.setScene(scene);
    }
    private void salvaPartita() {
        String json = """
            {
              "numeroIncontro": %d,
              "eroeSelezionato": %b,
              "attaccoSquadraUsato": %b,
              "giocatore": {
                "nome": "%s",
                "vitaAttuale": %d,
                "vitaMassima": %d,
                "danno": %d
              },
              "nemico": {
                "nome": "%s",
                "vitaAttuale": %d,
                "vitaMassima": %d
              }
            }
            """.formatted(
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
            Files.writeString(
                    Path.of("partita.json"),
                    json,
                    StandardCharsets.UTF_8
            );

            logArea.appendText("Partita salvata in partita.json.\n");

        } catch (IOException ex) {
            logArea.appendText("Errore durante il salvataggio della partita.\n");
            ex.printStackTrace();
        }
    }
    private void caricaPartitaDaFile() {
        Path file = Path.of("partita.json");

        if (!Files.exists(file)) {
            logArea.appendText("Nessuna partita salvata trovata.\n");
            return;
        }

        try {
            String json = Files.readString(file, StandardCharsets.UTF_8);

            numeroIncontro = estraiInt(json, "numeroIncontro");
            eroeSelezionato = estraiBoolean(json, "eroeSelezionato");
            attaccoSquadraUsato = estraiBoolean(json, "attaccoSquadraUsato");

            String giocatoreJson = estraiSezione(json, "giocatore");
            String nemicoJson = estraiSezione(json, "nemico");

            nomeGiocatoreAttuale = estraiString(giocatoreJson, "nome");
            vitaGiocatoreAttuale = estraiInt(giocatoreJson, "vitaAttuale");
            vitaGiocatoreMassima = estraiInt(giocatoreJson, "vitaMassima");
            dannoGiocatore = estraiInt(giocatoreJson, "danno");

            nomeNemicoAttuale = estraiString(nemicoJson, "nome");
            vitaNemicoAttuale = estraiInt(nemicoJson, "vitaAttuale");
            vitaNemicoMassima = estraiInt(nemicoJson, "vitaMassima");
            eroeAttuale = trovaEroePerNome(nomeGiocatoreAttuale);
            nemicoAttuale = trovaNemicoPerNome(nomeNemicoAttuale);
            eroeSelezionato = eroeAttuale != null;
            applicaVitaCaricata(eroeAttuale, vitaGiocatoreAttuale);
            applicaVitaCaricata(nemicoAttuale, vitaNemicoAttuale);

            aggiornaSchermataDopoCaricamento();
            if (attaccoSquadraButton != null) {
                attaccoSquadraButton.setDisable(attaccoSquadraUsato);
            }

            logArea.appendText("Partita caricata da partita.json.\n");

        } catch (IOException ex) {
            logArea.appendText("Errore durante il caricamento della partita.\n");
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
    private String estraiSezione(String json, String nomeSezione) {
        Pattern pattern = Pattern.compile("\"" + nomeSezione + "\"\\s*:\\s*\\{(.*?)\\}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    private String estraiString(String json, String chiave) {
        Pattern pattern = Pattern.compile("\"" + chiave + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    private int estraiInt(String json, String chiave) {
        Pattern pattern = Pattern.compile("\"" + chiave + "\"\\s*:\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return 0;
    }

    private boolean estraiBoolean(String json, String chiave) {
        Pattern pattern = Pattern.compile("\"" + chiave + "\"\\s*:\\s*(true|false)");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return Boolean.parseBoolean(matcher.group(1));
        }

        return false;
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
            logArea.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
            nomeNemicoLabel.setText("Nemico: sconfitto");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}