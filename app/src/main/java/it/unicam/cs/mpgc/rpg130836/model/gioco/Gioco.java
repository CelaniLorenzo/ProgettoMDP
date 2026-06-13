package it.unicam.cs.mpgc.rpg130836.model.gioco;

import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.output.ConsoleOutput;
import it.unicam.cs.mpgc.rpg130836.model.output.Output;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Combattente;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.CreatorePersonaggi;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import it.unicam.cs.mpgc.rpg130836.model.stato.ArchivioStatoGioco;
import it.unicam.cs.mpgc.rpg130836.model.stato.StatoGioco;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Gioco {

    private static final String PERCORSO_SALVATAGGIO = "salvataggi/stato_gioco.json";

    private final List<Eroe> eroi;
    private final List<Nemico> nemici;
    private final Combattimento combattimento;
    private final GestorePotenziamenti gestorePotenziamenti;
    private final ArchivioStatoGioco salvataggio;
    private final Output output;

    private int incontriDisputati;
    private String ultimoVincitore;

    public Gioco() {
        this(null, new ConsoleOutput());
    }

    public Gioco(ArchivioStatoGioco salvataggio) {
        this(salvataggio, new ConsoleOutput());
    }

    public Gioco(ArchivioStatoGioco salvataggio, Output output) {
        CreatorePersonaggi creatorePersonaggi = new CreatorePersonaggi();
        this.output = Objects.requireNonNull(output);

        this.eroi = creatorePersonaggi.creaEroi();
        this.nemici = creatorePersonaggi.creaNemici();
        this.combattimento = new Combattimento(new Dado(), output);
        this.gestorePotenziamenti = new GestorePotenziamenti(output);
        this.salvataggio = salvataggio;
        this.incontriDisputati = 0;
        this.ultimoVincitore = "Nessuno";
    }
    private void stampa(String messaggio) {
        output.stampa(messaggio);
    }
    public void avviaPartita() {
        stampa("Inizio partita RPG Iron Hulk.");

        while (esisteEroeVivo() && esisteNemicoVivo()) {
            Eroe eroe = trovaPrimoEroeVivo();
            Nemico nemico = trovaPrimoNemicoVivo();

            disputaIncontro(eroe, nemico);
        }

        stampa(calcolaEsitoPartita());
    }

    private void disputaIncontro(Eroe eroe, Nemico nemico) {
        stampa("");
        stampa("Nuovo combattimento: " + eroe.getNome() + " contro " + nemico.getNome());

        Combattente vincitore = combattimento.combatti(eroe, nemico);

        incontriDisputati++;
        ultimoVincitore = vincitore.getNome();

        stampa("Vincitore incontro: " + vincitore.getNome());

        gestorePotenziamenti.potenziaVincitore(vincitore);
    }

    private boolean esisteEroeVivo() {
        for (Eroe eroe : eroi) {
            if (eroe.isVivo()) {
                return true;
            }
        }

        return false;
    }

    private boolean esisteNemicoVivo() {
        for (Nemico nemico : nemici) {
            if (nemico.isVivo()) {
                return true;
            }
        }

        return false;
    }

    private Eroe trovaPrimoEroeVivo() {
        for (Eroe eroe : eroi) {
            if (eroe.isVivo()) {
                return eroe;
            }
        }

        throw new IllegalStateException("Non ci sono eroi vivi.");
    }

    private Nemico trovaPrimoNemicoVivo() {
        for (Nemico nemico : nemici) {
            if (nemico.isVivo()) {
                return nemico;
            }
        }

        throw new IllegalStateException("Non ci sono nemici vivi.");
    }

    public void salvaPartita() throws IOException {
        salvaPartita(PERCORSO_SALVATAGGIO);
    }

    public void salvaPartita(String percorso) throws IOException {
        salvataggioConfigurato().salva(creaStatoGioco(), percorso);
    }

    public StatoGioco caricaStatoGioco(String percorso) throws IOException {
        return salvataggioConfigurato().carica(percorso);
    }

    private ArchivioStatoGioco salvataggioConfigurato() {
        if (salvataggio == null) {
            throw new IllegalStateException(
                    "Configura un ArchivioStatoGioco per salvare o caricare la partita."
            );
        }

        return salvataggio;
    }

    public StatoGioco creaStatoGioco() {
        return StatoGioco.creaDaPartita(
                eroi,
                nemici,
                incontriDisputati,
                ultimoVincitore,
                calcolaEsitoPartita()
        );
    }

    private String calcolaEsitoPartita() {
        if (esisteEroeVivo() && !esisteNemicoVivo()) {
            return "Gli eroi hanno vinto.";
        }

        if (!esisteEroeVivo() && esisteNemicoVivo()) {
            return "I nemici hanno vinto.";
        }

        return "Partita in corso.";
    }

    public List<Eroe> getEroi() {
        return Collections.unmodifiableList(eroi);
    }

    public List<Nemico> getNemici() {
        return Collections.unmodifiableList(nemici);
    }

    public int getIncontriDisputati() {
        return incontriDisputati;
    }

    public String getUltimoVincitore() {
        return ultimoVincitore;
    }
}
