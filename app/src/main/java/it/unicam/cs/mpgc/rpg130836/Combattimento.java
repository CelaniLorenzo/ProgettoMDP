package it.unicam.cs.mpgc.rpg130836;

import java.util.Scanner;
import java.util.Random;

public class Combattimento {

    private Random random = new Random();

    private int lanciaDado() {
        return random.nextInt(6) + 1;
    }

    private Personaggio scegliAttaccante(Personaggio[] squadra, Reattore reattore) {

        while (true) {

            int max = -1;
            Personaggio attaccante = null;
            int conteggioMax = 0;

            for (Personaggio p : squadra) {
                if (!p.isVivo()) continue;

                int dado = lanciaDado();
                System.out.println(p.getNome() + " tira: " + dado);

                if (dado > max) {
                    max = dado;
                    attaccante = p;
                    conteggioMax = 1;
                } else if (dado == max) {
                    conteggioMax++;
                }
            }

            int dadoReattore = lanciaDado();
            System.out.println("Reattore tira: " + dadoReattore);

            if (dadoReattore > max) {
                return reattore;
            } else if (dadoReattore == max) {
                conteggioMax++;
            }

            if (conteggioMax == 1) {
                return attaccante;
            }

            System.out.println("⚖️ Pareggio! Si rilancia...\n");
        }
    }

    private void attaccoFinale(Personaggio[] squadra, Reattore reattore) {

        System.out.println(" ATTACCO FINALE COMBINATO!");

        int dannoTotale = 0;

        for (Personaggio p : squadra) {
            if (p.isVivo()) {
                int danno = p.attacco + p.lanciaDado();
                dannoTotale += danno;
                System.out.println(p.getNome() + " contribuisce: " + danno);
            }
        }

        reattore.setVita(reattore.getVita() - dannoTotale);

        System.out.println("DANNO TOTALE: " + dannoTotale);
    }

    private Personaggio scegliBersaglio(Personaggio[] squadra) {

        Personaggio bersaglio = null;

        while (bersaglio == null) {
            int index = random.nextInt(squadra.length);
            if (squadra[index].isVivo()) {
                bersaglio = squadra[index];
            }
        }

        return bersaglio;
    }

    public void avvia(Personaggio[] squadra, Reattore reattore) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== INIZIA IL COMBATTIMENTO ===");

        while (reattore.isVivo()) {

            if (reattore.checkAttaccoFinale()) {
                System.out.println("Vuoi usare l'attacco finale? (1=Si, 2=No)");
                int scelta = scanner.nextInt();

                if (scelta == 1) {
                    attaccoFinale(squadra, reattore);
                    if (!reattore.isVivo()) break;
                }
            }

            Personaggio attaccante = scegliAttaccante(squadra, reattore);

            if (attaccante == reattore) {

                reattore.controllaFuria();
                Personaggio bersaglio = scegliBersaglio(squadra);

                if (reattore.isFuria()) {
                    reattore.attaccoTriplo(bersaglio);
                } else {
                    reattore.attacca(bersaglio);
                }

            } else {

                System.out.println("\nTurno di " + attaccante.getNome());
                System.out.println("1) Attacco  2) Abilità  3) Difendi");

                int scelta = scanner.nextInt();

                if (scelta == 1) attaccante.attacca(reattore);
                else if (scelta == 2) attaccante.usaAbilita(reattore);
                else attaccante.difendi();

                reattore.registraColpo(attaccante);
            }

            reattore.resetTurno();

            boolean tuttiMorti = true;

            for (Personaggio p : squadra) {
                if (p.isVivo()) {
                    tuttiMorti = false;
                    break;
                }
            }

            if (tuttiMorti) {
                System.out.println("GAME OVER");
                scanner.close();
                return;
            }
        }

        System.out.println("=== VITTORIA ===");
        scanner.close();
    }
}