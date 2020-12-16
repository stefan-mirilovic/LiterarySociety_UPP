import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http : HttpClient) { }

  public registerReader(data: any): void {
    this.http.post(`${environment.baseUrl}/register/reader`, data);
  }

  // @ts-ignore
  public getFormData(): Observable <any> {
    return this.http.get(`${environment.baseUrl}/render/form`);
  }
}
