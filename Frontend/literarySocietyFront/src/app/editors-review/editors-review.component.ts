import { Component, OnInit } from '@angular/core';
import {DocumentService} from "../service/document.service";

@Component({
  selector: 'app-editors-review',
  templateUrl: './editors-review.component.html',
  styleUrls: ['./editors-review.component.css']
})
export class EditorsReviewComponent implements OnInit {
  public taskId:any;
  public loading:boolean;
  public process;

  constructor(private documentService: DocumentService) { }

  ngOnInit(): void {
    this.loading=true;
    var processId =localStorage.getItem("processId");
    console.log(processId);
    this.documentService.getNextTaskTable(processId).subscribe(
        data=>{
          console.log(data);
          this.taskId=data.taskId;
          this.loading=false;
        }
    );
  }

}
