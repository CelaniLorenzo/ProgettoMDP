package it.unicam.cs.mpgc.rpg130836;

import javafx.scene.control.TextArea;
import java.util.List;

public class GestoreCombattimentoMappa {

    private final Dado dado;

    public GestoreCombattimentoMappa(Dado dado) {
        this.dado = dado;
    }

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

    public void eseguiTurno(Eroe eroeAttuale,
                            Nemico nemicoAttuale,
                            TextArea logMappa) {

        Combattimento combattimentoMappa =
                new Combattimento(dado, new TextAreaOutput(logMappa));

        combattimentoMappa.eseguiTurnoSingolo(eroeAttuale, nemicoAttuale);
    }

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
    public boolean puoPotenziareMappa(boolean potenziamentoDisponibile,
                                      TextArea logMappa) {
        if (!potenziamentoDisponibile) {
            logMappa.appendText("Non hai potenziamenti disponibili.\n");
            return false;
        }

        return true;
    }

    public void potenziaSquadra(List<Eroe> eroiDisponibili,
                                GestorePotenziamenti gestorePotenziamentiMappa) {
        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo()) {
                gestorePotenziamentiMappa.potenziaVincitore(eroe);
            }
        }
    }

    public boolean puoPotenziareEroe(Eroe eroeDaPotenziare,
                                     TextArea logMappa) {
        if (eroeDaPotenziare == null) {
            logMappa.appendText("Non c'è nessun vincitore da potenziare.\n");
            return false;
        }

        return true;
    }
    public boolean nemicoSconfittoValido(Nemico nemicoAttuale) {
        return nemicoAttuale != null && !nemicoAttuale.isVivo();
    }

    public void scriviNemicoSconfitto(Nemico nemicoAttuale,
                                      TextArea logMappa) {
        if (logMappa != null) {
            logMappa.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
        }
    }

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