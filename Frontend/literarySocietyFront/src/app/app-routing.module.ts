import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from "./register/register.component";
import { ReaderComponent } from "./reader/reader.component";
import { WriterComponent } from "./writer/writer.component";
import { BookComponent } from "./book/book.component";
import { TableComponent } from "./table/table.component";
import { WriterDocumentComponent } from "./writer-document/writer-document.component";
import { SynopsisComponent } from "./synopsis/synopsis.component";
import { LoginComponent } from './login/login.component';
import { RegisterRedirectComponent } from './pages/register-redirect/register-redirect.component';
import { ReaderDashboardComponent } from './dashboard/reader-dashboard/reader-dashboard.component';
import { ReaderGuard } from './guard/reader.guard';
import { WriterDashboardComponent } from './dashboard/writer-dashboard/writer-dashboard.component';
import { WriterGuard } from './guard/writer.guard';
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';
import { AdminGuard } from './guard/admin.guard';
import { StoreComponent } from './pages/store/store.component';
import { BookDetailsComponent } from './pages/book-details/book-details.component';
import { MyBooksComponent } from './pages/my-books/my-books.component';


const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'confirm/:id/:uuid',
    component: RegisterRedirectComponent
  },
  { path: 'readerRegistration', component: ReaderComponent },
  { path: 'writerRegistration', component: WriterComponent },
  { path: 'book', component: BookComponent },
  { path: 'table', component: TableComponent },
  { path: 'writer/document', component: WriterDocumentComponent },
  { path: 'book/publish', component: SynopsisComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'reader-dashboard',
    component: ReaderDashboardComponent,
    canActivate: [ReaderGuard],
    children: [
      {
        path: '',
        component: StoreComponent,
        pathMatch: 'full'
      },
      {
        path: 'store',
        component: StoreComponent
      },
      {
        path: 'book/:id',
        component: BookDetailsComponent
      },
      {
        path: 'my-library',
        component: MyBooksComponent
      },
    ]
  },
  {
    path: 'writer-dashboard',
    component: WriterDashboardComponent,
    canActivate: [WriterGuard],
    children: [
      {
        path: 'book/publish',
        component: SynopsisComponent
      },
    ]
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
