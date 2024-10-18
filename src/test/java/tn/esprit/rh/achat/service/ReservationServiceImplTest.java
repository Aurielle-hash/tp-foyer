package tn.esprit.rh.achat.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {ReservationServiceImpl.class})
@TestMethodOrder(OrderAnnotation.class)
class ReservationServiceImplTest {

    @Autowired
    IReservationService reservationService;

    @Test
    @Order(1)
    public void testRetrieveAllReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        Assertions.assertNotNull(listReservations); // Vérifie que la liste n'est pas nulle
    }

    @Test
    @Order(2)
    public void testAddReservation() {
        Reservation r = new Reservation();
        r.setIdReservation("R1");
        r.setAnneeUniversitaire(new Date());
        r.setEstValide(true);
        Reservation savedReservation = reservationService.addReservation(r);
        Assertions.assertNotNull(savedReservation); // Vérifie que la réservation est bien ajoutée
    }

    @Test
    @Order(3)
    public void testRetrieveReservation() {
        Reservation reservation = reservationService.retrieveReservation("R1");
        Assertions.assertEquals("R1", reservation.getIdReservation()); // Vérifie que la réservation récupérée est correcte
    }

    @Test
    @Order(4)
    public void testModifyReservation() {
        Reservation r = reservationService.retrieveReservation("R1");
        r.setEstValide(false);
        Reservation updatedReservation = reservationService.modifyReservation(r);
        Assertions.assertFalse(updatedReservation.isEstValide()); // Vérifie que la modification est correcte
    }

    @Test
    @Order(5)
    public void testDeleteReservation() {
        reservationService.removeReservation("R1");
        Assertions.assertThrows(Exception.class, () -> {
            reservationService.retrieveReservation("R1");
        }); // Vérifie que la réservation est bien supprimée
    }
}
