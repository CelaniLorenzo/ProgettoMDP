package it.unicam.cs.mpgc.rpg130836.grafica.Avvio;

import it.unicam.cs.mpgc.rpg130836.grafica.Animazioni.GestoreAnimazioni;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.AperturaSchermataGioco;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.ControllerGioco;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.ControllerSalvataggio;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.GestoreCombattimentoGioco;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.GestoreSchermataGioco;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.SchermataGioco;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.AperturaMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.ControllerCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.ControllerMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.CreatoreElementiBaseMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreAvvioIncontroMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreCollisioniMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreElementiMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreEroiMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreIncontriMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreInizializzazioneMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreInterfacciaMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreMovimentoEroe;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreMovimentoNemiciMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreNemiciMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreNemiciVisualiMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestorePulsantiMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreResetPartita;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreRimozioneNemiciMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreTurnoCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.RisorseImmagini;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.SchermataMappa;
import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.CreatorePersonaggi;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.GestorePersonaggi;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import it.unicam.cs.mpgc.rpg130836.model.stato.StatoPartitaCorrente;
import it.unicam.cs.mpgc.rpg130836.persistenza.GestoreSalvataggioPartita;
import it.unicam.cs.mpgc.rpg130836.persistenza.SalvataggioJson;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DatiApplicazioneRpg {

    public static final String FILE_SALVATAGGIO = "partita.json";

    public final Stage stage;
    public final AperturaMappa aperturaMappa = new AperturaMappa();
    public final StatoPartitaCorrente statoPartitaCorrente =
            new StatoPartitaCorrente();
    public final GestoreCollisioniMappa gestoreCollisioniMappa =
            new GestoreCollisioniMappa();
    public final GestoreElementiMappa gestoreElementiMappa =
            new GestoreElementiMappa();
    public final GestoreInizializzazioneMappa gestoreInizializzazioneMappa =
            new GestoreInizializzazioneMappa();
    public final GestoreRimozioneNemiciMappa gestoreRimozioneNemiciMappa =
            new GestoreRimozioneNemiciMappa();
    public final AperturaSchermataGioco aperturaSchermataGioco =
            new AperturaSchermataGioco();
    public final CreatorePersonaggi creatorePersonaggi = new CreatorePersonaggi();
    public final SalvataggioJson salvataggioJson = new SalvataggioJson();
    public final RisorseImmagini risorseImmagini = new RisorseImmagini();
    public final GestoreNemiciMappa gestoreNemiciMappa = new GestoreNemiciMappa();
    public final GestoreAnimazioni gestoreAnimazioni = new GestoreAnimazioni();
    public final GestoreMovimentoEroe gestoreMovimentoEroe =
            new GestoreMovimentoEroe();
    public final SchermataMappa schermataMappa = new SchermataMappa();
    public final CreatoreElementiBaseMappa creatoreElementiBaseMappa =
            new CreatoreElementiBaseMappa(schermataMappa);
    public final SchermataGioco schermataGioco = new SchermataGioco();
    public final GestoreInterfacciaMappa gestoreInterfacciaMappa =
            new GestoreInterfacciaMappa();
    public final GestoreSchermataGioco gestoreSchermataGioco =
            new GestoreSchermataGioco(schermataGioco);
    public final GestoreTurnoCombattimentoMappa gestoreTurnoCombattimentoMappa =
            new GestoreTurnoCombattimentoMappa();
    public final DatiCombattimentoMappa datiCombattimentoMappa =
            new DatiCombattimentoMappa();
    public final GestorePulsantiMappa gestorePulsantiMappa =
            new GestorePulsantiMappa();
    public final ControllerMappa controllerMappa =
            new ControllerMappa(gestorePulsantiMappa);
    public final GestoreEroiMappa gestoreEroiMappa =
            new GestoreEroiMappa(risorseImmagini, schermataMappa);
    public final GestoreNemiciVisualiMappa gestoreNemiciVisualiMappa =
            new GestoreNemiciVisualiMappa(risorseImmagini, schermataMappa);
    public final GestoreSalvataggioPartita gestoreSalvataggioPartita =
            new GestoreSalvataggioPartita(
                    salvataggioJson,
                    FILE_SALVATAGGIO
            );
    public final ControllerSalvataggio controllerSalvataggio =
            new ControllerSalvataggio(
                    gestoreSalvataggioPartita,
                    FILE_SALVATAGGIO
            );
    public final GestorePersonaggi gestorePersonaggi =
            new GestorePersonaggi(creatorePersonaggi);
    public final ControllerGioco controllerGioco =
            new ControllerGioco(gestorePersonaggi);
    public final GestoreAvvioIncontroMappa gestoreAvvioIncontroMappa =
            new GestoreAvvioIncontroMappa();
    public final GestoreResetPartita gestoreResetPartita =
            new GestoreResetPartita(
                    gestorePersonaggi,
                    gestoreMovimentoEroe
            );
    public final ControllerCombattimentoMappa controllerCombattimentoMappa =
            new ControllerCombattimentoMappa(
                    gestoreAvvioIncontroMappa,
                    gestorePulsantiMappa
            );
    public final String[] mappe = {
            "/images/mappa.png",
            "/images/mappa_2.png",
            "/images/mappa_3.png"
    };

    public GestorePotenziamenti gestorePotenziamentiMappa;
    public Button attaccoSquadraButton;
    public Button potenziaButton;
    public boolean potenziamentoDisponibile = false;
    public Eroe eroeDaPotenziare;
    public int numeroIncontro = 0;
    public String nomeNemicoAttuale = "";
    public int vitaNemicoAttuale = 0;
    public int vitaNemicoMassima = 0;
    public Dado dado = new Dado();
    public Combattimento combattimento;
    public boolean giocatoreAttaccaPerPrimo = true;
    public int vitaGiocatoreAttuale = 100;
    public int vitaGiocatoreMassima = 100;
    public String nomeGiocatoreAttuale = "Nessun eroe";
    public int dannoGiocatore = 0;
    public boolean eroeSelezionato = false;
    public GestorePotenziamenti gestorePotenziamentiGioco;
    public GestoreCombattimentoGioco gestoreCombattimentoGioco;
    public GestoreIncontriMappa gestoreIncontriMappa;
    public List<Eroe> eroiDisponibili;
    public Eroe eroeAttuale;
    public List<Nemico> nemiciDisponibili;
    public Nemico nemicoAttuale;
    public boolean attaccoSquadraUsato = false;
    public ImageView eroeMappa;
    public GestoreMovimentoNemiciMappa gestoreMovimentoNemiciMappa;
    public List<ImageView> eroiMappa = new ArrayList<>();
    public List<Eroe> eroiAssociatiMappa = new ArrayList<>();
    public List<ImageView> nemiciMappa = new ArrayList<>();
    public List<Label> nomiNemiciMappa = new ArrayList<>();
    public List<Nemico> nemiciAssociatiMappa = new ArrayList<>();
    public Pane mappaPaneAttuale;
    public TextArea logMappa;
    public boolean potenziamentoDiSquadra = false;
    public boolean combattimentoMappaAttivo = false;
    public GestoreCombattimentoMappa gestoreCombattimentoMappa;
    public int indiceMappaAttuale = 0;
    public Button combattiMappaButton;
    public Button attaccoSquadraMappaButton;
    public Button potenziaMappaButton;

    /*
     * Costruisce l'oggetto che contiene tutti i dati principali
     * dell'applicazione RPG.
     *
     * Salva lo Stage principale dell'applicazione, cioè la finestra
     * su cui verranno mostrate le varie schermate del gioco.
     */
    public DatiApplicazioneRpg(Stage stage) {
        this.stage = Objects.requireNonNull(stage);
    }
}