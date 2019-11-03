import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatCardModule,
  MatTableModule,
  MatButtonModule,
  MatSortModule,
  MatPaginatorModule,
  MatRadioModule
 } from '@angular/material';
import { RouterModule } from '@angular/router';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [UserComponent, UserListComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatSortModule,
    MatPaginatorModule,
    RouterModule,
    MatRadioModule
  ]
})
export class UserModule { }
