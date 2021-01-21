import {Component, NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {ReaderComponent} from "./reader/reader.component";
import {WriterComponent} from "./writer/writer.component";
import {BookComponent} from "./book/book.component";
import {TableComponent} from "./table/table.component";
import {WriterDocumentComponent} from "./writer-document/writer-document.component";
import {SynopsisComponent} from "./synopsis/synopsis.component";


const routes: Routes = [
  {path: 'readerRegistration', component: ReaderComponent},
  {path: 'writerRegistration', component: WriterComponent},
  {path: 'book', component: BookComponent},
  {path: 'table', component: TableComponent},
  {path: 'writer/document', component: WriterDocumentComponent},
  {path: 'book/publish', component: SynopsisComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
