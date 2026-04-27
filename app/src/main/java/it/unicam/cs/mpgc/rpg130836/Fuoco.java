package it.unicam.cs.mpgc.rpg130836;

public class Fuoco extends Personaggio {

    public Fuoco() {
        super("Fuoco", 110, 18, 3);
    }

    @Override
    public void usaAbilita(Personaggio nemico) {
        int danno = attacco + 20 + lanciaDado();
        nemico.setVita(nemico.getVita() - danno);
        System.out.println(nome + " usa PALLA DI FUOCO → danno: " + danno);
    }
}
