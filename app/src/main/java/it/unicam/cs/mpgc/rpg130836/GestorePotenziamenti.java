package it.unicam.cs.mpgc.rpg130836;

public class GestorePotenziamenti {

    public void potenziaVincitore(Combattente vincitore) {
        if (vincitore == null) {
            throw new IllegalArgumentException("Il vincitore non può essere null.");
        }

        if (vincitore instanceof Potenziabile) {
            Potenziabile potenziabile = (Potenziabile) vincitore;
            potenziabile.potenzia();

            System.out.println(
                    vincitore.getNome()
                            + " si potenzia: "
                            + potenziabile.descrizionePotenziamento()
            );
        }
    }
}