import {Component, Input, OnInit} from '@angular/core';
import {RegisterService} from "../service/register.service";
import {Genre} from "../model/genre";
import {GenreService} from "../service/genre.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ValidationService} from "../service/validation.service";
import {isLineBreak} from "codelyzer/angular/sourceMappingVisitor";
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    public formFieldsDto = null;
    public formFields = [];
    public enumValues = [];
    public load: boolean;
    public isBeta = false;
    public fileMap = new Map<string, string>();
    @Input() taskId: string;
    @Input() formName: string;
    public currentFile;
    @Input() redirectLink: string;
    @Input() scsMsg: string;
    @Input() errMsg: string;
    @Input() tableRows: [];
    @Input() tableName: string;


    form = this.fb.group({});

    constructor(private registerService: RegisterService, private genreService: GenreService, private validationService: ValidationService, private fb: FormBuilder,
                private router: Router,
                private toastr: ToastrService) {
    }

    ngOnInit(): void {
        console.log(this.tableRows);
        this.load = true;
        this.registerService.getFormData(this.taskId).subscribe(
            res => {
                this.renderForm(this.taskId);
            }
        )

    }

    renderForm(taskId) {
        this.registerService.getFormData(taskId).subscribe(
            res => {
                console.log(res);
                this.formFieldsDto = res;
                this.formFields = res.formFields;
                this.taskId = res.processInstanceId;
                this.formFields.forEach((field) => {
                    if (field.type.name == 'enum' && field.input == 'radio') {
                        this.enumValues.push(Object.keys(field.type.values));
                    }
                });
                this.load = false;
            }
        );
    }

    onSubmit() {
        let o = new Array();
        for (var property in this.form.value) {

            if (this.fileMap.size > 0) {
                o.push({fieldId: property, fieldValue: this.convertToBase64(property), isFile: "yes"});
            } else {
                if (property == 'genre' && this.form.value[property] instanceof Array) {
                    o.push({fieldId: property, fieldValue: this.transform(this.form.value[property])});
                    console.log(this.transform(this.form.value[property]));
                }
                else if(property == 'editors' && this.form.value[property] instanceof Array){
                    o.push({fieldId: property, fieldValue: this.transform(this.form.value[property])});
                }
                else if(property == 'betaReaders' && this.form.value[property] instanceof Array){
                  o.push({fieldId: property, fieldValue: this.transform(this.form.value[property])});
                }
                else {

                    o.push({fieldId: property, fieldValue: this.form.value[property]});
                }
            }

        }
        console.log(o);
        this.registerService.registerReader(o, this.formFieldsDto.taskId).subscribe({
            next: () => {
                if (this.redirectLink) {
                    this.router.navigate([this.redirectLink]);
                }
                if (this.scsMsg) {
                    this.toastr.success(this.scsMsg)
                } else {
                    this.toastr.success("Successfully completed!")
                }
            },
            error: () => {
                if (this.errMsg) {
                    this.toastr.error(this.errMsg)
                } else {
                    this.toastr.error("An error occured!")
                }
            }
        });
    }

    public transform(input: Array<any>, sep = ','): string {
        return input.join(sep);
    }

    setBeta(event) {
        console.log(event);
        this.isBeta = event;
    }

    convertToBase64(property) {
        return this.fileMap.get(property);
    }
}
