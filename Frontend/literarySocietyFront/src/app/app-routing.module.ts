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
import {EditorsReviewComponent} from "./editors-review/editors-review.component";
import { StoreComponent } from './pages/store/store.component';
import { BookDetailsComponent } from './pages/book-details/book-details.component';
import { MyBooksComponent } from './pages/my-books/my-books.component';
import {ReclamationComponent} from "./reclamation/reclamation.component";
import {EditorsFindComponent} from "./editors-find/editors-find.component";
import { ComitteeDashboardComponent } from './dashboard/comittee-dashboard/comittee-dashboard.component';
import { ComitteeGuard } from './guard/comittee.guard';
import { EditorDashboardComponent } from './dashboard/editor-dashboard/editor-dashboard.component';
import { EditorGuard } from './guard/editor.guard';
import { MainEditorDashboardComponent } from './dashboard/main-editor-dashboard/main-editor-dashboard.component';
import { MainEditorGuard } from './guard/main-editor.guard';
import { ComitteeLeaderDashboardComponent } from './dashboard/comittee-leader-dashboard/comittee-leader-dashboard.component';
import { ComitteeLeaderGuard } from './guard/comittee-leader.guard';
import {EditorsNotesComponent} from "./editors-notes/editors-notes.component";
import {CommitteeDecisionComponent} from "./committee-decision/committee-decision.component";



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

  { path: 'readerRegistration', component: ReaderComponent},
  { path: 'writerRegistration', component: WriterComponent},
  { path: 'book', component: BookComponent},
  { path: 'table', component: TableComponent},
  { path: 'writer/document', component: WriterDocumentComponent},
  { path: 'book/publish', component: SynopsisComponent},
  { path: 'login', component: LoginComponent},
  { path: 'editor/review', component: EditorsReviewComponent},
  { path: 'find/editors', component: EditorsFindComponent},

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
      {
        path: 'reclamation',
        component: ReclamationComponent
      }
    ]
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard]
  },
  {
    path: 'committee-dashboard',
    component: ComitteeDashboardComponent,
    canActivate: [ComitteeGuard],
    children: [
      { path: 'committee/decision', component: CommitteeDecisionComponent},
    ]
  },
  {
    path: 'editor-dashboard',
    component: EditorDashboardComponent,
    canActivate: [EditorGuard],
    children: [
      { path: 'editor/notes', component: EditorsNotesComponent},
    ]
  },
  {
    path: 'main-editor-dashboard',
    component: MainEditorDashboardComponent,
    canActivate: [MainEditorGuard],
    children: [
      { path: 'find/editors', component: EditorsFindComponent},
    ]
  },
  {
    path: 'committee-leader-dashboard',
    component: ComitteeLeaderDashboardComponent,
    canActivate: [ComitteeLeaderGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
