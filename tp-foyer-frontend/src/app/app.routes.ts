import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { AppComponent } from './app.component';
import { AddReservationComponent } from './reservation-component/add-reservation/add-reservation.component';
import { ReservationDetailsComponent } from './reservation-component/reservation-details/reservation-details.component';

const routes: Routes = [
  { path: '', component: AppComponent },  // Page d'accueil
  { path: 'reservations/add', component: AddReservationComponent },  // Page d'ajout de réservation
  { path: 'reservations/details/:id', component: ReservationDetailsComponent },  // Page des détails de réservation
  { path: '**', redirectTo: '', pathMatch: 'full' }  // Redirection pour les routes non définies
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
