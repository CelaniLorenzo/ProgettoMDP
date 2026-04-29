package it.unicam.cs.mpgc.rpg130836;

import java.util.ArrayList;
import java.util.List;

public class CreatorePersonaggi {

    public List<Eroe> creaEroi() {
        List<Eroe> eroi = new ArrayList<>();

        eroi.add(new IronMan());
        eroi.add(new IronHeart());
        eroi.add(new IronHulk());

        return eroi;
    }

    public List<Nemico> creaNemici() {
        List<Nemico> nemici = new ArrayList<>();

        nemici.add(new Reattore());
        nemici.add(new SandMan());
        nemici.add(new Ultron());

        return nemici;
    }
}