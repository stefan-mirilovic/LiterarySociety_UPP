import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Transaction } from '../model/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http : HttpClient) { }

  public create(data: Transaction): Observable<any> {
    return this.http.post(`${environment.baseUrl}/transactions`, data);
  }
}
