import {Component, Input, OnInit} from '@angular/core';
import {Form} from "@angular/forms";

@Component({
    selector: 'app-enum-select-field',
    templateUrl: './enum-select-field.component.html',
    styleUrls: ['./enum-select-field.component.css']
})
export class EnumSelectFieldComponent implements OnInit {

    @Input() field: any;
    @Input() enumValues: any;
    @Input() form: Form;
    constructor() {
    }

    ngOnInit(): void {
    }

}
