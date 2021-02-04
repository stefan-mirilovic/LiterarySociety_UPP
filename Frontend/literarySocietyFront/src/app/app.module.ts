import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
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
import { AuthInterceptorService } from './interceptor/auth-interceptor.service';
import { RegisterRedirectComponent } from './pages/register-redirect/register-redirect.component';
import { ReaderDashboardComponent } from './dashboard/reader-dashboard/reader-dashboard.component';
import { ReaderGuard } from './guard/reader.guard';
import { WriterDashboardComponent } from './dashboard/writer-dashboard/writer-dashboard.component';
import { WriterGuard } from './guard/writer.guard';
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';
import { AdminGuard } from './guard/admin.guard';

import { EditorsReviewComponent } from './editors-review/editors-review.component';

import { StoreComponent } from './pages/store/store.component';
import { BookDetailsComponent } from './pages/book-details/book-details.component';
import { MyBooksComponent } from './pages/my-books/my-books.component';
import { ComitteeDashboardComponent } from './dashboard/comittee-dashboard/comittee-dashboard.component';
import { ComitteeGuard } from './guard/comittee.guard';
import { EditorDashboardComponent } from './dashboard/editor-dashboard/editor-dashboard.component';
import { EditorGuard } from './guard/editor.guard';
import { MainEditorDashboardComponent } from './dashboard/main-editor-dashboard/main-editor-dashboard.component';
import { MainEditorGuard } from './guard/main-editor.guard';
import { ComitteeLeaderDashboardComponent } from './dashboard/comittee-leader-dashboard/comittee-leader-dashboard.component';
import { ComitteeLeaderGuard } from './guard/comittee-leader.guard';


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
    RegisterRedirectComponent,
    ReaderDashboardComponent,
    WriterDashboardComponent,
    AdminDashboardComponent,

    EditorsReviewComponent,

    StoreComponent,
    BookDetailsComponent,
    MyBooksComponent,
    ComitteeDashboardComponent,
    EditorDashboardComponent,
    MainEditorDashboardComponent,
    ComitteeLeaderDashboardComponent,

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
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    },
    ReaderGuard,
    WriterGuard,
    AdminGuard,
    ComitteeGuard,
    EditorGuard,
    MainEditorGuard,
    ComitteeLeaderGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas);
  }
}
