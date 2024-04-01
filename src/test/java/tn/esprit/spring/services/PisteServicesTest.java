package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PisteServicesTest {

    @Autowired
    private IPisteServices pisteServices;

    @Test
    public void testAddPiste() {
        // Create a new piste
        Piste piste = new Piste();
        piste.setNamePiste("Red Slope");
        piste.setColor(Color.RED);
        piste.setLength(500);
        piste.setSlope(25);

        // Add the piste
        Piste savedPiste = pisteServices.addPiste(piste);

        // Check if piste is saved successfully
        assertNotNull(savedPiste.getNumPiste());
        assertEquals("Red Slope", savedPiste.getNamePiste());
        assertEquals(Color.RED, savedPiste.getColor());
        assertEquals(500, savedPiste.getLength());
        assertEquals(25, savedPiste.getSlope());
    }

    @Test
    public void testRetrievePiste() {
        // Add a new piste
        Piste piste = new Piste();
        piste.setNamePiste("Blue Slope");
        piste.setColor(Color.BLUE);
        piste.setLength(800);
        piste.setSlope(20);
        Piste savedPiste = pisteServices.addPiste(piste);

        // Retrieve the added piste
        Piste retrievedPiste = pisteServices.retrievePiste(savedPiste.getNumPiste());

        // Check if retrieved piste is not null
        assertNotNull(retrievedPiste);

        // Check if retrieved piste matches the added piste
        assertEquals(savedPiste.getNumPiste(), retrievedPiste.getNumPiste());
        assertEquals(savedPiste.getNamePiste(), retrievedPiste.getNamePiste());
        assertEquals(savedPiste.getColor(), retrievedPiste.getColor());
        assertEquals(savedPiste.getLength(), retrievedPiste.getLength());
        assertEquals(savedPiste.getSlope(), retrievedPiste.getSlope());
    }
}
