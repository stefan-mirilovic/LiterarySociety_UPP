import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Form, FormGroup} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";

@Component({
  selector: 'app-radio-button-field',
  templateUrl: './radio-button-field.component.html',
  styleUrls: ['./radio-button-field.component.css']
})
export class RadioButtonFieldComponent implements OnInit {

  @Input() field: any;
  @Input() enumValues: any;
  @Input() form: FormGroup;
  @Output() isBeta = new EventEmitter<boolean>();
  public enumList: any;
  constructor(private validationService:ValidationService) { }

  ngOnInit(): void {
      this.enumList= this.enumValues[0];
      this.form.addControl(this.field.id,this.validationService.createFormGroup(this.field));
  }

  changeRadio(event) {
    if(this.form.value.role=='BetaReader'){
      this.isBeta.emit(true);
    }
    else {
      this.isBeta.emit(false);
    }

  }
}
