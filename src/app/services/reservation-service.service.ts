import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Reservation} from '../models/reservation';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
  // This decorator marks the service as available to be injected at the root level, making it a singleton service.
})
export class ReservationServiceService {
  private apiUrl = `${environment.apiUrl}/reservation`;

  constructor(private http: HttpClient) { }

  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/retrieve-all-reservations`);
  }

  getReservationById(id: string): Observable<Reservation> {
    return this.http.get<Reservation>(`${this.apiUrl}/retrieve-reservation/${id}`);
  }

  addReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(`${this.apiUrl}/add-reservation`, reservation);
  }

  deleteReservation(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/remove-reservation/${id}`);
  }

  updateReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiUrl}/modify-reservation/`, reservation);
  }

  findByDateAndStatus(date: Date, status: boolean): Observable<Reservation[]> {
    const params = new HttpParams()
      .set('date', date.toISOString())
      .set('status', String(status));
    return this.http.get<Reservation[]>(`${this.apiUrl}/retrieve-reservation-date-status/`, { params });
  }
}
