package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.abilita.Reattore;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class RisorseImmagini {

    /*
     * Restituisce il percorso dell'immagine dell'eroe.
     */
    public String percorsoEroe(Eroe eroe) {
        if (eroe == null) {
            return "/images/iron_hulk.png";
        }

        return switch (eroe.getNome()) {
            case "Iron Hulk" -> "/images/iron_hulk.png";
            case "Iron Heart" -> "/images/iron_heart.png";
            case "Iron Man" -> "/images/iron_man.png";
            default -> "/images/iron_hulk.png";
        };
    }

    /*
     * Restituisce il percorso dell'immagine del nemico.
     */
    public String percorsoNemico(Nemico nemico) {
        if (nemico == null) {
            return "/images/ultron.png";
        }

        return switch (nemico.getNome()) {
            case "Reattore" -> "/images/reattore.png";
            case "Sandman" -> "/images/sandman.png";
            case "Ultron" -> "/images/ultron.png";
            default -> "/images/ultron.png";
        };
    }

    /*
     * Restituisce il percorso dell'immagine del potere.
     */
    public String percorsoPotere(Eroe eroe) {
        if (eroe == null) {
            return "/images/laser.png";
        }

        return switch (eroe.getNome()) {
            case "Iron Hulk" -> "/images/roccia.png";
            case "Iron Heart" -> "/images/bolla.png";
            case "Iron Man" -> "/images/laser.png";
            default -> "/images/laser.png";
        };
    }
}