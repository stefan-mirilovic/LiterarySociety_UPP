import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookDetails } from 'src/app/model/book-details';
import { BookService } from 'src/app/service/book.service';

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
    private router: Router
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

}
