package it.unicam.cs.mpgc.rpg130836;

public class Armatura implements Potenziabile {

    private final String nome;
    private final AbilitaSpeciale abilitaSpeciale;
    private int bonusAttacco;
    private int bonusDifesa;

    public Armatura(String nome, AbilitaSpeciale abilitaSpeciale, int bonusAttacco, int bonusDifesa) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome dell'armatura non può essere vuoto.");
        }

        if (abilitaSpeciale == null) {
            throw new IllegalArgumentException("L'abilità speciale non può essere null.");
        }

        if (bonusAttacco < 0 || bonusDifesa < 0) {
            throw new IllegalArgumentException("I bonus dell'armatura non possono essere negativi.");
        }

        this.nome = nome;
        this.abilitaSpeciale = abilitaSpeciale;
        this.bonusAttacco = bonusAttacco;
        this.bonusDifesa = bonusDifesa;
    }
    public int getBonusAttaccoTotale() {
        return bonusAttacco + abilitaSpeciale.getBonusAttacco();
    }

    public int getBonusDifesaTotale() {
        return bonusDifesa + abilitaSpeciale.getBonusDifesa();
    }

    @Override
    public void potenzia() {
        bonusAttacco += 2;
        bonusDifesa += 2;
        abilitaSpeciale.potenzia();
    }

    @Override
    public String descrizionePotenziamento() {
        return nome + " (+" + bonusAttacco + " att, +" + bonusDifesa + " dif) con "
                + abilitaSpeciale.descrizionePotenziamento();
    }

    public String getNome() {
        return nome;
    }

    public AbilitaSpeciale getAbilitaSpeciale() {
        return abilitaSpeciale;
    }

    public int getBonusAttacco() {
        return bonusAttacco;
    }

    public int getBonusDifesa() {
        return bonusDifesa;
    }
}
