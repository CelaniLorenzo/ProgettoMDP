package it.unicam.cs.mpgc.rpg130836;

import java.util.Collections;
import java.util.List;

public class GestorePersonaggi {

    private final CreatorePersonaggi creatorePersonaggi;

    public GestorePersonaggi(CreatorePersonaggi creatorePersonaggi) {
        this.creatorePersonaggi = creatorePersonaggi;
    }

    public List<Eroe> creaEroi() {
        return creatorePersonaggi.creaEroi();
    }

    public List<Nemico> creaNemiciMescolati() {
        List<Nemico> nemici = creatorePersonaggi.creaNemici();
        Collections.shuffle(nemici);
        return nemici;
    }

    public void mescolaNemici(List<Nemico> nemici) {
        Collections.shuffle(nemici);
    }
}