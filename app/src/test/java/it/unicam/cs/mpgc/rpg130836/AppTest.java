package it.unicam.cs.mpgc.rpg130836;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appSiIstanzia() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest);
    }
}
