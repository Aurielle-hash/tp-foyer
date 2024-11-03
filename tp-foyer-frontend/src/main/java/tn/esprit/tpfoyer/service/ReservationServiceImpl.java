package tn.esprit.tpfoyer.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    ReservationRepository reservationRepository;

    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation retrieveReservation(String reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    public Reservation addReservation(Reservation r) {

        System.out.println("Ajout de la réservation: " + r);
        // Votre logique d'ajout de réservation ici
        Reservation savedReservation = reservationRepository.save(r);
        System.out.println("Réservation ajoutée avec succès: " + savedReservation);
        return savedReservation;
    }

    public void removeReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
        // throw new EntityNotFoundException("Reservation not found");
        System.out.println("Reservation supprimée avec succès");
    }

    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> trouverResSelonDateEtStatus(Date d, boolean b) {
        return reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(d, b);
    }




}
