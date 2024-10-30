package tn.esprit.tpfoyer.controlller;
import static org.junit.jupiter.api.Assertions.*;
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
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FoyerRestControllerTest {
    @Mock
    private FoyerServiceImpl foyerService;

    @InjectMocks
    private FoyerRestController foyerController;
    private MockMvc mockMvc;
    private Foyer foyer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(foyerController).build();
    }

    @Test
    public void testGetAllFoyers() throws Exception {
        List<Foyer> mockFoyers = new ArrayList<>();
        mockFoyers.add(new Foyer(1L, "Foyer A", 100)); // Remplacez par le constructeur approprié
        mockFoyers.add(new Foyer(2L, "Foyer B", 200));

        when(foyerService.retrieveAllFoyers()).thenReturn(mockFoyers);

        mockMvc.perform(get("/foyer/retrieve-all-foyers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nomFoyer").value("Foyer A"))
                .andExpect(jsonPath("$[1].nomFoyer").value("Foyer B"));
    }

    @Test
    public void testCreateFoyer() throws Exception {
        Foyer foyer = new Foyer(1L, "Foyer A", 100);
        when(foyerService.addFoyer(any(Foyer.class))).thenReturn(foyer);

        mockMvc.perform(post("/foyer/add-foyer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idFoyer\":1,\"nomFoyer\":\"Foyer A\",\"capaciteFoyer\":100}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomFoyer").value("Foyer A"));
    }

    @Test
    public void testRetrieveFoyer() throws Exception {
        Foyer foyer = new Foyer(1L, "Foyer A", 100);
        when(foyerService.retrieveFoyer(1L)).thenReturn(foyer);

        mockMvc.perform(get("/foyer/retrieve-foyer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomFoyer").value("Foyer A"));
    }

    @Test
    public void testUpdateFoyer() throws Exception {
        Foyer foyer = new Foyer(1L, "Foyer A", 150);
        when(foyerService.modifyFoyer(any(Foyer.class))).thenReturn(foyer);

        mockMvc.perform(put("/foyer/modify-foyer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idFoyer\":1,\"nomFoyer\":\"Foyer A\",\"capaciteFoyer\":150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capaciteFoyer").value(150));
    }

    @Test
    public void testDeleteFoyer() throws Exception {
        doNothing().when(foyerService).removeFoyer(1L);

        mockMvc.perform(delete("/foyer/remove-foyer/1"))
                .andExpect(status().isNoContent());
    }
}
