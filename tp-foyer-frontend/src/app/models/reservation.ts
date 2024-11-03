import { Etudiant } from './etudiant';

export class Reservation {
  idReservation: string;
  anneeUniversitaire: Date;
  estValide: boolean;
  etudiants: Etudiant[];

  constructor(
    idReservation: string,
    anneeUniversitaire: Date,
    estValide: boolean,
    etudiants: Etudiant[]
  ) {
    this.idReservation = idReservation;
    this.anneeUniversitaire = anneeUniversitaire;
    this.estValide = estValide;
    this.etudiants = etudiants;
  }
}
