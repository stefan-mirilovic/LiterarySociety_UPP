import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import { Genre } from '../model/genre';

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(private http:HttpClient) { }

  public getAllGenres(): Observable <Genre[]> {
    return this.http.get<Genre[]>(`${environment.baseUrl}/genres`);
  }
}
