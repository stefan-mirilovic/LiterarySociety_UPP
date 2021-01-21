import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";

@Component({
  selector: 'app-text-area-field',
  templateUrl: './text-area-field.component.html',
  styleUrls: ['./text-area-field.component.css']
})
export class TextAreaFieldComponent implements OnInit {

  @Input() field: any;

  @Input()
  form: FormGroup;

  constructor(private validationService: ValidationService) {
  }

  ngOnInit(): void {
    this.form.addControl(this.field.id, this.validationService.createFormGroup(this.field));
  }

}
