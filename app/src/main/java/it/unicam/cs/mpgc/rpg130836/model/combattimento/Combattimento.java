package it.unicam.cs.mpgc.rpg130836.model.combattimento;

import it.unicam.cs.mpgc.rpg130836.model.output.Output;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Combattente;
import java.util.Objects;

public class Combattimento {

    private final Dado dado;
    private final Output output;

    /*
     * Costruisce un'istanza di Combattimento.
     */
    public Combattimento(Dado dado, Output output) {
        this.dado = Objects.requireNonNull(dado, "Il dado non può essere null.");
        this.output = Objects.requireNonNull(output, "L'output non può essere null.");
    }

    /*
     * Esegue il combattimento tra i combattenti indicati.
     */
    public Combattente combatti(Combattente primo, Combattente secondo) {
        validaCombattenti(primo, secondo);

        stampaInizio(primo, secondo);

        while (primo.isVivo() && secondo.isVivo()) {
            eseguiTurnoSingolo(primo, secondo);
            stampaStato(primo);
            stampaStato(secondo);
            output.stampa("------");
        }

        Combattente vincitore = determinaVincitore(primo, secondo);
        output.stampa(vincitore.getNome() + " ha vinto il combattimento!");

        return vincitore;
    }

    /*
     * Valida il combattenti.
     */
    private void validaCombattenti(Combattente primo, Combattente secondo) {
        if (primo == null || secondo == null) {
            throw new IllegalArgumentException("I combattenti non possono essere null.");
        }

        if (!primo.isVivo() || !secondo.isVivo()) {
            throw new IllegalArgumentException("Entrambi i combattenti devono essere vivi.");
        }
    }

    /*
     * Stampa l'inizio.
     */
    private void stampaInizio(Combattente primo, Combattente secondo) {
        output.stampa("");
        output.stampa("Inizia il combattimento tra " + primo.getNome() + " e " + secondo.getNome());
        output.stampa("-------");
    }

    /*
     * Esegue un singolo turno di combattimento.
     */
    public void eseguiTurnoSingolo(Combattente primo, Combattente secondo) {
        int tiroPrimo;
        int tiroSecondo;

        do {
            tiroPrimo = dado.tira();
            tiroSecondo = dado.tira();

            output.stampa(primo.getNome() + " tira: " + tiroPrimo);
            output.stampa(secondo.getNome() + " tira: " + tiroSecondo);

            if (tiroPrimo == tiroSecondo) {
                output.stampa("Pareggio! Si ritira il dado.");
            }

        } while (tiroPrimo == tiroSecondo);

        Combattente attaccante;
        Combattente difensore;
        int tiroVincente;

        if (tiroPrimo > tiroSecondo) {
            attaccante = primo;
            difensore = secondo;
            tiroVincente = tiroPrimo;
        } else {
            attaccante = secondo;
            difensore = primo;
            tiroVincente = tiroSecondo;
        }

        eseguiAttacchi(attaccante, difensore, tiroVincente);
    }

    /*
     * Esegue l'attacchi.
     */
    private void eseguiAttacchi(Combattente attaccante, Combattente difensore, int tiroVincente) {
        int numeroAttacchi = calcolaNumeroAttacchi(tiroVincente);

        output.stampa(attaccante.getNome() + " attacca " + numeroAttacchi + " volta/e.");

        for (int i = 1; i <= numeroAttacchi; i++) {
            if (!difensore.isVivo()) break;

            int danno = attaccante.attacca(difensore);

            output.stampa(attaccante.getNome() + " colpisce " +
                    difensore.getNome() + " causando " + danno + " danni.");
        }
    }

    /*
     * Calcola il numero di attacchi in base al tiro del dado.
     */
    private int calcolaNumeroAttacchi(int tiroDado) {
        return (tiroDado <= 2) ? 1 : (tiroDado <= 4) ? 2 : 3;
    }

    /*
     * Determina il vincitore del combattimento.
     */
    private Combattente determinaVincitore(Combattente primo, Combattente secondo) {
        if (primo.isVivo() && !secondo.isVivo()) return primo;
        if (secondo.isVivo() && !primo.isVivo()) return secondo;

        throw new IllegalStateException("Combattimento terminato senza vincitore valido");
    }

    /*
     * Stampa lo stato.
     */
    private void stampaStato(Combattente c) {
        output.stampa(c.getNome() +
                " | Vita: " + c.getVita() + "/" + c.getVitaMassima() +
                " | Attacco: " + c.calcolaAttacco() +
                " | Difesa: " + c.calcolaDifesa());
    }
}