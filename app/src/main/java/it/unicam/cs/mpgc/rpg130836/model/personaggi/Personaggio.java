package it.unicam.cs.mpgc.rpg130836.model.personaggi;

public abstract class Personaggio implements Combattente {

    private final String nome;
    private int vita;
    private int vitaMassima;
    private int attaccoBase;
    private int difesaBase;

    /*
     * Costruisce un'istanza di Personaggio.
     */
    protected Personaggio(String nome, int vitaMassima, int attaccoBase, int difesaBase) {
        validaParametri(nome, vitaMassima, attaccoBase, difesaBase);

        this.nome = nome;
        this.vitaMassima = vitaMassima;
        this.vita = vitaMassima;
        this.attaccoBase = attaccoBase;
        this.difesaBase = difesaBase;
    }

    /*
     * Controlla che i parametri del personaggio siano validi.
     */
    private static void validaParametri(String nome, int vitaMassima, int attaccoBase, int difesaBase) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del personaggio non può essere vuoto.");
        }
        if (vitaMassima <= 0) {
            throw new IllegalArgumentException("La vita massima deve essere positiva.");
        }
        if (attaccoBase < 0 || difesaBase < 0) {
            throw new IllegalArgumentException("Attacco e difesa non possono essere negativi.");
        }
    }

    /*
     * Esegue un attacco contro il bersaglio indicato.
     */
    @Override
    public int attacca(Combattente avversario) {
        if (avversario == null) {
            throw new IllegalArgumentException("L'avversario non può essere null.");
        }

        int danno = calcolaDanno(avversario);
        avversario.riceviDanno(danno);

        return danno;
    }

    /**
     * /*
     * Restituisce il danno del turno.
     * Metodo separato per il calcolo del danno (estendibile nelle sottoclassi)
     */
    protected int calcolaDanno(Combattente avversario) {
        return Math.max(1, calcolaAttacco() - avversario.calcolaDifesa());
    }

    /*
     * Applica il danno ricevuto dal personaggio.
     */
    @Override
    public void riceviDanno(int danno) {
        if (danno < 0) {
            throw new IllegalArgumentException("Il danno non può essere negativo.");
        }
        vita = Math.max(0, vita - danno);
    }

    /*
     * Calcola il valore totale dell'attacco.
     */
    @Override
    public int calcolaAttacco() {
        return attaccoBase;
    }

    /*
     * Calcola il valore totale della difesa.
     */
    @Override
    public int calcolaDifesa() {
        return difesaBase;
    }

    /*
     * Aumenta l'attacco base del personaggio.
     */
    protected void aumentaAttaccoBase(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("Incremento negativo.");
        }
        attaccoBase += incremento;
    }

    /*
     * Aumenta la difesa base del personaggio.
     */
    protected void aumentaDifesaBase(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("Incremento negativo.");
        }
        difesaBase += incremento;
    }

    /**
     * Aumenta la vita massima e cura il personaggio
     */
    protected void aumentaVitaMassimaERecupera(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("Incremento negativo.");
        }
        vitaMassima += incremento;
        vita += incremento;
    }

    /*
     * Cura il personaggio dei punti vita indicati.
     */
    protected void cura(int puntiVita) {
        if (puntiVita < 0) {
            throw new IllegalArgumentException("Cura negativa.");
        }
        vita = Math.min(vitaMassima, vita + puntiVita);
    }

    /*
     * Controlla se il personaggio è ancora vivo.
     */
    @Override
    public boolean isVivo() {
        return vita > 0;
    }

    /*
     * Restituisce il nome.
     */
    @Override
    public String getNome() {
        return nome;
    }

    /*
     * Restituisce la vita.
     */
    @Override
    public int getVita() {
        return vita;
    }

    /*
     * Restituisce la vita massima.
     */
    @Override
    public int getVitaMassima() {
        return vitaMassima;
    }

    /*
     * Restituisce l'attacco base.
     */
    public int getAttaccoBase() {
        return attaccoBase;
    }

    /*
     * Restituisce la difesa base.
     */
    public int getDifesaBase() {
        return difesaBase;
    }

    /*
     * Restituisce una rappresentazione testuale dell'oggetto.
     */
    @Override
    public String toString() {
        return nome + " [HP: " + vita + "/" + vitaMassima +
                ", ATK: " + attaccoBase +
                ", DEF: " + difesaBase + "]";
    }
}