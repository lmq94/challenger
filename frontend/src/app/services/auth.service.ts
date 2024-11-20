import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/users/login';

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<string> {
    const loginData = { email, password };
    return this.http.post('http://localhost:8080/users/login', { email, password }, { responseType: 'text' });

  }
}
