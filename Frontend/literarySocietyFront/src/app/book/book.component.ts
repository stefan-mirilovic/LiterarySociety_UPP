import { Component, OnInit } from '@angular/core';
import {processes} from "../../environments/processes";
import {RegisterService} from "../service/register.service";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  public formFieldsDto = null;
  public taskId:any;
  public loading:boolean;
  public process
  constructor(private processService:RegisterService) { }

  ngOnInit(): void {
    this.loading=true;
    this.processService.startProcess(processes.book).subscribe(
        data => {
          this.formFieldsDto=data;
          this.taskId=data.taskId;
          this.loading=false;
        });
  }

}
