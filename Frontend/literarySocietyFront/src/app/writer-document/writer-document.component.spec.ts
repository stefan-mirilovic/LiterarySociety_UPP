import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WriterDocumentComponent } from './writer-document.component';

describe('WriterDocumentComponent', () => {
  let component: WriterDocumentComponent;
  let fixture: ComponentFixture<WriterDocumentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WriterDocumentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WriterDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
