export class BookDisplay {
    id:number;
    title:string;
    writer:string;
    synopsis:string;
    owned: boolean;
    price: number;
    color: string;
    noOfPages: number;

    constructor(id:number, title:string, writer:string, synopsis:string, owned: boolean, price: number) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.synopsis = synopsis;
        this.owned = owned;
        this.price = price;
    }
}
  