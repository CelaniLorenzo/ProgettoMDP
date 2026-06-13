package it.unicam.cs.mpgc.rpg130836.model.output;

public class ConsoleOutput implements Output {
        @Override
        public void stampa(String messaggio) {
            System.out.println(messaggio);
        }
    }
