package it.unicam.cs.mpgc.rpg130836;

public class Fulmine extends Personaggio {

    public Fulmine() {
        super("Fulmine", 100, 15, 5);
    }

    @Override
    public void usaAbilita(Personaggio nemico) {
        int danno = (attacco * 2) + lanciaDado();
        nemico.setVita(nemico.getVita() - danno);
        System.out.println(nome + " usa SCARICA → danno: " + danno);
    }
}
