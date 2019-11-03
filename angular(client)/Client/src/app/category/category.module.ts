import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule,
  MatTableModule,
  MatPaginatorModule,
  MatDialogModule,
  MatCardModule,
  MatSortModule,
  MatInputModule,
  MatSlideToggleModule,
  MatOptionModule,
  MatSelectModule
} from '@angular/material';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CategoryRoutingModule } from './category-routing.module';
import { CategoryComponent } from './category.component';
import { CategoryRegisterEditDialogComponent } from './dialog/category-register-edit-dialog/category-register-edit-dialog.component';

@NgModule({
  declarations: [CategoryComponent, CategoryRegisterEditDialogComponent],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule,
    MatCardModule,
    MatSortModule,
    MatInputModule,
    MatSlideToggleModule,
    MatOptionModule,
    MatSelectModule,
    AngularFontAwesomeModule,
    FormsModule,
    ReactiveFormsModule
  ],
  entryComponents: [CategoryRegisterEditDialogComponent]
})
export class CategoryModule { }
