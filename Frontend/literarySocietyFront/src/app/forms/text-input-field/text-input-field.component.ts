import {Component, Input, OnInit} from '@angular/core';
import {Form} from "@angular/forms";

@Component({
  selector: 'app-text-input-field',
  templateUrl: './text-input-field.component.html',
  styleUrls: ['./text-input-field.component.css']
})
export class TextInputFieldComponent implements OnInit {

  @Input() field: any;
  @Input() form: Form;
  constructor() { }

  ngOnInit(): void {
  }

}
