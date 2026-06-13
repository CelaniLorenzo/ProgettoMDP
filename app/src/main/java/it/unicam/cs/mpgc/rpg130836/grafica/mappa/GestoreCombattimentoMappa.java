package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.grafica.gioco.TextAreaOutput;
import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.control.TextArea;

public class GestoreCombattimentoMappa {

    private final Dado dado;

    /*
     * Costruisce un'istanza di GestoreCombattimentoMappa.
     */
    public GestoreCombattimentoMappa(Dado dado) {
        this.dado = dado;
    }

    /*
     * Controlla se è possibile combattere.
     */
    public boolean puoCombattere(Eroe eroeAttuale,
                                 boolean eroeSelezionato,
                                 Nemico nemicoAttuale,
                                 TextArea logMappa) {

        if (eroeAttuale == null || !eroeSelezionato) {
            logMappa.appendText("Devi prima scegliere un eroe.\n");
            return false;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            logMappa.appendText("Devi prima toccare un nemico sulla mappa.\n");
            return false;
        }

        if (!eroeAttuale.isVivo()) {
            logMappa.appendText(eroeAttuale.getNome()
                    + " è sconfitto e non può combattere.\n");
            return false;
        }

        return true;
    }

    /*
     * Esegue il turno.
     */
    public void eseguiTurno(Eroe eroeAttuale,
                            Nemico nemicoAttuale,
                            TextArea logMappa) {

        Combattimento combattimentoMappa =
                new Combattimento(dado, new TextAreaOutput(logMappa));

        combattimentoMappa.eseguiTurnoSingolo(eroeAttuale, nemicoAttuale);
    }

    /*
     * Scrive nel log le vite aggiornate.
     */
    public void scriviViteAggiornate(Eroe eroeAttuale,
                                     Nemico nemicoAttuale,
                                     TextArea logMappa) {

        logMappa.appendText(
                eroeAttuale.getNome() + " vita: "
                        + eroeAttuale.getVita() + "/"
                        + eroeAttuale.getVitaMassima() + "\n"
        );

        logMappa.appendText(
                nemicoAttuale.getNome() + " vita: "
                        + nemicoAttuale.getVita() + "/"
                        + nemicoAttuale.getVitaMassima() + "\n"
        );
    }

    /*
     * Aggiorna il log delle vite.
     */
    public void aggiornaLogVite(Eroe eroe,
                                Nemico nemico,
                                TextArea logMappa) {

        logMappa.appendText(
                eroe.getNome()
                        + " vita: "
                        + eroe.getVita()
                        + "/"
                        + eroe.getVitaMassima()
                        + "\n"
        );

        logMappa.appendText(
                nemico.getNome()
                        + " vita: "
                        + nemico.getVita()
                        + "/"
                        + nemico.getVitaMassima()
                        + "\n"
        );
    }

    /*
     * Controlla se è possibile potenziare dalla mappa.
     */
    public boolean puoPotenziareMappa(boolean potenziamentoDisponibile,
                                      TextArea logMappa) {
        if (!potenziamentoDisponibile) {
            logMappa.appendText("Non hai potenziamenti disponibili.\n");
            return false;
        }

        return true;
    }

    /*
     * Potenzia la squadra.
     */
    public void potenziaSquadra(List<Eroe> eroiDisponibili,
                                GestorePotenziamenti gestorePotenziamentiMappa) {
        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo()) {
                gestorePotenziamentiMappa.potenziaVincitore(eroe);
            }
        }
    }

    /*
     * Controlla se è possibile potenziare l'eroe.
     */
    public boolean puoPotenziareEroe(Eroe eroeDaPotenziare,
                                     TextArea logMappa) {
        if (eroeDaPotenziare == null) {
            logMappa.appendText("Non c'è nessun vincitore da potenziare.\n");
            return false;
        }

        return true;
    }

    /*
     * Controlla se il nemico sconfitto è valido.
     */
    public boolean nemicoSconfittoValido(Nemico nemicoAttuale) {
        return nemicoAttuale != null && !nemicoAttuale.isVivo();
    }

    /*
     * Scrive il nemico sconfitto.
     */
    public void scriviNemicoSconfitto(Nemico nemicoAttuale,
                                      TextArea logMappa) {
        if (logMappa != null) {
            logMappa.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
        }
    }

    /*
     * Prepara il potenziamento di squadra.
     */
    public void preparaPotenziamentoSquadra(DatiCombattimentoMappa datiCombattimentoMappa,
                                            TextArea logMappa) {
        datiCombattimentoMappa.setPotenziamentoDisponibile(true);
        datiCombattimentoMappa.setEroeDaPotenziare(null);

        if (logMappa != null) {
            logMappa.appendText(
                    "Attacco di squadra riuscito! Premi Potenzia vincitore per potenziare tutti gli eroi.\n"
            );
        }
    }

    /*
     * Prepara il potenziamento dell'eroe.
     */
    public void preparaPotenziamentoEroe(DatiCombattimentoMappa datiCombattimentoMappa,
                                         Eroe eroeAttuale,
                                         TextArea logMappa) {
        datiCombattimentoMappa.setPotenziamentoDisponibile(true);
        datiCombattimentoMappa.setEroeDaPotenziare(eroeAttuale);

        if (logMappa != null && eroeAttuale != null) {
            logMappa.appendText(
                    eroeAttuale.getNome()
                            + " può essere potenziato! Premi Potenzia vincitore.\n"
            );
        }
    }
}