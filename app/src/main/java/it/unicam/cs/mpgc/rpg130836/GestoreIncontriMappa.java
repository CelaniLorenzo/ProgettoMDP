package it.unicam.cs.mpgc.rpg130836;

public class GestoreIncontriMappa {

    public boolean incontroValido(Nemico nemico) {
        return nemico != null && nemico.isVivo();
    }
    public String prendiNomeNemico(Nemico nemico) {
        return nemico.getNome();
    }

    public int prendiVitaNemico(Nemico nemico) {
        return nemico.getVita();
    }

    public int prendiVitaMassimaNemico(Nemico nemico) {
        return nemico.getVitaMassima();
    }
}