import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationServiceService } from '../../services/reservation-service.service';
import { Reservation } from '../../models/reservation';
@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.css']
})
export class ReservationDetailsComponent implements OnInit {
  reservation: Reservation | undefined;
  constructor(private route: ActivatedRoute,
              private reservationService: ReservationServiceService,
              private router: Router) { }
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.reservationService.getReservationById(id).subscribe((reservation) => {
        this.reservation = reservation;
      });
    }
  }

  updateReservation() {
    if (this.reservation) {
      this.reservationService.updateReservation(this.reservation).subscribe(() => {
        alert('Réservation mise à jour avec succès');
        this.router.navigate(['/']);
      });
    }
  }
}
