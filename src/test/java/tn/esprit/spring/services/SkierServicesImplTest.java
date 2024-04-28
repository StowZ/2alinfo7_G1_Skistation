package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkierServicesImplTest {

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SkierServicesImpl skierServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllSkiers() {
        // Given
        List<Skier> skiers = Arrays.asList(
                new Skier(1L, "John", "Doe", LocalDate.now(), "New York", null, null, null),
                new Skier(2L, "Jane", "Smith", LocalDate.now(), "Los Angeles", null, null, null)
        );
        when(skierRepository.findAll()).thenReturn(skiers);

        // When
        List<Skier> retrievedSkiers = skierServices.retrieveAllSkiers();

        // Then
        assertEquals(2, retrievedSkiers.size());
        assertEquals("John", retrievedSkiers.get(0).getFirstName());
        // Similarly, check other attributes
    }

    @Test
    void addSkier() {
        // Given
        Skier skierToAdd = new Skier(null, "John", "Doe", LocalDate.now(), "New York", null, null, null);
        Subscription subscription = new Subscription(1L, LocalDate.now(), LocalDate.now().plusYears(1), 100.0f, TypeSubscription.ANNUAL);
        skierToAdd.setSubscription(subscription);
        when(skierRepository.save(any(Skier.class))).thenReturn(skierToAdd);

        // When
        Skier addedSkier = skierServices.addSkier(skierToAdd);

        // Then
        assertEquals(skierToAdd, addedSkier);
        // You can also verify if save method of repository was called with correct arguments
        verify(skierRepository, times(1)).save(any(Skier.class));
    }


    @Test
    void removeSkier() {
        // Given
        Long skierId = 1L;

        // When
        skierServices.removeSkier(skierId);

        // Then
        verify(skierRepository, times(1)).deleteById(skierId);
    }

    @Test
    void retrieveSkier() {
        // Given
        Skier skier = new Skier(1L, "John", "Doe", LocalDate.now(), "New York", null, null, null);
        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));

        // When
        Skier retrievedSkier = skierServices.retrieveSkier(1L);

        // Then
        assertEquals(skier, retrievedSkier);
    }


    @Test
    void retrieveSkiersBySubscriptionType() {
        // Given
        List<Skier> skiers = Arrays.asList(
                new Skier(1L, "John", "Doe", LocalDate.now(), "New York", null, null, null),
                new Skier(2L, "Jane", "Smith", LocalDate.now(), "Los Angeles", null, null, null)
        );
        when(skierRepository.findBySubscription_TypeSub(TypeSubscription.ANNUAL)).thenReturn(skiers);

        // When
        List<Skier> retrievedSkiers = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        // Then
        assertEquals(2, retrievedSkiers.size());
    }
}
