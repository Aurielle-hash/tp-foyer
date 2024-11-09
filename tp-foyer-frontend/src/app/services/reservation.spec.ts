import { Reservation } from '../models/reservation';

describe('Reservation', () => {
  it('should create an instance', () => {
    expect(new Reservation()).toBeTruthy();
  });
});
