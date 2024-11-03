package tn.esprit.rh.achat.service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReservationServiceImplTestMockito {

    @InjectMocks
    private ReservationServiceImpl reservationService; // L'objet à tester

    @Mock
    private ReservationRepository reservationRepository; // Le mock

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testRetrieveAllReservations() {
        List<Reservation> mockReservations = new ArrayList<>();
        mockReservations.add(new Reservation("R1", new Date(), true));
        mockReservations.add(new Reservation("R2", new Date(), false));

        when(reservationRepository.findAll()).thenReturn(mockReservations);

        List<Reservation> listReservations = reservationService.retrieveAllReservations();

        Assertions.assertNotNull(listReservations); // Vérifie que la liste n'est pas nulle
        Assertions.assertEquals(2, listReservations.size()); // Vérifie le nombre d'éléments
    }

    @Test
    public void testAddReservation() {
        Reservation r = new Reservation("R1", new Date(), true);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(r);

        Reservation savedReservation = reservationService.addReservation(r);

        Assertions.assertNotNull(savedReservation); // Vérifie que la réservation est bien ajoutée
        Assertions.assertEquals("R1", savedReservation.getIdReservation()); // Vérifie l'ID
    }

    @Test
    public void testRetrieveReservation() {
        Reservation r = new Reservation("R1", new Date(), true);
        when(reservationRepository.findById("R1")).thenReturn(Optional.of(r));

        Reservation reservation = reservationService.retrieveReservation("R1");

        Assertions.assertEquals("R1", reservation.getIdReservation()); // Vérifie que la réservation récupérée est correcte
    }

    @Test
    public void testModifyReservation() {
        Reservation r = new Reservation("R1", new Date(), true);
        when(reservationRepository.findById("R1")).thenReturn(Optional.of(r));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(r);

        r.setEstValide(false);
        Reservation updatedReservation = reservationService.modifyReservation(r);

        Assertions.assertFalse(updatedReservation.isEstValide()); // Vérifie que la modification est correcte
    }

    @Test
    public void testAddMultipleReservations() {
        Reservation r3 = new Reservation("R3", new Date(), true);
        Reservation r2 = new Reservation("R2", new Date(), false);

        when(reservationRepository.save(r3)).thenReturn(r3);
        when(reservationRepository.save(r2)).thenReturn(r2);

        Reservation savedReservation1 = reservationService.addReservation(r3);
        Reservation savedReservation2 = reservationService.addReservation(r2);

        Assertions.assertNotNull(savedReservation1);
        Assertions.assertNotNull(savedReservation2);
        Assertions.assertEquals("R3", savedReservation1.getIdReservation());
        Assertions.assertEquals("R2", savedReservation2.getIdReservation());
    }

    @Test
    public void testDeleteReservation() {
        Reservation r = new Reservation("R4", new Date(), true);
        when(reservationRepository.findById("R4")).thenReturn(Optional.of(r));
        doNothing().when(reservationRepository).deleteById("R4");

        verify(reservationRepository, times(1)).deleteById("R4"); // Vérifie que deleteById a été appelé

        // Vérification que la réservation a bien été supprimée
        when(reservationRepository.findById("R4")).thenReturn(Optional.empty());

        // Vérifiez qu'une exception est levée lorsque l'on essaie de récupérer une réservation supprimée
        assertThrows(Exception.class, () -> {
            reservationService.retrieveReservation("R4"); // Vérifie que la réservation est bien supprimée
        });
    }


}
