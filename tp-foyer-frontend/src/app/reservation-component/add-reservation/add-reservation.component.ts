import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ReservationServiceService } from '../../services/reservation-service.service';
import { Reservation } from '../../models/reservation';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.css']
})
export class AddReservationComponent {
  reservation: Reservation = { idReservation: '', anneeUniversitaire: new Date(), estValide: false, etudiants: [] };

  constructor(private reservationService: ReservationServiceService, private router: Router) {}

  saveReservation() {
    this.reservationService.addReservation(this.reservation).subscribe(() => {
      alert('Réservation ajoutée avec succès');
      this.router.navigate(['/']);
    });
  }
}
