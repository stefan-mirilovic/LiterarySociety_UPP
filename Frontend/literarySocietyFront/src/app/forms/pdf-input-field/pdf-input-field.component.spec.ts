import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfInputFieldComponent } from './pdf-input-field.component';

describe('PdfInputFieldComponent', () => {
  let component: PdfInputFieldComponent;
  let fixture: ComponentFixture<PdfInputFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PdfInputFieldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PdfInputFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
