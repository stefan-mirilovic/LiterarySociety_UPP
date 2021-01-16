import {Component, Input, OnInit} from '@angular/core';
import {RegisterService} from "../service/register.service";
import {Genre} from "../model/genre";
import {GenreService} from "../service/genre.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ValidationService} from "../service/validation.service";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    public formFieldsDto = null;
    public formFields = [];
    public enumValues = [];
    public isBeta = false;
    @Input() taskId:string;
    @Input() formName:string;

    form=this.fb.group({

    });

    constructor(private registerService:RegisterService, private genreService:GenreService, private validationService:ValidationService, private fb: FormBuilder) { }

    ngOnInit(): void {
        this.registerService.getFormData(this.taskId).subscribe(
            res=>{
                this.renderForm(this.taskId);
            }
        )

    }

    renderForm(taskId){
        this.registerService.getFormData(taskId).subscribe(
            res => {
                console.log(res);
                this.formFieldsDto = res;
                this.formFields = res.formFields;
                this.taskId = res.processInstanceId;
                this.formFields.forEach( (field) => {
                    if (field.type.name == 'enum') {
                        this.enumValues = Object.keys(field.type.values);
                    }
                });
            }
        );
    }

    onSubmit() {
        let o = new Array();
        for(var property in this.form.value){
            o.push({fieldId : property, fieldValue : this.form.value[property]});
        }
        console.log(o);
        this.registerService.registerReader(o,this.formFieldsDto.taskId).subscribe();
    }

    onRoleChange(value: any){
        if(value=="BetaReader")
            this.isBeta=true;
        if(value=="Reader")
            this.isBeta=false;
    }
}
