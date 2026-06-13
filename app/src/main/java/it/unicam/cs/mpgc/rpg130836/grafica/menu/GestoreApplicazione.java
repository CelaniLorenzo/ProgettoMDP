package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.NavigazioneApplicazione;
import java.util.Objects;
import javafx.stage.Stage;

public class GestoreApplicazione {

    private final SchermataMenu schermataMenu;
    private final AzioniMenu azioniMenu;

    public GestoreApplicazione(NavigazioneApplicazione navigazione) {
        this(new SchermataMenu(), new AzioniMenu(navigazione));
    }

    public GestoreApplicazione(
            SchermataMenu schermataMenu,
            AzioniMenu azioniMenu
    ) {
        this.schermataMenu = Objects.requireNonNull(schermataMenu);
        this.azioniMenu = Objects.requireNonNull(azioniMenu);
    }

    public void avvia(Stage stage) {
        schermataMenu.mostra(
                stage,
                azioniMenu::nuovaPartita,
                azioniMenu::caricaPartita,
                stage::close
        );
    }
}
