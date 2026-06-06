package it.unicam.cs.mpgc.rpg130836;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SalvataggioJson implements Persistenza<StatoGioco> {

    private final Gson gson;

    public SalvataggioJson() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void salva(StatoGioco statoGioco, String percorso) throws IOException {
        if (statoGioco == null) {
            throw new IllegalArgumentException("Lo stato del gioco non può essere null.");
        }

        Path path = Paths.get(percorso);
        creaCartellaSeNecessaria(path);

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            gson.toJson(statoGioco, writer);
        }
    }

    @Override
    public StatoGioco carica(String percorso) throws IOException {
        Path path = Paths.get(percorso);

        if (!Files.exists(path)) {
            throw new IOException("File di salvataggio non trovato: " + percorso);
        }

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            StatoGioco statoGioco = gson.fromJson(reader, StatoGioco.class);

            if (statoGioco == null) {
                throw new IOException("Il file JSON è vuoto o non valido.");
            }

            return statoGioco;
        }
    }

    private void creaCartellaSeNecessaria(Path path) throws IOException {
        Path cartella = path.getParent();

        if (cartella != null) {
            Files.createDirectories(cartella);
        }
    }
}