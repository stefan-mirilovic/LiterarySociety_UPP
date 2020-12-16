import { Component, OnInit } from '@angular/core';
import {RegisterService} from "../service/register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public formField = [];
  public formFieldsDto = null;
  public genres = [];
  public formFields = [];
  public processInstance = "";
  public enumValues = [];
   tasks = [];



  constructor(private registerService:RegisterService) { }

  ngOnInit(): void {
    this.registerService.getFormData().subscribe(
      res => {
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
    this.registerService.registerReader(o).subscribe();
  }
}
