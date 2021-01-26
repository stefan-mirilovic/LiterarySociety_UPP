export class BookDetails {
    id:number;
    title:string;
    writer:string;
    isbn:string;
    publishingYear:number;
    genre:string;
    synopsis:string;
    noOfPages:number;
    owned: boolean;
    price: number;

    constructor(id:number, title:string, writer:string, isbn:string, publishingYear:number, genre:string, synopsis:string, owned: boolean, price: number) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.isbn = isbn;
        this.publishingYear = publishingYear;
        this.genre = genre;
        this.synopsis = synopsis;
        this.owned = owned;
        this.price = price;
    }
}
  