import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {CreateWorkPlaceRequest, WorkPlace} from '../interfaces/work-place';

@Injectable({
  providedIn: 'root'
})
export class WorkPlaceService {

  private apiUrl = 'http://localhost:8080/workPlaces';

  constructor(private http: HttpClient) {}

  getPlants(): Observable<WorkPlace[]> {
    return this.http.get<WorkPlace[]>(this.apiUrl);
  }

  createWorkPlace(workPlace: CreateWorkPlaceRequest): Observable<string> {
    return this.http.post<string>(this.apiUrl, workPlace, { responseType: 'text' as 'json' });
  }

  updateWorkPlace(id: string | null, WorkPlace: WorkPlace): Observable<WorkPlace> {
    return this.http.patch<WorkPlace>(`${this.apiUrl}/${id}`, WorkPlace);
  }

  deleteWorkPlace(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
