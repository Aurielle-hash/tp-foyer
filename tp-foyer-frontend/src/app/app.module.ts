import { NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app.routes';

import { AppComponent } from './app.component';
import { AddReservationComponent } from './reservation-component/add-reservation/add-reservation.component';
import { ReservationDetailsComponent } from './reservation-component/reservation-details/reservation-details.component';
import { ReservationServiceService } from './services/reservation-service.service';

@NgModule({
  declarations: [
    AppComponent,                // Main component displaying the list of reservations
    AddReservationComponent,     // Component to add a new reservation
    ReservationDetailsComponent  // Component for reservation details and modification
  ],
  imports: [
    BrowserModule,               // Basic module for Angular applications
    FormsModule,                 // Module for handling forms
    AppRoutingModule,             // Routing module for navigation management
    HttpClientModule            // Module for making HTTP requests

  ],
  //providers: [ReservationServiceService], // Reservation service for HTTP calls to the backend
  bootstrap: [AppComponent]       // Root component to be launched at startup
})
export class AppModule { }
