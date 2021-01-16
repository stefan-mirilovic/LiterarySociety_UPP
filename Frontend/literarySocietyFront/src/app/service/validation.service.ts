import { Injectable } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  createFormGroup(fieldId: any) {
    const group: any = {};
    let validators = [];
    validators.push(Validators.required);
    validators.push(Validators.minLength(fieldId.constraints.minlength));
    validators.push(Validators.maxLength(fieldId.constraints.maxlength));
    if(fieldId.id === "email"){
      validators.push(Validators.email)
    }
    if(fieldId.id ==="password"){
      validators.push(Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}'));
    }
    if(fieldId.constraint ==="notnumber"){
      validators.push(Validators.pattern("^[0-9]*$"));
    }
    return validators;
  }

  getValidateEmail(form, field){
    if(form.controls[field.id].hasError('email')){
      return true;
    }}
  getValidateRequired(form, field){
    if(form.controls[field.id].hasError('required')){
      return true;
    }
  }
  getValidateMin(form,field){
    if(form.controls[field.id].hasError('minLength')){
      return true;
    }
  }

  getValidateMax(form,field){
    if(form.controls[field.id].hasError('maxLength')){
      return true;
    }
  }

  getValidatePattern(form,field){
    if(form.controls[field.id].hasError('pattern')){
      return true;
    }
  }

}
