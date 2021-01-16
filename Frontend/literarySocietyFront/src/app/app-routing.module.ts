import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {ReaderComponent} from "./reader/reader.component";
import {WriterComponent} from "./writer/writer.component";


const routes: Routes = [
  { path: 'readerRegistration', component: ReaderComponent},
  { path: 'writerRegistration', component: WriterComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
