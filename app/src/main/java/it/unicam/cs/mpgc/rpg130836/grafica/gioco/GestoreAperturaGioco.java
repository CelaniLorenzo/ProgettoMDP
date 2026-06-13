package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.AccessoSchermataGioco;
import java.util.Objects;

public class GestoreAperturaGioco {

    private final AccessoSchermataGioco accessoSchermataGioco;

    public GestoreAperturaGioco(AccessoSchermataGioco accessoSchermataGioco) {
        this.accessoSchermataGioco =
                Objects.requireNonNull(accessoSchermataGioco);
    }

    public void apriGioco() {
        accessoSchermataGioco.mostraSchermataGioco();
    }
}
