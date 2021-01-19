import {Component, OnInit} from '@angular/core';
import {RegisterService} from "../service/register.service";
import {processes} from "../../environments/processes";

@Component({
    selector: 'app-reader',
    templateUrl: './reader.component.html',
    styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {

    public formFieldsDto = null;
    public taskId:any;
    public loading:boolean;
    public process
    constructor(private processService: RegisterService) {
    }

    ngOnInit(): void {
        this.loading=true;
        this.processService.startProcess(processes.reader).subscribe(
            data => {
                this.formFieldsDto=data;
                this.taskId=data.taskId;
                this.loading=false;
            });
    }
}
