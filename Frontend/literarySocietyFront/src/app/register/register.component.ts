import { Component, OnInit } from '@angular/core';
import {RegisterService} from "../service/register.service";
import {Genre} from "../model/genre";
import {GenreService} from "../service/genre.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  genreList : Genre[];
  public formField = [];
  public formFieldsDto = null;
  public formFields = [];
  public processInstance = "";
  public enumValues = [];
  public isBeta = false;
   tasks = [];



  constructor(private registerService:RegisterService, private genreService:GenreService) { }

  ngOnInit(): void {
    this.registerService.startProcess().subscribe(
        data => {
            console.log(data);
            this.renderForm(data.taskId);
            console.log(this.enumValues);
    });
  }

  renderForm(processInstanceId){
    this.registerService.getFormData(processInstanceId).subscribe(
        res => {
          console.log(res);
          this.formFieldsDto = res;
          this.formFields = res.formFields;
          this.processInstance = res.processInstanceId;
          this.formFields.forEach( (field) => {
            if (field.type.name == 'enum') {
              this.enumValues = Object.keys(field.type.values);
            }
          });
        }
    );
  }

  onSubmit(value: any, f: any) {
    let o = new Array();
    for (var property in value){
      o.push({fieldId : property, fieldValue : value[property]});
    }
    console.log(o);
    console.log(this.formFieldsDto.taskId);
    this.registerService.registerReader(o,this.formFieldsDto.taskId).subscribe();
  }

  onRoleChange(value: any){
    if(value=="BetaReader")
      this.isBeta=true;
    if(value=="Reader")
      this.isBeta=false;
  }
}
