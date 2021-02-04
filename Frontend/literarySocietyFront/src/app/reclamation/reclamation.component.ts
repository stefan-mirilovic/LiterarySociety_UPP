import { Component, OnInit } from '@angular/core';
import {RegisterService} from "../service/register.service";
import {processes} from "../../environments/processes";

@Component({
  selector: 'app-reclamation',
  templateUrl: './reclamation.component.html',
  styleUrls: ['./reclamation.component.css']
})
export class ReclamationComponent implements OnInit {

  public formFieldsDto = null;
  public taskId:any;
  public loading:boolean;
  public process;
  public redirectLink = "/login";

  constructor(private processService:RegisterService) { }

  ngOnInit(): void {
    this.loading=true;
    this.processService.startProcess(processes.plagiarism).subscribe(
      data => {
        this.formFieldsDto=data;
        localStorage.setItem('processId',this.formFieldsDto.processInstanceId);
        this.taskId=data.taskId;
        console.log(this.formFieldsDto);
        this.loading=false;
      });
  }

}
