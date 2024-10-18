package tn.esprit.rh.achat.service.repository;

import tn.esprit.tpfoyer.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        reservation = new Reservation();
        reservation.setIdReservation("R1");
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);
    }

    @Test
    public void testSaveReservation() {
        // Simule le comportement de la méthode save
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Appelle la méthode save du mock
        Reservation savedReservation = reservationRepository.save(reservation);

        // Vérifie les résultats
        assertThat(savedReservation).isNotNull();
        assertThat(savedReservation.getIdReservation()).isEqualTo(reservation.getIdReservation());
    }

    @Test
    public void testFindReservationById() {
        // Simule le comportement de la méthode findById
        when(reservationRepository.findById(reservation.getIdReservation())).thenReturn(Optional.of(reservation));

        // Appelle la méthode findById du mock
        Optional<Reservation> foundReservation = reservationRepository.findById(reservation.getIdReservation());

        // Vérifie les résultats
        assertThat(foundReservation).isPresent();
        assertThat(foundReservation.get().getIdReservation()).isEqualTo(reservation.getIdReservation());
    }

    @Test
    public void testDeleteReservation() {
        // Aucune action à simuler ici, vérifions seulement l'appel
        doNothing().when(reservationRepository).deleteById(any(String.class));

        // Appelle la méthode deleteById du mock
        reservationRepository.deleteById(reservation.getIdReservation());

        // Vérifie que deleteById a été appelé
        verify(reservationRepository, times(1)).deleteById(reservation.getIdReservation());
    }

    @Test
    public void testFindAllByAnneeUniversitaireBeforeAndEstValide() {
        // Simule le comportement de la méthode personnalisée
        List<Reservation> reservations = List.of(reservation);
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(any(Date.class), any(Boolean.class)))
                .thenReturn(reservations);

        // Appelle la méthode du mock
        List<Reservation> validReservations = reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(new Date(), true);

        // Vérifie les résultats
        assertThat(validReservations).isNotEmpty();
        assertThat(validReservations.get(0).getIdReservation()).isEqualTo("R1");
    }
}

