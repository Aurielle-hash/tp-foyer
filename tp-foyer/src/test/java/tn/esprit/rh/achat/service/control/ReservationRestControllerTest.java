package tn.esprit.rh.achat.service.control;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.tpfoyer.control.ReservationRestController;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

class ReservationRestControllerTest {

    @Mock
    private IReservationService reservationService;

    @InjectMocks
    private ReservationRestController reservationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    public void testGetAllReservations() throws Exception {
        List<Reservation> mockReservations = new ArrayList<>();
        mockReservations.add(new Reservation("R1", new Date(), true));
        mockReservations.add(new Reservation("R2", new Date(), false));

        when(reservationService.retrieveAllReservations()).thenReturn(mockReservations);

        mockMvc.perform(get("/reservation/retrieve-all-reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idReservation").value("R1"))
                .andExpect(jsonPath("$[1].idReservation").value("R2"));
    }

    @Test
    public void testRetrieveReservation() throws Exception {
        Reservation reservation = new Reservation("R1", new Date(), true);
        when(reservationService.retrieveReservation("R1")).thenReturn(reservation);

        mockMvc.perform(get("/reservation/retrieve-reservation/R1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReservation").value("R1"));
    }

    @Test
    public void testRetrieveReservationByDateAndStatus() throws Exception {
        List<Reservation> mockReservations = new ArrayList<>();
        mockReservations.add(new Reservation("R1", new Date(), true));

        when(reservationService.trouverResSelonDateEtStatus(any(Date.class), anyBoolean()))
                .thenReturn(mockReservations);

        mockMvc.perform(get("/reservation/retrieve-reservation-date-status/2024-01-01/true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idReservation").value("R1"));
    }

    @Test
    public void testAddReservation() throws Exception {
        Reservation reservation = new Reservation("R3", new Date(), true);
        when(reservationService.addReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(post("/reservation/add-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idReservation\":\"R3\",\"anneeUniversitaire\":\"2024-01-01T00:00:00.000+00:00\",\"estValide\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idReservation").value("R3"));
    }

    @Test
    public void testRemoveReservation() throws Exception {
        doNothing().when(reservationService).removeReservation("R1");

        mockMvc.perform(delete("/reservation/remove-reservation/R1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testModifyReservation() throws Exception {
        Reservation reservation = new Reservation("R1", new Date(), false);
        when(reservationService.modifyReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(put("/reservation/modify-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idReservation\":\"R1\",\"anneeUniversitaire\":\"2024-01-01T00:00:00.000+00:00\",\"estValide\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estValide").value(false));
    }
}
