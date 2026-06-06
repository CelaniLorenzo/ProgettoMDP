package it.unicam.cs.mpgc.rpg130836;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Gioco {

    private static final String PERCORSO_SALVATAGGIO = "salvataggi/stato_gioco.json";

    private final List<Eroe> eroi;
    private final List<Nemico> nemici;
    private final Combattimento combattimento;
    private final GestorePotenziamenti gestorePotenziamenti;
    private final Persistenza<StatoGioco> salvataggio;
    private final Output output;

    private int incontriDisputati;
    private String ultimoVincitore;

    public Gioco() {
        CreatorePersonaggi creatorePersonaggi = new CreatorePersonaggi();
        this.output = new ConsoleOutput();

        this.eroi = creatorePersonaggi.creaEroi();
        this.nemici = creatorePersonaggi.creaNemici();
        this.combattimento = new Combattimento(new Dado(), output);
        this.gestorePotenziamenti = new GestorePotenziamenti(output);
        this.salvataggio = new SalvataggioJson();
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
        salvataggio.salva(creaStatoGioco(), percorso);
    }

    public StatoGioco caricaStatoGioco(String percorso) throws IOException {
        return salvataggio.carica(percorso);
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