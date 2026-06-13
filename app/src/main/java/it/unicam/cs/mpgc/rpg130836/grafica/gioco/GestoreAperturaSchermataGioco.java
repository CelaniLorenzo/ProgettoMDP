package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.AccessoSchermataGioco;
import java.util.Objects;

public class GestoreAperturaSchermataGioco {

    private final AccessoSchermataGioco accessoSchermataGioco;

    public GestoreAperturaSchermataGioco(
            AccessoSchermataGioco accessoSchermataGioco
    ) {
        this.accessoSchermataGioco =
                Objects.requireNonNull(accessoSchermataGioco);
    }

    public void mostra() {
        accessoSchermataGioco.mostraSchermataGioco();
    }
}
