import { Component, OnInit } from '@angular/core';
import {DocumentService} from "../service/document.service";

@Component({
  selector: 'app-editors-notes',
  templateUrl: './editors-notes.component.html',
  styleUrls: ['./editors-notes.component.css']
})
export class EditorsNotesComponent implements OnInit {


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
