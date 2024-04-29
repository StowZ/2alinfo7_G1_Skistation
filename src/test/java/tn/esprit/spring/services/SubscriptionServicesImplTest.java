package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubscriptionServicesImplTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test for addSubscription method
    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());
        subscription.setPrice(100.0f);

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);

        assertEquals(subscription.getTypeSub(), savedSubscription.getTypeSub());
        assertEquals(subscription.getStartDate(), savedSubscription.getStartDate());
        assertEquals(subscription.getPrice(), savedSubscription.getPrice());
    }

    // Add more test methods for other service methods (updateSubscription, retrieveSubscriptionById, etc.)
}
