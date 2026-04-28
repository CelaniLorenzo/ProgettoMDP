package it.unicam.cs.mpgc.rpg130836;

public class Combattimento {

    private final Dado dado;

    public Combattimento(Dado dado) {
        this.dado = dado;
    }

    public Personaggio combatti(Personaggio primo, Personaggio secondo) {
        System.out.println();
        System.out.println("Inizia il combattimento tra " + primo.getNome() + " e " + secondo.getNome());
        System.out.println("--------------------------------");

        while (primo.isVivo() && secondo.isVivo()) {
            eseguiTurno(primo, secondo);

            System.out.println(primo.stato());
            System.out.println(secondo.stato());
            System.out.println("--------------------------------");
        }

        if (primo.isVivo()) {
            System.out.println(primo.getNome() + " ha vinto il combattimento!");
            return primo;
        } else {
            System.out.println(secondo.getNome() + " ha vinto il combattimento!");
            return secondo;
        }
    }

    private void eseguiTurno(Personaggio primo, Personaggio secondo) {
        int tiroPrimo;
        int tiroSecondo;

        do {
            tiroPrimo = dado.tira();
            tiroSecondo = dado.tira();

            System.out.println(primo.getNome() + " tira il dado: " + tiroPrimo);
            System.out.println(secondo.getNome() + " tira il dado: " + tiroSecondo);

            if (tiroPrimo == tiroSecondo) {
                System.out.println("Pareggio! Si ritira il dado.");
            }

        } while (tiroPrimo == tiroSecondo);

        Personaggio attaccante;
        Personaggio difensore;
        int tiroVincente;

        if (tiroPrimo > tiroSecondo) {
            attaccante = primo;
            difensore = secondo;
            tiroVincente = tiroPrimo;
        } else {
            attaccante = secondo;
            difensore = primo;
            tiroVincente = tiroSecondo;
        }

        int numeroAttacchi = calcolaNumeroAttacchi(tiroVincente);

        System.out.println(attaccante.getNome() + " attacca " + numeroAttacchi + " volta/e.");

        for (int i = 1; i <= numeroAttacchi; i++) {
            if (!difensore.isVivo()) {
                break;
            }

            int danno = attaccante.attacca(difensore);

            System.out.println(
                    attaccante.getNome()
                            + " colpisce "
                            + difensore.getNome()
                            + " causando "
                            + danno
                            + " danni."
            );
        }
    }

    private int calcolaNumeroAttacchi(int tiroDado) {
        if (tiroDado <= 2) {
            return 1;
        } else if (tiroDado <= 4) {
            return 2;
        } else {
            return 3;
        }
    }
}