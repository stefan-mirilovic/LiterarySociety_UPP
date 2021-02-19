import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainEditorReviewComponent } from './main-editor-review.component';

describe('MainEditorReviewComponent', () => {
  let component: MainEditorReviewComponent;
  let fixture: ComponentFixture<MainEditorReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainEditorReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainEditorReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
