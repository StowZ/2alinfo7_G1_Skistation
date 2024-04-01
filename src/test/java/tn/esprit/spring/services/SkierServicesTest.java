package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SkierServicesTest {

    @Autowired
    private ISkierServices skierServices;

    @Test
    public void testAddSkier() {
        // Create a new skier
        Skier skier = new Skier();
        skier.setFirstName("John");
        skier.setLastName("Doe");
        skier.setDateOfBirth(LocalDate.of(1990, 1, 1));
        skier.setCity("New York");

        // Create a subscription for the skier
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());

        // Set subscription for the skier
        skier.setSubscription(subscription);

        // Add the skier
        Skier savedSkier = skierServices.addSkier(skier);

        // Check if skier is saved successfully
        assertNotNull(savedSkier.getNumSkier());
        assertEquals("John", savedSkier.getFirstName());
        assertEquals("Doe", savedSkier.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), savedSkier.getDateOfBirth());
        assertEquals("New York", savedSkier.getCity());
        assertNotNull(savedSkier.getSubscription());
        assertEquals(TypeSubscription.ANNUAL, savedSkier.getSubscription().getTypeSub());
        assertNotNull(savedSkier.getSubscription().getStartDate());
        assertNotNull(savedSkier.getSubscription().getEndDate());
    }
}
