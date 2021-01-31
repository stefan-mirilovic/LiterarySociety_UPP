import { Component, OnInit } from '@angular/core';
import {DocumentService} from "../service/document.service";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  public taskId:any;
  public loading:boolean;
  public process;
  public tableRows= [];

  constructor(private documentService: DocumentService ) { }

  ngOnInit(): void {
    var processId =localStorage.getItem("processId");
    this.documentService.getNextTaskTable(processId).subscribe(
      data=>{
        console.log(data);
        this.tableRows = data.tableRows;
        this.taskId=data.taskId;
        this.loading=false;
      }
    );
  }

  toArray(row: any) {
    let arr = [];
    for(let key in row) {
      for(let s in row[key]){
        arr.push(row[key][s]);
      }
    }
    return arr;
  }

  download(fieldValue: any){
    var blob = new Blob([this.base64ToBlob(fieldValue)], { type: 'application/pdf' });
    const blobUrl = URL.createObjectURL(blob);
    const pdf = document.createElement('pdf');
    window.open(blobUrl);
  }


  public base64ToBlob(fieldValue) {
    let byteCharacters = atob(fieldValue);
    let byteArrays = [];
    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
      let slice = byteCharacters.slice(offset, offset + 512);

      let byteNumbers = new Array(slice.length);
      for (var i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }
      let byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }
    return new Blob(byteArrays, {type: ''});
  }
}
