import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BookDisplay } from 'src/app/model/book-display';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.css']
})
export class MyBooksComponent implements OnInit {
  books: BookDisplay[];
  synopsisMaxLength: number = 400;
  title: string;
  loading: boolean;
  noOfPages: number;
  pageNo: number;
  resultsPerPage: number;

  constructor(
    private bookService: BookService,
    private toastr: ToastrService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loading = true;
    this.title = "My Library";
    this.books = [];
    this.resultsPerPage = 10;
    this.pageNo = 0;
    this.noOfPages = 1;
    this.getBooks();
  }

  getBooks() {
    this.books = [];
    this.bookService.findAllOwnedWithPagination(this.resultsPerPage, this.pageNo).subscribe({
      next: (results) => {
        this.books = results;
        if (results.length != 0) {
          this.noOfPages = results[0].noOfPages;
        }
        this.loading = false;
        for (let book of this.books) {
          if (book.synopsis.length > this.synopsisMaxLength) {
            book.synopsis = book.synopsis.substr(0, this.synopsisMaxLength-3) + "..."
          }
          book.color = this.randomColorBackground(); 
        }
      },
      error: data => {
        this.loading = false;
        if (data.error && typeof data.error === "string")
          this.toastr.error(data.error);
        else
          this.toastr.error("An error occured while getting books!")
      }
    })
  }

  randomColor() {
    //return Math.floor(Math.random()*16777215).toString(16);
    let letters = '0123456789ABCDEF'.split('');
    let color = '#';
    for (let i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

  randomColorBackground() {
    let r = this.randomColor()
    console.log(r);
    return "background-color: " + r;
  }

  previousPage() {
    --this.pageNo;
    this.loading = true;
    this.getBooks();
  }

  nextPage() {
    ++this.pageNo;
    this.loading = true;
    this.getBooks();
  }

  onResultPerPageChange() {
    this.pageNo = 0;
    this.loading = true;
    this.getBooks();
  }

  details(book: BookDisplay) {
    localStorage.setItem("prevPage", "reader-dashboard/my-library")
    this.router.navigate([`/reader-dashboard/book/${book.id}`]);
  }

  download(event, book: BookDisplay) {
    event.stopPropagation();
    this.toastr.info("Placeholder");
  }
}
