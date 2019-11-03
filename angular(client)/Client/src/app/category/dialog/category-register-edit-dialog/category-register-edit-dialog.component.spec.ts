import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryRegisterEditDialogComponent } from './category-register-edit-dialog.component';

describe('CategoryRegisterEditDialogComponent', () => {
  let component: CategoryRegisterEditDialogComponent;
  let fixture: ComponentFixture<CategoryRegisterEditDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoryRegisterEditDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryRegisterEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
