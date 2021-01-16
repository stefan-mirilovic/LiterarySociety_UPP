import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { TextInputFieldComponent } from './forms/text-input-field/text-input-field.component';
import { EnumSelectFieldComponent } from './forms/enum-select-field/enum-select-field.component';
import {ReactiveFormsModule} from "@angular/forms";
import { ReaderComponent } from './reader/reader.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    TextInputFieldComponent,
    EnumSelectFieldComponent,
    ReaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
