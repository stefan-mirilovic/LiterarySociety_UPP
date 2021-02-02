import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorsReviewComponent } from './editors-review.component';

describe('EditorsReviewComponent', () => {
  let component: EditorsReviewComponent;
  let fixture: ComponentFixture<EditorsReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorsReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorsReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
