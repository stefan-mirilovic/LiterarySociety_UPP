import { Component, OnInit } from '@angular/core';
import {processes} from "../../environments/processes";
import {RegisterService} from "../service/register.service";

@Component({
  selector: 'app-writer',
  templateUrl: './writer.component.html',
  styleUrls: ['./writer.component.css']
})
export class WriterComponent implements OnInit {
  public formFieldsDto = null;
  public taskId:any;
  public loading:boolean;
  public process
  constructor(private processService:RegisterService) { }

  ngOnInit(): void {
    this.loading=true;
    this.processService.startProcess(processes.writer).subscribe(
        data => {
          this.formFieldsDto=data;
          localStorage.setItem('processId',this.formFieldsDto.processInstanceId);
          this.taskId=data.taskId;
          console.log(this.formFieldsDto);
          this.loading=false;
        });
  }

}
