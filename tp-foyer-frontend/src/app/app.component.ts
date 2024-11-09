import {Component, OnInit} from '@angular/core';
import { ReservationServiceService } from './services/reservation-service.service';
import { Reservation } from './models/reservation';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'tp-foyer-frontend';
  reservations: Reservation[] = [];
  searchId: string = '';
  searchDate: string = '';
  searchStatus: boolean = false;

  constructor(private reservationService: ReservationServiceService, private router: Router) {}

  ngOnInit() {
    this.loadReservations();
  }

  loadReservations() {
    this.reservationService.getAllReservations().subscribe((data: Reservation[]) => {
      this.reservations = data;
    });
  }

  searchById() {
    if (this.searchId.trim()) {
      this.reservationService.getReservationById(this.searchId).subscribe((reservation) => {
        this.reservations = reservation ? [reservation] : [];
      });
    } else {
      this.loadReservations(); // Si le champ est vide, recharge toutes les réservations
    }
  }

  searchByDateAndStatus() {
    if (this.searchDate) {
      const date = new Date(this.searchDate);
      this.reservationService.findByDateAndStatus(date, this.searchStatus).subscribe((data: Reservation[]) => {
        this.reservations = data;
      });
    }
  }

  deleteReservation(id: string) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette réservation ?')) {
      this.reservationService.deleteReservation(id).subscribe(() => {
        this.loadReservations(); // Recharge la liste après suppression
      });
    }
  }
}
