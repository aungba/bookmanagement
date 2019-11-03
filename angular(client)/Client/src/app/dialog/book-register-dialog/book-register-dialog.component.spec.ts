import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookRegisterDialogComponent } from './book-register-dialog.component';

describe('BookRegisterDialogComponent', () => {
  let component: BookRegisterDialogComponent;
  let fixture: ComponentFixture<BookRegisterDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookRegisterDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookRegisterDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
