import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookDetails } from 'src/app/model/book-details';
import { Transaction } from 'src/app/model/transaction';
import { BookService } from 'src/app/service/book.service';
import { TransactionService } from 'src/app/service/transaction.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {
  book: BookDetails
  loading: boolean;

  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private bookService: BookService,
    private transactionService: TransactionService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loading = true;
    const id: Observable<number> = this.route.params.pipe(map(p => p.id));
    id.subscribe(id => {
      if (id)
        this.bookService.getBook(id).subscribe({
          next: (result) => {
            this.book = result;
            this.loading = false;
          },
          error: data => {
            this.loading = false;
            if (data.error && typeof data.error === "string")
              this.toastr.error(data.error);
            else
              this.toastr.error("An error occured while getting books!")
          }
        })
    });
  }

  back() {
    let prevPage: string = localStorage.getItem("prevPage");
    localStorage.removeItem("prevPage");
    this.router.navigate([prevPage]);
  }

  purchase() {
    let transaction: Transaction = new Transaction(null, null, null, this.book.id, null);
    this.transactionService.create(transaction).subscribe({
      next: (result) => {
        window.location.href = `${environment.paymentUrl}?merchantId=${result.merchantId}&amount=${result.amount}&successUrl=${result.successUrl}&failedUrl=${result.failedUrl}&errorUrl=${result.errorUrl}`;
      },
      error: data => {
        this.loading = false;
        if (data.error && typeof data.error === "string")
          this.toastr.error(data.error);
        else
          this.toastr.error("An error occured while purchasing book!")
      }
    })
  }

}
