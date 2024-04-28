package tn.esprit.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PisteRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPisteServices pisteServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new PisteRestController(pisteServices)).build();
    }

    @Test
    void testAddPiste() throws Exception {
        Piste piste = new Piste(null, "Test Piste", Color.GREEN, 1000, 10, null);

        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        mockMvc.perform(post("/piste/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(piste)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.namePiste").value("Test Piste"));

        verify(pisteServices, times(1)).addPiste(any(Piste.class));
    }

    @Test
    void testGetAllPistes() throws Exception {
        Piste piste1 = new Piste(1L, "Piste 1", Color.BLUE, 2000, 15, null);
        Piste piste2 = new Piste(2L, "Piste 2", Color.RED, 1500, 12);
        List<Piste> pistes = Arrays.asList(piste1, piste2);

        when(pisteServices.retrieveAllPistes()).thenReturn(pistes);

        mockMvc.perform(get("/piste/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].namePiste").value("Piste 1"))
                .andExpect(jsonPath("$[1].namePiste").value("Piste 2"));

        verify(pisteServices, times(1)).retrieveAllPistes();
    }

    // Add more test methods for other controller endpoints similarly
}
