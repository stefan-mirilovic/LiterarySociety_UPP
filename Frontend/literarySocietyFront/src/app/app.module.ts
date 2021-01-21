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
import { WriterComponent } from './writer/writer.component';
import { BookComponent } from './book/book.component';
import { TableComponent } from './table/table.component';
import { TableFieldComponent } from './forms/table-field/table-field.component';
import { PdfInputFieldComponent } from './forms/pdf-input-field/pdf-input-field.component';
import { RadioButtonFieldComponent } from './forms/radio-button-field/radio-button-field.component';
import { WriterDocumentComponent } from './writer-document/writer-document.component';
import { SynopsisComponent } from './synopsis/synopsis.component';
import { TextAreaFieldComponent } from './forms/text-area-field/text-area-field.component';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from './material-module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    TextInputFieldComponent,
    EnumSelectFieldComponent,
    ReaderComponent,
    WriterComponent,
    BookComponent,
    TableComponent,
    TableFieldComponent,
    PdfInputFieldComponent,
    RadioButtonFieldComponent,
    WriterDocumentComponent,
    SynopsisComponent,
    TextAreaFieldComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    ToastrModule.forRoot(),
    FontAwesomeModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas);
  }
}
