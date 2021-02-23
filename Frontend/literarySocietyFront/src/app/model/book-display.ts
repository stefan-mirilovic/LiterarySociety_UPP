export class BookDisplay {
    id:number;
    title:string;
    writer:string;
    synopsis:string;
    owned: boolean;
    price: number;
    color: string;
    noOfPages: number;
    path: string;

    constructor(id:number, title:string, writer:string, synopsis:string, owned: boolean, price: number,path: string) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.synopsis = synopsis;
        this.owned = owned;
        this.price = price;
        this.path = path;
    }
}
