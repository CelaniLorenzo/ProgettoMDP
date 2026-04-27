package it.unicam.cs.mpgc.rpg130836;

import java.util.HashSet;

public class Reattore extends Personaggio {

    private int vitaMassima;
    private boolean furia = false;
    private boolean attaccoFinaleAttivato = false;

    private HashSet<Personaggio> colpitoDa = new HashSet<>();

    public Reattore() {
        super("Reattore Instabile", 200, 25, 10);
        this.vitaMassima = this.vita;
    }

    public void registraColpo(Personaggio p) {
        colpitoDa.add(p);

        if (colpitoDa.size() >= 3) {
            this.vita = 0;
            System.out.println(" ATTACCO COMBINATO! Reattore distrutto!");
        } else {
            rigenera();
        }
    }

    private void rigenera() {
        this.vita += 10;
        System.out.println(" Il reattore si rigenera!");
    }

    public void resetTurno() {
        colpitoDa.clear();
    }

    public void controllaFuria() {
        if (!furia && vita <= vitaMassima / 2) {
            furia = true;
            System.out.println(" Il reattore entra in modalità FURIA!");
        }
    }

    public boolean isFuria() {
        return furia;
    }

    public void attaccoTriplo(Personaggio bersaglio) {
        System.out.println(" ATTACCO TRIPLO!");
        for (int i = 0; i < 3; i++) {
            this.attacca(bersaglio);
        }
    }

    public boolean checkAttaccoFinale() {
        if (!attaccoFinaleAttivato && vita <= (vitaMassima * 0.3)) {
            attaccoFinaleAttivato = true;
            System.out.println(" ATTACCO FINALE DISPONIBILE!");
            return true;
        }
        return false;
    }
}
