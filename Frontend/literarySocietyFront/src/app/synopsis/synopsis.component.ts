import {Component, OnInit} from '@angular/core';
import {processes} from "../../environments/processes";
import {RegisterService} from "../service/register.service";

@Component({
  selector: 'app-synopsis',
  templateUrl: './synopsis.component.html',
  styleUrls: ['./synopsis.component.css']
})
export class SynopsisComponent implements OnInit {

  public formFieldsDto = null;
  public taskId: any;
  public loading: boolean;
  public process;

  constructor(private processService: RegisterService) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.processService.startProcess(processes.book).subscribe(
      data => {
        this.formFieldsDto = data;
        this.taskId = data.taskId;
        this.loading = false;
      });
  }

}
