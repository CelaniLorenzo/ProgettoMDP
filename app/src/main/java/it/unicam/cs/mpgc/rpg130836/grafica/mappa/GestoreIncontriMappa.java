package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class GestoreIncontriMappa {

    /*
     * Controlla se l'incontro è valido.
     */
    public boolean incontroValido(Nemico nemico) {
        return nemico != null && nemico.isVivo();
    }

    /*
     * Restituisce il nome del nemico.
     */
    public String prendiNomeNemico(Nemico nemico) {
        return nemico.getNome();
    }

    /*
     * Restituisce la vita del nemico.
     */
    public int prendiVitaNemico(Nemico nemico) {
        return nemico.getVita();
    }

    /*
     * Restituisce la vita massima del nemico.
     */
    public int prendiVitaMassimaNemico(Nemico nemico) {
        return nemico.getVitaMassima();
    }
}