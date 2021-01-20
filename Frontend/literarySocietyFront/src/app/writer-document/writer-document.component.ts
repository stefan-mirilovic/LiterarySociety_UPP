import { Component, OnInit } from '@angular/core';
import {DocumentService} from "../service/document.service";

@Component({
  selector: 'app-writer-document',
  templateUrl: './writer-document.component.html',
  styleUrls: ['./writer-document.component.css']
})
export class WriterDocumentComponent implements OnInit {
  public formFieldsDto = null;
  public taskId:any;
  public loading:boolean;
  public process

  constructor(private documentService:DocumentService) { }

  ngOnInit(): void {
      this.loading=true;
      var processId =localStorage.getItem("processId");
      console.log(processId);
      this.documentService.documentProcess(processId).subscribe(
          data=>{
              console.log(data);
              this.taskId=data.taskId;
              this.loading=false;
          }
      )
  }

}
