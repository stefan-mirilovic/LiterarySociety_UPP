import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {ReaderComponent} from "./reader/reader.component";
import {WriterComponent} from "./writer/writer.component";
import {BookComponent} from "./book/book.component";
import {TableComponent} from "./table/table.component";


const routes: Routes = [
  { path: 'readerRegistration', component: ReaderComponent},
  { path: 'writerRegistration', component: WriterComponent},
  { path: 'book', component: BookComponent},
  { path: 'table', component: TableComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
