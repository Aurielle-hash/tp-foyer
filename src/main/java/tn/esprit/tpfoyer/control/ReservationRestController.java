package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    IReservationService reservationService;

    // http://localhost:8089/tpfoyer/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return new ResponseEntity<>(listReservations, HttpStatus.OK);
    }

    // http://localhost:8089/tpfoyer/reservation/retrieve-reservation/{reservation-id}
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public ResponseEntity<Reservation> retrieveReservation(@PathVariable("reservation-id") String rId) {
        Reservation reservation = reservationService.retrieveReservation(rId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    // http://localhost:8089/tpfoyer/reservation/retrieve-reservation-date-status/{d}/{v}
    @GetMapping("/retrieve-reservation-date-status/{d}/{v}")
    public ResponseEntity<List<Reservation>> retrieveReservationByDateAndStatus(
            @PathVariable("d") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d,
            @PathVariable("v") boolean v) {
        List<Reservation> reservations = reservationService.trouverResSelonDateEtStatus(d, v);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    // http://localhost:8089/tpfoyer/reservation/add-reservation
    @PostMapping("/add-reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.addReservation(r);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    // http://localhost:8089/tpfoyer/reservation/remove-reservation/{reservation-id}
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public ResponseEntity<Void> removeReservation(@PathVariable("reservation-id") String rId) {
        reservationService.removeReservation(rId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // http://localhost:8089/tpfoyer/reservation/modify-reservation
    @PutMapping("/modify-reservation")
    public ResponseEntity<Reservation> modifyReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.modifyReservation(r);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
