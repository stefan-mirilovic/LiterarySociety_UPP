import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorsNotesComponent } from './editors-notes.component';

describe('EditorsNotesComponent', () => {
  let component: EditorsNotesComponent;
  let fixture: ComponentFixture<EditorsNotesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorsNotesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorsNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
