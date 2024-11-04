package tn.esprit.tpfoyer.controlller;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.tpfoyer.control.FoyerRestController;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FoyerRestControllerTest {
    @Mock
    private FoyerServiceImpl foyerService;

    @InjectMocks
    private FoyerRestController foyerController;
    private MockMvc mockMvc;
    //private Foyer foyer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();
    }

    @Test
    public void testGetFoyers() throws Exception {
        Foyer foyer1 = new Foyer();
        foyer1.setIdFoyer(1L);
        foyer1.setNomFoyer("Foyer A");

        Foyer foyer2 = new Foyer();
        foyer2.setIdFoyer(2L);
        foyer2.setNomFoyer("Foyer B");

        List<Foyer> foyers = Arrays.asList(foyer1, foyer2);
        when(foyerService.retrieveAllFoyers()).thenReturn(foyers);

        mockMvc.perform(get("/foyer/retrieve-all-foyers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nomFoyer", is("Foyer A")))
                .andExpect(jsonPath("$[1].nomFoyer", is("Foyer B")));

        verify(foyerService, times(1)).retrieveAllFoyers();
    }

    @Test
    public void testAddFoyer() throws Exception {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer A");

        when(foyerService.addFoyer(any(Foyer.class))).thenReturn(foyer);

        mockMvc.perform(post("/foyer/add-foyer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idFoyer\":1,\"nomFoyer\":\"Foyer A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomFoyer", is("Foyer A")));

        verify(foyerService, times(1)).addFoyer(any(Foyer.class));
    }

    @Test
    public void testRetrieveFoyer() throws Exception {
        Foyer foyer = new Foyer(1L, "Foyer A", 100);
        when(foyerService.retrieveFoyer(1L)).thenReturn(foyer);

        mockMvc.perform(get("/foyer/retrieve-foyer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomFoyer").value("Foyer A"));

        verify(foyerService, times(1)).retrieveFoyer(1L);
    }

    @Test
    public void testModifyFoyer() throws Exception {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer Updated");



        when(foyerService.modifyFoyer(any(Foyer.class))).thenReturn(foyer);

        mockMvc.perform(put("/foyer/modify-foyer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idFoyer\":1,\"nomFoyer\":\"Foyer Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomFoyer", is("Foyer Updated")));

        verify(foyerService, times(1)).modifyFoyer(any(Foyer.class));
    }

    @Test
    public void testRemoveFoyer() throws Exception {
        doNothing().when(foyerService).removeFoyer(1L);

        mockMvc.perform(delete("/foyer/remove-foyer/1"))
                .andExpect(status().isOk());

        verify(foyerService, times(1)).removeFoyer(1L);
    }
}
