import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorsFindComponent } from './editors-find.component';

describe('EditorsFindComponent', () => {
  let component: EditorsFindComponent;
  let fixture: ComponentFixture<EditorsFindComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorsFindComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorsFindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
