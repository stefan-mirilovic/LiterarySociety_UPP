import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";

@Component({
  selector: 'app-pdf-input-field',
  templateUrl: './pdf-input-field.component.html',
  styleUrls: ['./pdf-input-field.component.css']
})
export class PdfInputFieldComponent implements OnInit {
  @Input() field: any;
  @Input() form: FormGroup;
  path:string="Chose";
  constructor(private validationService:ValidationService) { }

  ngOnInit(): void {
    this.form.addControl(this.field.id,this.validationService.createFormGroup(this.field));
  }

  textFileAdded(event) {
    const file: File = event.target.files[0];
    this.path = file.name;
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload=()=>{
      this.form.value[this.field.id]=reader.result;
    };
    console.log(this.form);


  }
}
