import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
  @Input()
  filesList;

  constructor(private validationService:ValidationService) { }

  ngOnInit(): void {
    this.form.addControl(this.field.id,this.validationService.createFormGroup(this.field));
  }

  textFileAdded(event) {
    const file = event.target.files[0];
    this.path=file.name;
    const reader = new FileReader();
    reader.addEventListener('load', (event: any) => {
      this.filesList.set(this.field.id,event.target.result);
    });
    reader.readAsDataURL(file);
  }
}
