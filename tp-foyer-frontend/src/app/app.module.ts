import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { AddReservationComponent } from './reservation-component/add-reservation/add-reservation.component';
import { ReservationDetailsComponent } from './reservation-component/reservation-details/reservation-details.component';
import { AppRoutingModule } from './app.routes';
import { ReservationServiceService } from './services/reservation-service.service';

@NgModule({
  declarations: [
    AppComponent,                // Composant principal affichant la liste des réservations
    AddReservationComponent,      // Composant pour ajouter une nouvelle réservation
    ReservationDetailsComponent   // Composant pour les détails et la modification d'une réservation
  ],
  imports: [
    BrowserModule,                // Module de base pour les applications Angular
    FormsModule,                  // Module pour la gestion des formulaires
    HttpClientModule,              // Module pour les appels HTTP
    AppRoutingModule              // Module de routage pour gérer la navigation
  ],
  providers: [ReservationServiceService], // Service de réservation pour les appels HTTP au backend
  bootstrap: [AppComponent]        // Composant racine qui sera lancé au démarrage

})

export class AppModule { }
