import {Component, Input, OnInit, Output} from '@angular/core';
import {Form} from "@angular/forms";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";

@Component({
  selector: 'app-text-input-field',
  templateUrl: './text-input-field.component.html',
  styleUrls: ['./text-input-field.component.css']
})
export class TextInputFieldComponent implements OnInit {

  @Input() field: any;

  @Input()
  form: FormGroup;
  public fieldTextType: boolean;

  constructor(private validationService: ValidationService) {
  }

  ngOnInit(): void {
    this.form.addControl(this.field.id, this.validationService.createFormGroup(this.field));
  }

  get validateEmail() {
    return this.validationService.getValidateEmail(this.form, this.field);
  }

  get validateRequired() {
    return this.validationService.getValidateRequired(this.form, this.field);
  }

  get minRequired() {
    return this.validationService.getValidateMin(this.form, this.field);
  }

  get maxRequired() {
    return this.validationService.getValidateMax(this.form, this.field);
  }

  get password() {
    return this.validationService.getValidatePattern(this.form, this.field);
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }
}
