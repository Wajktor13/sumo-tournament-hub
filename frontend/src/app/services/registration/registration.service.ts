import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Registration } from '../../models/registration';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  private apiResource = 'registrations';

  constructor(private httpClient: HttpClient) {}

  public addRegistration(registration: Partial<Registration>): boolean {
    this.httpClient.post(
      `${environment.apiUrl}/${this.apiResource}`,
      registration,
    );

    this.httpClient
      .post(`${environment.apiUrl}/${this.apiResource}`, registration)
      .subscribe({
        next: (v) => console.log(v),
      });

    return true;
  }
}
