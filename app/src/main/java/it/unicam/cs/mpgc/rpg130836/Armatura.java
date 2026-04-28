package it.unicam.cs.mpgc.rpg130836;

public abstract class Armatura implements Potenziabile {

    private final String nome;
    private int livello;
    private final AbilitaSpeciale abilitaSpeciale;

    protected Armatura(String nome, AbilitaSpeciale abilitaSpeciale) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome dell'armatura non può essere vuoto");
        }

        if (abilitaSpeciale == null) {
            throw new IllegalArgumentException("L'abilità speciale non può essere nulla");
        }

        this.nome = nome;
        this.livello = 1;
        this.abilitaSpeciale = abilitaSpeciale;
    }

    public String getNome() {
        return nome;
    }

    public int getLivello() {
        return livello;
    }

    public AbilitaSpeciale getAbilitaSpeciale() {
        return abilitaSpeciale;
    }

    public int getBonusAttacco() {
        return abilitaSpeciale.getBonusAttacco();
    }

    public int getBonusDifesa() {
        return abilitaSpeciale.getBonusDifesa();
    }

    @Override
    public void potenzia() {
        livello++;
        abilitaSpeciale.potenzia();
    }
}