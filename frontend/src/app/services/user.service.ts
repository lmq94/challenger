import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RegisterUser} from '../interfaces/Register-user';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/users/register';
  constructor(private http: HttpClient) { }

  registerUser(user: RegisterUser): Observable<string> {
    return this.http.post<string>(this.apiUrl, user, { responseType: 'text' as 'json' });
  }
}
