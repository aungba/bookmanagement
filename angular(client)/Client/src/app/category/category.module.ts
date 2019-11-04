import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
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
