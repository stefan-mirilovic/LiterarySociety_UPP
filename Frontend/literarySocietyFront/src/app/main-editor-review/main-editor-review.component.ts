import { Component, OnInit } from '@angular/core';
import {DocumentService} from "../service/document.service";

@Component({
  selector: 'app-main-editor-review',
  templateUrl: './main-editor-review.component.html',
  styleUrls: ['./main-editor-review.component.css']
})
export class MainEditorReviewComponent implements OnInit {


  public taskId:any;
  public loading:boolean;
  public process;
  public tableRows= [];
  constructor(private documentService: DocumentService) { }

  ngOnInit(): void {
    this.loading=true;
    var processId =localStorage.getItem("processId");
    this.documentService.documentProcess(processId).subscribe(
      data=>{
        console.log(data);
        this.tableRows = data.tableDto.tableRows;
        this.taskId=data.formDto.taskId;
        console.log(this.taskId);
        console.log(this.tableRows);
        this.loading=false;
      }
    );
  }
}
