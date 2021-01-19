import {Component, Input, OnInit} from '@angular/core';
import {Form, FormControl, FormGroup, Validators} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";

@Component({
    selector: 'app-enum-select-field',
    templateUrl: './enum-select-field.component.html',
    styleUrls: ['./enum-select-field.component.css']
})
export class EnumSelectFieldComponent implements OnInit {

    @Input() field: any;
    @Input() enumValues: any;
    @Input() form: FormGroup;
    @Input() isBeta:boolean;

    constructor(private validationService:ValidationService) {
    }

    ngOnInit(): void {
        this.form.addControl(this.field.id,this.validationService.createFormGroup(this.field));
    }

    check(){
        var ret=false;
        this.form.controls[this.field.id].disable();
        if(this.isBeta){
            this.form.controls[this.field.id].enable();
            ret = this.isBeta;
        }
        return ret;
    }

}
