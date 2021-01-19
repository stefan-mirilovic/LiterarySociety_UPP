import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http : HttpClient) { }

  public startProcess(processName): Observable<any> {
    return this.http.get(`${environment.baseUrl}/startProcess/${processName}`);
  }

  public registerReader(data: any, taskId:string) {
    return this.http.post(`${environment.baseUrl}/submitForm/${taskId}`, data);
  }

  // @ts-ignore
  public getFormData(taskId): Observable <any> {
    return this.http.get(`${environment.baseUrl}/getForm/${taskId}`);
  }
}
