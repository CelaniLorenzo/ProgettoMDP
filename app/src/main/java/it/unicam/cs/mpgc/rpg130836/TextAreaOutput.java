package it.unicam.cs.mpgc.rpg130836;

import javafx.scene.control.TextArea;

import java.util.Objects;

public class TextAreaOutput implements Output {

    private final TextArea textArea;

    public TextAreaOutput(TextArea textArea) {
        this.textArea = Objects.requireNonNull(textArea, "La TextArea non può essere null.");
    }

    @Override
    public void stampa(String messaggio) {
        textArea.appendText(messaggio + "\n");
    }
}