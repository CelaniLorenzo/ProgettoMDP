package it.unicam.cs.mpgc.rpg130836.model.output;

public class ConsoleOutput implements Output {

        /*
         * Stampa il messaggio ricevuto.
         */
        @Override
        public void stampa(String messaggio) {
            System.out.println(messaggio);
        }
    }
