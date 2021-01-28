import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BookDetails } from '../model/book-details';
import { BookDisplay } from '../model/book-display';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http : HttpClient) { }

  public findAllWithPagination(resultsPerPage: number, pageNo: number, genreid: number): Observable<BookDisplay[]> {
    return this.http.get<BookDisplay[]>(`${environment.baseUrl}/books/store`, {
      params: {
        resperpage: resultsPerPage.toString(),
        pageno: pageNo.toString(),
        genreid: genreid ? genreid.toString() : undefined
      }
    });
  }

  public findAllOwnedWithPagination(resultsPerPage: number, pageNo: number): Observable<BookDisplay[]> {
    return this.http.get<BookDisplay[]>(`${environment.baseUrl}/books/owned`, {
      params: {
        resperpage: resultsPerPage.toString(),
        pageno: pageNo.toString()
      }
    });
  }

  public getBook(id: number) {
    return this.http.get<BookDetails>(`${environment.baseUrl}/books/store/${id}`)
  }
}
