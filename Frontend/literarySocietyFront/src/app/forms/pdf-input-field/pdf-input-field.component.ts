import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-pdf-input-field',
  templateUrl: './pdf-input-field.component.html',
  styleUrls: ['./pdf-input-field.component.css']
})
export class PdfInputFieldComponent implements OnInit {
  @Input() field: any;
  @Input() form: FormGroup;
  constructor() { }

  ngOnInit(): void {
  }

}
