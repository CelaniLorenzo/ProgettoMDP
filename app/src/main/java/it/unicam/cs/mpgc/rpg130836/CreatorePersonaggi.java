package it.unicam.cs.mpgc.rpg130836;

import java.util.ArrayList;
import java.util.List;

public class CreatorePersonaggi {

    public List<Eroe> creaEroi() {
        List<Eroe> eroi = new ArrayList<>();

        eroi.add(new Eroe("Iron Hulk", 120, 25, 15,
                new Armatura("Armatura Iron Hulk", new ForzaHulk(), 6, 5)));

        eroi.add(new Eroe("Iron Heart", 90, 18, 12,
                new Armatura("Armatura Iron Heart", new BollaRosa(), 4, 4)));

        eroi.add(new Eroe("Iron Man", 100, 20, 10,
                new Armatura("Armatura Iron Man", new Repulsore(), 5, 3)));

        return eroi;
    }


    public List<Nemico> creaNemici() {
        List<Nemico> nemici = new ArrayList<>();

        nemici.add(new Nemico("Ultron", 110, 22, 14));
        nemici.add(new Nemico("Sandman", 100, 20, 12));
        nemici.add(new Nemico("Reattore", 80, 15, 8));

        return nemici;
    }
}
