import { Component, OnInit } from '@angular/core';
import {DocumentService} from "../service/document.service";

@Component({
  selector: 'app-send-book',
  templateUrl: './send-book.component.html',
  styleUrls: ['./send-book.component.css']
})
export class SendBookComponent implements OnInit {
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
        this.taskId=data.formDto.taskId;
        this.loading=false;
      }
    )
  }

}
