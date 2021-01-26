export class Transaction {
    id:number;
    timestamp:string;
    initiatorId:number;
    bookId:number;
    status:number;

    constructor(id:number, timestamp:string, initiatorId:number, bookId:number, status:number) {
        this.id = id;
        this.timestamp = timestamp;
        this.initiatorId = initiatorId;
        this.bookId = bookId;
        this.status = status;
    }
}
  