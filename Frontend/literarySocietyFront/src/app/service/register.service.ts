import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http : HttpClient) { }

  public startProcess(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/readerRegistration`);
  }

  public registerReader(data: any, taskId:string) {
    return this.http.post(`${environment.baseUrl}/readerRegistration/${taskId}`, data);
  }

  // @ts-ignore
  public getFormData(taskId): Observable <any> {
    return this.http.get(`${environment.baseUrl}/readerRegistration/${taskId}`);
  }
}
