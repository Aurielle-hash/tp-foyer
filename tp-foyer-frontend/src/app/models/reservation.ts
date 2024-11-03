import {Etudiant} from './etudiant';

export class Reservation {
  idReservation: string;
  anneeUniversitaire: Date;
  estValide: boolean;
  etudiants: Etudiant[];

  constructor() {
    this.idReservation = idReservation;
    this.anneeUniversitaire = anneeUniversitaire;
    this.estValide = estValide;
    this.etudiants = etudiants;
  }
}
